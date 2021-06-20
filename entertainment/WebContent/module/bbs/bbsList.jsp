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
		<!-- timepick -->
		<link href="<%=basePath %>media/timepick/css/font-awesome.min.css" rel="stylesheet">
		<link href="<%=basePath %>media/timepick/css/prettify-1.0.css" rel="stylesheet">
		<link href="<%=basePath %>media/timepick/css/bootstrap-datetimepicker.css" rel="stylesheet">
		<!-- timepickend -->
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
					<span>论坛管理</span><span>/</span><span>发帖管理</span>
			</div>
			<div class="title2">
					发帖管理
			</div>
		
		
			<div class="row">
				<div class="col-lg-12">
					<div class="content-panel">
				
							<!-- <button type="button" class="btn btn-theme04" id="removeBtn" >
								<i class="fa fa-remove"></i> 删除
							</button>
							<div class="form-group service_input">
								<select id="section"	class="select_input" style="width: 120px">
									<option value="">全部板块</option>
									
								</select>
								<input type="text" class="form-control" id="addName" placeholder="根据发布者查询" style="width: 12%">
								<input type="text" class="form-control" id="title" placeholder="根据标题查询" style="width: 12%">
								
								<div class="timepickerDiv">
									<input type="text" id="time1"
										style="positon:relative;width:120px;"
										class="form-control widthAuto" placeholder="开始时间"/>
								</div>
								
								<div class="timepickerDiv">
									<input type="text" id="time2"
										style="positon:relative;width:120px;"
										class="form-control widthAuto" placeholder="结束时间"/>
								</div>
								<button type="button" class="btn btn-theme03" id="finBtn">
									<i class="fa fa-search"></i> 查询
								</button>
								<button type="button" class="btn btn-theme02" id="searchReset">
									<i class="fa"></i> 重置
								</button>
							</div> -->
							 <div class="form-group service_input">
								<div class="left" style="flex-wrap:wrap;">
									<div style="width:100%;display:flex; margin-bottom:10px;">
										<div class="search-item">
												<div class="lab">发布者&nbsp;&nbsp;&nbsp;</div>
												<input type="text" class="form-control" id="addName" placeholder="">
											
											</div>
											<div class="search-item">
												<div class="lab">标题&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
												<input type="text" class="form-control" id="title" placeholder="">
											
											</div>
									
									</div>
								<div style="width:100%;display:flex;">
										<div class="search-item">
											
												<div class="lab">开始时间</div>
												<input type="text" class="form-control" id="time1" placeholder="">
											</div>
											<div class="search-item">
											
												<div class="lab">结束时间</div>
												<input type="text" class="form-control" id="time2" placeholder="">
											</div>
								
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
							
							
							
							
							
							
			
					<!-- 	<section id="no-more-tables">
								<table id="table_model" class="table table-bordered table-striped table-condensed cf">
								</table>
								<div id="page_moodel"></div>
						</section> -->
						<section id="no-more-tables" class="content">
								<div class="con-top">
									<div class="tit left">
									帖子列表
									</div>
									<div class="right">
									<button type="button" class="btn btn-theme04 sc" id="removeBtn">
										 删除
										</button> 
									<!-- 	<button type="button" class="btn btn-theme xz " id="addBtn">
										新增
										</button> -->
										
									
									</div>
									
							 
							</div>
							<div class="table-con" style="height:calc(100% - 162px);">
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
		<!-- timepick -->
		<script src="<%=basePath %>media/timepick/js/moment-with-locales.js"></script>
		<script src="<%=basePath %>media/timepick/js/bootstrap-datetimepicker.js"></script>
		<!-- timepickend -->
		<!-- 加载JS -->
		<script src="<%=basePath %>module/js/common.table.js"></script>
		<!-- 公用js -->
		<script src="<%=basePath %>module/js/common.js"></script>		
		<!-- 加载信息提示窗口 -->
		<script src="<%=basePath %>module/js/dialog.js"></script>
		<script src="<%=basePath %>module/js/messageModal.js"></script>
		<script src="<%=basePath %>module/bbs/bbsList.js"></script>
		<script type="text/javascript">
			$(function(){
				//查询条件重置
				$('#searchReset').click(function(){
					$("#searchReset").siblings("input").val('');
					$("#searchReset").siblings().find( "option:first").prop("selected", 'selected')
					$("#searchReset").siblings().find( "select").selectpicker('refresh');
					$("#time1").val('');
					$("#time2").val('');


				})
			});
		</script>
	</body>
</html>
