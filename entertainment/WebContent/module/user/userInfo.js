var addUserUrl='addUser.html';
var updUserUrl='updUser.html';
var validateUserCode='getUserByCode.html';
var uploadFileUrl='../file/upload.html';
var configUrl='../';
var iconRootUrl='../';
var checkCertificateCode='checkCertificateCode.html';


$(function(){
	var params=GetUrlParms();
	var type=params.type;
	if(type=='upd'){
		initUpdEvent(params.code);
	}else{
		initAddEvent();
	}

});

//默认事件
function initEvent(){
	//时间控件
	$("#militaryDate").datetimepicker({
		format: "YYYY-MM-DD"
	});
	//input获得焦点事件
	$("input").focus(function(){
		$("input").not(this).popover('hide');
		$(this).popover('show');
	});
	//
	$("input").change(function(){
		$("input").popover('hide');
		console.log('change');
	});
	//返回
	$("#back").click(function(){
		window.history.go(-1);
	});
	//证件类型按钮
	$("#certificateType button").click(function(){
		$("#certificateType button.btn-primary").removeClass("btn-primary").addClass("btn-default");
		$(this).addClass("active").removeClass("btn-default").addClass("btn-primary");
	});
	//用户身份
	$("#userType button").click(function(){
		$("#userType button.btn-primary").removeClass("btn-primary").addClass("btn-default");
		$(this).addClass("active").removeClass("btn-default").addClass("btn-primary");
	});
	//上传头像
	$("#icon_input").change(function(e){
		var file=e.target.files[0];
		//先显示头像
		var windowURL = window.URL || window.webkitURL;
		var dataURL = windowURL.createObjectURL(file);
		$("#icon").attr('src',dataURL);
		//上传头像
		var fromData=new FormData();
		fromData.append('file',file);
		$.ajax({
			type:'post',
			url:uploadFileUrl,
			cache: false,
			processData: false,
		    contentType: false,
			data:fromData,
			success:function(data){
				if(data.flag){
					$("#icon").attr('data-src',data.data);
				}
                messageWin.alert("提示", data.msg);
			},
			error:function(err){

			}
		});
	});
	//userCode
	$("#userCode").off('change');
	$("#userCode").change(function(e){
		$("#userCodeP").hide();
		$("#userCodeP").html('');
		var code=$("#userCode").val();
		if(code==''||code==""||code==undefined){
			$("input").not(this).popover('hide');
			$(this).popover('show');
			return;
		}
		var code1=$("#userCode1").val();
		if(code!=null&&code!=undefined&&code!=code1){
            $.ajax({
                type:'post',
                url:validateUserCode,
                data:{code:code},
                success:function(user){
                    if(user!=null){
                        $("#userCodeP").show();
                        $("#userCodeP").html('该账号已存在');
                    }
                },
                error:function(err){

                }
            });
		}
	});
}

//写入数据
function writeData(code){
	$.ajax({
		type:'post',
		url:validateUserCode,
		data:{code:code},
		success:function(user){
			if(user!=null){
				$("#name").val(user.name);
				$("#department").val(user.department);
				$("#userCode").val(user.userCode);
                $("#userCode1").val(user.userCode);
				$("#militaryDate").val(user.militaryDate);
				$("#certificateCode").val(user.certificateCode);
                $("#certificateCodeP").val(user.certificateCode);
				$("#certificateType button").removeClass("btn-primary").addClass("btn-default");
				
				$("#certificateType button").each(function(index,value){
					
					if($(value).attr("data-type")==user.certificateType){
						$(value).addClass("btn-primary");
					}
				});
				$("#userType button").removeClass("btn-primary").addClass("btn-default");
//				$("#userType button[data-type='"+user.certificateType+"']").removeClass("btn-default").addClass("btn-primary");
				$("#userType button").each(function(index,value){
					if($(value).attr("data-type")==user.userType){
						$(value).addClass("btn-primary");
					}
				});
				$("#state").bootstrapSwitch('setState',user.state==1);
				$("#icon").attr('src',user.headImage);
				$("#icon").attr('data-src',user.headImage);
				$("#submit").one('click',function(){
					updUser(user.id);
				});
			}
		},
		error:function(err){
			
		}
	});
    initEvent();
}
//修改绑定事件
function initUpdEvent(code){
	$("#title").html('用户修改');
	writeData(code);
}

//新增绑定事件
function initAddEvent(){
	$("#title").html('新增用户');
    $("#userCode").val("");
    $("#password").val("");
	$("#submit").one('click',function(){
		addUser();
	});
    initEvent();
}

//检查参数
function getParams(type){

	var password=$("#password").val();
	//input 数据
	var user={
		name:$("#name").val(),
		department:$("#department").val(),
		userCode:$("#userCode").val(),
		password:password,
		militaryDate:$("#militaryDate").val(),
//		ertificateType:$("#ertificateType button.btn-primary").attr('data-type'),
		certificateCode:$("#certificateCode").val(),
//		state:$("#state").prop('checked'),
//		userType:$("#userType button.btn-primary").attr('data-type')
	};
	
	for(var key in user){
		if(user[key]==null||user[key]==''){
			/*if(key=='password'&&type=='upd'){
				continue;
			}*/
			$("#"+key).focus();
			return;
		}
	}

    var result=formValidator.init("updateUserForm");
    if(!result.flag){
        result.dom.attr('data-content',result.message);
        result.dom.popover('show');
        result.dom.focus();
        $("#update_user").one("click",function(){
            updModel(code);
        });
        return;
    }
	//密码
    // if(password.length<32){
        password=hex_md5(password);
    // }
    user.password=password;
	user.certificateType=$("#certificateType button.btn-primary").attr('data-type');
	user.state=$("#state").prop('checked')?1:2;
	user.userType=$("#userType button.btn-primary").attr('data-type');
	if(!($("#name").val().length>=2&&$("#name").val().length<=6)){
		$("#name").attr('data-content',"姓名应当在2至6个字符");
		$("#name").popover('show');
		$("#name").focus();
		if(type!='upd'){
			$("#submit").one('click',addUser);//重新绑定
		}else {
			user.name="";
		}
		return;
	}
	if($("#state").prop('checked')==1){
        //身份证判重
        var certificateCode=$("#certificateCode").val();
        var certificateCodeP=$("#certificateCodeP").val();
        if(user.certificateType==0){
			if(validateIdCard(certificateCode)){
				var flag=false;
				if(certificateCode!=null&&certificateCode!=""&&certificateCode!=''&&certificateCodeP!=undefined&&certificateCodeP!=certificateCode){
					$.ajax({
						async:false,
						type:'post',
						url:checkCertificateCode,
						data:{code:certificateCode},
						success:function(data){
							if(data!=null){
								$("#certificateCode").attr('data-content',"证件号码已存在，请重新填写");
								$("#certificateCode").popover('show');
								$("#certificateCode").focus();
								if(type!='upd'){
									$("#submit").one('click',addUser);//重新绑定
								}else {
									user.userCode="";
								}
							}else {
								flag=true;
								user.certificateCode=certificateCode;
							}
						},
						error:function(err){

						}
					});
					if(!flag){
						return;
					}
				}
			}else {
				$("#certificateCode").attr('data-content',"证件号码格式不正确，请重新填写");
				$("#certificateCode").popover('show');
				$("#certificateCode").focus();
				if(type!='upd'){
					$("#submit").one('click',addUser);//重新绑定
				}else {
					user.userCode="";
				}
				return;
			}

		}else if(user.certificateType==1){

			if(isOfficerCard(certificateCode).status==1){
				var flag=false;
				if(certificateCode!=null&&certificateCode!=""&&certificateCode!=''&&certificateCodeP!=undefined&&certificateCodeP!=certificateCode){
					$.ajax({
						async:false,
						type:'post',
						url:checkCertificateCode,
						data:{code:certificateCode},
						success:function(data){
							if(data!=null){
								$("#certificateCode").attr('data-content',"证件号码已存在，请重新填写");
								$("#certificateCode").popover('show');
								$("#certificateCode").focus();
								if(type!='upd'){
									$("#submit").one('click',addUser);//重新绑定
								}else {
									user.userCode="";
								}
							}else {
								flag=true;
								user.certificateCode=certificateCode;
							}
						},
						error:function(err){

						}
					});
					if(!flag){
						return;
					}
				}
			}else {
				$("#certificateCode").attr('data-content',"证件号码格式不正确，请重新填写");
				$("#certificateCode").popover('show');
				$("#certificateCode").focus();
				if(type!='upd'){
					$("#submit").one('click',addUser);//重新绑定
				}else {
					user.userCode="";
				}
				return;
			}
		}

	}else {
		//军官证
        user.certificateCode=certificateCode;
	}

	//头像
	var icon=$("#icon").attr('data-src');
	if(icon==null||icon==''){
        messageWin.alert("提示", "请先上传头像");
		return null;
	}
	var code=$("#userCode").val();
    var code1=$("#userCode1").val();
	var flag=true;
	if(code!=null&&code!=""&&code!=''&&code!=code1){
        $.ajax({
            async:false,
            type:'post',
            url:validateUserCode,
            data:{code:code},
            success:function(data){
                if(data!=null){
                    $("#userCodeP").show();
                    $("#userCodeP").html('该账号已存在');
                }else {
                	flag=false;
				}
            },
            error:function(err){

            }
        });
        if(flag){
            if(type!='upd'){
                $("#submit").one('click',addUser);//重新绑定
            }else {
                user.userCode="";
            }
            return;
        }
	}
	user.icon=icon;
	return user;
}

//新增方法
function addUser(){


	var user=getParams();
	if(user==null){
		$("#submit").one('click',addUser);//重新绑定
		return;
	}

	//提交
	$.ajax({
		type:'post',
		data:user,
		url:addUserUrl,
		success:function(data){
			if(!data.flag){
				$("#submit").one('click',addUser);//重新绑定
                messageWin.alert("提示",data.msg);
                return;
				/*Alert({
					msg:data.msg,
					onOk:function(){
						console.log('ok');
					}
				});*/
			}else{
                messageWin.alertback("提示", data.msg,function(){
                    window.history.go(-1);
                });
				/*Alert({
					msg:data.msg,
					onOk:function(){
						window.history.go(-1);
					}
				});*/
			}
		},
		error:function(){
			
		}
	});
}


//修改方法
function updUser(id){
	var user=getParams('upd');
	if(user==null||user.userCode==null||user.userCode==""){
		$("#submit").one('click',function(){
			updUser(id);
		});//重新绑定
		return;
	}

	user.id=id;
	//提交
	$.ajax({
		type:'post',
		data:user,
		url:updUserUrl,
		success:function(data){
			if(!data.flag){
				$("#submit").one('click',function(){
					updUser(id);
				});//重新绑定
                messageWin.alert("提示",data.msg);
				/*Alert({
					msg:data.msg,
					onOk:function(){
						
					}
				});*/
			}else{
                messageWin.alertback("提示", data.msg,function(){
                    window.history.go(-1);
                });
				/*Alert({
					msg:data.msg,
					onOk:function(){
						window.history.go(-1);
					}
				});*/
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
//检查身份证号是否正确
function validateIdCard(idCard) {
    //15位和18位身份证号码的正则表达式
    var regIdCard = /^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;
    //如果通过该验证，说明身份证格式正确，但准确性还需计算
    if (regIdCard.test(idCard)) {
        if (idCard.length == 18) {
            var idCardWi = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2); //将前17位加权因子保存在数组里
            var idCardY = new Array(1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2); //这是除以11后，可能产生的11位余数、验证码，也保存成数组
            var idCardWiSum = 0; //用来保存前17位各自乖以加权因子后的总和
            for (var i = 0; i < 17; i++) {
                idCardWiSum += idCard.substring(i, i + 1) * idCardWi[i];
            }
            var idCardMod = idCardWiSum % 11;//计算出校验码所在数组的位置
            var idCardLast = idCard.substring(17);//得到最后一位身份证号码
            //如果等于2，则说明校验码是10，身份证号码最后一位应该是X
            if (idCardMod == 2) {
                if (idCardLast == "X" || idCardLast == "x") {
                    return true;
                    //alert("恭喜通过验证啦！");
                } else {
                    return false;
                    //alert("身份证号码错误！");
                }
            } else {
                //用计算出的验证码与最后一位身份证号码匹配，如果一致，说明通过，否则是无效的身份证号码
                if (idCardLast == idCardY[idCardMod]) {
                    //alert("恭喜通过验证啦！");
                    return true;
                } else {
                    return false;
                    //alert("身份证号码错误！");
                }
            }
        }
    } else {
        //alert("身份证格式不正确!");
        return false;
    }
}
function isOfficerCard(card) {
    // 军官证
    // 规则： 军/兵/士/文/职/广/（其他中文） + "字第" + 4到8位字母或数字 + "号"
    // 样本： 军字第2001988号, 士字第P011816X号
    var reg = /^[\u4E00-\u9FA5](字第)([0-9a-zA-Z]{4,8})(号)$/;
    if (reg.test(card) === false) {
        return { 'status': 0, 'msg': '军官证号不合规' };
    } else {
        return { 'status': 1, 'msg': '校验通过' };
    }
}