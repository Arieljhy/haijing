var url='getUserAnserList.html';
var thead = new Array('sequence','title','testResult');
var title = new Array('题号','题目','考试结果');
var infoHref="titleInfo.html";//新增修改跳转页面
var removeUrl='removetTitle.html';
var	anserDetail="anserDetail.html";
var dataSource;
var table;//表格

$(function(){
	var params=GetUrlParms();
	$("#testId").val(params.testId);
	$("#userId").val(params.userId);
	$("#title").html("试卷题目"+params.title);
	$("#str").html(params.userName+"的得分为_"+params.score+"_分");
	$("#remark").html("总题数："+params.totalCount+",正确题数："+params.trueCount+",错误题数："+params.errorCount);
	initEvent();
	getContentList(url);	
});

function initEvent(){
	//返回
	$("#back").click(function(){
		window.history.go(-1);
	});
	/*$("#removeBtn").click(function(){
		var ids=table.getCheck('id');
		if(ids==undefined||ids==''){
			return;
		}
		removeContent(ids.join(','));
	});*/
	/*$("#addBtn").bind('click',function(){
		window.location.href=infoHref+"?type=add";
	});
	$("#finBtn").click(function(){
		getContentList(url);	
	});*/
	
}

function getContentList(_url){
	var dataUrl=_url?_url:url;
	if($("#testId").val()==''||$("#userId").val()==''){
		alert("参数错误");
		return;
	}
	var data={
		//addName:$("#addName").val(),
		//title:$("#title").val(),
		//classifyId:$("#section").val(),
		//startDate:$("time1").val(),
		//endDate:$("#time2").val()
		id:$("#testId").val(),
		userId:$("#userId").val()
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
	
	dataSource.reffunc=getContentList;
	dataSource.funcsTitle=['查看详情'];
	dataSource.funcs=[function(data){
		window.location.href=anserDetail+"?userId="+data.userId+"&&titleId="+data.titleId;
	}];
	console.log(dataSource);
	table=myTable(dataSource);
	
	table.createTable("table_model", title, thead, "操作", {
		testResult:function(value){
			if(value.type == '1'){
				return '正确';
			}else{
				if(value.score==0){
					return '错误';
				}
				return '正确';
			}

		}
	});
	table.createPageHelper("page_moodel");
	//table.addTableCheckbox();
}


//获取URL所有参数
function GetUrlParms() {
  var args = new Object();
  var query = location.search.substring(1); //获取查询串   
  
  var pairs = query.split("&"); //在逗号处断开   
 
  for (var i = 0; i < pairs.length; i++) {
      var pos = pairs[i].indexOf('='); //查找name=value   
      if (pos == -1) continue; //如果没有找到就跳过   
      var argname = pairs[i].substring(0, pos); //提取name   
      var value = pairs[i].substring(pos + 1); //提取value 
      args[argname] = decodeURI(value); //存为属性   
  }
  return args;
};


