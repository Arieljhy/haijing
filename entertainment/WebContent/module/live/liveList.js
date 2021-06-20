var listUrl='getLiveList.html';
var removeUrl='removeLive.html';
$(function(){
	MyTable.init();
	refresh();
	initEvent();
});

function initEvent(){
	$('select').selectpicker();
	$("#addBtn").click(function(){
		window.location.href='./liveInfo.html';
	});
	$("#removeBtn").click(function(){
		var ids=MyTable.getCheck('table','id');
		if(ids==undefined||ids==''){
			return;
		}
		removeVideos(ids.join(','));
	});
	$("#finBtn").click(function(){
		refresh();
	});
}



function refresh(page){
	var url=page?(listUrl+'?page='+page):listUrl;
	var data={
		name:$("#name").val()
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

function removeLive(ids){
	Confirm({title:"提示", msg:"确认删除 ?",onOk: function(){
		$.ajax({
			type: 'post',
			url: removeUrl,
			data: {ids: ids},
			success: function (data) {
				if (data.flag) {
					refresh();
				} else {
					Alert(data.message);
				}
			},
			error: function () {

			}
		});
	}})
}

function drawMyTable(data){
	var params={
		titles:['直播主题','直播时间','直播人','视频名称','回看','状态'],
		dataTitles:['name','timeStr','person','code','record','stateStr'],
		data:data.data,
		total:data.total,
		index:data.index,
		reffunc:refresh,
		pageSize:data.pageSize,
		trFunc:function(value,index){
			console.log(value,index);
		},
		
	};
	MyTable.draw('table','page',params);
	MyTable.addTableCheckbox('table');
	MyTable.addOperation('table',{
		title:'操作',
		operations:[ {
			title:'播放',
			state: 2,
			index: 'state',
			func:function(value){
				window.location.href='startLive.html?type=upd&id='+value.id;
			}
		},{
			title:'回放',
			state: 3,
			index: 'state',
			func:function(value){
				if(value.record=='否'){
					Alert("该直播不能回放");
				}else{
					window.location.href='recordLive.html?type=upd&id='+value.id;
				}

			}
		},{
			title:'修改',
			state: 0,
			index: 'state',
			func:function(value){
				window.location.href='liveInfo.html?type=upd&id='+value.id;
			}
		},{
			title:'删除',
			func:function(value){
				removeVideos(value.id);
			}
		}]
	});
};


function removeVideos(ids){
	Confirm({title:"提示", msg:"确认删除 ?",onOk: function(){
			$.ajax({
				type:'post',
				url:removeUrl,
				data:{ids:ids},
				success:function(data){
					if(data.flag){
						refresh();
					}else{
						Alert(data.message);
					}
				},
				error:function(){

				}
			});
		}});
}