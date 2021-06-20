var listUrl='userMessageList.html';

$(function(){
	MyTable.init();
	refresh();
	initEvent();
});

function initEvent(){
	
	$('select').selectpicker();
	
	$("#finBtn").click(function(){
		refresh();
	});
}

function refresh(page){
	var url=page?(listUrl+'?page='+page):listUrl;
	var data={
		content:$("#content").val(),
		type:$("#type").val(),
		isPrivate:$("#isPrivate").val(),
		fromPeople:$("#fromPeople").val(),
		toPeople:$("#toPeople").val()
	};
	$.ajax({
		type:'post',
		url:url,
		data:data,
		success:function(data){
			drawMyTable(data);
		},
		error:function(err){
			
		}
	});
};


function drawMyTable(data){
	var params={
		titles:['发言时间','发言人','发送对象','内容'],
		dataTitles:['createTime','fromPeople','toPeople','content'],
		data:data.data,
		total:data.total,
		index:data.index,
		pageSize:data.pageSize,
		reffunc:refresh,
		trFunc:function(value,index){
			console.log(value,index);
		},
		dataTitlesFunc:{
			'fromPeople':function(value,i,j){
				return value.fromPeople+'('+(value.fromCode||'群聊')+')';
			},
			'toPeople':function(value,i,j){
				return value.toPeople+'('+(value.toCode||'群聊')+')';
			},
			'content':function(value){
				if(value.type==1){
					return "<a class='fancybox' data-bit='"+value.content+"' href='"+value.content+"'>图片</a>";
				}else{
					return value.content;
				}
			}
		},
		
	};
	MyTable.draw('table','page',params);
	jQuery(".fancybox").fancybox();
//	$(".show").click(function(){
//		var bitMap=$(this).attr("data-bit");
//		var canvas=document.getElementById("showImg");
//		var ctx=canvas.getContext("2d");
//		ctx.drawImage(bitMap,0,0);
//	});
};