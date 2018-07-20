package com.boco.quartz.job;

import com.boco.entity.DalDataDetail;
import com.boco.entity.LinuxCmdEntity;
import com.boco.mybatis.service.DalDataDetailService;
import com.boco.util.SSHConnectUtil;
import org.apache.commons.io.IOUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.StringReader;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 收集data_gndp下的数据特点，做为经验值，每天统计前一天
 */
public class CounterDataGndp implements Job {
    private Logger log = LoggerFactory.getLogger(CounterDataGndp.class);

    @Resource
    private DalDataDetailService service;

    @Override
    @Transactional
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        scan_gndp_data();
    }

    private void scan_gndp_data() {
        log.info("开始扫描data_gndp数据完整性");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String preTime = sdf.format(new Date().getTime()-2 * 60 * 60 * 1000L).substring(0,10);
        String[] cmds = {
                "find /opt/npmuser/db_sum/data_gndp/*"+preTime+"/*/*csv.gz"//前一天目录数
//                "find /opt/npmuser/db_sum/data_gndp/tpd_eutrancell_0_q_"+preTime+"/*/*csv.gz"//前一天目录数
        };
        //1.统计前一天所有时间点的目录数，以出现次数最多的做为经验值
        SSHConnectUtil sshConnectUtil = new SSHConnectUtil("性能汇总服务器","10.174.243.76",22,"npmuser","P!nios04",cmds);
        String result = sshConnectUtil.exec().get(0).getReport();
        try {
            List<String> fileList = IOUtils.readLines(new StringReader(result));
            String[] subCmds = new String[fileList.size()];
            List<DalDataDetail> parseFileList = new ArrayList<>();
            for (int i = 0; i < fileList.size(); i++) {
                String fileStr = fileList.get(i);
                subCmds[i] = "zcat "+fileList.get(i)+"|wc -l";
                String[] strs = fileStr.split("/");
                String ds_time = strs[strs.length-2];
                String table_str = strs[strs.length-1];
                DalDataDetail dalDataDetail = new DalDataDetail();
                dalDataDetail.setSource("10.174.243.76_data_gndp");
                dalDataDetail.setDatasourcename(ds_time.substring(0,ds_time.lastIndexOf("_")));
                dalDataDetail.setCmd(subCmds[i]);
                dalDataDetail.setTablename(table_str.split("\\.")[0]);
                dalDataDetail.setStart_time(new Timestamp(sdf.parse(ds_time.substring(ds_time.lastIndexOf("_")+1,ds_time.length())).getTime()));
                parseFileList.add(dalDataDetail);
            }
            sshConnectUtil.setCmds(subCmds);
            List<LinuxCmdEntity> execResult = sshConnectUtil.exec();
                for (LinuxCmdEntity cmdEntity : execResult) {
                    for (DalDataDetail dalDataDetail : parseFileList) {
                        if(dalDataDetail.getCmd().equals(cmdEntity.getCmd())){
                            dalDataDetail.setNum(Long.parseLong(cmdEntity.getReport().trim()));
                            break;
                        }
                    }
                }
            log.info("插入data_gndp完整性数据["+service.insertBatch(parseFileList)+"]条");;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws JobExecutionException {
        new CounterDataGndp().execute(null);
}    }

