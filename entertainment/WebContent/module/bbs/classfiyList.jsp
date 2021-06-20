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
		
		
		html,body{
		height:100%;
		width:100%;
		}
		.w1{
		height:100%; 
		width:100%;
		}
		
		.title1 {
			width:100%;
			height: 20px;
			line-height: 20px;
			margin-bottom:8px;
		}
		.title1 span{
			display:inline-block;
			
			height: 20px;
			font-size: 12px;
			font-family: PingFangSC-Regular, PingFang SC;
			font-weight: 400;
			color: #999;
			margin-right:8px;
			
		}
		.title2{
			width: 120px;
			height: 28px;
			font-size: 20px;
			font-family: PingFangSC-Medium, PingFang SC;
			font-weight: 500;
			color: rgba(0, 0, 0, 0.85);
			line-height: 28px;
			margin-bottom:16px;
		}
		.content-panel{
		padding:0;
			background-color:transparent;
		}
		.service_input{
			width:100%;
			display:flex;
			padding:24px;
				background-color:#fff;
		}
		.service_input .left{
			width:80%;
				display:flex;
				height:32px;

		}
		.service_input .left .search-item{
			display:flex;
	
			height:32px;
			line-height:32px;
			margin-right:24px;
		}
		.service_input .left .search-item .lab{
				
			height: 22px;
			font-size: 14px;
			font-family: PingFangSC-Regular, PingFang SC;
			font-weight: 400;
			margin-right:10px;
			
		}
		.service_input .left .search-item input{
			width:292px;
			height:32px;	
			
		}
		
		
		.service_input .right{
			width:20%;
			height:32px;
			text-align:right;
		}
		.service_input .right button{
		
			height:32px;
			line-height:32px;
			font-size: 14px;
			font-family: PingFangSC-Regular, PingFang SC;
			font-weight: 400;
			padding:0 16px;
			text-align:center;
			
			
		}
		
		.service_input .right button.cz{
			border:1px solid #eee;
			background-color:#fff;
			margin-right:8px;
		}
		.service_input .right button.cx{
		background-color:#1890ff;
		border:none;
		}
		
		.row{
			height:calc(100% - 71px);
			margin:0;
		
		}
		.col-lg-12{
			height:100%;
			padding:0;
		}
		.content-panel{
			height:100%;
		}
		.con-top{
			width:100%;
			
			display:flex;
			padding:16px 24px;
				background-color:#fff;
				justify-content:space-between;
		}
		
		
		
		.con-top .left{
			width:20%;
			text-align:left;
		
				height:32px;
				line-height:32px;
				
				font-size: 16px;
				font-family: PingFangSC-Regular, PingFang SC;
				font-weight: 400;
				color:#000;

		}
		.con-top  .right{
			width:20%;
			height:32px;
			text-align:right;
		}
		.con-top  .right button{
		
			height:32px;
			line-height:32px;
			font-size: 14px;
			font-family: PingFangSC-Regular, PingFang SC;
			font-weight: 400;
			padding:0 16px;
			text-align:center;
			
			
		}
		
		.con-top  .right button.sc{
			border:1px solid #eee;
			background-color:#fff;
			color:#666;
			margin-right:8px;
		}
		.con-top .right button.xz{
		background-color:#1890ff;
		border:none;
		}
		
		.content{
		height:calc(100% - 95px);
		position:relative;
		background-color:#fff;
		
		}
		.table-con{
		/* padding:0 16px; */
		
		height:calc(100% - 120px);
		padding:0 24px;
		
		overflow-y:auto;
		
		}
		.table-con #table{ 
		 border:none;
		}
		.table-con #table thead  tr {
		 border-bottom:1px dotted #000;
		
		}
		.table-con #table thead tr th{
		 border:none;
		 background-color:#f9f9f9;
		 height:54px;
		 line-height:54px;
		  padding:0;
		 
		}
		.table-con #table tbody tr {
		 border-bottom:1px dotted #000;
		
		}
		.table-con #table tbody tr td{
		 height:54px;
		 line-height:54px;
		 border:none;
		 background-color:#fff;
		 padding:0;
		}
		
		.table-con #table thead tr th:nth-child(1){
			text-align:center;
		
		
		}
		.table-con #table thead tr th:nth-child(2){
			text-align:center;
		
		
		}
		.table-con #table thead tr th:nth-child(3){
			text-align:center;
			width:120px;
		
		
		}
	
		.table-con #table thead tr th:nth-child(6){
			text-align:center;
		
		
		}
		
		
		.table-con #table tbody tr td:nth-child(1){
			text-align:center;
		
		
		}
		.table-con #table tbody tr td:nth-child(2){
			text-align:center;
		
		}
		.table-con #table tbody tr td:nth-child(3){
			text-align:center;
			width:120px;
		
		
		}
		
		.table-con #table tbody tr td:nth-child(6){
			text-align:center;
		
		
		}
		
		.table-con #table tbody tr td:last-child a{
			
		color:#1890FF;
		
		}
		
		
		
		
		
		.page-con{
		/* position:absolute;
		bottom:0;
		left:0;
		right:0; */
		height:32px;
		margin:16px 0;
		padding:0 24px;
		}
		
		.pagination_new.pagination_new-centered{
		margin:0;
		margin:16px 0;
		text-align:right;
		
		}
		.pagination_new.pagination_new-centered ul li a{ 
		
		
		color:#666 ;
		
		
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
		
		.pagination_new ul > .disabled > span, .pagination_new ul > .disabled > a, .pagination_new ul > .disabled > a:hover, .pagination_new ul > .disabled > a:focus{
		
		background: #FFFFFF;
			border-radius: 2px;
			border: 1px solid #1890FF;
			
			color:#1890FF;
		
		
		}
	
	
		
		
		</style>
		
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
		
		.modal-body .form-group{
			margin-bottom:20px !important;
			display:flex !important;
			justify-content:space-between !important;
		
		}
	
		.modal-body .form-group label:nth-child(1){
		border:none !important;
			background:#fff !important;
				width:20% !important;
			text-align:right !important;
			color:#999;
			font-size:15px;
			line-height:35px;
		
		}
		.modal-body .form-group label:nth-child(2){
		
		border-radius:4px  !important;
		width:80% !important;
		font-size:15px;
		
		line-height:37px;
		
		}
		
		 .modal-footer .btn-primary{
		 background:#1890ff !important;
		 border:none !important;
		 
		 }
		
		
		</style>
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
		 padding:0 15px 0 12px !important;
		 }
		
		
		</style>
	</head>
	<body>
		<section class="wrapper w1">
		
			<div class="title1">
					<span>论坛管理</span><span>/</span><span>论坛板块管理</span>
			</div>
			<div class="title2">
					论坛板块管理
			</div>
		
		
		
		
			<div class="row ">
				<div class="col-lg-12">
					<div class="content-panel">
				<!-- 
							<button type="button" class="btn btn-theme" id="addBtn">
								<i class="fa fa-plus"></i> 新增
							</button>
							<button type="button" class="btn btn-theme04" id="removeBtn">
								<i class="fa fa-remove"></i> 删除
							</button>
							<div class="form-group service_input">
								<input type="text" class="form-control" id="name" placeholder="根据板块名称查询">
								<input type="text" class="form-control" id="remark" placeholder="根据备注查询">
								<select id="state">
									<option value="">全部</option>
									<option value="1">启用</option>
									<option value="2">禁用</option>
								</select>
								<button type="button" class="btn btn-theme03" id="finBtn">
									<i class="fa fa-search"></i> 查询
								</button>
								<button type="button" class="btn btn-theme02" id="searchReset">
									<i class="fa"></i> 重置
								</button>
							</div>
						 -->
						 <div class="form-group service_input">
								<div class="left">
									<div class="search-item">
										<div class="lab">板块名称</div>
										<input type="text" class="form-control" id="name" placeholder="">
									
									</div>
									<div class="search-item">
										<div class="lab">备注</div>
										<input type="text" class="form-control" id="remark" placeholder="">
									
									</div>
									<div class="search-item">
										<div class="lab">状态</div>
										<select id="state">
											<option value="">全部</option>
											<option value="1">启用</option>
											<option value="2">禁用</option>
										</select>
									
									</div>
										
									
									
										
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
						 
						 
						 
						 
						 
						<!-- <section id="no-more-tables">
							<table id="table"
								class="table table-bordered table-striped table-condensed cf">
							</table>
							<div id="page"></div>
						</section> -->
						<section id="no-more-tables" class="content">
								<div class="con-top">
									<div class="tit left">
									板块列表
									</div>
									<div class="right">
									<button type="button" class="btn btn-theme04 sc" id="removeBtn">
										 删除
										</button> 
										<button type="button" class="btn btn-theme xz " id="addBtn">
										新增
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
		<!-- 查看详情 -->
		<section>
			<div class="modal fade" id="classfiyInfoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog" style="width:400px;">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
								&times;
							</button>
							<h4 class="modal-title" id="">
								详情
							</h4>
						</div>
						<div class="modal-body row classfiy_info" style="text-align: left;">
							<div class="form-group">
								<label class="label label-default">序列:</label>
								<label class="info_label" id="info_sequence"></label>
							</div>
							<div class="form-group">
								<label class="label label-default">板块名称:</label>
								<label class="info_label" id="info_name"></label>
							</div>
							<div class="form-group">
								<label class="label label-default">备注:</label>
								<label class="info_label" id="info_remark"></label>
							</div>
							<div class="form-group">
								<label class="label label-default">启用状态:</label>
								<label class="info_label" id="info_state"></label>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		<!-- js placed at the end of the document so the pages load faster -->
		<script src="<%=basePath %>media/assets/js/jquery.js"></script>
		<script src="<%=basePath %>media/assets/js/bootstrap.min.js"></script>
		<!-- 选择框 -->
		<script src="<%=basePath %>media/assets/js/bootstrap-select.min.js"></script>
		<!-- 加载JS -->
		<script src="<%=basePath %>module/js/table.js"></script>
		<!-- 公用js -->
		<script src="<%=basePath %>module/js/common.js"></script>
		<!-- 加载信息提示窗口 -->
		<script src="<%=basePath %>module/js/dialog.js"></script>
		<script src="<%=basePath %>module/js/messageModal.js"></script>
		<script src="<%=basePath %>module/bbs/classfiyList.js"></script>
	</body>
</html>
