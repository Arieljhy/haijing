<%@ page language="java" import="java.util.*" pageEncoding="Utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="Dashboard">
	<meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
	<title></title>
	<!-- Bootstrap core CSS -->
	<link href="<%=basePath %>media/assets/css/bootstrap_new.css" rel="stylesheet"/>
	<link href="<%=basePath %>media/assets/css/bootstrap.css" rel="stylesheet">
	<!--external css-->
	<link href="<%=basePath %>media/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
	<!-- Custom styles for this template -->
	<link href="<%=basePath %>media/assets/css/style.css" rel="stylesheet">
	<link href="<%=basePath %>media/assets/css/style-responsive.css" rel="stylesheet">
	<link href="<%=basePath %>media/assets/css/table-responsive.css" rel="stylesheet">
	<link href="<%=basePath %>media/assets/css/zabuto_calendar.css" rel="stylesheet" >
	<link href="<%=basePath %>media/assets/css/bootstrap-select.min.css" rel="stylesheet">
	<link href="<%=basePath %>module/css/common.css" rel="stylesheet">
	
	<link href="<%=basePath %>module/css/newcommon.css" rel="stylesheet">
	<style>
	.modal-body-left{
	width:100%;
	padding:0 24px;
	background-color:#fff;
	
	}
	#menuBody_add{
	width:100%;
	
	
	}
	
	
	</style>
</head>
<body>
<section class="wrapper w1">
	<div class="title1">
					<span>系统管理</span><span>/</span><span>菜单管理</span>
				</div>
				<div class="title2">
					菜单管理
	</div>
	<div class="row mt">
		<div class="col-lg-12">
			<div class="content-panel">
				<%--<h4>--%>
					<%--<button type="button" class="btn btn-theme" id="addBtn">--%>
						<%--<i class="fa fa-check"></i> 修改菜单--%>
					<%--</button>--%>
				<%--</h4>--%>
				<section id="no-more-tables">
					<div class="roleBody margintop10" style="display:table">
						<div class="modal-body-left">
							<!-- <h4 class="input-group span_width" style="font-size: 16px;">菜单修改:</h4> -->
							<div class="margintop10" id="menuBody_add"></div>
						</div>
					</div>
					<div id="page_moodel"></div>
				</section>
			</div>
		</div>
	</div>
</section>
<section>
	<!-- 修改菜单名称 -->
	<form class="modal fade in" id="addRole">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">×</button>
					<h4 class="modal-title" id="myModalLabel">点击菜单修改</h4>
				</div>
				<div class="modal-body" id="roleInfo_body">
					<div class="input-group margintop10">
						<span class="input-group-addon span_width" id="basic-addon1">菜单名称:</span>
						<input id="addRoleName" type="text" class="form-control" placeholder="菜单名称"
							    >
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button class="btn btn-primary" type="button" id="addRoleBtn">提交</button>
				</div>
			</div>
		</div>
	</form>
</section>
<!-- js placed at the end of the document so the pages load faster -->
<script src="<%=basePath %>media/assets/js/jquery.js"></script>
<script src="<%=basePath %>media/assets/js/bootstrap.min.js"></script>
<script src="<%=basePath %>media/assets/js/jquery.dcjqaccordion.2.7.js"></script>
<script src="<%=basePath %>media/assets/js/jquery.scrollTo.min.js"></script>
<script src="<%=basePath %>media/assets/js/jquery.nicescroll.js"></script>
<script src="<%=basePath %>media/assets/js/jquery.sparkline.js"></script>
<script src="<%=basePath %>media/assets/js/bootstrap-select.min.js"></script>
<!--common script for all pages-->
<script src="<%=basePath %>media/assets/js/common-scripts.js"></script>
<script src="<%=basePath %>media/assets/js/gritter/js/jquery.gritter.js"></script>
<script src="<%=basePath %>media/assets/js/gritter-conf.js"></script>
<script src="<%=basePath %>media/assets/js/sparkline-chart.js"></script>
<script src="<%=basePath %>media/assets/js/zabuto_calendar.js"></script>

<script type="text/javascript" src="<%=basePath %>module/js/bootstrap-tree/bootstrap-treeview.js"></script>
<!-- 加载JS -->
<script src="<%=basePath %>module/js/common.table.js"></script>
<!-- 公用js -->
<script src="<%=basePath %>module/js/common.js"></script>
<!-- 加载信息提示窗口 -->
<script src="<%=basePath %>module/js/messageModal.js"></script>

<script>
    var getMenuListUrl="getMenuList.html";//全部菜单
    var updateMenu="updateMenu.html";//修改菜单名称
    //初始化
    $(function(){
        initRole();
        $('#menuBody_add').on('nodeSelected', function(event, data) {
            btnClick(data.text,data.tags);
        });
    });
	function initRole() {
        $.ajax({
            async:false,
            type:'post',
            url:getMenuListUrl,
            data:{},
            success:function(data){
                writeRoleInDomId('menuBody_add',data);
            },
            error:function(err){

            }
        });
    }
    //写入角色多选框
    function writeRoleInDomId(id,menu){
        var obj=$("#"+id);
        obj.html('');
        var data=[];
        //获取父节点
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
        //获取子节点
        for(var i=0;i<menu.length;i++){
            if(menu[i].parentMenuId>0){
                var m={
                    text: menu[i].menuName,
                    href: '#',
                    tags: menu[i].id,
                };
                data.forEach(function(value,index,array){
                    //二级节点寻找父节点
                    if(value.tags==menu[i].parentMenuId){
                        array[index].nodes.push(m);
                    }
                });
            }
        }
        //创建树
        $('#'+id).treeview({
            levels: 99,
            data: data,
            showCheckbox:false,
            multiSelect: false,
            // nodeIcon: "glyphicon glyphicon-stop", //所有节点的默认图标
            // nodeSelected:function(e,node){
            //     btnClick(node.text,node.tags);
            // }
        });
        $('#'+id).treeview('expandAll', {silent:true });
        $('#menuBody_add').on('nodeSelected', function(event, data) {
            btnClick(data.text,data.tags);
        });
    }
    function btnClick(text,id) {
		$("#addRoleName").val(text);
		$("#addRole").modal("show");
        $("#addRoleBtn").unbind('click');
        $("#addRoleBtn").one('click',function(){
            addRole(id);
        });
    }
    function addRole(id) {
        var addRoleName=$("#addRoleName").val();
        if(addRoleName==undefined||addRoleName==null||addRoleName==""||addRoleName==''||addRoleName.indexOf(" ") >= 0||addRoleName.length>8){
            messageWin.alert("提示", "请输入菜单名称，限8个字不能有空格！");
            $("#addRoleBtn").one('click',function(){
                addRole(id);
            });
            return;
		}else {
            data={
                id:id,
				name:addRoleName
			};
            $.ajax({
                url:updateMenu,
                type:'post',
                data:data,
                success:function(data){
                    if(data==true||data=="true"){
                        $("#addRole").modal('hide');
                        messageWin.alert("提示", "修改成功");
                        //initRole();
                        parent.location.reload();
                    }else{
                        messageWin.alert("提示", "修改失败");
                        $("#addRoleBtn").one('click',function(){
                            addRole();
                        });
                    }
                },
                error:function(err){

                }
            });
		}
    }
</script>
</body>
</html>
