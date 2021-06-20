var updateTitleUrl="updateTitle.html";
var titleDetailUrl="getMainTestDetail.html"
var addTitleUrl="addTitle.html";
var updateWordUrl="updateWord.html";
var url='getTitleList.html';
var thead = new Array('sequence','title','anser','trueAnser','type');
var title = new Array('题号','题目','答案','正确答案','类型');
var infoHref="titleInfo.html";//新增修改跳转页面
var removeUrl='removetTitle.html';
var delTitle='delTitle.html';
var dataSource;
var table;//表格
var ueditor;
var resultAnalyseUeditor;
var titleList=[]
var titleListItem="<tr>" +
	"<td>:title</td>" +
	"<td>:type</td>" +
	"<td>:anser</td>" +
	"<td><a href='javascript:updTitle(:index)'>修改</a>" +
	/*"&nbsp;&nbsp;<a href='javascript:delTitle(:index)'>删除</a>" +*/
/*	"&nbsp;&nbsp;<a href='javascript:viewTitle(:index)'>查看</a></td>" +*/
	"</tr>";
var anserList=[]; //添加题目时用于添加答案的数组集合
var anserListItem="<tr>" +
	"<td>:option</td>" +
	"<td><input type='text' value=':content'/></td>" +
	"<td><input type='text' value=':score'/></td>" +
	"<td>:checkbox</td>" +
	"</tr>";
var anserListIndex=0;

var updateIndex=0;
var updateFlag=true;
$(function(){
	resultAnalyseUeditor = UE.getEditor('resultAnalyse',{
		toolbars: [
			[
				'undo', //撤销
				'redo', //重做
				'bold', //加粗
				'indent', //首行缩进
				'italic', //斜体
				'underline', //下划线
				'strikethrough', //删除线
				'subscript', //下标
				'fontborder', //字符边框
				'superscript', //上标
				'formatmatch', //格式刷
				'blockquote', //引用
				'pasteplain', //纯文本粘贴模式
				'selectall', //全选
				'horizontal', //分隔线
				'removeformat', //清除格式
				'time', //时间
				'date', //日期
				'unlink', //取消链接
				'insertrow', //前插入行
				'insertcol', //前插入列
				'inserttitle', //插入标题
				'cleardoc', //清空文档
				'fontfamily', //字体
				'fontsize', //字号
				'paragraph', //段落格式
				'justifyleft', //居左对齐
				'justifyright', //居右对齐
				'justifycenter', //居中对齐
				'justifyjustify', //两端对齐
				'forecolor', //字体颜色
				'insertorderedlist', //有序列表
				'insertunorderedlist', //无序列表
				'directionalityltr', //从左向右输入
				'directionalityrtl', //从右向左输入
				'rowspacingtop', //段前距
				'rowspacingbottom', //段后距
				'imagenone', //默认
				'imageleft', //左浮动
				'imageright', //右浮动
				'imagecenter', //居中
				'lineheight', //行间距
				'customstyle', //自定义标题
				'autotypeset', //自动排版
				'touppercase', //字母大写
				'tolowercase', //字母小写
				'simpleupload', //单图上传,
				/*'insertvideo',//视频*/
				'kityformula',
				/*'insertimage',*/
				'attachment'
			]
		]
	});

	var params=GetUrlParms();

	$("#testId").val(params.id);
	var data={
		id:params.id
	};
	$.ajax({
		type:'post',
		data:data,
		url:titleDetailUrl,
		success:function(data){
			$("#studyTitle").val(data.title);
			console.log(data.remark);
			$("#remark").val(data.remark);
			resultAnalyseUeditor.addListener("ready", function () {
				resultAnalyseUeditor.setContent(data.resultAnalyse);
			});
		},
		error:function(){
			Alert("服务器繁忙,请稍后再试!");
		},complete:function(){
			layer.closeAll();
		}
	});



	initEvent();
	getContentList(url);


	for(var i=0;i<26;i++) {
		anserList.push({option:String.fromCharCode(65 + i),score:0,content:'',trueAnser:false});
	}


	$("#addAnser").click(function () {
		console.log(anserListIndex)
		if(anserListIndex>=anserList.length-1){
			//Alert('添加的选项超出限制');
			layer.msg('添加的选项超出限制');
		}
		var item=anserListItem;
		var list=anserList;
		item=item.replace(":option",list[anserListIndex].option).replace(":score",list[anserListIndex].score).
		replace(":content",list[anserListIndex].content).replace(":checkbox","<input type='checkbox'>");
		$("#ansersTable tbody","#addShow").append(item);
		anserListIndex++;
	});

	$("#titleSubmit","#addShow").click(function () {

		if(!$("#title","#addShow").val()){
			layer.msg("请输入题目标题");
			$("#title","#addShow").focus();
			return
		}
		var itemList=$("#ansersTable>tbody tr");
		var flag=true;
		if(itemList.size()>0) {
			var anserStr=[];
			$(itemList).each(function(index,item){
				var tds=$(item).find("td");

				if(!$("input[type='text']",$(tds[1])).val())
				{
					$("input[type='text']",$(tds[1])).focus()
					layer.msg("请输入答案内容");
					flag=false;
					return;
				}else if(!$("input[type='text']",$(tds[2])).val() || isNaN($("input[type='text']",$(tds[2])).val())){
					$("input[type='text']",$(tds[2])).focus()
					layer.msg("请输入分值");
					flag=false;
					return;
				}

				if($("input[type='checkbox']",$(tds[3])).is(':checked')){ //勾选为了正确答案
					anserStr.push($(tds[0]).text());
				}
			});

			if(!flag){
				return;
			}

			if ($("#type", "#addShow").val() == '1' && (anserStr.length <= 0 || anserStr.length > 1)) {
				layer.msg('单选题必须且只能勾选一项为正确答案');
				return;
			} else if ($("#type", "#addShow").val() == '2' && anserStr.length <= 1) {
				layer.msg('多选题必须勾选一项以上为正确答案');
				return;
			}

if(updateFlag){

	layer.confirm('确认修改？',{
		btn: ['确定','取消'] //按钮
	}, function(layerIndex){
		var updTitle=titleList[updateIndex];
		updTitle.title=$("#title", "#addShow").val();
		updTitle.type=$("#type", "#addShow").val();
		updTitle.trueAnser=anserStr.join('、');
		var itemList=$("#ansersTable>tbody tr","#addShow");
		var flag=true;
		//$(itemList).each(function(index,item){
			for(var i=0;i<itemList.length;i++){
				var item=itemList[i];
				var index=i;
				var tds=$(item).find("td");
				if(!$("input[type='text']",$(tds[1])).val()){
					$("input[type='text']",$(tds[1])).focus();
					flag=false;
					layer.msg('请输入内容');
					break;
				}else if(isNaN($("input[type='text']",$(tds[2])).val())){
					$("input[type='text']",$(tds[2])).focus();
					flag=false;
					layer.msg('请输入分值');
					break;
				}
				if(index<updTitle.anserList.length){
					updTitle.anserList[index].option=$(tds[0]).text();
					updTitle.anserList[index].content=$("input[type='text']",$(tds[1])).val();
					updTitle.anserList[index].score=$("input[type='text']",$(tds[2])).val();
				}else{

					updTitle.anserList.push({
							option:$(tds[0]).text(),
							content:$("input[type='text']",$(tds[1])).val(),
							score:$("input[type='text']",$(tds[2])).val()
						}
					);
				}
			}
			if(!flag){
				layer.close(layerIndex);
				return;
			}
	//	});
		var index =layer.load(1, {
			shade: [0.1,'#fff'] //0.1透明度的白色背景
		});
		//提交
		$.ajax({
			type:'post',
			data:JSON.stringify(updTitle),
			contentType : "application/json;charset=UTF-8",
			dataType : 'json',
			url:updateTitleUrl,
			success:function(data){
				if(data.code==1){
					Alert({
						msg:'修改成功',
						onOk:function(){
							//window.location.reload();
							titleList[updateIndex]=updTitle;
							//	var tableTitle=$("#titleTable>tbody");

							/*var titleItem=titleListItem;
							titleItem=titleItem.replace(":title",updTitle.title).replace(":type",(updTitle.type=='1'?'单选题':'多选题')).
							replace(":anser",updTitle.trueAnser).replace(":index",updateIndex)*/

							/*var titleItem="<tr>" +
								"<td>:title</td>" +
								"<td>:type</td>" +
								"<td>:anser</td>" +
								"<td><a href='javascript:updTitle(:index)'>修改</a>&nbsp;&nbsp;<a href='javascript:delTitleStatus(:mainId,:id,:index)'>删除</a></td>" +
								"</tr>";
							titleItem=titleItem.replace(":title",updTitle.title).replace(":type",(updTitle.type=='1'?'单选题':'多选题')).
							replace(":anser",updTitle.trueAnser).replace(":index",updateIndex).
							replace(":mainId",updTitle.mainId).replace(":id",updTitle.id).replace(":index",updateIndex);*/


							var $tr=$("<tr></tr>");
							$tr.append("<td>"+updTitle.title+"</td>")
							$tr.append("<td>"+(updTitle.type=='1'?'单选题':'多选题')+"</td>")
							$tr.append("<td>"+updTitle.trueAnser+"</td>")
							$tr.append("<td><a class='updateTitle'>修改</a>&nbsp;&nbsp;" +
								"<a class='delTitleStatus' mainId='"+updTitle.mainId+"' id='"+updTitle.id+"'>删除</a></td>");


							if(titleList.length==1){
								$("#titleTable>tbody").append($tr);
							}else if(updateIndex<=0){
								$("#titleTable>tbody> tr").eq(1).before($tr);
							}else{
								$("#titleTable>tbody> tr").eq(updateIndex).after($tr);
							}
							$('#titleTable>tbody tr:eq('+updateIndex+')').remove();
						}
					});
				}else{
					Alert("修改失败");
				}
			},
			error:function(){
				Alert("服务器繁忙,请稍后再试!");
			},complete:function(){
				layer.closeAll();
			}
		});
	}, function(){

	});

/*	messageWin.confirm({
		msg: '确认修改?', onOk: function () {

		}
	})*/


}else{

	layer.confirm('确认新增？',{
		btn: ['确定','取消'] //按钮
	}, function(layerIndex) {
		var updTitle = new Object();
		updTitle.mainId = $("#testId").val();
		updTitle.title = $("#title", "#addShow").val();
		updTitle.type = $("#type", "#addShow").val();
		updTitle.trueAnser = anserStr.join('、');
		updTitle.anserList = [];
		var itemList = $("#ansersTable>tbody tr", "#addShow");
		var flag=true;
		//$(itemList).each(function(index,item){
		for(var i=0;i<itemList.length;i++){
			var item=itemList[i];
			var index=i;
			var tds = $(item).find("td");
			if(!$("input[type='text']",$(tds[1])).val()){
				$("input[type='text']",$(tds[1])).focus();
				flag=false;
				layer.msg('请输入内容');
				break;
			}else if(isNaN($("input[type='text']",$(tds[2])).val())){
				$("input[type='text']",$(tds[2])).focus();
				flag=false;
				layer.msg('请输入分值');
				break;
			}
			updTitle.anserList.push({
					option: $(tds[0]).text(),
					content: $("input[type='text']", $(tds[1])).val(),
					score: $("input[type='text']", $(tds[2])).val()
				}
			);
	}
		if(!flag){
			layer.close(layerIndex);
			return;
		}
		//});
		//提交
		layer.load(1, {
			shade: [0.1,'#fff'] //0.1透明度的白色背景
		});
		$.ajax({
			type:'post',
			data:JSON.stringify(updTitle),
			contentType : "application/json;charset=UTF-8",
			dataType : 'json',
			url:addTitleUrl,
			success:function(data){
				if(data.code==1){
					layer.closeAll();
					Alert({
						msg:'新增题目成功',
						onOk:function(){
							titleList.push(data.data);
					/*		var titleItem=titleListItem;
							titleItem=titleItem.replace(":title",updTitle.title).replace(":type",(updTitle.type=='1'?'单选题':'多选题')).
							replace(":anser",updTitle.trueAnser).replace(":index",titleList.length-1)*/
							/*var titleItem="<tr>" +
								"<td>:title</td>" +
								"<td>:type</td>" +
								"<td>:anser</td>" +
								"<td><a href='javascript:updTitle(:index)'>修改</a>&nbsp;&nbsp;<a href='javascript:delTitleStatus(:mainId,:id,:index)'>删除</a></td>" +
								"</tr>";
							titleItem=titleItem.replace(":title",updTitle.title).replace(":type",(updTitle.type=='1'?'单选题':'多选题')).
							replace(":anser",updTitle.trueAnser).replace(":index",titleList.length-1).
							replace(":mainId",data.data.mainId).replace(":id",data.data.id).replace(":index",titleList.length-1);
*/
				/*			var $tr=$("<tr></tr>");
							$tr.append("<td>"+updTitle.title+"</td>")
							$tr.append("<td>"+(updTitle.type=='1'?'单选题':'多选题')+"</td>")
							$tr.append("<td>"+updTitle.trueAnser+"</td>")
							$tr.append("<td><a href='javascript:updTitle("+titleList.length-1+")'>修改</a>&nbsp;&nbsp;" +
								"<a href='javascript:delTitleStatus("+data.data.mainId+","+data.data.id+","+$tr+")'>删除</a></td>");*/

							var $tr=$("<tr></tr>");
							$tr.append("<td>"+updTitle.title+"</td>")
							$tr.append("<td>"+(updTitle.type=='1'?'单选题':'多选题')+"</td>")
							$tr.append("<td>"+updTitle.trueAnser+"</td>")
							$tr.append("<td><a class='updateTitle'>修改</a>&nbsp;&nbsp;" +
								"<a class='delTitleStatus' mainId='"+data.data.mainId+"' id='"+data.data.id+"'>删除</a></td>");

							$("#titleTable>tbody").append($tr);
						}
					});
				}else{
					Alert("新增题目失败");
				}

			},
			error:function(){
				Alert("服务器繁忙,请稍后再试!");

			},complete:function(){
				layer.closeAll();
			}
			});
	},function(index){

	});





}
			/*messageWin.confirm({
				msg: '确认修改?', onOk: function () {*/


			/*	}
			});*/
		}else{
			layer.msg("请添加答案选项");
		}
	});
	$("#titleBack").click(function () {
		layer.closeAll();
	});

	$("#submit").click(function(){
		updatePaper();
	});

	$("#addTitle").click(function(){
		updateFlag=false;
		layer.open({
			title:'新增题目',
			type: 1,
			skin: 'layui-layer-rim', //加上边框
			area: ['80%', '80%'], //宽高
			content:$('#addShow'),
			end: function(){ //此处用于演示
				anserListIndex=0;
				updateFlag=true;
				$("#ansersTable>tbody").empty();
				$("#title","#addShow").val('');
				$("#type","#addShow").selectpicker('val', ['1']);
				submitAnserList=[];
			}
		});
	});

	$("#titleTable>tbody").delegate("a.delTitleStatus",'click','',function(){
		if($("#titleTable>tbody tr").size()==1){
			Alert('至少需要保留一题');
			return;
		}
		var that=this;
		messageWin.confirm('提示','确认删除该题吗?删除后无法恢复!',function(){
			var mainId=$(that).attr('mainId');
			var id=$(that).attr('id');
			$.ajax({
				type:'post',
				url:delTitle,
				data: {mainId:mainId,id:id},
				success:function(data){
					/*if($('#titleTable>tbody tr').size()==1){
						//$('#titleTable>tbody tr:eq(0)').remove();

						$(index).remove();
					}else{
						//$('#titleTable>tbody tr:eq('+index+')').remove();
						//$('#titleTable>tbody tr')
						$(index).remove();
					}*/
					var tr=$(that).parent().parent()
					titleList.splice($("#titleTable>tbody tr").index($(tr)),1);
					$(tr).remove();
					messageWin.alertback("提示", "删除成功！");
				},
				error:function(err){

				}
			});
		})
	});



	$("#titleTable>tbody").delegate("a.updateTitle",'click','',function(){
		var tr=$(this).parent().parent();
		var index=$("#titleTable>tbody tr").index($(tr))
		$("#title","#addShow").val(titleList[index].title);
		$("#type","#addShow").selectpicker('val', titleList[index].type)
		var submitAnserList= titleList[index].anserList;
		for(var i=0;i<submitAnserList.length;i++){
			var item=anserListItem;
			item=item.replace(":option",anserList[i].option).
			replace(":score",submitAnserList[i].score).
			replace(":content",submitAnserList[i].content)
				.replace(":checkbox","<input type='checkbox'"+ (titleList[index].trueAnser.indexOf(anserList[i].option)>-1?' checked':'')+">");
			$("#ansersTable>tbody","#addShow").append(item);
		}

		anserListIndex=submitAnserList.length;
		updateIndex=index;
		layer.open({
			title:'修改题目',
			type: 1,
			skin: 'layui-layer-rim', //加上边框
			area: ['80%', '80%'], //宽高
			content:$('#addShow'),
			end: function(){ //此处用于演示
				anserListIndex=0;
				$("#ansersTable>tbody","#addShow").empty();
				$("#title","#addShow").val('');
				$("#type","#addShow").selectpicker('val','1');
			}
		});
	});


});

//检查参数
function getParams(type){
	if(!$("#studyTitle").val()){
		layer.msg("请输入问卷标题")
		return
	}
	var mainTest={
		id:$("#testId").val(),
		title:$("#studyTitle").val(),
		type:$("#studyType").val(),
		remark:$("#remark").val(),
		resultAnalyse:resultAnalyseUeditor.getContent()
	};
	return mainTest;

}
//新增方法
function updatePaper(){
	var params=getParams();
	if(params==null){
		//$("#submit").one('click',addAdvert);//重新绑定
		//Alert('提交的数据有误');
		return;
	}
	var index =layer.load(1, {
		shade: [0.1,'#fff'] //0.1透明度的白色背景
	});
	//提交
	$.ajax({
		type:'post',
		data:JSON.stringify(params),
		contentType : "application/json;charset=UTF-8",
		dataType : 'json',
		url:updateWordUrl,
		success:function(data){
			if(data.code==1){
				Alert({
					msg:'修改成功',
					onOk:function(){
						window.location.href='testList.html?type='+$("#studyType").val();
					}
				});
			}else{
				Alert("修改试卷失败");
			}
			layer.close(index)
		},
		error:function(){
			Alert("服务器繁忙,请稍后再试!");
			layer.close(index)
		}
	});
}

function initEvent(){
	//返回
	$("#back").click(function(event){
		//alert(123123)
		var fromurl = document.referrer;
		if(fromurl){
			location.href=fromurl;
		}else{
			window.history.back();
		}
		//location.replace(this.href);
		//event.returnValue=false;
	});
	//select初始化
	$('select').selectpicker();
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
		id:$("#testId").val(),
		pageSize:100
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

function updTitle(index){
	//var title=titleList[index];
	$("#title","#addShow").val(titleList[index].title);
	$("#type","#addShow").selectpicker('val', titleList[index].type)
	var submitAnserList= titleList[index].anserList;
	for(var i=0;i<submitAnserList.length;i++){
		var item=anserListItem;
		item=item.replace(":option",anserList[i].option).
		replace(":score",submitAnserList[i].score).
		replace(":content",submitAnserList[i].content)
			.replace(":checkbox","<input type='checkbox'"+ (titleList[index].trueAnser.indexOf(anserList[i].option)>-1?' checked':'')+">");
		$("#ansersTable>tbody","#addShow").append(item);
	}

	anserListIndex=submitAnserList.length;
	updateIndex=index;
	layer.open({
		title:'修改题目',
		type: 1,
		skin: 'layui-layer-rim', //加上边框
		area: ['80%', '80%'], //宽高
		content:$('#addShow'),
		end: function(){ //此处用于演示
			anserListIndex=0;
			$("#ansersTable>tbody","#addShow").empty();
			$("#title","#addShow").val('');
			$("#type","#addShow").selectpicker('val','1');
		}
	});
}

function delTitle(index){
	messageWin.confirm({
		msg: '确认删除?', onOk: function () {
			$('#titleTable>tbody tr:eq('+index+')').remove();

		/*	$.ajax({
				type: 'post',
				url: removeUrl,
				data: {ids: ids},
				success: function (data) {
					if (data.flag) {

						getContentList(url);

					} else {
						alert("删除失败！");
					}
				},
				error: function () {

				}
			});*/
		}
	})

}

/*function delTitleStatus(mainId,id,index){
	messageWin.confirm('提示','确认删除该题吗?删除后无法恢复!',function(){
			$.ajax({
				type:'post',
				url:delTitle,
				data: {mainId:mainId,id:id},
				success:function(data){
					if($('#titleTable>tbody tr').size()==1){
						//$('#titleTable>tbody tr:eq(0)').remove();

						$(index).remove();
					}else{
						//$('#titleTable>tbody tr:eq('+index+')').remove();
						//$('#titleTable>tbody tr')
						$(index).remove();
					}

					messageWin.alertback("提示", "删除成功！");
				},
				error:function(err){

				}
			});
	})

}*/
function init(json) {

	var data=JSON.parse(json.data);
	titleList=data;
	var tableTitle=$("#titleTable>tbody");
	for(var i=0;i<data.length;i++){
		/*var titleItem="<tr>" +
			"<td>:title</td>" +
			"<td>:type</td>" +
			"<td>:anser</td>" +
			"<td><a href='javascript:updTitle(:index)'>修改</a>&nbsp;&nbsp;<a href='javascript:delTitleStatus(:mainId,:id,:index)'>删除</a></td>" +
			"</tr>";
		titleItem=titleItem.replace(":title",data[i].title).replace(":type",(data[i].type=='1'?'单选题':'多选题')).
		replace(":anser",data[i].trueAnser).replace(":index",i).replace(":mainId",data[i].mainId).replace(":id",data[i].id).replace(":index",i);*/

		var $tr=$("<tr></tr>");
		$tr.append("<td>"+data[i].title+"</td>")
		$tr.append("<td>"+(data[i].type=='1'?'单选题':'多选题')+"</td>")
		$tr.append("<td>"+data[i].trueAnser+"</td>")
		$tr.append("<td><a class='updateTitle'>修改</a>&nbsp;&nbsp;" +
			"<a class='delTitleStatus' mainId='"+data[i].mainId+"' id='"+data[i].id+"'>删除</a></td>");
		tableTitle.append($tr);


	}
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
  console.log(args)
  return args;
};


