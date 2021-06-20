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
	</head>
	<body>
		<section class="wrapper w1">
			<div class="title1">
					<span>资源管理</span><span>/</span><span>书籍管理</span>
				</div>
				<div class="title2">
					书籍管理
				</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="content-panel">
					<%-- 
							<button type="button" class="btn btn-theme" id="addBtn">
								<i class="fa fa-check"></i> 新增（）
							</button>
							<button type="button" class="btn btn-theme04" id="removeBtn">
								<i class="fa fa-pencil"></i> 删除
							</button>
							<div class="form-group service_input">							
								<input type="text" class="form-control" id="name" placeholder="根据名称查询" style="margin-left: 10px;">
								<label style="margin-left: 10px;">分类:</label>
								<select id="category">
									<option value="">全部</option>
									<c:forEach items="${category}" varStatus="i" var="item" >
										<option value="${item.id}">${item.name}</option>
									</c:forEach>
								</select>
								<button type="button" class="btn btn-theme03" id="finBtn" style="margin-left: 10px;">
									<i class="fa fa-search"></i> 查询
								</button>
								<button type="button" class="btn btn-theme02" id="searchReset">
									<i class="fa"></i> 重置
								</button>
								<button type="button" class="btn btn-theme03" id="syncBtn" style="margin-left: 10px;">
									<i class="fa fa-search"></i> 同步文件
								</button>
							</div>
				 --%>
				 
				  <div class="form-group service_input">
								<div class="left">
									<div class="search-item">
										<div class="lab">名称</div>
										<input type="text" class="form-control" id="name" placeholder="">
									
									</div>
									 <div class="search-item">
										<div class="lab">分类</div>
										<select id="category">
											<option value="">全部</option>
											<c:forEach items="${category}" varStatus="i" var="item" >
												<option value="${item.id}">${item.name}</option>
											</c:forEach>
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
									影视列表
									</div>
									<div class="right">
									<button type="button" class="btn btn-theme04 sc" id="removeBtn">
										 删除
										</button> 
										<!-- <button type="button" class="btn btn-theme xz " id="addBtn">
										新增
										</button> -->
										
										<button type="button" class="btn btn-theme03 xz" id="syncBtn" >
													 同步文件
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
		<script src="<%=basePath %>module/js/layer/layer.js"></script>
		<script src="<%=basePath %>module/resource/bookList.js"></script>
	</body>
</html>
