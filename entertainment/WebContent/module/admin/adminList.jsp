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
		<%-- <link href="<%=basePath %>module/css/common.css" rel="stylesheet"> --%>
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
		.modal-body{
		padding:15px 24px;
		
		}
		.modal-header .close{
		height:25px !important;
		font-size:25px !important;
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
			text-align:left !important;
		
		
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
					<span>????????????</span><span>/</span><span>????????????</span>
				</div>
				<div class="title2">
					????????????
				</div>
				
				
				<div class="row">
					<div class="col-lg-12">
						<div class="content-panel">
							<input type="hidden" id="userCode" value="${userCode}"/>
						


								<!-- <button type="button"  class="btn btn-theme" id="addbtn">
									<i class="fa fa-check"></i> ??????
								</button>
								<button type="button"  class="btn btn-theme02" id="selectBtn">
									<i class="fa fa-pencil"></i> ???????????????
								</button>
								<button type="button" class="btn btn-theme04" >
									<i class="fa fa-trash-o"></i> ??????
								</button>
								<div class="form-group service_input">
									<input type="text" class="form-control" placeholder="????????????????????????" id="searchUserName">
									<button type="button" class="btn btn-theme03" id="searchSubmit">
										<i class="fa fa-search"></i> ??????
									</button>
									<button type="button" class="btn btn-theme02" id="searchReset">
										<i class="fa"></i> ??????
									</button>
								</div> -->
								
								<div class="form-group service_input">
								<div class="left">
									<div class="search-item">
										<div class="lab">????????????</div>
										<input type="text" class="form-control" id="searchUserName" placeholder="">
									
									</div>
									<!-- <div class="search-item">
										<div class="lab">????????????</div>
										<input type="text" class="form-control" id="remark" placeholder="">
									
									</div> -->
								</div>
								<div class="right">
										<button type="button" class="btn cz" id="searchReset">
										 	??????
										</button>
										<button type="button" class="btn btn-theme03 cx" id="finBtn">
											??????
										</button>
										
										
								</div>
						
								
							</div>
					
					
					
					
							<!-- <section id="no-more-tables">
								<table id="table_model" class="table table-bordered table-striped table-condensed cf">
								</table>
								<div id="page_moodel"></div>
							</section> -->
							
							<section id="no-more-tables" class="content">
								<div class="con-top">
									<div class="tit left">
									????????????
									</div>
									<div class="right">
									<button type="button" class="btn btn-theme04 sc" id="removeBtn">
										 ??????
										</button> 
										<button type="button" class="btn btn-theme xz " id="addBtn">
										??????
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
				</div>
			</section>
			<section>
				<!--add -->
				<form action="insertRole.html" class="modal fade in" id="insertUser" role="dialog" aria-labelledby="myModalLabel"
					aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">??</button>
								<h4 class="modal-title" id="myModalLabel">????????????</h4>
							</div>
							<div class="modal-body">
								<div class="input-group margintop10">
								  <span class="input-group-addon span_width" id="basic-addon1">????????????:</span>
								  <input id="addUserName" type="text" class="form-control" placeholder="????????????"  name="userName"
								  	  data-options="required:true,length:10"
								      data-toggle="popover" data-placement="top" 
								      data-content="?????????????????????">
								</div>
								<div class="input-group margintop10">
								  <span class="input-group-addon span_width" id="basic-addon1">????????????:</span>
								  <input id="addLoginName" type="text" class="form-control" placeholder="?????????????????????"  name="loginName"
								  	  data-options="required:true,validType:checkUser,length:20"
								      data-toggle="popover" data-placement="top"  
								      data-content="?????????????????????">
								</div>
								<div class="input-group margintop10">
								  <span class="input-group-addon span_width" id="basic-addon1">???&nbsp;&nbsp;???:</span>
								  <input id="addPswd" type="password" class="form-control" placeholder="???????????????"  name="pswd"
								  	  data-options="required:true,validType:isPwd"
								      data-toggle="popover" data-placement="top" 
								      data-content="???????????????">
								</div>
		 						<div class="input-group margintop10"> 
								  <span class="input-group-addon span_width" id="basic-addon1">????????????:</span>
		 						  <input id="addPswd1" type="password" class="form-control" placeholder="?????????????????????"  name="pswd1" 
		 						  	  aria-describedby="basic-addon1" data-options="required:true,validType:isPwd" 
		 						      data-container="body" data-toggle="popover" data-placement="top"  trigger="focus" 
		 						      data-content="?????????????????????"> 
		 						</div> 
								<div class="input-group margintop10">
								  <span class="input-group-addon span_width" id="basic-addon1">????????????:</span>
								  <input id="addTel" type="text" class="form-control"  data-options="validType:isPhone" data-placement="top" 
								  	data-toggle="popover"	placeholder="????????????"  name="tel">
								</div>
								<div class="input-group margintop10">
								  <span class="input-group-addon span_width" id="basic-addon1">???&nbsp;&nbsp;???:</span>
								  <input id="addPost" type="text" class="form-control"
										 data-options="required:true,length:21"
										 data-toggle="popover" data-placement="top"  trigger="focus"
										 data-content="?????????????????????20??????"

										 placeholder="??????"  name="post">
								</div>
								<div class="input-group margintop10" >
								  <span class="input-group-addon span_width" id="basic-addon1">???&nbsp;&nbsp;???:</span>
								  <div class="dropdown">
									  <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
									    <span id="addState" data="1" name="pstate">??????</span><!-- <input type="hidden" id="addState" name="state" value="1"/> -->
									    <span class="caret"></span>
									  </button>
									  <ul class="dropdown-menu" aria-labelledby="dropdownMenu1" id="stateUl">
									    <li value="1" onclick="changeState(1,'addState');" ><a href="javascript:void()">??????</a></li>
									    <li value="0" onclick="changeState(0,'addState');"><a href="javascript:void()">??????</a></li>
									  </ul>
								  </div>
								</div>
								<div class="input-group margintop10">
								  <span class="input-group-addon span_width" >???&nbsp;&nbsp;???:</span>
								  <div class="dropdown"  data-placement="top"  data-change="changeEvent()"
								  	data-toggle="manual">
								  	<!-- ??????????????? -->
								  	<select id="roleSelect"  class="selectpicker show-tick form-control"  data-live-search="false"></select>
								  </div>
								</div>
							</div>
							
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">??????</button>
								<button class="btn btn-primary" type="button" id="add_user">??????</button>
							</div>
						</div>
					</div>
				</form>
		
				<form class="modal fade in" id="updateUserForm" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">??</button>
								<h4 class="modal-title" id="myModalLabel">????????????</h4>
							</div>
							<div class="modal-body">
								<div class="input-group margintop10">
								  <span class="input-group-addon" id="basic-addon1">????????????:</span>
								  <input id="updateUserName" type="text" class="form-control popover-show" placeholder="????????????"  name="userName"
								  	  data-options="required:true,length:10"
								      data-toggle="popover" data-placement="top"  trigger="focus"
								      data-content="?????????????????????">
								</div>
								<div class="input-group margintop10">
								  <span class="input-group-addon" id="basic-addon1">????????????:</span>
								  <input id="updateTel" type="text" class="form-control popover-show" data-options="validType:isPhone"
								  data-placement="top"  data-toggle="popover"	placeholder="????????????"  name="tel">
								</div>
								<div class="input-group margintop10">
								  <span class="input-group-addon" id="basic-addon1">???&nbsp;&nbsp;???:</span>
								  <input id="updatePost" type="text" class="form-control popover-show"
										 data-options="required:true,length:21"
										 data-toggle="popover" data-placement="top"  trigger="focus"
										 data-content="?????????????????????20??????"
								  	placeholder="???&nbsp;&nbsp;???"  name="post">
								</div>
								<div class="input-group margintop10" >
								  <span class="input-group-addon" id="basic-addon1">???&nbsp;&nbsp;???:</span>
								  <div class="dropdown">
									  <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
									    <span id="updateState" data="0" name="pstate">??????</span><!-- </span><input type="hidden" id="updateState" name="state" value="1"/> -->
									    <span class="caret"></span>
									  </button>
									  <ul class="dropdown-menu" aria-labelledby="dropdownMenu1" id="stateUl">
									    <li value="1" onclick="changeState(1,'updateState');" ><a href="#">??????</a></li>
									    <li value="0" onclick="changeState(0,'updateState');"><a href="#">??????</a></li>
									  </ul>
								  </div>
								</div>
								<div class="input-group margintop10">
								  <span class="input-group-addon" id="basic-addon1">???&nbsp;&nbsp;???:</span>
								  <div class="dropdown" id="div_Roles_update">
								  	<!-- ??????????????? -->
								  	<select id="updateRoleSelect" class="selectpicker show-tick form-control"   data-live-search="false"></select>
								  </div>
								</div>
							</div>
							
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">??????</button>
								<button class="btn btn-primary" type="button" id="update_user" >??????</button>
							</div>
						</div>
					</div>
				</form>
				
				<form class="modal fade in" id="updateUserPswForm" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">??</button>
								<h4 class="modal-title" id="myModalLabel">????????????</h4>
							</div>
							<div class="modal-body">
								<div class="input-group margintop10">
								  <span class="input-group-addon" id="basic-addon1">?????????:</span>
								  <input id="updatePswd" type="password" class="form-control" placeholder="??????????????????"  name="pswd"
								  	  data-options="required:true,validType:isPwd"
								      data-toggle="popover" data-placement="top" 
								      data-content="???????????????">
								</div>
		 						<div class="input-group margintop10"> 
								  <span class="input-group-addon" id="basic-addon1">????????????:</span>
		 						  <input id="updatePswd1" type="password" class="form-control" placeholder="????????????????????????"  name="pswd1" 
		 						  	  aria-describedby="basic-addon1" data-options="required:true,validType:isPwd" 
		 						      data-container="body" data-toggle="popover" data-placement="top"  trigger="focus" 
		 						      data-content="?????????????????????"> 
		 						</div> 
								
							</div>
							
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">??????</button>
								<button class="btn btn-primary" type="button" id="update_user_psw" >??????</button>
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
		
		<!-- ??????JS -->
		<script src="<%=basePath %>module/js/common.table.js"></script>
		<!--???????????? -->
		<script src="<%=basePath %>module/js/validator.js"></script>
		<!-- ??????js -->
		<script src="<%=basePath %>module/js/common.js"></script>		
		<!-- ???????????????????????? -->
		<script src="<%=basePath %>module/js/messageModal.js"></script>
		<!-- ??????????????? -->
		<script src="<%=basePath %>module/js/select.js"></script>
		<script src="<%=basePath %>media/js/md5.js"></script>
		<!-- ????????????JS -->
		<script src="<%=basePath %>module/admin/adminList.js"></script>
	</body>
</html>
