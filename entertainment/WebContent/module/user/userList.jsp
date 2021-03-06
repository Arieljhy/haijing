<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<link href="<%=basePath %>module/css/newcommon.css" rel="stylesheet">
		<link href="<%=basePath %>media/assets/css/bootstrap.css" rel="stylesheet">
		<!--external css-->
		<link href="<%=basePath %>media/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />		
		<!-- select -->
		<link href="<%=basePath %>media/assets/css/bootstrap-select.min.css" rel="stylesheet" >
		<!-- Custom styles for this template -->
		<link href="<%=basePath %>media/assets/css/style.css" rel="stylesheet">
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
				<%-- 
							<button type="button" class="btn btn-theme" id="addBtn">
								<i class="fa fa-check"></i> ??????
							</button>
							<button type="button" class="btn btn-theme04" id="removeBtn">
								<i class="fa fa-search"></i> ??????
							</button>
							<div class="form-group service_input">
								<input type="text" class="form-control" id="userCode" placeholder="??????????????????">
								<input type="text" class="form-control" id="name" placeholder="??????????????????">
								<select id="state">
									<option value="">??????</option>
									<option value="1">??????</option>
									<option value="2">??????</option>
								</select>
								<select id="department">
									<option value="">??????</option>
									<c:forEach items="${departments}" varStatus="i" var="item" >
										<option value="${item.id}">${item.name}</option>
									</c:forEach>
								</select>
								<button type="button" class="btn btn-theme03" id="finBtn">
									<i class="fa fa-search"></i> ??????
								</button>
								<button type="button" class="btn btn-theme02" id="searchReset">
									<i class="fa"></i> ??????
								</button>
							</div> --%>
							<div class="form-group service_input">
								<div class="left">
									<div class="search-item">
										<div class="lab">??????</div>
										<input type="text" class="form-control" id="userCode" placeholder="">
									
									</div>
									<div class="search-item">
										<div class="lab">??????</div>
										<input type="text" class="form-control" id="name" placeholder="">
									
									</div>
									<div class="search-item">
										<div class="lab">??????</div>
										<select id="state">
											<option value="">??????</option>
											<option value="1">??????</option>
											<option value="2">??????</option>
										</select>
									
									</div>
										<div class="search-item">
											<div class="lab">??????</div>
											<select id="department">
												<option value="">??????</option>
												<c:forEach items="${departments}" varStatus="i" var="item" >
													<option value="${item.id}">${item.name}</option>
												</c:forEach>
											</select>
										</div>
									
									
										
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
							<table id="table"
								class="table table-bordered table-striped table-condensed cf">
							</table>
							<div id="page"></div>
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
									<table id="table"
										class="table table-bordered table-striped table-condensed cf">
									</table>
							</div>
							
							<div class="page-con">
									<div id="page"></div>
							
							</div>
							
						</section>
						
						
						
						
						
						
						
						
					
				</div>
			</div>
		</div>
		</section>
		<!-- ???????????? -->
		<section>
			<div class="modal fade" id="userInfoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
								&times;
							</button>
							<h4 class="modal-title" id="">
								??????
							</h4>
						</div>
						<div class="modal-body row info" style="text-align: center;">
							<div class="col-sm-4 left">
								<img class="icon_img" id="info_icon" src="<%=basePath %>media/img/2.0_icon_11.png"/>
							</div>
							<div class="col-sm-8 right">
								<div class="form-group">
									<label class="label label-default">??????:</label>
									<label id="info_name"></label>
								</div>
								<div class="form-group">
									<label class="label label-default">??????:</label>
									<label id="info_department"></label>
								</div>
								<div class="form-group">
									<label class="label label-default">????????????:</label>
									<label id="info_military_date"></label>
								</div>
								<div class="form-group">
									<label class="label label-default">????????????:</label>
									<label id="info_certificate_type"></label>
								</div>
								<div class="form-group">
									<label class="label label-default">????????????:</label>
									<label id="info_certificate_code"></label>
								</div>
								<div class="form-group">
									<label class="label label-default">??????:</label>
									<label id="info_state"></label>
								</div>
								<div class="form-group">
									<label class="label label-default">??????:</label>
									<label id="info_user_type"></label>
								</div>
							</div>	
						</div>
					</div>
				</div>
			</div>
		</section>
		<!-- js placed at the end of the document so the pages load faster -->
		<script src="<%=basePath %>media/assets/js/jquery.js"></script>
		<script src="<%=basePath %>media/assets/js/bootstrap.min.js"></script>
		<!-- ????????? -->
		<script src="<%=basePath %>media/assets/js/bootstrap-select.min.js"></script>
		<!-- ??????JS -->
		<script src="<%=basePath %>module/js/table.js"></script>
		<!-- ??????js -->
		<script src="<%=basePath %>module/js/common.js"></script>		
		<!-- ???????????????????????? -->
		<script src="<%=basePath %>module/js/dialog.js"></script>
		<script src="<%=basePath %>module/js/messageModal.js"></script>
		<script src="<%=basePath %>media/js/md5.js"></script>
		<!-- ????????????JS -->
		<script type="text/javascript">
			var department={};
			var departments=JSON.parse('${department}');
			console.log(departments);
			departments.forEach(function(value){
				department[value.id]=value.name;
			});
		</script>
		<script src="<%=basePath %>module/user/userList.js"></script>
	</body>
</html>
