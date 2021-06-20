var listUrl='userList.html';
var removeUrl='removeUser.html';
var iconRootUrl='../';
$(function(){
	MyTable.init();
	refresh();
	initEvent();
});

function initEvent(){
	$('select').selectpicker();
	$("#addBtn").click(function(){
		window.location.href='./toUserInfo.html';
	});
	$("#removeBtn").click(function(){
		var ids=MyTable.getCheck('table','id');
		if(ids==undefined||ids==''){
			return;
		}
        messageWin.confirm("提示", "确定删除账号吗？", function(){
            removeUsers(ids.join(','));
        });
	});
	$("#finBtn").click(function(){
		refresh();
	});
}

function refresh(page){
	console.log("page");
	console.log(page);
	var url=page?(listUrl+'?page='+page):listUrl;
	console.log("page");
	console.log(url);
	var data={
		userCode:$("#userCode").val(),
		name:$("#name").val(),
		state:$("#state").val(),
		department:$("#department").val()
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

function removeUsers(ids){
    $.ajax({
        async:false,
        type:'post',
        url:removeUrl,
        data:{ids:ids},
        success:function(data){
            if(data.flag){
                messageWin.alertback("提示", data.msg,function(){
                    refresh();
                });
                /*Alert({
                    msg:data.msg,
                    onOk:function(){
                        refresh();
                    }
                });*/
            }else{
                messageWin.alert("提示",data.msg);
                return;
            }
        },
        error:function(){

        }
    });
    /*Confirm({
        msg:"确定删除账号吗？",
        onOk:function(){
            $.ajax({
                type:'post',
                url:removeUrl,
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
                        Alert(data.msg);
                    }
                },
                error:function(){

                }
            });
        }
    });*/
}

function drawMyTable(data){
	var params={
		titles:['编号','账号','姓名','部门','状态',' 身份'],
		dataTitles:['id','userCode','name','department','state','userType'],
		data:data.data,
		total:data.total,
		index:data.index,
		reffunc:refresh,
		pageSize:data.pageSize,
		trFunc:function(value,index){
			console.log(value,index);
		},
		dataTitlesFunc:{
			'userCode':function(value,i,j){
				console.log(value,i,j);
				return value['userCode'];
			},
			'department':function(value,i,j){
				return department[value.department];
			},
			'state':function(value,i,j){
				return value.state==1?'启用':'禁用';
			},
			'userType':function(value){
				return value.userType==1?'普通':'管理员';
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
				$("#info_icon").attr('src',value.headImage);
				$("#info_name").html(value.name);
				$("#info_department").html(department[value.department]);
				$("#info_military_date").html(value.militaryDate);
				$("#info_certificate_type").html(value.certificateType==0?'身份证':'军官证');
				$("#info_certificate_code").html(value.certificateCode);
				$("#info_state").html(value.state==1?'启用':'禁用');
				$("#info_user_type").html(value.userType==1?'普通':'管理员');
				$("#userInfoModal").modal('show');
			}
		},{
			title:'修改',
			func:function(value){
				window.location.href='toUserInfo.html?type=upd&code='+value.userCode;
			}
		},{
			title:'删除',
			func:function(value){
                messageWin.confirm("提示", "确定删除账号吗？", function(){
                    removeUsers(value.id);
                });
			}
		}]
	});
};