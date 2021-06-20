
$(document).ready(function(){
//	var bind_name="input";//定义所要绑定的事件名称
//    if(navigator.userAgent.indexOf("MSIE")!=-1) bind_name="propertychange";//判断是否为IE内核 IE内核的事件名称要改为propertychange
//    /*输入框键盘离开事件绑定*/
//    $("input").bind(bind_name,function(){
//    	console.log(this.name);
////    	if(this.name=="chineseinput"){
////    		$("#clearDiv").hide();
////    		return;
////    	}
//        if(this.value!=null&&this.value!=""){
//            var inputWidth=$(this).outerWidth();
//            var inputHeight=$(this).outerHeight();
//            var inputOffset =  $(this).offset();
//            var inputTop=inputOffset.top;
//            var inputLeft=inputOffset.left;
//            $("#clearDiv").css({top:inputTop+2,left:inputLeft+inputWidth-27}).show();
//            inputObj=this;
//        }else{
//            $("#clearDiv").hide();
//        }
//    });
    
	initNumber();
	initOnumber();
	initPercentNumber();
	initChar();
	
//	var objs=document.getElementsByName("chineseinput");
//	for(var i=0;i<objs.length;i++){
// 		$(objs[i]).unbind(bind_name);
// 		
//	}
	initChinese();
});

function initOnumber(){
	var objs=document.getElementsByName("onumber");
	for(var i=0;i<objs.length;i++){
 		$(objs[i]).keyup(oNumberEvent);
 		//(objs[i]).oncontextmenu=function(){console.log("select"); return false;};
 		//(objs[i]).onmouseup=function(){console.log("select"); return false;};
	}
}

function initPercentNumber(){
	var objs=document.getElementsByName("percent");
	for(var i=0;i<objs.length;i++){
 		$(objs[i]).keyup(function(){
			var oldvalue=$(this).val();
			var newvalue='';
			for(var i=0;i<oldvalue.length;i++){
				if(i==0){
					if(oldvalue[i]>='1'&&oldvalue[i]<='9'){
						newvalue+=oldvalue[0];
					}
				}
				else if(i==1){
					if(oldvalue[i]>='0'&&oldvalue[i]<='9'){
						newvalue+=oldvalue[1];
					}
				}
				else if(i==2){
					if(oldvalue.substr(0,3)=='100'){
						newvalue+=oldvalue[2];
					}
				}
				else{
					break;
				}
			}	
			$(this).val(newvalue);	
		});
	}
}

function initNumber(){
	var objs=document.getElementsByName("number");
	for(var i=0;i<objs.length;i++){
 		$(objs[i]).keyup(numberEvent);
	}
}

function oNumberEvent(){
	var oldvalue=$(this).val();
	var newvalue='';
	var pointnum=0;
	for(var i=0;i<oldvalue.length;i++){
		if(oldvalue[i]>='0'&&oldvalue[i]<='9'){
			if(oldvalue[i]=='0'&&i==0){
				continue;
			}
			newvalue+=oldvalue[i];
		}
	}	
	$(this).val(newvalue);
}

function numberEvent(){
	var oldvalue=$(this).val();
	var newvalue='';
	var pointnum=0;
	if(oldvalue.length==1){
		if(oldvalue[0]>='0'&&oldvalue[0]<='9'){
			newvalue+=oldvalue[0];
		}
	}else if(oldvalue.length==2){
		newvalue+=oldvalue[0];
		if(oldvalue[0]=='0'){
			if(oldvalue[1]=='.'){
				newvalue+='.';
			}
			else if(oldvalue[1]>='0'&&oldvalue[1]<='9'){
				newvalue=oldvalue[1];
			}
		}else if(oldvalue[0]>='1'&&oldvalue[0]<='9'&&oldvalue[1]>='0'&&oldvalue[1]<='9'||oldvalue[1]=='.'){
			newvalue+=oldvalue[1];
		}else{
			if(oldvalue[1]>='0'&&oldvalue[1]<='9'){
				newvalue=oldvalue[1];
			}
		}
	}else{
		for(var i=0;i<oldvalue.length;i++){
			if(newvalue.length==1&&oldvalue[0]>='0'&&oldvalue[0]<='9'){
				if(oldvalue[i]!='.'){
					newvalue+=oldvalue[i];
				}
				else{
					newvalue+=oldvalue[i];
					pointnum++;
				}
				continue;
			}
			if(oldvalue[i]=='.'){
				pointnum++;
			}
			if(pointnum<=1){
				if((oldvalue[i]>='0'&&oldvalue[i]<='9')||oldvalue[i]=='.'){
					newvalue+=oldvalue[i];
				}
			}else{
				if(oldvalue[i]>='0'&&oldvalue[i]<='9'){
					newvalue+=oldvalue[i];
				}
			}
		}
	}	
	$(this).val(newvalue);	
}

function initChar(){
	var objs=document.getElementsByName("charinput");
	for(var i=0;i<objs.length;i++){
 		$(objs[i]).keyup(charEvent);
 		
	}
}

function charEvent(){
	var oldValue=$(this).val();
	var newValue='';
	for(var i=0;i<oldValue.length;i++){
		if((oldValue[i]>='a'&&oldValue[i]<='z')||(oldValue[i]>='A'&&oldValue[i]<='Z')||oldValue[i]=='_'){
			newValue+=oldValue[i];
		}
	}
	$(this).val(newValue);
}

function initChinese(){
	var objs=document.getElementsByName("chineseinput");
	for(var i=0;i<objs.length;i++){
 		$(objs[i]).keyup(chineseEvent);
 		
	}
}
function chineseEvent(){
	var oldValue=$(this).val();
	var newValue='';
	for(var i=0;i<oldValue.length;i++){
		if (oldValue[i].match(/^[\u4e00-\u9fa5]/)){
			newValue+=oldValue[i];
			console.log(newValue);
		}
	}
	
	$(this).val(newValue);
}
//function isChinese(temp)
//2	{
//3	  var re=/[^/u4e00-/u9fa5]/;
//4	  if (re.test(temp)) return false ;
//5	  return true ;
//6	}




