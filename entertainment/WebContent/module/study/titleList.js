var url='getTitleList.html';
var thead = new Array('sequence','title','anser','trueAnser','type');
var title = new Array('题号','题目','答案','正确答案','类型');
var infoHref="titleInfo.html";//新增修改跳转页面
var removeUrl='removetTitle.html';
var titleDetailUrl="getMainTestDetail.html"
var dataSource;
var table;//表格

$(function(){
	var params=GetUrlParms();
	$("#testId").val(params.id);
var	type=params.type;


	initEvent();
	getContentList(url);
	var data={
		id:params.id
	};
	$.ajax({
		type:'post',
		data:data,
		url:titleDetailUrl,
		success:function(data){
			$("#remark").html(data.remark?data.remark:'');
			$("#title").html(data.title?data.title:'');
			if(type==1){
			
				
				$("#mytit").html("心理问卷管管理");
			}else{
				
				$("#mytit").html("在线考试管理");
			}
			
			var totalCount=0;
			var replyCount=0;
			if(data.totalCount ){
				totalCount=data.totalCount
			}
			if(data.replyCount){
				replyCount=data.replyCount
			}
			$("#str").html("总题数："+totalCount+",答题人数："+replyCount);
		},
		error:function(){
			Alert("服务器繁忙,请稍后再试!");
		},complete:function(){
			//layer.closeAll();
		}
	});



});

function initEvent(){
	//返回
	$("#back").click(function(event){
	/*	window.history.back();
		//location.replace(this.href);
		event.returnValue=false;*/
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



function init(json) {
	dataSource = json;
	
	dataSource.reffunc=getContentList;
	/*dataSource.funcsTitle=['修改'];
	dataSource.funcs=[function(data){
		window.location.href=infoHref+"?id="+data.id+"&&type=upd";
	}];*/
	console.log(dataSource);
	table=myTable(dataSource);
	
	table.createTable("table_model", title, thead, "操作", {
		type:function(value){
			if(value.type==1){
				return '单选';
			}
			return '多选';
		},
		trueAnser:function(value){
			var	trueAnser=value.trueAnser;
			if(typeof(trueAnser)=="undefined"||trueAnser==undefined||trueAnser==""||trueAnser==null){
				return '无正确答案';
			}else{
				return '选项'+trueAnser+'正确';
			}
			
		},
		anser:function(value){
			var	anserList=value.anserList;
			var	anserStr="";
			$.each(anserList,function(n,value){
				var	abc=String.fromCharCode(n+65);
				if(n==0){
					anserStr+=abc+":";
				}else{
					anserStr+="<br>"+abc+":";
				}//多选项方法
				/*switch(n)
				{
				case 0:
					anserStr+="A:";	
				  break;
				case 1:
					anserStr+="<br>B:";	
				  break;
				case 2:
					anserStr+="<br>C:";	
				  break;
				case 3:
					anserStr+="<br>D:";	
				  break;
				};*/
				anserStr+=value.content;
			});
			return	anserStr;
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


