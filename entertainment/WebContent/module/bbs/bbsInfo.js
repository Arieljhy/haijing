/**
 * 全局变量
 */
var serverURL='addFile.html';
var updTopicURL='updBbs.html';
var findTopicOneURL='getBbsById.html';
var	getClassifyIdUrl="getClassify.html";
var	uploadUrl="upload.html";
//获取板块列表
function getClassifyId(a){
	$.ajax({
		url:getClassifyIdUrl,
		data:{},
		type:'post',
		success:function(data){
			$.each(data,function(n,value){
				if(a==value.id){
					$("#section").append('<option	 selected="selected" value="'+value.id+'">'+value.name+'</option>');
				}else{
					$("#section").append('<option value="'+value.id+'">'+value.name+'</option>');
				}
			});
		},
		error:function(){
			
		}
	});
}

$(function(){
	
	var params=GetUrlParms();
	bindBtn(params);
	initData(params);
	upload();
	//xmlBtn 打开文件域
	$("#xmlBtn").click(function(){
		$("#file").click();
	});
	
});

function initData(params){
		
		$.ajax({
			type:'post',
			async:false,
			url:findTopicOneURL,
			data:{id:params.id},
			success:function(data){
				var bbsId=data.bbsId;
				var type=data.type;
				hideType(type);
				if(type=="1"){
					$("#htmlTitle").html("帖子标题："+data.title);
				}else{
					$("#htmlTitle").html("回帖修改");
				}
				getClassifyId(data.classifyId);
				$("#BbsId").val(bbsId);
				$("#type").val(type);
				$("#title").val(data.title);
				$("#content").val(data.content);
				console.log(data);
				//alert(data);
				//alert(data.imageList);
				//alert(data.imageList+"0909");
				var	image=data.imageList;
				$("#imageList").val(image);
				//alert(image+"imagel");
				//alert($("#imageList").val()+"val");
				$("#imageUl").append(setImage(image));
			},
			error:function(){
				
			}
		});
	
	
}





//上传文件
function upload(){
	var	oldStr=$("#imageList").val();

		$("#file").change(function(){
        var name = '';
        var fd = new FormData();
        var i=0;
        var files = this.files;
      fd.append("upload", files.length);
        for(i=0;i<files.length;i++){
            var file = files[i];
            if(name=='')
            	name = file.name;
            else
            	name = name +','+file.name;
            size = file.size;
            type = file.type;
            /*if(type!="text/xml"){
            	alert("文件格式不匹配，只支持后缀为xml格式的文件！");
            	return ;
            }*/
            fd.append("upfile", $("#file").get(0).files[i]);
        }
        $.ajax({
                url: uploadUrl,
                type: "POST",
                processData: false,
                contentType: false,
                data: fd,
                success: function(data) {
                	data=$.parseJSON(data);
                   if(data.succeed){//上传成功
                	  // alert(oldStr);
                	   var	image="";
					   var	oldStr=$("#imageList").val();
                	   console.log(oldStr);
                	   console.log("oldStr");
					   oldStrLen=oldStr.split(",");
                	 if(oldStrLen.length==0){
                		 image= oldStr+data.relativePaths;
                	 }else{
                		 image= oldStr+","+data.relativePaths;
                	 }
					   if(image.substring(0, 1) == ","){
						   console.log(true);
						   image = image.substr(1);
					   }
                	  //var	image= 
                	  //alert(image);
                	  $("#imageList").val(image);
                	   //alert(image);
                	   //$("#imageUl").append(setImage(data.relativePaths));
                	   $("#imageUl").append(addImage(data.relativePaths));
					   // $("ul").find("li").remove();
					   // var imageArray=image.split(",");
					   // for(var i=0;i<imageArray.length;i++){
						//    a=imageArray[i].lastIndexOf('.');
						//    type=imageArray[i].substring(a+1);
						//    if(type=="bmp"||type=="png"||type=="gif"||type=="jpg"||type=="jpeg"){
						// 	   str+='<li	style="list-style:none;display:inline-block;float:left;margin-right:15px;"><img	style="width:160px;height:160px;" src="'+imageArray[i]+'"><a	href="javascript:delImage('+i+');">删除</a></li>';
						//    }else	if(type=="mp4"||type=="rmvb"||type=="avi"||type=="ogg"||type=="wmv"){
						// 	   str+='<li	style="list-style:none;display:inline-block;float:left;margin-right:15px;"><video	style="width:320px;height:160px;" src="'+imageArray[i]+'"	controls autobuffer></video><a	href="javascript:delImage('+i+');">删除</a></li>';
						//    }
					   // }
					   // $("#imageUl").append(str);


                	}else{
                	   alert("上传失败！");
                	   return;
                   }
                }
        });
        
});
}


function bindBtn(params){
	//getClassifyId();
	$("#backBtn").bind("click",function(){
		window.history.go(-1);
	});
	
	$("#sumbitBtn").bind("click",function(){
		updTopic(params.id);
	});
	
	
}

function updTopic(id){
	var type=$("#type").val();
	var title=$("#title").val();
	var sectionId =0;
	if(type=="1"){
		sectionId =$("#section").val();
	}
	var content=$("#content").val();
	
	var data={
		id:id,
		classifyId:sectionId,
		title:title,
		content:content,
		imageList:$("#imageList").val()
	};
	$.ajax({
		type:'post',
		url:updTopicURL,
		data:data,
		success:function(data){
			
			if(data.flag){
				messageWin.alertback("提示", data.message,function(){
					window.history.go(-1);
				});
				
			}else{
				messageWin.alert("提示", data.message);
			}
		}
	});
}


function hideType(type){
	if(type=="1"){
		$("#hide1").show();
		$("#hide2").show();
	}else{
		$("#hide1").hide();
		$("#hide2").hide();
	}
}

function	setImage(imageList){

	var	oldStr=$("#imageList").val();
		if(typeof(imageList)=="undefined"||imageList==undefined||imageList==""||imageList==null){
			return	"";
		}else{
			var	oldStr=$("#imageList").val();
			var len=oldStr.length;
			//var	imageArray=imageList.split(",");
			var	imageArray=oldStr.split(",");
			str='';
			var	a,type;
			$("ul").find("li").remove();//表示删除所有的li
			for(var i=0;i<imageArray.length;i++){
				a=imageArray[i].lastIndexOf('.');
				type=imageArray[i].substring(a+1);
				if(type=="bmp"||type=="png"||type=="gif"||type=="jpg"||type=="jpeg"){
					str+='<li	style="list-style:none;display:inline-block;float:left;margin-right:15px;"><img	style="width:160px;height:160px;" src="'+imageArray[i]+'"><a	href="javascript:delImage('+i+');">删除</a></li>';
				}else	if(type=="mp4"||type=="rmvb"||type=="avi"||type=="ogg"||type=="wmv"){
					str+='<li	style="list-style:none;display:inline-block;float:left;margin-right:15px;"><video	style="width:320px;height:160px;" src="'+imageArray[i]+'"	controls autobuffer></video><a	href="javascript:delImage('+i+');">删除</a></li>';
				}
			}
			return	str;
		}
};
function	addImage(imageList){

	var	oldStr=$("#imageList").val();
		if(typeof(imageList)=="undefined"||imageList==undefined||imageList==""||imageList==null){
			return	"";
		}else{
			var	oldStr=$("#imageList").val();
			var len=oldStr.length;
			var	imageArray=imageList.split(",");
			//var	imageArray=oldStr.split(",");
			str='';
			var	a,type;
			var num=$(".inputgroup ul li").length;
			for(var i=0;i<imageArray.length;i++){
				a=imageArray[i].lastIndexOf('.');
				type=imageArray[i].substring(a+1);
				if(type=="bmp"||type=="png"||type=="gif"||type=="jpg"||type=="jpeg"){
					str+='<li	style="list-style:none;display:inline-block;float:left;margin-right:15px;"><img	style="width:160px;height:160px;" src="'+imageArray[i]+'"><a	href="javascript:delImage('+num+');">删除</a></li>';
				}else	if(type=="mp4"||type=="rmvb"||type=="avi"||type=="ogg"||type=="wmv"){
					str+='<li	style="list-style:none;display:inline-block;float:left;margin-right:15px;"><video	style="width:320px;height:160px;" src="'+imageArray[i]+'"	controls autobuffer></video><a	href="javascript:delImage('+i+');">删除</a></li>';
				}
			}
			return	str;
		}
};

function	delImage(n){
	var	imageList=$("#imageList").val();
	var	imageArray=imageList.split(",");
	imageArray.splice(n,1);
	var	a="";
	if(imageArray.length==0){
		a="";
	}else{
		a=imageArray.join(",");
		//$("#imageList").val(imageArray.join(","));
	}
	$("#imageList").val(a);
	$("#imageUl li:eq("+n+")").remove();
	$("#imageUl").append(setImage($("#imageList").val()));


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






