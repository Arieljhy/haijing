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
		
		
		</style>
	</head>
	<body>
		<section class="wrapper w1">
			<div class="title1">
				<span>咨讯管理</span><span>/</span><span>咨讯发布管理</span>
			</div>
			<div class="title2">
				咨讯发布管理
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="content-panel">
					
					<div class="form-group service_input">
								<div class="left">
									<div class="search-item">
										<div class="lab">发布者</div>
										<input type="text" class="form-control" id="author" placeholder="">
									
									</div>
									<div class="search-item">
										<div class="lab">标题</div>
										<input type="text" class="form-control" id="title" placeholder="">
									
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
					
							<%-- <button type="button" class="btn btn-theme" id="addBtn">
								<i class="fa fa-check"></i> 新增
							</button>
							<button type="button" class="btn btn-theme04" id="removeBtn">
								<i class="fa fa-search"></i> 删除
							</button>
							<div class="form-group service_input">
								<input type="text" class="form-control" id="author" placeholder="根据发布者查询">
								<input type="text" class="form-control" id="title" placeholder="根据标题查询">
								<select id="state">
									<option value="">全部</option>
									<c:forEach items="${classfiys}" varStatus="i" var="item" >
										<option value="${item.id}">${item.name}</option>
									</c:forEach>
								</select>
								<button type="button" class="btn btn-theme03" id="finBtn">
									<i class="fa fa-search"></i> 查询
								</button>
								<button type="button" class="btn btn-theme02" id="searchReset">
									<i class="fa"></i> 重置
								</button>
							</div> --%>
					
					
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
						<!-- <section id="no-more-tables">
							<table id="table"
								class="table table-bordered table-striped table-condensed cf">
							</table>
							<div id="page"></div>
						</section> -->
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
		<script src="<%=basePath %>module/advert/advert.js"></script>
	</body>
</html>
