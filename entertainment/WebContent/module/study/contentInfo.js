var addAdvertUrl="addContent.html";
var getAdvertUrl="findContentOne.html";
var updAdvertUrl="updContent.html";
var ueditor;

$(function(){
	var params=GetUrlParms();
	var type=params.type;
	initUeditor();
	ueditor.addListener( 'ready', function( editor ) {
		if(type=='upd'){
			initUpdEvent(params.id);
		}else{
			initAddEvent();
		}
	});
	initEvent();
});


//修改绑定事件
function initUpdEvent(id){
	$("#title").html('修改内容');
	writeData(id);
}

//新增绑定事件
function initAddEvent(){
	$("#title").html('新增内容');
	$("#submit").one('click',function(){
		addAdvert();
	});
}

function initEvent(){
	
	//input获得焦点事件
	$("input").focus(function(){
		$("input").not(this).popover('hide');
		$(this).popover('show');
	});
	//
	$("input").on('change',function(){
		$("input").popover('hide');
		console.log('change');
	});
	//返回
	$("#back").click(function(){
		window.history.go(-1);
	});
}

//写入数据
function writeData(id){
	$.ajax({
		type:'post',
		url:getAdvertUrl,
		data:{id:id},
		success:function(data){
				var advert=data;
				$("#content_title").val(advert.title);
				$("#type").val(advert.type);
				$("#remark").val(advert.remark);
				ueditor.setContent(advert.content);
				$("#submit").one('click',function(){
					updAdvert(advert.id);
				});
			
		},
		error:function(err){
			
		}
	});
}

//检查参数
function getParams(type){
	//input 数据
	var advert={
			title:$("#content_title").val()
		};
		
		for(var key in advert){
			if(advert[key]==null||advert[key]==''){
				if(key=='title'){
					$("#content_title").focus();
				}else{
					$("#"+key).focus();
				}
				
				return;
			}
		}
	
	
	advert.type=$("#type").val();
	var remark=$("#remark").val();
	if(remark==null||remark==''){
        Alert('摘要不能为空');
		return null;
	}
	advert.remark=remark;
	var content=ueditor.getContent();
	if(content==null||content==''){
		Alert('资讯内容不能为空');
		return null;
	}
	advert.content=content;
	
	return advert;
}

//新增方法
function addAdvert(){
	var advert=getParams();
	if(advert==null){
		$("#submit").one('click',addAdvert);//重新绑定
		return;
	}
	
	//提交
	$.ajax({
		type:'post',
		data:advert,
		url:addAdvertUrl,
		success:function(data){
			if(!data.flag){
				$("#submit").one('click',addAdvert);//重新绑定
				Alert({
					msg:data.message,
					onOk:function(){
						console.log('ok');
					}
				});
			}else{
				Alert({
					msg:data.message,
					onOk:function(){
						window.history.go(-1);
					}
				});
			}
		},
		error:function(){
			
		}
	});
}

//修改方法
function updAdvert(id){
	var advrty=getParams('upd');
	if(advrty==null){
		$("#submit").one('click',function(){
			updAdvert(id);
		});//重新绑定
		return;
	}
	advrty.id=id;
	//提交
	$.ajax({
		type:'post',
		data:advrty,
		url:updAdvertUrl,
		success:function(data){
			if(!data.flag){
				$("#submit").one('click',function(){
					updAdvert(id);
				});//重新绑定
				Alert({
					msg:data.message,
					onOk:function(){
						
					}
				});
			}else{
				Alert({
					msg:data.message,
					onOk:function(){
						window.history.go(-1);
					}
				});
			}
		},
		error:function(){
			
		}
	});
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

function initUeditor(){
	ueditor = UE.getEditor('content',{
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
				        /*'insertimage'*/
   				]
				]
   	});
};