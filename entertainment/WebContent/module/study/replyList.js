var url='getReplyList.html';
var thead = new Array('userName','trueCount','errorCount','totalCount','score');
var title = new Array('用户名','正确题数','错误题数','总题数','总分');
var infoHref="resultInfo.html";//新增修改跳转页面
var removeUrl='removetTitle.html';
var	userAnserList='userAnserList.html';//getUserAnserList
var titleDetailUrl="getMainTestDetail.html"
var exportlUrl="exportExcel.html"
var dataSource;
var table;//表格

$(function(){
	var params=GetUrlParms();
	$("#testId").val(params.id);
	var	type=params.type;


	var data={
		id:params.id
	};
	$.ajax({
		type:'post',
		data:data,
		url:titleDetailUrl,
		success:function(data){
			if(type==1){
				$("#title").html("心理测评-试卷题目："+data.title);
			}else{
				$("#title").html("网上考试-试卷题目："+data.title);
			}

			$("#testName").val(data.title);
			$("#remark").show();
			$("#remark").html("结果说明："+data.resultAnalyse);
		},
		error:function(){
			Alert("服务器繁忙,请稍后再试!");
		},complete:function(){
			//layer.closeAll();
		}
	});



	initEvent();
	getContentList(url);


	$("#export").click(function(){
		location.href=exportlUrl+"?id="+$("#testId").val()+"&testName="+$("#testName").val();
	});

});

function initEvent(){
	//返回
	$("#back").click(function(event){
		//window.history.go(-1);
		//location.replace(this.href);
	//	event.returnValue=false;

		var fromurl = document.referrer;
		if(fromurl){
			location.href=fromurl;
		}else{
			window.history.back();
		}
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
	if($("#testId").val()==''){
		alert("参数错误");
		return;
	}
	var data={
		//addName:$("#addName").val(),
		//title:$("#title").val(),
		//classifyId:$("#section").val(),
		//startDate:$("time1").val(),
		//endDate:$("#time2").val()
		id:$("#testId").val()
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

function removeContent(ids){
	$.ajax({
		type:'post',
		url:removeUrl,
		data:{ids:ids},
		success:function(data){
			if(data.flag){
				alert("删除成功！");
				getContentList(url);
				/*Alert({
					msg:data.msg,
					onOk:function(){
						getBbsList(url);
					}
				});*/
			}else{
				alert("删除失败！");
			}
		},
		error:function(){
			
		}
	});
}

function init(json) {
	dataSource = json;
	
	dataSource.reffunc=getContentList;
	dataSource.funcsTitle=['查看详情'];
	dataSource.funcs=[function(data){
		window.location.href=userAnserList+"?testId="+data.testId
		+"&&userId="+data.userId+"&&title="+data.title
		+"&&score="+data.score+"&&totalCount="+data.totalCount
		+"&&errorCount="+data.errorCount+"&&trueCount="+data.trueCount
		+"&&userName="+data.userName;
	}];
	console.log(dataSource);
	table=myTable(dataSource);
	
	table.createTable("table_model", title, thead, "操作", {
		
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


