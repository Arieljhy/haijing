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
	
		
		.modal-content{
			border-radius:8px !important;
		}
		.modal-header{
		background:#fff !important;
		border-radius:8px !important;
	
		
		}
		.modal-title{
			color:#000 !important;
		
		}
		.modal-header .close{
		height:25px !important;
		font-size:25px !important;
		}
		.modal-body{
		padding:15px 24px;
		max-height:411px;
		overflow-y:auto;
		
		}
		
		.modal-body .input-group{
			margin-bottom:20px !important;
			display:flex !important;
			justify-content:space-between !important;
		
		}
		.modal-body .input-group .input-group-addon{
			border:none !important;
			background:#fff !important;
			width:15% !important;
			text-align:left;
		
		}
		.modal-body .input-group input{
		border-radius:4px  !important;
		width:85% !important;
		
		}
		.modal-body .input-group .dropdown{
		width:85% !important;
		text-align:left !important;
		}
		.modal-body .input-group .dropdown .dropdown-toggle{
		width:100% !important;
			display:flex !important;
		
		justify-content:space-between !important;
		
		
		
		}
		.modal-body .input-group .dropdown .dropdown-toggle .caret{
		margin:auto  0 !important;
		}
		 .modal-body .input-group .dropdown  .dropdown-menu{
		 width:100% !important;
		 }
		 .modal-footer .btn-primary{
		 background:#1890ff !important;
		 border:none !important;
		 
		 }
		 .roleBody{
		
		 }
		 .roleBody .modal-body-left{
		 width:100%;
		 
		 }
		 
		 .pagination_new.pagination_new-centered ul li:nth-child(1) a{
		color:#666 !important;
		border:1px solid #eee  !important;
		}
		.pagination_new.pagination_new-centered ul li:nth-child(2) a{
		color:#666 !important;
		border:1px solid #eee  !important;
		}
		.pagination_new.pagination_new-centered ul li:nth-child(3) a{
		color:#666 !important;
		border:1px solid #eee  !important;
		}
		.pagination_new.pagination_new-centered ul li:last-child a{ 
		
		border:1px solid #eee !important;
		color:#666 !important;
		
		}
		.pagination_new.pagination_new-centered ul li:nth-last-child(2) a{ 
		
		border:1px solid #eee !important;
		color:#666 !important;
		
		
		}
		.pagination_new.pagination_new-centered ul li:nth-last-child(3) a{ 
		
		border:1px solid #eee !important;
		color:#666 !important;
		
		
		}
		.pagination_new.pagination_new-centered ul li:nth-last-child(4) a{ 
		
		border:1px solid #eee !important;
		color:#666 !important;
		
		
		}
		 
		 
		 
		</style>
	</head>
	<body>
		<section class="wrapper w1">	
				<div class="title1">
					<span>系统管理</span><span>/</span><span>角色管理</span>
				</div>
				<div class="title2">
					角色管理
				</div>
		
		
	
			<div class="row">
				<div class="col-lg-12">
					<div class="content-panel">
					
						<!-- 	<button type="button" class="btn btn-theme" id="addBtn">
								<i class="fa fa-check"></i> 新增
							</button>
							<div class="form-group service_input">
								<input type="text" class="form-control" placeholder="根据角色名称查询" id="searchRoleName">
								<button type="button" class="btn btn-theme03" id="finBtn">
									<i class="fa fa-search"></i> 查询
								</button>
								<button type="button" class="btn btn-theme02" id="searchReset">
									<i class="fa"></i> 重置
								</button>
							</div> -->
							
							<div class="form-group service_input">
								<div class="left">
									<div class="search-item">
										<div class="lab">角色名称</div>
										<input type="text" class="form-control" id="searchRoleName" placeholder="">
									
									</div>
									<!-- <div class="search-item">
										<div class="lab">备注检索</div>
										<input type="text" class="form-control" id="remark" placeholder="">
									
									</div> -->
								</div>
								<div class="right">
										<button type="button" class="btn cz" id="searchReset">
										 	重置
										</button>
										<button type="button" class="btn btn-theme03 cx" id="finBtn">
											查询
										</button>
										
										
								</div>
						
								
							</div>
					
					
					
					
					<!-- 	<section id="no-more-tables">
							<table id="table_model"
								class="table table-bordered table-striped table-condensed cf">
							</table>
							<div id="page_moodel"></div>
						</section>
						
 -->
 
 							<section id="no-more-tables" class="content">
								<div class="con-top">
									<div class="tit left">
									角色列表
									</div>
									<div class="right">
									<!-- <button type="button" class="btn btn-theme04 sc" id="removeBtn">
										 删除
										</button>  -->
										<button type="button" class="btn btn-theme xz " id="addBtn">
										新增
										</button>
										
									
									</div>
									
							 
							</div>
							<div class="table-con">
									<table id="table_model"
										class="table table-bordered table-striped table-condensed cf">
									</table>
							</div>
							
							<div class="page-con">
									<div id="page_moodel"></div>
							
							</div>
							
						</section>





				</div>
			</div>
		</section>
		
		<section>
			<!--权限详细  -->
			<form class="modal fade in" id="roleInfo">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">×</button>
							<h4 class="modal-title" id="myModalLabel">修改</h4>
						</div>
						<div class="modal-body">
							<div class="roleBody margintop10" style="display:table">
								<div class="modal-body-left">
									<span class="input-group span_width" >菜单权限:</span>
									<div class="margintop10" id="roleInfo_body"></div>
								</div>
							</div>
						</div>
							
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
							<button class="btn btn-primary" type="button" id="updInfo">提交</button>
						</div>
					</div>
				</div>
			</form>
			
			<!-- 新增角色 -->
			<form class="modal fade in" id="addRole">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">×</button>
							<h4 class="modal-title" id="myModalLabel">新增</h4>
						</div>
						<div class="modal-body" id="roleInfo_body">
							<div class="input-group margintop10">
								<span class="input-group-addon span_width" id="basic-addon1">角色名称:</span>
								<input id="addRoleName" type="text" class="form-control" placeholder="角色名称"
								  	  data-options="required:true,length:11"
								      data-toggle="popover" data-placement="top" 
								      data-content="请输入角色名称，限10个字">
							</div>
							<div class="roleBody margintop10" style="display:table">
								<div class="modal-body-left">
									<span class="input-group span_width" >菜单权限:</span>
									<div class="margintop10" id="menuBody_add"></div>
								</div>
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
		<!--数据验证 -->
		<script src="<%=basePath %>module/js/validator.js"></script>
		<!-- 公用js -->
		<script src="<%=basePath %>module/js/common.js"></script>		
		<!-- 加载信息提示窗口 -->
		<script src="<%=basePath %>module/js/messageModal.js"></script>
		
		<!-- 加载页面JS -->
		<script src="<%=basePath %>module/admin/roleList.js"></script>
	</body>
</html>
