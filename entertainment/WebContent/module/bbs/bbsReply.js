
/**全局变量***/
var url = 'getBbsReply.html';
var infoHref="bbsInfo.html";//新增修改跳转页面
var removeUrl="removeBbs.html";
var dataSource;
var	userId;
var	flag=false;
var table;//表格
$(function() {
	var params=GetUrlParms();
	bingBtn(params);
	userId=params.addPerson;
	$("#bbsId").val(params.id);
	$("#pageTitle").html(params.title);
	$("#title").html(params.title);
	$("#clickCount").html("查看："+params.clickCount);
	$("#replyCount").html("回复："+params.replyCount);
	getTopicList(url);	
	
});



//绑定事件按钮
function bingBtn(params){

	
}
function back() {
	sessionStorage.setItem('refresh', 'true');
	window.history.go(-1);
}
// $("#backBtn").bind("click",function(){
// 	//window.history.go(-1);
// 	sessionStorage.setItem('refresh', 'true');
// 	history.go(-1);
//
// });

function getTopicList(_url) {
	var dataUrl=_url?_url:url;
	var	addPerson="";
	if(flag){
			addPerson=userId;
	}
	$.ajax({
		url : dataUrl,
		data : {
			"id" : $("#bbsId").val(),
			"addPerson":addPerson
		},
		type : 'post',
		datatype : 'json',
		beforeSend : function() {
		},
		success : function(data) {
			init(data);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
		}
	});
}

function init(json) {
	
	dataSource = json;
	dataSource.reffunc=getTopicList;
	table=myTable(dataSource);
	$("#bbsTbody").empty();
	$.each($.parseJSON(dataSource.data),function(n,value){
		//
		var	addPerson=value.addPerson;
		var content=value.content;
		var	floorName=value.floorName;
		var	addName=value.addName;
		var	headImage=value.headImage;
		var addDte=value.addDate;
		var	imageList=value.imageList;
		if(userId==addPerson){
			
			str='<tr><td class="bbs-user-info"	style="width: 200px;height: 426px;"><div class="bbs-user-name">'
				+addName+'<span>'+'楼主'+'</span></div><div class="bbs-user-avatar">'
				+'<img src="'+headImage+'"></div><div class="bbs-user-data"></div>'
				+' </td><td class="bbs-floor-content"><div class="bbs-floor-text" style="word-wrap: break-word;word-break:break-all;">'
				+content+'<br>'+setImage(imageList)+' </div><div class="bbs-floor-tail"><span class="bbs-floor-tail-info"><span>发表于'
				+addDte+'</span>|<a	style="color:#547fff;" href="javascript:lookAuthor();">只看该作者</a></span>'
				+'<a class="bbs-floor-reply" href="javascript:removeBbs('+value.id+');">删除</a>'
				+'<a class="bbs-floor-reply" href="javascript:updBbs('+value.id+');">修改</a>'
				+'</div></td></tr>';
		}else{
			str='<tr><td class="bbs-user-info"	style="width: 200px;height: 426px;"><div class="bbs-user-name">'
				+addName+'<span>'+floorName+'</span></div><div class="bbs-user-avatar">'
				+'<img src="'+headImage+'"></div><div class="bbs-user-data"></div>'
				+' </td><td class="bbs-floor-content"><div class="bbs-floor-text" style="word-wrap: break-word;word-break:break-all;">'
				+content+'<br>'+setImage(imageList)+' </div><div class="bbs-floor-tail"><span class="bbs-floor-tail-info"><span>发表于'
				+addDte+'</span>|<a	style="color:#547fff;" href="javascript:lookAuthor();">只看该作者</a></span>'
				+'<a class="bbs-floor-reply" href="javascript:removeBbs('+value.id+');">删除</a>'
				+'<a class="bbs-floor-reply" href="javascript:updBbs('+value.id+');">修改</a>'
				+'</div></td></tr>';
		}
		$("#bbsTbody").append(str);
		
	 });
	table.createPageHelper("page_moodel");
}
function	lookAuthor(){
	flag=true;
	getTopicList(url);	
}






function removeBbs(ids){
	if(ids==$("#bbsId").val()){
		messageWin.alert("提示","您要删除的是主贴，请返回列表页进行删除。");
		//alert( "您要删除的是主贴，请返回列表页进行删除。");
		//messageWin.alert("提示",);
		return false;
	}
	messageWin.confirm("提示", "确定删除回帖吗？", function() {
		$.ajax({
			type:'post',
			url:removeUrl,
			data:{ids:ids,flag:1},
			success:function(data){
				if(data.flag){
					messageWin.alert("提示", data.msg);
					//alert(data.msg);
					getTopicList(url);

				}else{
					messageWin.alert("提示", data.msg);
					//alert(data.msg);
				}
			},
			error:function(){

			}
		});
	})

};

function	updBbs(id){
	window.location.href=infoHref+"?type=upd&id="+id;
}


function	setImage(imageList){
	if(imageList==''||imageList==undefined||imageList==null){
		return	'';
	}
	var	imageArray=imageList.split(",");
	str='<ul	style="list-style:none;display:inline-block;">';
	var	a,type;
	$.each(imageArray,function(n,value){
		a=value.lastIndexOf('.');
		type=value.substring(a+1); 
		if(type=="bmp"||type=="png"||type=="gif"||type=="jpg"||type=="jpeg"){
			str+='<li	style="list-style:none;display:inline-block;float:left;margin-right:15px;"><img	style="width:160px;height:160px;" src="'+value+'"></li>';
		}else	if(type=="mp4"||type=="rmvb"||type=="avi"||type=="ogg"||type=="wmv"){
			str+='<li	style="list-style:none;display:inline-block;float:left;margin-right:15px;"><video	style="width:320px;height:160px;" src="'+value+'"	controls autobuffer></video></li>';
		}
		
	});
	str+='</ul>';
	return	str;
	
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
}
