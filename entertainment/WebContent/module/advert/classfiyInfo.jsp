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
		
		</style>
	</head>
	<body>
		<section class="wrapper w1">
		
			<div class="title1">
					<span>资讯管理</span><span>/</span><span class="fh">资讯板块管理</span><span>>></span><span id="ttt"></span>
			</div>
			<div class="title2">
					<div class="left" id="title">新增资讯板块</div>
					<div class="right">
								<button type="button" class="btn btn-default fh" >返回</button>
								<button type="button"  class="btn btn-info tj">提交</button>
					</div>	
			</div>
		<div class="row">
			<div class=" col-lg-12 content-panel" style="background:#fff;padding:25px 0 0 0;">
			
			
			
				<input type="hidden" data-trigger="" class="form-control"  id="classFyId" >
				<form class="" action="">
					<div class="row show-grid">
						<div class="col-sm-5 form-horizontal" style="display:inline-block;">
							<div class="form-group">
								<label class="col-sm-2 control-label" for="sequence">序列</label>
								<div class="col-sm-8">
									<input  type="number" oninput="if(value.length>9)value=value.slice(0,9)" data-trigger="" class="form-control" data-title="必填" id="sequence" placeholder="序列">
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label" for="name">板块名称</label>
								<div class="col-sm-8">
									<input type="text" data-trigger="" maxlength="6" class="form-control" data-title="必填" id="name" placeholder="板块名称">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label" for="remark">备注</label>
								<div class="col-sm-8">
									<input type="text" data-trigger="" maxlength="25" class="form-control" data-title="必填" id="remark" placeholder="备注">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label" for="state">启用状态</label>
								<div class="col-sm-8">
									<div class="switch" off-label="禁用" on-label="启用" onclick="checkboxOnclick(this)">
										<input class="switch-small" id="state" type="checkbox" checked="true"  data-size="small">
									</div>
									
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group">
							<div class="col-sm-8">
								<button type="button" id="back" class="btn btn-default" style="display:none;">返回</button>
								<button type="button" id="submit" class="btn btn-info" style="display:none;">提交</button>
							</div>	
						</div>
					</div>
				</form>
			</div>
		</div>	
		</section>
		
		<!-- js placed at the end of the document so the pages load faster -->
		<script src="<%=basePath %>media/assets/js/jquery.js"></script>
		<script type="text/javascript">
		
			$('.fh').click(function(){
				$("#back").trigger('click');
			})
			$('.tj').click(function(){
				$("#submit").trigger('click');
			})
		</script>
		<script src="<%=basePath %>media/assets/js/bootstrap.min.js"></script>
		<!-- switch -->
		<script src="<%=basePath %>media/assets/js/bootstrap-switch.js"></script>
		
		<!-- 加载JS -->
		<script src="<%=basePath %>module/js/table.js"></script>
		<!-- 公用js -->
		<script src="<%=basePath %>module/js/common.js"></script>		
		<!-- 加载信息提示窗口 -->
		<script src="<%=basePath %>module/js/dialog.js"></script>
		
		<!-- 加载页面JS -->
		<script src="<%=basePath %>module/advert/classfiyInfo.js"></script>
	</body>
</html>
