var listUrl='classfiyList.html';
var removeUrl='removeClassfiy.html';
var isData='isData.html';
$(function(){
	MyTable.init();
	refresh();
	initEvent();
});

function initEvent(){
	$('select').selectpicker();
	$("#addBtn").click(function(){
		window.location.href='./toClassfiyInfo.html';
	});
	$("#removeBtn").click(function(){
		var ids=MyTable.getCheck('table','id');
		if(ids==undefined||ids==''){
			return;
		}

		removeClassfiys(ids.join(','));


	});
	$("#finBtn").click(function(){
		refresh();
	});
}

function refresh(page){
	var url=page?(listUrl+'?page='+page):listUrl;
	var data={
		name:$("#name").val(),
		remark:$("#remark").val(),
		state:$("#state").val()
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

function removeClassfiys(ids){
	$.ajax({
		type:'post',
		url:isData,
		data:{ids:ids},
		success:function(data){
			if(data.flag){
				Alert({
					msg:data.msg,
					onOk:function(){
						refresh();
					}
				});
			}else{
				messageWin.confirm("提示", "确定删除论坛板块吗？", function() {
					$.ajax({
						type: 'post',
						url: removeUrl,
						data: {ids: ids},
						success: function (data) {
							if (data.flag) {

								messageWin.alert("提示", data.msg);
									refresh();

							} else {
								messageWin.alert("提示", data.msg);
								return;
							}
						},
						error: function () {

						}
					});
				})
			}
		},
		error:function(){

		}
	});
}

function drawMyTable(data){
	var params={
		titles:['编号','序列','板块名称','备注','启用状态'],
		dataTitles:['id','sequence','name','remark','state'],
		data:data.data,
		total:data.total,
		index:data.index,
		reffunc:refresh,
		pageSize:data.pageSize,
		trFunc:function(value,index){
			console.log(value,index);
		},
		dataTitlesFunc:{
			'state':function(value,i,j){
				return value.state==1?'启用':'禁用';
			}
		},
	};
	MyTable.draw('table','page',params);
	MyTable.addTableCheckbox('table');
	MyTable.addOperation('table',{
		title:'操作',
		operations:[{
			title:'查看',
			func:function(value){
				$("#info_sequence").html(value.sequence);
				$("#info_remark").html(value.remark);
				document.getElementById("info_remark").style.wordBreak="break-al";
				document.getElementById("info_remark").style.wordWrap="break-word";
				document.getElementById("info_remark").style.width="300px";

				$("#info_name").html(value.name);
				$("#info_state").html(value.state==1?'启用':'禁用');
				$("#classfiyInfoModal").modal('show');
			}
		},{
			title:'修改',
			func:function(value){
				window.location.href='toClassfiyInfo.html?type=upd&id='+value.id;
			}
		},{
			title:'删除',
			func:function(value){
				removeClassfiys(value.id);

			}
		}]
	});
};