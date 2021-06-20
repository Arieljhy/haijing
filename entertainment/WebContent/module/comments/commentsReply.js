var getCommentUrl="getComments.html";
var deleteComments="deleteComments.html";
var idReply="";
var typeReply="";

var thead = new Array('id','addDate','content','addName');
var title = new Array('编号','发布时间','内容','发布者');
var dataSource;
var table;//表格



$(function() {
	var params=GetUrlParms();
	idReply=params.id;
	typeReply=params.type;
	getCommentsClassify();
	bingBtn();
});



//绑定事件按钮
function bingBtn(){
	$("#backBtn").bind("click",function(){
		window.history.go(-1);
	});
	
}

function getCommentsClassify(_url){
	var dataUrl=_url?_url:getCommentUrl;
	var data={
			id:idReply,
			type:typeReply
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
	dataSource.funcsTitle=['刪除'];
	dataSource.funcs=[function(data){
		deleteCommentsFunc(data.id);
	}];
	table=myTable(dataSource);
	
	table.createTable("table_model", title, thead, "操作",{});
	table.createPageHelper("page_moodel");
	table.addTableCheckbox();
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

function deleteCommentsFunc(id){
	var data={
			id:id
		};
		$.ajax({
			type:'post',
			url:deleteComments,
			data:data,
			success:function(data){
				if(data.flag){
					Alert({
						msg: "删除成功"
					});
					getCommentsClassify();
				}else{
					Alert({
						msg: "删除失败"
					});
				}
			},
			error:function(err){
				
			}
		});
}
