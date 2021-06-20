
/**
 * 表单验证
 * 例子：html <input id="addUserName" type="text" class="form-control" placeholder="用户姓名"  name="userName"
			data-options="required:true,length:10,validType:isRightfulString,"   
			data-toggle="popover" data-placement="top" 
			data-content="请输入用户姓名">
	验证:  	required:true	(必填),
			length:10		(字符长度),
			validType:isRightfulString	(isRightfulString--为验证方法)
   信息提示：	data-toggle="popover" 
   			data-placement="top" 
			data-content="请输入用户姓名"
	   JS应用：var result = formValidator.init("insertUser");    //insertUser  form表单ID
			 if(!result.flag){
				$(result.dom).attr("data-content",result.message);
				$(result.dom).popover('toggle');
				$(result.dom).focus();
				return;
			 }
	 
 */
var getByCodeUrl="getUserByCode.html";
function formValidator(){
	this.message = "";
	this.flag = false;
	this.returnData = {"flag":this.flag,"message":this.message,dom:""};
	
	/**
	 * 表单验证
	 * formTags   form表单ID
	 * 
	 */
	formValidator.prototype.init = function (formTags){
		var dataOptions = $("#"+ formTags +" [data-options]");
		var dataoption;
		for(var i=0;i<dataOptions.length;i++){
			var dop = "{"+$(dataOptions[i]).attr("data-options")+"}";
			dataoption = $(dataOptions[i]);
			var data =$(dataOptions[i]).val();
			var dopJson =  eval("(" + dop + ")");
			var result = "";
			$.each(dopJson,function(key,value) {
				if(key=="required"){
					if(value){
						result = formValidator.required(value,data);
						if(!result.flag){
							return false;
						}
					}
				}
				if(key=="validType"){
					result = formValidator.validType(data,value);
					if(!result.flag){
						return false;
					}
				}
				if(key=="length"){
					result = formValidator.length(data,value);
					if(!result.flag){
						return false;
					}
				}
			});
			if(result!=null&&!result.flag){
				break;
			}
		}
		this.returnData.flag=result.flag;
		this.returnData.message = result.message;
		this.returnData.dom = dataoption;
		return this.returnData;
	};
	formValidator.prototype.required = function (val,data){
		var returnData = {"flag":false,"message":"必填"};
		this.flag = false;
		this.message = "";
		if(data==null||data==""){
			this.flag = false;
			this.message = "必填";
		}else{
			this.flag = true;
			this.message = "";
		}
		returnData.flag = this.flag;
		returnData.message = this.message;
		return returnData;
	};
	formValidator.prototype.validType = function (data,callback){
		return callback(data);
	};
	formValidator.prototype.length = function (data,length){
		 var returnData = {"flag":false,"message":"请输入小于"+length+"个字!"};
		 if(data.length > 0 && data.length < length){
			 returnData.flag=true;
			 returnData.message="";
		 }
		 return returnData;
	};

}
var formValidator = new formValidator();



/*****************************************************************
						校验工具类  (linjq)       
*****************************************************************/

/**
 * 邮箱验证
 */
function email(str){
	 var returnData = {"flag":false,"message":"请输入正确的email!"};

	 var result=str.match(/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/);
	 if(result==null){
		 return returnData;
	 }
	 returnData = {"flag":true,"message":""};
	 return returnData;
}

/**
 * 字符长度
 * @param val
 * @param length
 * @returns {___anonymous3314_3358}
 */
function strLength(val,length){
	 var returnData = {"flag":false,"message":"请输入小于"+length+"个字!"};
	 if(str!=null||str!="") {
		 if(val.length<0&&val.length>=length){
			 returnData.flag=true;
			 returnData.message="";
		 }
	 }
	 return returnData;
}

/**
 * 判断是否为合法字符(a-zA-Z0-9-_)
 */
function isRightfulString(str){
	var returnData = {"flag":false,"message":"只能输入字母数字下划线!"};
	var result=str.match(/^[a-zA-Z0-9_]{1,}$/);
	if(result!=null){
		returnData.flag=true;
	}
	return returnData;
}

function isRightfulString1(str){
	var returnData = {"flag":false,"message":"只能输入字母数字下划线!"};
	var result=str.match(/^[a-zA-Z0-9_]{1,}$/);
	if(result!=null){
		returnData.flag=true;
	}
	returnData.flag=true;
	return returnData;
}
/*
* 判断账号是否重复
* */
function checkUser(code) {
    var returnData = {"flag":false,"message":"只能输入字母数字下划线!"};
    var result=code.match(/^[a-zA-Z0-9_]{1,}$/);
    if(result==null){
        return returnData;
    }else {
        returnData.message ="此登录账号已存在";
        $.ajax({
            async:false,
            type:'post',
            data:{code:code},
            url:getByCodeUrl,
            success:function(data){
                if(data==null||data==""){
                    returnData.flag=true;
                }
            },
            error:function(err){
                console.log("data:"+err);
            }
        });
        return returnData;
	}

}
/**
 * 匹配密码，以字母开头，长度在6-12之间，只能包含字母、字符、数字和下划线。
 */
function isPwd(str){
	var returnData = {"flag":false,"message":"以字母开头，长度在8-20之间，只能包含字母、数字和下划线!"};
    var result=str.match(/^[a-zA-Z][0-9 | A-Z | a-z | \_]{7,19}$/);
    if(result!=null){
    	returnData.flag=true;
    }
    return returnData;
}
 
/**
 * 匹配phone
 */
function isPhone(str){
	var returnData = {"flag":true,"message":""};
	if(str!=null&&str!=''){
		var result=str.match(/^1[345789]\d{9}$/);
		if(result==null){
			returnData.flag=false;
			returnData.message="请输入正确的电话号码!";
		}
	}
    return returnData;
}

/**
 * 判断整数num是否等于0
 * 
 * @param num
 * @return
 * @author jiqinlin
 */
function isIntEqZero(num){
	var returnData = {"flag":false,"message":"不等于0"};
	 if(num==0){
		returnData.message="等于0";
	    returnData.flag=true;
	 }
	 return returnData;
}


/**
 * 判断整数num是否大于0
 * 
 * @param num
 * @return
 * @author jiqinlin
 */
function isIntGtZero(num){ 
	var returnData = {"flag":false,"message":"小于0"};
	 if(num>0){
		returnData.message="大于0";
	    returnData.flag=true;
	 }
	 return returnData;
}

/**
 * 判断整数num是否大于或等于0
 * 
 * @param num
 * @return
 * @author jiqinlin
 */
function isIntGteZero(num){ 
    var returnData = {"flag":false,"message":"小于0"};
	 if( num>=0){
		returnData.message="大于等于0";
	    returnData.flag=true;
	 }
	 return returnData;
}

/**
 * 匹配money
 */
function isMoney(str){
	var returnData = {"flag":true,"message":""};
    if(str!=null||str!="") {
    	var result=str.match(/^(([1-9]\d*)|(([0-9]{1}|[1-9]+)\.[0-9]{1,2}))$/);
    	if(result==null){
        	returnData.flag=false;
        	returnData.message="请输入正确金额!";
        }
    }
    return returnData;
} 

/**
 * 只能输入数字[0-9]
 */
function isDigits(str){
    var returnData = {"flag":true,"message":""};
    if(str!=null||str!="") {
    	var result=str.match(/^\d+$/);
    	if(result==null){
        	returnData.flag=false;
        	returnData.message="只能输入数字[0-9]!";
        }
    }
    return returnData;
}

/**
 * 匹配中文(包括汉字和字符)
 */
function isChinaChar(str){
	var returnData = {"flag":true,"message":""};
    if(str!=null||str!="") {
    	var result=str.match(/^[a-zA-Z\u4e00-\u9fa5]+$/);
    	if(result==null){
        	returnData.flag=false;
        	returnData.message="只能输入中英文字符!";
        }
    }
    return returnData;
} 


/*****************************************************************
                  表单校验工具类  (linjq)       
*****************************************************************/
 





/**
 * 判断浮点数num是否等于0
 * 
 * @param num 浮点数
 * @return
 * @author jiqinlin
 */
function isFloatEqZero(num){ 
    return num==0;
}

/**
 * 判断浮点数num是否大于0
 * 
 * @param num 浮点数
 * @return
 * @author jiqinlin
 */
function isFloatGtZero(num){ 
    return num>0;
}

/**
 * 判断浮点数num是否大于或等于0
 * 
 * @param num 浮点数
 * @return
 * @author jiqinlin
 */
function isFloatGteZero(num){ 
    return num>=0;
}



/**
 * 判断数值类型，包括整数和浮点数
 */
function isNumber(str){
    if(isDouble(str) || isInteger(str)) return true;
    return false;
}     
    

    
///**
// * 匹配phone
// */
//function isPhone(str){
//    if(str==null||str=="") return false;
//    var result=str.match(/^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/);
//    if(result==null)return false;
//    return true;
//}

/**
 * 匹配mobile
 */
function isMobile(str){
    if(str==null||str=="") return false;
    var result=str.match(/^((\(\d{2,3}\))|(\d{3}\-))?((13\d{9})|(15\d{9})|(18\d{9}))$/);
    if(result==null)return false;
    return true;
}     

/**
 * 联系电话(手机/电话皆可)验证   
 */
function isTel(text){
    if(isMobile(text)||isPhone(text)) return true;
    return false;
}

/**
 * 匹配qq
 */
function isQq(str){
    if(str==null||str=="") return false;
    var result=str.match(/^[1-9]\d{4,12}$/);
    if(result==null)return false;
    return true;
}     

/**
 * 匹配english
 */
function isEnglish(str){
    if(str==null||str=="") return false;
    var result=str.match(/^[A-Za-z]+$/);
    if(result==null)return false;
    return true;
}     

/**
 * 匹配integer
 */
function isInteger(str){
    if(str==null||str=="") return false;
    var result=str.match(/^[-\+]?\d+$/);
    if(result==null)return false;
    return true;
}     

/**
 * 匹配double或float
 */
function isDouble(str){
    if(str==null||str=="") return false;
    var result=str.match(/^[-\+]?\d+(\.\d+)?$/);
    if(result==null)return false;
    return true;
}     


/**
 * 匹配邮政编码
 */
function isZipCode(str){
    if(str==null||str=="") return false;
    var result=str.match(/^[0-9]{6}$/);
    if(result==null)return false;
    return true;
} 

/**
 * 匹配URL
 */
function isUrl(str){
    if(str==null||str=="") return false;
    var result=str.match(/^http:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\’:+!]*([^<>\"])*$/);
    if(result==null)return false;
    return true;
} 



/**
 * 匹配english
 */
function isEnglish(str){
    if(str==null||str=="") return false;
    var result=str.match(/^[A-Za-z]+$/);
    if(result==null)return false;
    return true;
} 

/**
 * 匹配身份证号码
 */
function isIdCardNo(num) {
	// if (isNaN(num)) {alert("输入的不是数字！"); return false;}
	var len = num.length, re;
	if (len == 15)
		re = new RegExp(/^(\d{6})()?(\d{2})(\d{2})(\d{2})(\d{2})(\w)$/);
	else if (len == 18)
		re = new RegExp(/^(\d{6})()?(\d{4})(\d{2})(\d{2})(\d{3})(\w)$/);
	else {
		alert("输入的数字位数不对。");
		return false;
	}
	var a = num.match(re);
	if (a != null) {
		if (len == 15) {
			var D = new Date("19" + a[3] + "/" + a[4] + "/" + a[5]);
			var B = D.getYear() == a[3] && (D.getMonth() + 1) == a[4]
					&& D.getDate() == a[5];
		} else {
			var D = new Date(a[3] + "/" + a[4] + "/" + a[5]);
			var B = D.getFullYear() == a[3] && (D.getMonth() + 1) == a[4]
					&& D.getDate() == a[5];
		}
		if (!B) {
			alert("输入的身份证号 " + a[0] + " 里出生日期不对。");
			return false;
		}
	}
	if (!re.test(num)) {
		alert("身份证最后一位只能是数字和字母。");
		return false;
	}

	return true;
} 

/**
 * 匹配汉字
 */
function isChinese(str){
    if(str==null||str=="") return false;
    var result=str.match(/^[\u4e00-\u9fa5]+$/);
    if(result==null)return false;
    return true;
} 

/**
 * 匹配中文(包括汉字和字符)
 */
function isChineseChar(str){
    if(str==null||str=="") return false;
    var result=str.match(/^[\u0391-\uFFE5]+$/);
    if(result==null)return false;
    return true;
}     

/**
 * 字符验证，只能包含中文、英文、数字、下划线等字符。
 */
function stringCheck(str){
    if(str==null||str=="") return false;
    var result=str.match(/^[a-zA-Z0-9\u4e00-\u9fa5-_]+$/);
    if(result==null)return false;
    return true;
}     

/**
 * 过滤中英文特殊字符，除英文"-_"字符外
 */
function stringFilter(str){
    var pattern = new RegExp("[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]");
    var rs = "";
    for (var i = 0; i < str.length; i++) {
        rs = rs + str.substr(i, 1).replace(pattern, '');
    }
    return rs;
} 

/**
 * 判断是否包含中英文特殊字符，除英文"-_"字符外
 */
function isContainsSpecialChar(str){
    if(str==null||str=="") return false;
    var reg = RegExp(/[(\ )(\`)(\~)(\!)(\@)(\#)(\$)(\%)(\^)(\&)(\*)(\()(\))(\+)(\=)(\|)(\{)(\})(\')(\:)(\;)(\')(',)(\[)(\])(\.)(\<)(\>)(\/)(\?)(\~)(\！)(\@)(\#)(\￥)(\%)(\…)(\&)(\*)(\（)(\）)(\—)(\+)(\|)(\{)(\})(\【)(\】)(\‘)(\；)(\：)(\”)(\“)(\’)(\。)(\，)(\、)(\？)]+/);   
    return reg.test(str);    
}
/**
 * 判断是否包含全角 True 没有全角，False有全角
 */
function isFullWidth(str)//True 没有全角，False有全角
{
    for (var i = 0; i < str.length; i++)
    {
        strCode = str.charCodeAt(i);
        if ((strCode > 65248) || (strCode == 12288))
        {
            return false;
        }
    }
    return true;
}