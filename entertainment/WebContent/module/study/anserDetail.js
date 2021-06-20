var url='getAnserDetail.html';
$(function(){
	var params=GetUrlParms();
	/*
	$("#testId").val(params.testId);
	$("#userId").val(params.userId);
	$("#title").html("试卷题目"+params.title);
	$("#str").html(params.userName+"的得分为_"+params.score+"_分");
	$("#remark").html("总题数："+params.totalCount+",正确题数："+params.trueCount+",错误题数："+params.errorCount);
	*/initEvent();
	getContentList(url,params.titleId,params.userId);	
});

function initEvent(){
	//返回
	$("#back").click(function(){
		window.history.go(-1);
	});
	
	
}

function getContentList(_url,titleId,userId){
	var dataUrl=_url?_url:url;
	if(titleId==''||userId==''){
		alert("参数错误");
		return;
	}
	var data={
		//addName:$("#addName").val(),
		//title:$("#title").val(),
		//classifyId:$("#section").val(),
		//startDate:$("time1").val(),
		//endDate:$("#time2").val()
		titleId:titleId,
		userId:userId
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



function init(data) {
	console.log(data);
	var	title=data.title;
	var	sequence=data.sequence;
	$("#title").html(sequence+":"+title);
	var	anserList=data.anserList;
	var	anserIds=data.anserIds;
	//var	score1=data.score;
	var	liName='';
	var	score;
	var	anserId;
	var	liStr='';
	//var	count=0;
	var	remark="";
	var	str=data.trueAnser;
	var userAnswer='';
	$.each(anserList,function(n,value){
		liName=String.fromCharCode(n+65);//将0，1，2转A,B,C
		score=value.score;
		anserId=value.id;
		liStr='<li>'+liName+":"+value.content+"(分值："+score+");";
		$("#anserUl").append(liStr);
	/*	if(score==0){
			count+=1;
		}else{
			str+=liName;
		}*/
		var split = anserIds.split(",");
		split.forEach(function(val){
			if(anserId == val){
				userAnswer+=liName+","
			}
		})

	});
	userAnswer=userAnswer.substr(0,userAnswer.length-1)
	remark="该答卷人选择的选项为："+userAnswer;
	$("#remark").html(remark);
	$("#str").html("正确答案："+str);
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


