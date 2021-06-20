var url="getCommentsClassify.html"
var getClassifyIdUrl="getClassify.html"
	var commentsReply="commentsReply.html"
var thead = new Array('id','addDate','classifyName','addName','title',"type");
var title = new Array('编号','发布时间','板块名称','发布者','标题',"所属模块");
var dataSource;
var table;//表格

$(function(){
	initEvent();
	getCommentsClassify(url);	
	getClassifyId();
});

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


function initEvent(){
	$("#finBtn").click(function(){
		getCommentsClassify(url);	
	});
	
}



function getCommentsClassify(_url){
	var dataUrl=_url?_url:url;
	var data={
//		addName:$("#addName").val(),
//		title:$("#title").val(),
		classifyId:$("#section").val()
//		startDate:$("time1").val(),
//		endDate:$("#time2").val()
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



function init(json) {
	dataSource = json;
	
	dataSource.reffunc=getCommentsClassify;
	dataSource.funcsTitle=['详情'];
	dataSource.funcs=[function(data){
		window.location.href=commentsReply+"?id="+data.id+"&type="+data.type;
	}];
	table=myTable(dataSource);
	
	table.createTable("table_model", title, thead, "操作", {
		type:function(value){
			if(value.type==1){
				return '文化娱乐';
			}else if(value.status==2){
				return '资讯';
			}
		}
	});
	table.createPageHelper("page_moodel");
	table.addTableCheckbox();
}