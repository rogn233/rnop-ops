var myModalContent;
var myModalLabel;
var myModal;
var myTable;
var myForm;

function showModal(title, url, data) {
    myModalLabel.html(title);
    myModalContent.load(url, data);
    $("#common-form-submit").one("click", function (e) {
        var myForm = $("#common-form");
        $.ajax({
            type: 'post',
            url: myForm.attr("action"),
            data: myForm.serialize(),
            success: function (data) {
                if (data.success) {
                    myModal.modal('hide');
                    myTable.bootstrapTable('refresh');
                }
                swal(data.desc);
            },
            error: function () {
                swal("处理异常");
            }
        });
    });
    myModal.modal('show');
}

$(document).ready(function () {
    myModalContent = $("#myModalContent");
    myModalLabel = $("#myModalLabel");
    myModal = $("#myModal");
    myTable = $("#table");
    myForm = $("#common-form");

    $("#about").off().on("click",function () {
        swal({
            title: "关于",
            html: true,
            confirmButtonColor: "#0ac5d4",
            confirmButtonText: "确定",
            text: "<p style='text-align: left;font-size: 14px;'>\n" +
            "        系统名称：RNOP运维系统<br>\n" +
            "        使用技术：springboot+mybatis+thymeleaf+bootstrap+jquery<br>\n" +
            "        作者：朱容赋\n" +
            "    </p>"
        });
    });
    $("#set-server-connect").off().on("click",function (e) {
        buildTable(myTable, "setConnect", "/connect/getAll");
        bindAdd("新増服务器连接方式", "setConnect");
        bindEdit("修改服务器连接方式", "setConnect");
        bindDel("/connect/del");
    });
    $("#set-map-dic").off().on("click",function () {
        buildTableMap(myTable);
        bindAdd("新増字典数据", "setDicMap");
        bindEdit("修改字典数据", "setDicMap");
        bindDel("/dic/del");
        // buildTable($("#table"),15,36);
    });
    $("#set-db-connect").off().on("click",function (e) {
        buildTable(myTable, "setDbConnect", "/db_connect/getAll");
        bindAdd("新増数据库连接方式", "setDbConnect");
        bindEdit("修改数据库连接方式", "setDbConnect");
        bindDel("/db_connect/del");
    });
    $("#set-mq-connect").off().on("click",function (e) {
        buildTable(myTable, "setMqConnect", "/mq_connect/getAll");
        bindAdd("新増MQ连接方式", "setMqConnect");
        bindEdit("修改MQ连接方式", "setMqConnect");
        bindDel("/mq_connect/del");
    });
    $("#set-cron-job").off().on("click",function (e) {
        buildTable(myTable, "setQuartz", "/cron/queryAll");
        bindAdd("新増定时任务", "setQuartz");
        bindEdit("修改定时任务", "setQuartz");
        bindDel("/cron/del");
        bindAlert($("#btn_pause"),"挂起","/cron/pause")
        bindAlert($("#btn_resume"),"激活","/cron/resume")
    });
    $("#data-monitor").off().on("click",function (e) {
        buildTable(myTable, "dataMonitor", "/data_monitor/pageQuery");
        $("#toolbar").hide();
    });

});

function bindAdd(title, url) {
    $("#btn_add").off().on("click",function () {
        showModal(title, url);
    });
}

function bindDel(url) {
    $("#btn_delete").off().on("click",function () {
        var selections = myTable.bootstrapTable('getSelections');
        if (selections.length == 0) {
            swal({
                title: "操作有误!",
                text: "请先选择要删除的记录!",
                type: "error",
                confirmButtonText: "确定",
                confirmButtonColor: "#0ac5d4"
            });
            // alert("请先选择要删除的记录!");
            return;
        }
        swal({
                title: "确定删除吗？",
                text: "你将无法恢复选中的数据",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确定删除！",
                closeOnConfirm: false
            },
            function () {
                $.ajax({
                    url: url,
                    type: "post",
                    dataType: "json",
                    data: {"dicMaps": JSON.stringify(selections)},
                    success: function (data) {
                        swal("删除成功", data.desc, "success");
                        myTable.bootstrapTable('refresh');
                    },
                    error: function () {
                        swal("删除失败", "服务器内部错误。", "error");
                    }
                });
            });
    });
}

function bindAlert(element,title,url) {
    element.off().on("click",function () {
        var selections = myTable.bootstrapTable('getSelections');
        if (selections.length == 0) {
            swal({
                title: "操作有误!",
                text: "请先选择要"+title+"的记录!",
                type: "error",
                confirmButtonText: "确定",
                confirmButtonColor: "#0ac5d4"
            });
            // alert("请先选择要删除的记录!");
            return;
        }
        swal({
                title: "确定"+title+"吗？",
                text: "请谨慎操作",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确定"+title+"！",
                closeOnConfirm: false
            },
            function () {
                $.ajax({
                    url: url,
                    type: "post",
                    dataType: "json",
                    data: {"dicMaps": JSON.stringify(selections)},
                    success: function (data) {
                        if(data.success){
                            swal(title+"成功", data.desc, "success");
                        }else {
                            swal(title+"失败", data.desc, "error");
                        }
                        myTable.bootstrapTable('refresh');
                    },
                    error: function () {
                        swal(title+"失败", "服务器内部错误。", "error");
                    }
                });
            });
    });
    element.show();
}



function bindEdit(title, url) {
    $("#btn_edit").off().on("click",function () {
        var selections = myTable.bootstrapTable('getSelections');
        if (selections.length != 1) {
            swal({
                title: "操作有误!",
                text: "请选择一条要编辑的记录!",
                type: "error",
                confirmButtonText: "确定",
                confirmButtonColor: "#0ac5d4"
            });
            return;
        }
        console.log(selections[0]);
        showModal(title, url, selections[0]);
    });
}

function buildTable($el, tab_name, url) {
    var columns = getColumns(tab_name);
    $el.bootstrapTable('destroy').bootstrapTable({
        columns: columns,
        search: true,
        url: url,
        type: "post",
        contentType : "application/x-www-form-urlencoded",
        cardview:true,
        sidePagination: "server",
        queryParams : function (params) {
            //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            var temp = {
                limit: params.limit,                         //页面大小
                page: params.offset,   //页码
                sort: params.sort,      //排序列名
                sortOrder: params.order, //排位命令（desc，asc）
                search:params.searchText
            };
            return temp;
        }
    });
    $("#btn_pause").hide();
    $("#btn_resume").hide();
    $("#toolbar").show();
}

function getColumns(tab_name) {
    var myColumns = [];
    // 加载动态表格
    $.ajax({
        url: "/dic/queryByTab",
        type: 'post',
        dataType: "json",
        data: {"tab_name": tab_name},
        async: false,
        success: function (returnValue) {
            myColumns.push({
                checkbox: true
            });
            $.each(returnValue, function (i, item) {
                myColumns.push({
                    "field": item.col_name,
                    "title": item.zh_name,
                    sortable: true
                });
            });
            console.log(myColumns);
        }
    });
    return myColumns;
}


function buildTableMap($el) {
    $el.bootstrapTable('destroy').bootstrapTable({
        columns: [{
            "field": "id",
            visible: false
        }, {
            checkbox: true
        }, {
            "field": "tab_name",
            "title": "表格名称"
        }
            , {
                "field": "col_name",
                "title": "数据库列名"
            }
            , {
                "field": "zh_name",
                "title": "中文名称"
            }
            , {
                "field": "type",
                "title": "表单类型"
            }
            , {
                "field": "insert_time",
                "title": "插入时间"
            }
        ],
        search: true,
        url: '/dic/queryAll',
        method: 'get'
    });
    $("#btn_pause").hide();
    $("#btn_resume").hide();
    $("#toolbar").show();
}