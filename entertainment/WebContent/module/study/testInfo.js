var addWordUrl="addWord.html";
var getAdvertUrl="getAdvertById.html";
var updAdvertUrl="updAdvert.html";
var ueditor;
var resultAnalyseUeditor;
var anserList=[]; //添加题目时用于添加答案的数组集合
var anserListIndex=0;
var anserListItem="<tr>" +
            "<td>:option</td>" +
            "<td><input type='text' value=':content'/></td>" +
            "<td><input type='text' value=':score'/></td>" +
            "<td>:checkbox</td>"+
			"<td>:delete</td>";

    "</tr>";

var titleListItem="<tr>" +
    "<td>:title</td>" +
    "<td>:type</td>" +
    "<td>:anser</td>" +
    "<td><a class='delTitleStatus' href='javascript:void(0)'>删除</a>&nbsp;&nbsp;<a class='viewTitleStatus' href='javascript:void(0)'>查看</a></td>" +
    "</tr>";



var submitAnserList=[];

var titleList=[];

function delTitle(index){
    titleList.splice(index,1);
    $('#titleTable>tbody tr:eq('+index+')').remove();
}

function viewTitle(index){
  $("#title","#viewTitle").val(titleList[index].title);
  $("#type","#viewTitle").selectpicker('val', titleList[index].type);
   var submitAnserList= titleList[index].anserList;
   for(var i=0;i<submitAnserList.length;i++){
       var item=anserListItem;
       item=item.replace(":option",submitAnserList[i].option).replace(":score",submitAnserList[i].score).
       replace(":content",submitAnserList[i].content).replace(":checkbox","<input type='checkbox'"+ (submitAnserList[i].trueAnser?' checked':'')+">")
		   .replace(":delete","");


       $("#ansersTable>tbody","#viewTitle").append(item);
   }


    layer.open({
        title:'查看题目',
        type: 1,
        skin: 'layui-layer-rim', //加上边框
        area: ['80%', '80%'], //宽高
        content:$('#viewTitle'),
        end: function(){ //此处用于演示
            $("#ansersTable>tbody","#viewTitle").empty();
            $("#title","#viewTitle").val('');
            $("#type","#viewTitle").val('1');
        }
    });

}

$(function(){
    for(var i=0;i<26;i++) {
        anserList.push({option:String.fromCharCode(65 + i),score:0,content:'',trueAnser:false});
    }

	$("#titleTable>tbody").delegate("a.delTitleStatus",'click','',function(){
					var that=this;
					var tr=$(that).parent().parent()
					titleList.splice($("#titleTable>tbody tr").index($(tr)),1);
					$(tr).remove();
	});



	$("#titleTable>tbody").delegate("a.viewTitleStatus",'click','',function(){
		var that=this;
		var tr=$(that).parent().parent()
		var index=$("#titleTable>tbody tr").index($(tr));
		$("#title","#viewTitle").val(titleList[index].title);
		$("#type","#viewTitle").selectpicker('val', titleList[index].type);
		var submitAnserList= titleList[index].anserList;
		for(var i=0;i<submitAnserList.length;i++){
			var item=anserListItem;
			item=item.replace(":option",submitAnserList[i].option).replace(":score",submitAnserList[i].score).
			replace(":content",submitAnserList[i].content).replace(":checkbox","<input type='checkbox'"+ (submitAnserList[i].trueAnser?' checked':'')+">")
				.replace(":delete","");


			$("#ansersTable>tbody","#viewTitle").append(item);
		}


		layer.open({
			title:'查看题目',
			type: 1,
			skin: 'layui-layer-rim', //加上边框
			area: ['80%', '80%'], //宽高
			content:$('#viewTitle'),
			end: function(){ //此处用于演示
				$("#ansersTable>tbody","#viewTitle").empty();
				$("#title","#viewTitle").val('');
				$("#type","#viewTitle").val('1');
			}
		});



	});


    $("#addAnser").click(function () {
        if(anserListIndex>=anserList.length-1){
            alert('添加的选项超出限制');
        }
        var item=anserListItem;
        var list=anserList;


        if((anserListIndex-1)>=0){
        	$("a",$("#ansersTable tbody>tr:eq("+(anserListIndex-1)+")").find("td:eq(4)")).remove();
        	if((anserListIndex-1)>0){
				//$("#ansersTable tbody>tr:eq("+(anserListIndex-1)+")").find("td:eq(3)").append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
			}

		}

        item=item.replace(":option",list[anserListIndex].option).replace(":score",list[anserListIndex].score).
        replace(":content",list[anserListIndex].content).replace(":checkbox","<input type='checkbox'>");

        if(anserListIndex>0){
			item=item.replace(":delete","<a href='javascript:delAnswer("+anserListIndex+")'>删除</a>");
		}else{
			item=item.replace(":delete","");
		}

        $("#ansersTable tbody","#addShow").append(item);
        anserListIndex++;
    });


    $("#titleBack").click(function () {
        layer.closeAll();
    });
    $("#viewBack").click(function () {
        layer.closeAll();
    });


    $("#titleSubmit").click(function () {
    	var flag=true;
    	if(!$("#title","#addShow").val()){
			layer.msg("请输入题目标题");
			$("#title","#addShow").focus();
			return
		}

        var itemList=$("#ansersTable>tbody tr");

        if(itemList.size()>0){
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

            if($("#type","#addShow").val()=='1' && (anserStr.length<=0 || anserStr.length>1)){
				layer.msg("单选题必须且只能勾选一项为正确答案");
                return;
            }else if($("#type","#addShow").val()=='2' && anserStr.length<=1){
				layer.msg("多选题至少需要勾选两项及以上为正确答案");
                return;
            }


            $(itemList).each(function(index,item){
                var tds=$(item).find("td");
                submitAnserList.push({option:$(tds[0]).text(),
					content:$("input[type='text']",$(tds[1])).val(),
					score:$("input[type='text']",$(tds[2])).val(),
                    trueAnser:$("input[type='checkbox']",$(tds[3])).is(':checked')});
            });



            titleList.push({title:$("#title","#addShow").val(),type:$("#type","#addShow").val(),
				anserList:submitAnserList,trueAnser:anserStr.join('、')});
            var titleItem=titleListItem;
            titleItem=titleItem.replace(":title",$("#title","#addShow").val()).replace(":type",($("#type","#addShow").val()=='1'?'单选题':'多选题')).
            replace(":anser",anserStr.join('、')).replace(":index",titleList.length-1).replace(":index",titleList.length-1);

            //:index

            $("#titleTable tbody").append(titleItem);
			layer.closeAll();
        }else{
            //alert('没有选项')
			layer.msg("请添加答案选项");
        }

    });


	var params=GetUrlParms();
	var type=params.type;
	initUeditor();
	resultAnalyseUeditor.addListener( 'ready', function( editor ) {
		if(type=='upd'){
			initUpdEvent(params.id);
		}else{
			initAddEvent();
		}
	});
	initEvent();
	$("#submit").click(function(){
		addPaper();
	});

	$("#addTitle").click(function(){
        anserListIndex=0;
        layer.open({
            title:'新增题目',
            type: 1,
            skin: 'layui-layer-rim', //加上边框
            area: ['80%', '80%'], //宽高
            content:$('#addShow'),
            end: function(){ //此处用于演示
                $("#ansersTable>tbody").empty();
                $("#title","#addShow").val('');
                $("#type","#addShow").selectpicker('val', ['1']);
				submitAnserList=[];
            }
    });
    });






});

function delAnswer(index){
	$("#ansersTable tbody>tr:eq("+index+")").remove();
	--anserListIndex;
	if(index>1){
		--index
		$("#ansersTable tbody>tr:eq("+(index)+")").find("td:eq(4)").append("<a href='javascript:delAnswer("+(index)+")'>删除</a>")
	}

}
//修改绑定事件
function initUpdEvent(id){
	$("#title").html('修改问卷');
	writeData(id);
}

//新增绑定事件
function initAddEvent(){
	$("#title").html('新增问卷');

}

function initEvent(){
	//时间控件
	$("#startDate").datetimepicker({
		format: "YYYY-MM-DD HH:mm:ss"
	});
	//select初始化
	$('select').selectpicker();
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
		//window.history.go(-1);
		var fromurl = document.referrer;
		if(fromurl){
			location.href=fromurl;
		}else{
			window.history.back();
		}
	});
}

//写入数据
function writeData(id){
	$.ajax({
		type:'post',
		url:getAdvertUrl,
		data:{id:id},
		success:function(data){
			if(data.flag){
				//资讯选项赋值-s___zgl0901
				$("#type").html("");
				for(var i=0;i<data.classfiys.length;i++){
					var t=data.classfiys[i];
					var $type=$("<option value='"+t.id+"'>"+t.name+"</option>");
					$("#type").append($type);
					$("#type").selectpicker("refresh");//刷新
				}
				//资讯选项赋值-e___zgl0901
				var advert=data.data;
				$("#advert_title").val(advert.title);
				$("#author").val(advert.author);
				$("#startDate").val(advert.startDate);
				$("#type").val(advert.type);
				$("#type").selectpicker("refresh");//刷新资讯选项___zgl0901
				$("#abstracts").val(advert.abstracts);
				ueditor.setContent(advert.content);
				
				$("#submit").one('click',function(){
					updAdvert(advert.id);
				});
			}
		},
		error:function(err){
			
		}
	});
}

//检查参数
function getParams(type){
	//input 数据
/*	var advert={
		title:$("#advert_title").val(),
		startDate:$("#startDate").val(),
		author:$("#author").val()
	};
	
	for(var key in advert){
		if(advert[key]==null||advert[key]==''){
			if(key=='title'){
				$("#advert_title").focus()
			}else{
				$("#"+key).focus();
			}
			
			return;
		}
	}
	
	advert.type=$("#type").val();
	
	var abstracts=$("#abstracts").val();
	if(abstracts==null||abstracts==''){
		Alert('摘要不能为空');
		return null;
	}
	advert.abstracts=abstracts;
	var content=ueditor.getContent();
	if(content==null||content==''){
		Alert('资讯内容不能为空');
		return null;
	}
	advert.content=content;
	
	return advert;*/

var mainTest={
	title:$("#studyTitle").val(),
	type:$("#studyType").val(),
	remark:$("#remark").val(),
	resultAnalyse:resultAnalyseUeditor.getContent()
};
if(!mainTest.title){
	$("#studyTitle").focus();
	layer.msg("请输入标题");
	return;
}else if(!mainTest.type){
	layer.msg("试卷类型不存在");
	return;
}else if(!titleList || titleList.length<=0){
	layer.msg("题目至少需要添加一题");
	return;
}

var params={
	mainTest:mainTest,
	testTitles:titleList
};
return params;

}

//新增方法
function addPaper(){
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
		url:addWordUrl,
		success:function(data){
			if(data.code==1){
				Alert({
					msg:'新增成功',
					onOk:function(){
						window.location.href='testList.html?type='+$("#studyType").val();
					}
				});
			}else{
				Alert("新增失败");
			}
			layer.close(index)
		},
		error:function(){
			Alert("服务器繁忙,请稍后再试!");
			layer.close(index)
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
					msg:data.msg,
					onOk:function(){
						
					}
				});
			}else{
				Alert({
					msg:data.msg,
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
               /* 'insertvideo',//视频*/
                'kityformula',
                /*'insertimage',*/
                'attachment'
            ]
        ]
    });



};