
/**全局变量***/
var url = 'getAdminList.html';
var thead = new Array("userName", "userCode","post", "status", "addDate", "updateDate", "rolesStr");
var title = new Array("用户姓名", "登录账号", "职务", "状态","建立时间","修改时间","角色");

var addUrl="addAdmin.html";
var getByCodeUrl="getUserByCode.html";
var updateAdminUrl="updUserByCode.html";
var removeAdminUrl="removeByCode.html";
var updatePwdUrl="updPwdbyCode.html";
var dataSource;

var addModal="#insertUser";
var updateModal="#updateUserForm";
var updatePwdModal="#updateUserPswForm";
var table;//表格
$(function() {
	initPopover();
	bingBtn();
	initRoleSelect();
	getAdminList(url);	
	
});



//初始化角色多选框
function initRoleSelect(){
	var jsonTitle = {
		"id" : "id",
		"name":"value"
	};
	// getRoleList(function(data){
	//
	// 	creatSelect("div_Roles",data,jsonTitle);
	// 	creatSelect("div_Roles_update",data,jsonTitle);
	// });
	getRoleList()
}

//绑定事件按钮
function bingBtn(){
	$("#addbtn").on('click',showAddModal);
	$("#selectBtn").on('click',getSelect);
	$("#searchSubmit").click(function() {
		getAdminList(url);
	});	
}



function getAdminList(_url) {
	var dataUrl=_url?_url:url;
	$.ajax({
		url : dataUrl,
		data : {
			"userName" : $("#searchUserName").val(),
			// "userCode": $("#userCode").val()
		},
		type : 'post',
		datatype : 'json',
		beforeSend : function() {
		},
		success : function(data) {
			init(data);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
		}
	});
}

function init(json) {
	dataSource = json;
	var userCode=$("#userCode").val();
	var data=JSON.parse(dataSource.data);
	//console.info("data:"+JSON.parse(data));
	dataSource.reffunc=getAdminList;
    dataSource.funcsTitle=['修改','修改密码','删除'];
    dataSource.funcs=[function(data){
        showUpdModal(data.userCode);
    },
        function(data){
            showUpdPwdModal(data.userCode);
        },
        function(data){
            var code=data.userCode;
            if(userCode==code){
                messageWin.alertback("提示", "您当前正在使用该账号，无法删除！");
                return;
			}
            messageWin.confirm("提示", "确认删除账号"+code+"?", function(){
                removeUser(code);
            });
        }];


	console.log(dataSource);
	table=myTable(dataSource);
	
	table.createTable("table_model", title, thead, "操作",{
		status:function(value){
			if(value.status==0){
				return '停用';
			}
			return '正常';
		}
	});
	table.createPageHelper("page_moodel");
	table.addTableCheckbox();
}
//新增用户 状态选项
function changeState(value,spanid){
	$("#"+spanid).attr('data',value);
	$("#"+spanid).html(value==1?'正常':'停用');
}

//新增事件
function showAddModal(){
	setSelectValByArray("div_Roles",[]);
	$(addModal+" input[type=text]").val('');
	changeState(1,'addState');
	$(addModal).modal("show");
	$("#add_user").unbind("click");
	$("#add_user").one("click",addModel);
	
}

//修改事件
function showUpdModal(code){
	//当前登录用户
    var userCode=$("#userCode").val();
	$.ajax({
		type:'post',
		data:{code:code},
		url:getByCodeUrl,
		success:function(data){
			console.log("data:"+data);
			$("#updateUserName").val(data.userName);
			$("#updateTel").val(data.tel);
			$("#updatePost").val(data.post);
			var status=data.status;
            if(code==userCode){
                status =1;
            }
			changeState(status,'updateState');
			var roles=data.roles.split(',');
			$("#updateRoleSelect").selectpicker('val',roles)
		},
		error:function(err){
			console.log("data:"+err);
		}
	});
	$("#update_user").unbind("click");
	$(updateModal).modal("show");
	$("#update_user").one("click",function(){
		updModel(code);
	});
}

//修改密码事件
function showUpdPwdModal(code){
	$(updatePwdModal).find("input[type=text]").val('');
	$(updatePwdModal).modal("show");
	$("#update_user_psw").unbind("click");
	$("#update_user_psw").one("click",function(){
		updPwd(code);
	});
}

//修改账号
function updModel(code){
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
	var roleArr=$("#updateRoleSelect").val()
	console.log("roleArr"+roleArr);
	if(roleArr==undefined||roleArr==""){
		$("#div_Roles_update").attr('data-content',"必选");
		$("#div_Roles_update").popover('show');
		$("#div_Roles_update").focus();
		$("#update_user").one("click",function(){
			updModel(code);
		});
		return;
	}
    //当前登录用户
    var userCode=$("#userCode").val();
	if(userCode==code){
		var status=$("#updateState").attr("data");
		if(status!=1||status!="1"){
            messageWin.alertback("提示", "您当前正在使用该账号，无法修改为停用状态！");
            $("#update_user").one("click",function(){
                updModel(code);
            });
            return;
		}
	}
	var roles=roleArr
	var data={
			code:code,
			userName:$("#updateUserName").val(),
			tel:$("#updateTel").val(),
			post:$("#updatePost").val(),
			state:$("#updateState").attr("data"),
			role:roles
	};
	$.ajax({
		type:'post',
		url:updateAdminUrl,
		data:data,
		success:function(data){
			if(data.flag){
				$(updateModal).modal('hide');
				messageWin.alertback("提示", "修改成功！", function(){
					
					getAdminList();
				});
			}else{
				messageWin.alertback("提示", "修改失败！");
				$("#update_user").one("click",function(){
					updModel(code);
				});
			}
		},
		error:function(err){
			
		}
	});
}
//新增账号提交
function addModel(){
	var result=formValidator.init("insertUser");
	if(!result.flag){
		result.dom.attr('data-content',result.message);
		result.dom.popover('show');
		result.dom.focus();
		$("#add_user").one("click",addModel);
		return;
	}
	var roleArr=$("#roleSelect").val()
	console.log("roleArr"+roleArr);
	if(roleArr==undefined||roleArr==""){
		$("#div_Roles").attr('data-content',"必选");
		$("#div_Roles").popover('show');
		$("#div_Roles").focus();
		$("#add_user").one("click",addModel);
		return;
	}
	var roles=roleArr
	var password=$("#addPswd").val();
    var password1=$("#addPswd1").val();
	if(password!=password1){
        $("#addPswd1").attr('data-content',"与新密码不一致，请重新输入");
        $("#addPswd1").popover('show');
        $("#addPswd1").focus();
        $("#add_user").one("click",addModel);
		return;
	}
	password=hex_md5(password);
    password1=hex_md5(password1);
    $("#addPswd").val(password);
    $("#addPswd1").val(password1);
    console.info($("#addTel").val());
	var data={
			userName:$("#addUserName").val(),
			loginCode:$("#addLoginName").val(),
			pwd:password,
			tel:$("#addTel").val(),
			post:$("#addPost").val(),
			state:$("#addState").attr("data"),
			role:roles
	};
	$.ajax({
		type:'post',
		url:addUrl,
		data:data,
		success:function(data){
			console.log(data);
			if(data.flag){
				$(addModal).modal('hide');
				messageWin.alertback("提示", "新增成功！", function(){
					
					getAdminList();
				});
				
			}else{
				$("#add_user").one("click",addModel);
			}
		},
		error:function(){
			$("#add_user").one("click",addModel);
		},
		complete:function(){
			
		}
	});
}

//确认删除用户
function removeUser(code){
	$.ajax({
		type:'post',
		url:removeAdminUrl,
		data:{code:code},
		success:function(data){
			if(data.flag){
				getAdminList();
				messageWin.alertback("提示", "删除成功！");
			}
		},
		error:function(error){
			
		}
	});
}

//修改密码
function updPwd(code){
	var result=formValidator.init("updateUserPswForm");
	if(!result.flag){
		result.dom.attr('data-content',result.message);
		result.dom.popover('show');
		result.dom.focus();
		$("#update_user_psw").one("click",function(){
			updPwd(code);
		});
		return;
	}
    var password=$("#updatePswd").val();
    var password1=$("#updatePswd1").val();
    if(password!=password1){
        $("#updatePswd1").attr('data-content',"与新密码不一致，请重新输入");
        $("#updatePswd1").popover('show');
        $("#updatePswd1").focus();
        $("#update_user_psw").one("click",function(){
            updPwd(code);
        });
        return;
    }
    password=hex_md5(password);
    password1=hex_md5(password1);

    $("#updatePswd").val(password);
    $("#updatePswd1").val(password1);
	var data={
			code:code,
			pwd:password
	};
	$.ajax({
		type:'post',
		url:updatePwdUrl,
		data:data,
		success:function(data){
			if(data.flag){
				$("#updateUserPswForm").modal("hide");
				messageWin.alertback("提示", "修改成功！");
			}
		},
		error:function(err){
			
		}
	});
}


//获取选择数据
function getSelect(){
	var data=table.getCheck("userCode");
	console.log(data);
}

//
function changeEvent(id){
	console.log(id);
}