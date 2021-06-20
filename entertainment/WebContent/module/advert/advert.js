var listUrl='advertList.html';
var removeUrl='removeAdvert.html';
$(function(){
	MyTable.init();
	refresh();
	initEvent();
});

function initEvent(){
	$('select').selectpicker();
	$("#addBtn").click(function(){

		window.location.href='./toAdvertInfo.html';

	});
	$("#removeBtn").click(function(){
		var ids=MyTable.getCheck('table','id');
		if(ids==undefined||ids==''){
			return;
		}
		removeAdverts(ids.join(','));
	});
	$("#finBtn").click(function(){
		refresh();
	});
}

function refresh(page){
	var url=page?(listUrl+'?page='+page):listUrl;
	var data={
		author:$("#author").val(),
		title:$("#title").val(),
		type:$("#state").val()
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

function removeAdverts(ids){

	messageWin.confirm("提示", "确定删除资讯吗？", function(){
		$.ajax({
			type:'post',
			url:removeUrl,
			data:{ids:ids},
			success:function(data){
				if(data.flag){
					messageWin.alert("提示", data.msg);
					refresh();
				}else{
					messageWin.alert("提示",data.msg);
					return;
				}
			},
			error:function(){

			}
		});
	});


}

function drawMyTable(data){
	var params={
		titles:['编号','发布时间','资讯板块','发布者','标题'],
		dataTitles:['id','startDate','type','author','title'],
		data:data.data,
		total:data.total,
		index:data.index,
		pageSize:data.pageSize,
		reffunc:refresh,
		trFunc:function(value,index){
			console.log(value,index);
		},
		dataTitlesFunc:{
			type:function(value){
				return $("#state option[value='"+value.type+"']").html();
			}
		},
		
	};
	MyTable.draw('table','page',params);
	MyTable.addTableCheckbox('table');
	MyTable.addOperation('table',{
		title:'操作',
		operations:[{
			title:'修改',
			func:function(value){
				window.location.href='toAdvertInfo.html?type=upd&id='+value.id;
			}
		},{
			title:'删除',
			func:function(value){

				removeAdverts(value.id);
			}
		}]
	});
};