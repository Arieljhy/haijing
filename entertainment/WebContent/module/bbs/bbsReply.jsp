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
		<link href="<%=basePath %>module/css/newcommon.css" rel="stylesheet">
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
		<link href="<%=basePath %>module/css/style.css" rel="stylesheet">
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
		 
		 .fh{
		 cursor:pointer;
		 
		 }
		 .fh:hover{
		
		 text-decoration:underline;
		  color:#1890ff;
		 
		 }
		 .myform{
		 margin:0 !important;
		 }
		 .form-horizontal .form-group label{
		 color:#999 !important;
		 	
		 }
		  .form-horizontal .form-group  select{
		  width:100%;
		 }
		 .btn{
		 height:32px;
		 padding:0 10px;
		 line-height:32px;
		 
		 }
		 .btn.btn-primary{
		 background-color:#1890ff;
		 border:none;
		 
		 }
		 .switch{
		 }
		 .has-switch span.switch-left{
		 background-color:#fff;
		 border:1px solid #1890ff;
		  color:#1890ff;
		 
		 }
		 .has-switch label {
		     border: 1px solid #1890ff;
		     color:#1890ff;
		     background-color:#1890ff;
		 }
		 .clee:after{
		 content: "";  
                    display: table;  

		 }
		 .title2{
		 width:100%;
		 display:flex !important;
		 justify-content:sapce-between;
		 padding-right:0;
		 
		 }
		 .title2 .left{
		 width:80%;
		 text-align:left;
		 
		 }
		 .title2 .right{
	 width:20%;
		
		 
		 }
		 .title2 .right button{
		 	float:right;
		 	margin-left:10px;
		 	
		 }
		  .title2 .right  .btn.btn-info{
		 	float:right;
		 	margin-left:10px;
		 	
		
		 background-color:#1890ff  !important;
		 border:none;
		 
		 }
		 .service_input .left .search-item .btn-group .btn{
		 padding:0 !important;
		 }
		 
		 .bbs-floor-tail a{
		  color:#1890ff !important;
		 
		 }
		
		</style>
	</head>
	
	<body>
	<section>
		<section class="wrapper w1">
		<div class="title1">
					<span>论坛管理</span><span>/</span><span class="fh">发帖管理</span><span>>></span><span id="ttt">详情</span>
			</div>
			<div class="title2">
			<!-- id="title" -->
					<div class="left" >帖子标题：<span id="pageTitle"></span></div>
					<div class="right">
					<button type="button" class="btn btn-default fh" >返回</button>
						
					</div>	
			</div>
			<div class="row ">
				<div class="col-lg-12" style="background:#fff;padding:25px 0 0 0;">
				<input type="hidden" id="bbsId">
					<!-- <div class="content-panel">
						
						<input type="hidden" id="bbsId">
					<div class="mytit" style="">
						<label 	style="font-size: 20px;margin-left:10px;">标题：</label>
						<label	 id="pageTitle"	style="font-size: 20px;"></label> 
						</div>
							 -->
							<!-- <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> -->
							<label	 style="display:none;" >
								<button type="button" id="backBtn" onclick="back()">
								关闭
							</button>
							</label>
							
						

						<section id="no-more-tables" style="background:#fff;padding:0 10px;">
							
							<div class="bbs-detail-box">
								<table>
									<thead>
										<tr>
											<th style="width: 200px;">
												<div class="pull-left" id="clickCount"></div>
												<div class="pull-right" id="replyCount"></div></th>
											<th class="bbs-detail-title" id="title"></th>
										</tr>
									</thead>
									<tbody id="bbsTbody">
									
									</tbody>
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




	</section>

	<!-- js placed at the end of the document so the pages load faster -->
		<script src="<%=basePath%>media/assets/js/jquery.js"></script>
		<script type="text/javascript">
		
			$('.fh').click(function(){
				$("#backBtn").trigger('click');
			})
			
		</script>
		<script src="<%=basePath%>media/assets/js/bootstrap.min.js"></script>
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
		
		<!-- 加载JS -->
		<script src="<%=basePath %>module/js/common.table.js"></script>
		<!--数据验证 -->
		<script src="<%=basePath %>module/js/validator.js"></script>
		<!-- 公用js -->
		<script src="<%=basePath %>module/js/common.js"></script>		
		<!-- 加载信息提示窗口 -->

		<script src="<%=basePath %>module/js/dialog.js"></script>
	    <script src="<%=basePath %>module/js/messageModal.js"></script>
		<!-- 下拉多选框 -->
		<script src="<%=basePath %>module/js/select.js"></script>

		<!-- 加载页面JS -->
		<script src="<%=basePath %>module/bbs/bbsReply.js"></script>
	</body>
</html>
