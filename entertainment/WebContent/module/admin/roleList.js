//全局变量
var dataUrl="getRoleList.html";//角色表格数据获取接口
var menuListUrl="getAdminMenuList.html";//角色权限接口
var updRoleMenuUrl="updRoleMenuUrl.html";//更新角色权限接口
var getMenuListUrl="getMenuList.html";//菜单
var addRoleUrl="addRole.html";//新增角色
var removeRoleUrl="removeRole.html";//删除角色
var stopRoleUrl="stopOrUseRole.html";//启用禁止角色
var checkRoleUrl="checkRole.html";//查询此角色下是否有用户
var checkAdminRole="checkAdminRole.html";
var regionUrl="../dic/getRegion.html";//获取地区
var chinaReginoCode="100000";

var table;//角色表格


var roleTitle=['角色名称','状态','添加时间'];
var roleJsonTitle=['roleName','state','addDate'];

//初始化
$(function(){
	initPopover();
	initRole();
	btnClick();
});

//绑定按钮事件
function btnClick(){
	$("#addBtn").on('click',function(){
		$.ajax({
			type:'post',
			url:getMenuListUrl,
			data:{},
			success:function(data){
				writeRoleInDomId('menuBody_add',"areaBody_add",data);
			},
			error:function(err){
				
			}
		});
		$("#addRole").modal("show");
		$("#addRoleBtn").unbind('click');
		$("#addRoleBtn").one('click',function(){
			addRole();
		});
	});
    $("#finBtn").on('click',function(){
        initRole();
	})
}

//加载角色表格数据
function initRole(_url){
	url=_url?_url:dataUrl;
	
	//获取搜索条件
	
	var data={
        roleName:$("#searchRoleName").val()
	};
	
	$.ajax({
		url:url,
		type:'post',
		data:data,
		success:function(data){
			initTable(data);
		},
		error:function(err){
			
		}
	});
}

//初始化角色Table
function initTable(data){
	var code=$("#")
	var tableData={
			reffunc:initRole,//刷新页面方法
			data:data.data,//表格数据
			total:data.total,//数据总数
			index:data.index,//当前页
			displayNum:6,//可见页数
			pageSize:data.pageSize,//数据数量
			path:data.path,//跳转地址
			nullStr:'',//数据为空时显示字符
			funcsTitle:['修改',function(value){
				if(value.state==0){
					return "启用";
				}else{
					return "禁用";
				}
			},'删除'],//操作内容
			funcs:[function(value){
				showRoleInfoModal(value);
			},
			function(value){
				var use=value.state==1?'禁用':'启用';
				if(value.state==1){
					if(value.roleName!='admin'){
						$.ajax({
							async:false,
							type:'post',
							data:{id:value.id},
							url:checkAdminRole,
							success:function(data){
								if(data.flag=="true"||data.flag==true){
									messageWin.confirm("提示", "确认"+use+value.roleName+"角色吗？禁用角色后拥有该角色的账号将不可使用", function(){
										stopOrUseRole(value.id,value.state);
									});
								}else {
									messageWin.alert("提示", "此角色下包含当前账号，无法禁用！");
									return;
								}
							},
							error:function(err){
								console.log("data:"+err);
							}
						});
					}else {
						messageWin.alert("提示", "admin角色无法禁用！");
						return;
					}

				}else {
                    messageWin.confirm("提示", "确认"+use+value.roleName+"角色吗？启用角色后拥有该角色的账号将可使用", function(){
                        stopOrUseRole(value.id,value.state);
                    });
				}
			},
			function(value){
				if(value.roleName!='admin') {
					$.ajax({
						async: false,
						type: 'post',
						data: {id: value.id},
						url: checkRoleUrl,
						success: function (data) {
							if (data == "true" || data == true) {
								messageWin.alert("提示", "此角色存在对应的账号，请先删除相关账号后再删除角色！");
								return;
							} else {
								messageWin.confirm("提示", "确认删除" + value.roleName + "角色吗？", function () {
									removeRole(value.id);
								});
							}
						},
						error: function (err) {
							console.log("data:" + err);
						}
					});
				}else{
					messageWin.alert("提示", "admin角色无法删除！");
					return;
				}
			}]//操作对应方法
	};
	table=myTable(tableData);
	
	table.createTable('table_model', roleTitle, roleJsonTitle, '操作', {
		state:function(value){
			if(value.state==0){
				return '禁止';
			}
			return '正常';
		}
	});
	table.createPageHelper('page_moodel');
	
}

function showRoleInfoModal(value){
	$("#roleInfo_body").html('');
	$.ajax({
		type:'post',
		url:menuListUrl,
		data:{role:value.id},
		success:function(data){
			var menu1=data.has;
			var menu2=data.all;
			writeRoleInDomId("roleInfo_body","roleAreaInfo_body",menu2);
			var nodes=$("#roleInfo_body").treeview("getUnchecked");
			
			//初始化菜单权限
			for(var i=0;i<menu1.length;i++){
				for(var j=0;j<nodes.length;j++){
					if(menu1[i].id==nodes[j].tags){
						$("#roleInfo_body").treeview("setCheckByNodeId",nodes[j].nodeId);
						if(!menu1[i].authorList){
							break;
						}
//						var array=menu1[i].authorList.split(',');
//						var nodes1=nodes[j].nodes;
//						for(var k=0;k<nodes1.length;k++){
//							for(var z=0;z<array.length;z++){
//								if(nodes1[k].tags.forid==nodes[j].tags&&nodes1[k].tags.type==array[z]){
//									$("#roleInfo_body").treeview("setCheckByNodeId",nodes1[k].nodeId);
//								}
//							}
//						}
//						
//						break;
					}
				}
				
			}
			
			$("#updInfo").unbind('click');
			$("#updInfo").one('click',function(){
				updRoleMenu(value);
			});
		},
		error:function(err){
			
		}
	});
	
	$("#roleInfo").modal('show');
}

//修改角色权限
function updRoleMenu(value){
	
	//获取菜单权限
	var menus=[];
	var authors=$('#roleInfo_body').treeview("getChecked");
	if(authors.length<=0){
        messageWin.alert("提示", "至少选择一个菜单权限！");
        return;
	}
	for(var i=0;i<authors.length;i++){
		var menu={};
		if(!authors[i].tags.type){
			menu.id=authors[i].tags;
			menu.authorlist='';
			menus.push(menu);
		}
	}
	for(var i=0;i<authors.length;i++){
		if(authors[i].tags.type){
			for(var j=0;j<menus.length;j++){
				
				if(menus[j].id==authors[i].tags.forid){
					
					menus[j].authorlist+=authors[i].tags.type+',';
				}
			}
		}
	}
	
	$.ajax({
		type:'post',
		url:updRoleMenuUrl,
		data:{roleid:value.id,menuids:JSON.stringify(menus)},
		success:function(data){
			if(data.flag){
				$("#roleInfo").modal('hide');
				messageWin.alert("提示", "修改成功！");
			}else{
				messageWin.alert("提示", "修改失败！");
				$("#updInfo").one('click',function(){
					updRoleMenu(value);
				});
			}
		},
		error:function(err){
			
		}
	});
}

//写入角色多选框
function writeRoleInDomId(id,id1,menu){
	var obj=$("#"+id);
	obj.html('');
	var data=[];
	for(var i=0;i<menu.length;i++){
		if(menu[i].parentMenuId==0){
			var m={
				text: menu[i].menuName,
				href: '#',
				tags: menu[i].id,
				nodes:[]
			};
			data.push(m);
		}
	}
	for(var i=0;i<menu.length;i++){
		if(menu[i].parentMenuId>0){
			/*var author=[{
				text:'增',
				href:'#',
				tags:{type:'1'},
				node:[]
			},{
				text:'删',
				href:'#',
				tags:{type:'2'},
				node:[]
			},{
				text:'改',
				href:'#',
				tags:{type:'3'},
				node:[]
			}];
			author[0].tags.forid=menu[i].id;
			author[1].tags.forid=menu[i].id;
			author[2].tags.forid=menu[i].id;*/
			var m={
				text: menu[i].menuName,
				href: '#',
				tags: menu[i].id,
				/*nodes:author*/
			};
			data.forEach(function(value,index,array){
				if(value.tags==menu[i].parentMenuId){
					array[index].nodes.push(m);
				}
			});
		}
	}
	$('#'+id).treeview({
        levels: 99,
        data: data,
        showCheckbox:true,
        onNodeChecked : function(event, data) {
			//点击选中
        },
        onNodeUnchecked : function(event, data){
			//点击取消
            var parentId=data.parentId;
            var flag=false;
            if(parentId!=null&&parentId!=undefined){
                var nodes = $('#'+id).treeview('getSiblings', data.nodeId);
                if(nodes.length>0){
                    for(var i in nodes){
						if(nodes[i].state.checked){
							flag=true;
						}
                    }
                    if(!flag){
                        $('#'+id).treeview('uncheckNode', [ parentId, {silent: true}]);
					}
				}
			}
        }
      });
}
//新增角色
function addRole(){
	
	var result=formValidator.init("addRole");
	if(!result.flag){
		console.log(result);
		result.dom.attr('data-content',result.msg);
		result.dom.popover('show');
		
		result.dom.focus();
		$("#addRoleBtn").one("click",function(){
			addRole();
		});
		return;
	}
	//菜单权限
	var menus=[];
	var authors=$('#menuBody_add').treeview("getChecked");
    if(authors.length<=0){
        messageWin.alert("提示", "至少选择一个菜单权限！");
        return;
    }
	for(var i=0;i<authors.length;i++){
		var menu={};
		if(!authors[i].tags.type){
			menu.id=authors[i].tags;
			menu.authorlist='';
			menus.push(menu);
		}
	}
	for(var i=0;i<authors.length;i++){
		if(authors[i].tags.type){
			for(var j=0;j<menus.length;j++){
				
				if(menus[j].id==authors[i].tags.forid){
					
					menus[j].authorlist+=authors[i].tags.type+',';
				}
			}
		}
	}
	var data={
		roleName:$("#addRoleName").val(),
		menus:JSON.stringify(menus)
	};
	$.ajax({
		url:addRoleUrl,
		type:'post',
		data:data,
		success:function(data){
			if(data.flag){
				$("#addRole").modal('hide');
				messageWin.alert("提示", "新增成功");
				initRole();
			}else{
				messageWin.alert("提示", data.message);
				$("#addRoleBtn").one('click',function(){
					addRole();
				});
			}
		},
		error:function(err){
			
		}
	});
}

//删除角色
function removeRole(id){
	$.ajax({
		type:'post',
		url:removeRoleUrl,
		data:{roleId:id},
		success:function(data){
			if(data.flag){
				messageWin.alert("提示", "删除成功");
				initRole();
			}else{
				messageWin.alert("提示", data.msg);
			}
		},
		error:function(err){
			
		}
	});
}

function stopOrUseRole(id,status){
	$.ajax({
		type:'post',
		url:stopRoleUrl,
		data:{roleId:id,status:status==1?0:1},
		success:function(data){
			messageWin.alert("提示", data.message);
			initRole();
		},
		error:function(err){
			
		}
	});
}

