var url = 'getTestList.html';
var thead = new Array('id', 'title', 'totalCount', 'replyCount', 'type', 'addDate');
var title = new Array('id', '标题', '总题数', '答题人数', '类型', '发布时间');
var infoHref = "testInfo.html";//新增修改跳转页面
var removeUrl = 'removeTest.html';
var titleUrl = 'titleList.html';
var replyUrl = 'replyList.html';
var updateUrl = 'updateTitle.html';
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
    /*$("#addBtn").bind('click',function(){
        window.location.href=infoHref+"?type=add";
    });*/
    $("#finBtn").click(function () {
        getContentList(url);
    });

    $("#importBtn").bind('click', showImportModle);


    $("#addBtn").click(function(){
        window.location.href='./toTestInfo.html?type='+$("#addBtn").attr("t");
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
        msg: '确认删除?', onOk: function () {

            $.ajax({
                type: 'post',
                url: removeUrl,
                data: {ids: ids},
                success: function (data) {
                    if (data.flag) {

                        getContentList(url);

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
    dataSource.funcsTitle = ['修改','查看详情', '答题情况', '删除'];
    dataSource.funcs = [function (data) {
        window.location.href = updateUrl + "?id=" + data.id+"&&type=" + data.type;
          /*  + "&&totalCount=" + data.totalCount + "&&replyCount=" + data.replyCount
            + "&&remark=" + encodeURI(data.remark)+ "&&resultAnalyse=" + encodeURI(data.resultAnalyse)+ "&&type=" + data.type;*/

    },function (data) {
        window.location.href = titleUrl + "?id=" + data.id+"&&type=" + data.type;
         /*   + "&&totalCount=" + data.totalCount + "&&replyCount=" + data.replyCount
            + "&&remark=" + data.remark+ "&&resultAnalyse=" + data.resultAnalyse+ "&&type=" + data.type;*/
    },
        function (data) {
            window.location.href = replyUrl + "?id=" + data.id +"&&type=" + data.type;
            /*    + "&&totalCount=" + data.totalCount + "&&replyCount=" + data.replyCount
                + "&&remark=" + data.remark+ "&&resultAnalyse=" + data.resultAnalyse+ "&&type=" + data.type;*/
        },
        function (data) {
            removeContent(data.id);
        }];
    console.log(dataSource);
    table = myTable(dataSource);

    table.createTable("table_model", title, thead, "操作", {
        type: function (value) {
            if (value.type == 1) {
                return '心理测试';
            }
            return '网上考试';
        }
    });
    table.createPageHelper("page_moodel");
    table.addTableCheckbox();
}

function showImportModle() {
    $("#uploadExcel").modal("show");
    $("#hide1").hide();
    //import_team
    $("#import_team").unbind("click");
    $("#import_team").one("click", submitModel);
}

function submitModel() {
    $("#uploadExcel").ajaxSubmit({
        type: "POST",
        url: "uploadWord.html",
        dataType: "json",
        success: function (data) {
            alert("成功");
        }
    });
}

function importExcel(event){
    event.preventDefault();
    var excelFileVal = $("#excelFile").val();
    if(excelFileVal!='' && excelFileVal!=undefined && excelFileVal.lastIndexOf(".doc")>0){
        $("#uploadExcel").submit()
    }else{
        Alert("请按照word模板导入文件");
    }
}
