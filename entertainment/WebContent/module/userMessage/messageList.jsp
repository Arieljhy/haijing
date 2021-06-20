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
		<link href="<%=basePath %>media/assets/js/fancybox/jquery.fancybox.css" rel="stylesheet" />
		<!--external css-->
		<link href="<%=basePath %>media/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />		
		<!-- select -->
		<link href="<%=basePath %>media/assets/css/bootstrap-select.min.css" rel="stylesheet" >
		<!-- Custom styles for this template -->
		<link href="<%=basePath %>media/assets/css/style.css" rel="stylesheet">
		<link href="<%=basePath %>module/css/common.css" rel="stylesheet">
	</head>
	<body>
		<section class="wrapper">
			<h4 id="pageTitle"></h4>
			<div class="row mt">
				<div class="col-lg-12">
					<div class="content-panel">
						<h4>
							<div class="form-group service_input">
								<input type="text" class="form-control" id="content" placeholder="根据关键词查询">
								<input type="text" class="form-control" id="fromPeople" placeholder="根据发言人查询">
								<input type="text" class="form-control" id="toPeople" placeholder="根据发送对象查询">
								<label>内容类型：</label>
								<select id="type">
									<option value="">全部</option>
									<option value="1">图片</option>
									<option value="0">文字</option>
								</select>
								<label>聊天类型：</label>
								<select id="isPrivate">
									<option value="">全部</option>
									<option value="1">私聊</option>
									<option value="2">群聊</option>
								</select>
								<button type="button" class="btn btn-theme03" id="finBtn">
									<i class="fa fa-search"></i> 查询
								</button>
							</div>
						</h4>
						<section id="no-more-tables">
							<table id="table"
								class="table table-bordered table-striped table-condensed cf">
							</table>
							<div id="page"></div>
						</section>
					</div>
				</div>
			</div>
		</section>
		<section>
			<div class="modal fade" id="openBig" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" 
									aria-hidden="true">×
							</button>
						</div>
						<div class="modal-body">
							<canvas id="showImg" width="100px" height="100px"></canvas>
						</div>
					</div>
				</div>
			</div>
		</section>
		
		<!-- js placed at the end of the document so the pages load faster -->
		<script src="<%=basePath %>media/assets/js/jquery.js"></script>
		<script src="<%=basePath %>media/assets/js/bootstrap.min.js"></script>
		
		<script src="<%=basePath %>media/assets/js/fancybox/jquery.fancybox.js"></script> 
		<!-- 选择框 -->
		<script src="<%=basePath %>media/assets/js/bootstrap-select.min.js"></script>
		<!-- 加载JS -->
		<script src="<%=basePath %>module/js/table.js"></script>
		<!-- 公用js -->
		<script src="<%=basePath %>module/js/common.js"></script>		
		<!-- 加载信息提示窗口 -->
		<script src="<%=basePath %>module/js/dialog.js"></script>
		
		<!-- 加载页面JS -->
		
		<script src="<%=basePath %>module/userMessage/messageList.js"></script>
	</body>
</html>
