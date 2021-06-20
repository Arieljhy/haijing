var listUrl='getResourceList.html';
var removeUrl='removeResource.html';
$(function(){
	MyTable.init();
	refresh();
	initEvent();
});

function initEvent(){
	$('select').selectpicker();
	$("#addBtn").click(function(){
		window.location.href='./musicInfo.html';
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

    $("#syncBtn").click(function(){
        //同步文件
        var index;
        $.ajax({
            type:'post',
            url:'./syncAudio.html',
            beforeSend:function(){
                //loading层
                 index = layer.load(1, {
                    shade: [0.1,'#fff'] //0.1透明度的白色背景
                });
            },
            success:function(data){
                if(data && data.code==200){
                    layer.msg('同步成功');
                    location.reload();
                }else{
                    layer.close(index);
                    layer.msg(data.message);
                }
            },
            error:function(err){
                layer.close(index);
                layer.msg("服务器繁忙");
            },complete:function () {
             //   layer.closeAll();
            }
        });
    });
}

function refresh(page){
	var url=page?(listUrl+'?page='+page):listUrl;
	var data={
		category:$("#category").val(),
		name:$("#name").val(),
		state:$("#state").val(),
		fileType:2
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

function removeVideos(ids){
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
		titles:['名称','歌手','分类','播放量'],
		dataTitles:['name','author','typeStr','playCount'],
		data:data.data,
		total:data.total,
		index:data.index,
		reffunc:refresh,
		pageSize:data.pageSize,
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
		operations:[/*{
			title:'查看',
			func:function(value){
				
			}
		},*/{
			title:'修改',
			func:function(value){
				window.location.href='musicInfo.html?type=upd&id='+value.id;
			}
		},{
			title:'删除',
			func:function(value){
				removeVideos(value.id);
			}
		}]
	});
};