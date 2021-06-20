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
		<link href="<%=basePath %>media/assets/css/bootstrap_new.css" rel="stylesheet"/>
		<link href="<%=basePath %>media/assets/css/bootstrap.css" rel="stylesheet">
		<!--external css	图标-->
		<link href="<%=basePath %>media/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />		
		<!-- Custom styles for this template -->
		<link href="<%=basePath %>media/assets/css/style.css" rel="stylesheet">
		<link href="<%=basePath %>media/assets/css/style-responsive.css" rel="stylesheet">		
		<link href="<%=basePath %>media/assets/css/table-responsive.css" rel="stylesheet">
		<link href="<%=basePath %>media/assets/css/zabuto_calendar.css" rel="stylesheet" >
		<link href="<%=basePath %>media/assets/css/bootstrap-select.min.css" rel="stylesheet">
		<link href="<%=basePath %>module/css/common.css" rel="stylesheet">
		<link href="<%=basePath %>module/css/newcommon.css" rel="stylesheet">
		
		<style type="text/css">
	.select_input{
	display: inline-block;
	width: 200px;
	height: 34px;
	padding: 6px 12px;
	font-size: 14px;
	line-height: 1.42857143;
	color: #555;
    background-color: #fff;
    background-image: none;
    border: 1px solid #ccc;
    border-radius: 4px;
	}
	</style>
	</head>
	<body>

		<section class="wrapper w1">
				<div class="title1">
					<span>测试管理</span><span>/</span>
					 <c:if test="${type eq '1'}">
                                <span>心理问卷管理</span>
                            </c:if>
                             <c:if test="${type eq '2'}">
                                <span>在线考试管理</span>
                            </c:if>
					
			</div>
			<div class="title2" style="width:100%;display:flex;justify-content:space-between;">
			 <c:if test="${type eq '1'}">
                             <div class="left">心理问卷管理</div>	
              </c:if>
             <c:if test="${type eq '2'}">
                             <div class="left">在线考试管理</div>	
                            
              </c:if>
				
				
				
				<div class="right" style="width:30%;text-align:right;padding-right:24px;">
										<!-- <button type="button" class="btn cz" id="searchReset">
										 	重置
										</button>
										<button type="button" class="btn btn-theme03 cx" id="finBtn">
											查询
										</button> -->
							<button type="button"  class="btn btn-theme02   cz" id="importBtn" style="background-color:#1890ff;border:none;">
								 导入
							</button>
							<c:if test="${type eq '1'}">
                                <a class="btn btn-theme03 cx" href="../upload/mode.doc" style="background-color:#1890ff; border:none;">
                               <!--  <i class="fa fa-pencil"></i> -->
                                 导出模板文件</a>
                            </c:if>
                            <c:if test="${type eq '2'}">
                                <a class="btn btn-theme03 cx" href="../upload/mode1.doc" style="background-color:#1890ff;border:none;">导出模板文件</a>
                            </c:if>
										
				</div>
				
			</div>
			
			
			
			<div class="row">
				<div class="col-lg-12">
					<div class="content-panel">
					<input type="hidden" id="type" value="${type}">
						
							<%-- <button type="button" class="btn btn-theme" id="addBtn" t="${type}">
								<i class="fa fa-check"></i> 新增
							</button>
							<button type="button" class="btn btn-theme04" id="removeBtn">
								<i class="fa fa-remove"></i> 删除
							</button>
							<button type="button"  class="btn btn-theme02" id="importBtn">
									<i class="fa fa-pencil"></i> 导入
							</button>
                            <c:if test="${type eq '1'}">
                                <a class="btn btn-theme03" href="../upload/mode.doc"><i class="fa fa-pencil"></i> 导出模板文件</a>
                            </c:if>
                            <c:if test="${type eq '2'}">
                                <a class="btn btn-theme03" href="../upload/mode1.doc"><i class="fa fa-pencil"></i> 导出模板文件</a>
                            </c:if>
							<input type="hidden" id="type" value="${type}">
							
							
							（）
							<div class="form-group service_input">
								<select id="type"	class="select_input">
									<option value="">类型</option>
									<option value="1">心理测试</option>
									<option value="2">网上考试</option>
								</select>
								
								
								<button type="button" class="btn btn-theme03" id="finBtn">
									<i class="fa fa-search"></i> 查询
								</button>
							</div>
							
							（）
							
							 --%>
							 
							 <div class="form-group service_input" style="display:none;" >
								<div class="left" style="width:10%;height:64px;">
										
								</div>
								<%-- <div class="right" style="width:90%;">
										<!-- <button type="button" class="btn cz" id="searchReset">
										 	重置
										</button>
										<button type="button" class="btn btn-theme03 cx" id="finBtn">
											查询
										</button> -->
							<button type="button"  class="btn  cz" id="importBtn">
								 导入
							</button>
							<c:if test="${type eq '1'}">
                                <a class="btn btn-theme03 cx" href="../upload/mode.doc">
                               <!--  <i class="fa fa-pencil"></i> -->
                                 导出模板文件</a>
                            </c:if>
                            <c:if test="${type eq '2'}">
                                <a class="btn btn-theme03 cx" href="../upload/mode1.doc">导出模板文件</a>
                            </c:if>
										
								</div> --%>
					</div>
				<!-- 
						<section id="no-more-tables">
								<table id="table_model" class="table table-bordered table-striped table-condensed cf">
								</table>
								<div id="page_moodel"></div>
						</section> -->
						
						<section id="no-more-tables" class="content" style="height:100%;">
								<div class="con-top">
									
									 <c:if test="${type eq '1'}">
                             <div class="tit left">心理问卷列表</div>	
              </c:if>
             <c:if test="${type eq '2'}">
                             <div class="tit left">在线考列表</div>	
                            
              </c:if>
									<div class="right">
									 <button type="button" class="btn btn-theme04 sc" id="removeBtn">
										 删除
										</button>  
										<button type="button" class="btn btn-theme xz " id="addBtn"  t="${type}">
										新增
										</button>
										
									
									</div>
									
							 
							</div>
							<div class="table-con" >
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
				<form method="post"  action="uploadWord.html" enctype="multipart/form-data" class="modal fade in" id="uploadExcel" role="dialog" aria-labelledby="myModalLabel"
					aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">×</button>
								<h4 class="modal-title" id="myModalLabel">导入试题</h4>
								
							</div>
							<div class="modal-body">
								<div class="input-group margintop10">
								  <span class="input-group-addon span_width" id="basic-addon1">试题信息:</span>
								  <input name="type" type="hidden" value="${type}">
								   <input type="file" id="excelFile" multiple="multiple" accept="application/msword" class="form-control" placeholder="请选择word文件" name="file">
								</div>
								
							</div>
							
							<div class="modal-footer">
								<button		 class="btn btn-primary" 	type="submit" onclick="importExcel(event)">导入</button>
								<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
								<!-- <button class="btn btn-primary" type="button" id="import_team">提交</button> -->
							</div>
						</div>
					</div>
				</form>
		
			</section>
		
		<!-- js placed at the end of the document so the pages load faster -->
		<script src="<%=basePath %>media/assets/js/jquery-1.8.3.min.js"></script>
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
		
		<!-- 加载JS -->
		<script src="<%=basePath %>module/js/common.table.js"></script>
		<!-- 公用js -->
		<script src="<%=basePath %>module/js/common.js"></script>		
		<!-- 加载信息提示窗口 -->
		<script src="<%=basePath %>module/js/dialog.js"></script>
		<script src="<%=basePath %>module/js/jquery.form.js"></script>
		<script src="<%=basePath %>module/study/testList.js"></script>

	<script>
		<c:if test="${not empty requestScope.importFlag}">
			Alert("${requestScope.importFlag}")
		</c:if>
	</script>
	</body>
</html>
