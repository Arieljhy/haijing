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
		
		<!-- timepick -->
		<link href="<%=basePath %>media/timepick/css/font-awesome.min.css" rel="stylesheet">
		<link href="<%=basePath %>media/timepick/css/prettify-1.0.css" rel="stylesheet">
		<link href="<%=basePath %>media/timepick/css/bootstrap-datetimepicker.css" rel="stylesheet">
		<!-- timepickend -->
		
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
					<span>资源管理</span><span>/</span><span class="fh">书籍管理</span><span>>></span><span id="ttt"></span>
			</div>
			<div class="title2">
					<div class="left" id="title">书籍管理</div>
					<div class="right">
								<button type="button" class="btn btn-default fh" >返回</button>
								<button type="button"  class="btn btn-info tj">提交</button>
					</div>	
			</div>
			<div class="col-lg-12 content-panel" style="background:#fff;padding:25px 0 0 0;height:calc(100% - 72px);">
				<!-- <h3 id="title">新增视频</h3> -->
				<form class="" action="">
					<div class="row show-grid">
						<div class="col-sm-10 form-horizontal" style="display:inline-block;">
							<div class="form-group" id="imgGroup">
								<label id="uploadLabel" class="col-sm-2 control-label">封面图片</label>
								<div class="col-sm-8 uploadImg">
									<img alt="" style="max-width: 300px; max-height: 100px;" src=" " id="icon"><br/>
									<input type="file" id="fileUpload" class="form-control"/>
								</div>								
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">发布者</label>
								<div class="col-sm-8">
									<input type="text" data-trigger="" disabled="disabled" class="form-control" data-title="必填" id="userName" placeholder="发布者" value="${userName}">
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-2 control-label"  >名称</label>
								<div class="col-sm-8">
									<input type="text" style="display: none" id="nameInput" disabled class="form-control" placeholder="名称">
									<select class="selectpicker" data-virtual-scroll="600" style="width:500px"  placeholder="名称"   id="name" data-window-padding="bottom"  data-live-search="true">
									</select>
									<button type="button"  class="btn btn-info" id="getUrlBtn"    onclick="getUrl()" style="display: inline-block;">确定</button>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">发布日期</label>
								<div class="col-sm-8">
									<input type="text" data-trigger="" class="form-control" data-title="必填" id="addDate" placeholder="发布日期">
								</div>
							</div>							
							<div class="form-group">
								<label class="col-sm-2 control-label">分类</label>
								<div class="col-sm-8">
									<select id="type" disabled>
										<c:forEach items="${type}" varStatus="i" var="item" >
											<option value="${item.id}">${item.name}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">作者</label>
								<div class="col-sm-8">
									<input type="text" data-trigger="" data-title="必填" class="form-control" id="author"
										   data-options="required:true,length:11"
										   data-toggle="popover" data-placement="top"
										   data-content="请输入作者，限10个字"
										   placeholder="作者">
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">简介</label>
								<div class="col-sm-8">
									<textarea id="introduction" style= "overflow:hidden; resize:none;width:100%;height:80px;"  class="form-control" rows="" cols=""></textarea>
								</div>
							</div>		
							<div class="form-group">
								<label class="col-sm-2 control-label">内容</label>
								<div class="col-sm-8">
									<table id="content" class="table table-bordered table-striped table-condensed cf">
									</table>
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
		</section>
		
		<!-- js placed at the end of the document so the pages load faster -->
		<script src="<%=basePath %>media/assets/js/jquery.js"></script>
		<script type="text/javascript">
		$(function(){
	
		
			
			$('.fh').click(function(){
				$("#back").trigger('click');
			})
			$('.tj').click(function(){
				$("#submit").trigger('click');
			})
			
			
		})
			
		</script>
		<script src="<%=basePath %>media/assets/js/bootstrap.min.js"></script>
		<!-- switch -->
		<script src="<%=basePath %>media/assets/js/bootstrap-switch.js"></script>
		<!-- 选择框 -->
		<script src="<%=basePath %>media/assets/js/bootstrap-select.min.js"></script>
		
		<script type="text/javascript" src="<%=basePath %>media/editor/ueditor.config.js"></script>
		<!-- 编辑器源码文件 -->
		<script type="text/javascript" src="<%=basePath %>media/editor/ueditor.all.js"></script>

		<!-- timepick -->
		<script src="<%=basePath %>media/timepick/js/moment-with-locales.js"></script>
		<script src="<%=basePath %>media/timepick/js/bootstrap-datetimepicker.js"></script>
		<!-- timepickend -->
		
		<!-- 加载JS -->
		<script src="<%=basePath %>module/js/table.js"></script>
		<!-- 公用js -->
		<script src="<%=basePath %>module/js/common.js"></script>		
		<!-- 加载信息提示窗口 -->
		<script src="<%=basePath %>module/js/dialog.js"></script>		
		<!-- 加载页面JS -->
		<script src="<%=basePath %>module/resource/bookInfo.js"></script>
	</body>
</html>
