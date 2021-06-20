var url = 'getContentList.html';
var thead = new Array('id', 'title', 'remark', 'type', 'addDate');
var title = new Array('文章id', '文章标题', '摘要', '类型', '发布时间');
var infoHref = "contentInfo.html";//新增修改跳转页面
var removeUrl = 'removeContent.html';
var dataSource;
var table;//表格

$(function () {
    initEvent();
    getContentList(url);
});

function initEvent() {

    $("#removeBtn").click(function () {
        var ids = table.getCheck('id');
        if (ids == undefined || ids == '') {
            return;
        }
        removeContent(ids.join(','));
    });
    $("#addBtn").bind('click', function () {
        window.location.href = infoHref + "?type=add";
    });
    $("#finBtn").click(function () {
        getContentList(url);
    });

}

function getContentList(_url) {
    var dataUrl = _url ? _url : url;
    var data = {
        //addName:$("#addName").val(),
        //title:$("#title").val(),
        //classifyId:$("#section").val(),
        //startDate:$("time1").val(),
        //endDate:$("#time2").val()
        type: $("#type").val()
    };
    $.ajax({
        type: 'post',
        url: dataUrl,
        data: data,
        success: function (data) {
            init(data);
        },
        error: function (err) {

        }
    });
};

function removeContent(ids) {
    messageWin.confirm({
        msg: "确认删除吗？", onOk: function () {
            $.ajax({
                type: 'post',
                url: removeUrl,
                async: false,
                data: {ids: ids},
                success: function (data) {
                    if (data.flag) {

                        getContentList(url);


                        /*Alert({
                            msg:data.msg,
                            onOk:function(){
                                getBbsList(url);
                            }
                        });*/
                    } else {
                        alert("删除失败！");
                    }
                },
                error: function () {

                }
            });
        }
    })

}

function init(json) {
    dataSource = json;

    dataSource.reffunc = getContentList;
    dataSource.funcsTitle = ['修改', '删除'];
    dataSource.funcs = [function (data) {
        window.location.href = infoHref + "?id=" + data.id + "&&type=upd";
    },
        function (data) {
            removeContent(data.id);

        }];
    console.log(dataSource);
    table = myTable(dataSource);

    table.createTable("table_model", title, thead, "操作", {
        type: function (value) {
            if (value.type == 1) {
                return '规章制度';
            }
            return '学习教育';
        }
    });
    table.createPageHelper("page_moodel");
    table.addTableCheckbox();
}


