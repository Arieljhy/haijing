var url='getBbsList.html';
var thead = new Array('id','addDate','classifyName','addName','title','clickCount','replyCount');
var title = new Array('编号','发布时间','板块名称','发布者','标题','浏览数','回复数');
var infoHref="bbsInfo.html";//新增修改跳转页面
var bbsReply="bbsReply.html";
var	getClassifyIdUrl="getClassify.html";
var removeUrl='removeBbs.html';
var dataSource;
var table;//表格
//获取板块列表
function getClassifyId(){
	$.ajax({
		url:getClassifyIdUrl,
		data:{},
		type:'post',
		success:function(data){
			$.each(data,function(n,value){
				$("#section").append('<option value="'+value.id+'">'+value.name+'</option>');
			});
		},
		error:function(){
		}
	});
}
$(function(){
	initDateTimePicker();
	initEvent();
	getBbsList(url);	
});


// function refresh(page){
// 	var url=page?(listUrl+'&page='+page):listUrl;
// 	var data={
// 		name:$("#name").val(),
// 		remark:$("#remark").val(),
// 		state:$("#state").val()
// 	};
// 	$.ajax({
// 		type:'post',
// 		url:url,
// 		data:data,
// 		success:function(data){
// 			drawMyTable(data);
// 		},
// 		error:function(err){
//
// 		}
// 	});
// };

function initEvent(){
	
	getClassifyId();
	//$('select').selectpicker();
	$("#removeBtn").click(function(){
		var ids=table.getCheck('id');
		if(ids==undefined||ids==''){
			return;
		}
		removeBbs(ids.join(','));

	});
	$("#finBtn").click(function(){
		getBbsList(url);	
	});
	
}

function getBbsList(_url){
	var dataUrl=_url?_url:url;
	var data={
		addName:$("#addName").val(),
		title:$("#title").val(),
		classifyId:$("#section").val(),
		startDate:$("#time1").val(),
		endDate:$("#time2").val()
	};
	$.ajax({
		type:'post',
		url:dataUrl,
		data:data,
		success:function(data){
			init(data);
		},
		error:function(err){
			
		}
	});
};

function removeBbs(ids){
	messageWin.confirm("提示", "确定删除帖子吗？", function(){
		$.ajax({
			type:'post',
			url:removeUrl,
			data:{ids:ids},
			success:function(data){
				if (data.flag) {
					messageWin.alert("提示", data.msg);
					getBbsList(url);
				} else {
					messageWin.alert("提示", data.msg);
					return;
				}
			},
			error:function(){

			}
		});
	});

}

function init(json) {
	dataSource = json;
	dataSource.reffunc=getBbsList;
	dataSource.funcsTitle=['详情','修改','删除'];
	dataSource.funcs=[function(data){
		window.location.href=bbsReply+"?id="+data.id+"&title="+data.title+"&clickCount="+data.clickCount+"&replyCount="+data.replyCount+"&addPerson="+data.addPerson;
	},function(data){
		window.location.href=infoHref+"?id="+data.id;
	},
	function(data){
		removeBbs(data.id);
	}];
	console.log(dataSource);
	table=myTable(dataSource);
	
	table.createTable("table_model", title, thead, "操作", {
	});
	table.createPageHelper("page_moodel");
	table.addTableCheckbox();
}

function initDateTimePicker(){
	$('#time1').datetimepicker({
		format:"YYYY-MM-DD"
	});
	$('#time2').datetimepicker({
		format:"YYYY-MM-DD"
	});

}
// window.addEventListener('pageshow', function(event) {
// // 	if(event.persisted) { // ios 有效, android 和 pc 每次都是 false
// // 		location.reload();
// // 	} else { // ios 除外
// // 		if(sessionStorage.getItem('refresh') === 'true') {
// // 			location.reload();
// // 		}
// // 	}
// // 	sessionStorage.removeItem('refresh');
// // });