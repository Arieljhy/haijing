<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="Dashboard">
<meta name="keyword"
	content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
<title></title>
<!-- Bootstrap core CSS -->
<link href="<%=basePath%>media/assets/css/bootstrap_new.css"
	rel="stylesheet" />
<link href="<%=basePath%>media/assets/css/bootstrap.css"
	rel="stylesheet">
<!--external css	图标-->
<link
	href="<%=basePath%>media/assets/font-awesome/css/font-awesome.css"
	rel="stylesheet" />
<!-- Custom styles for this template -->
<link href="<%=basePath%>media/assets/css/style.css" rel="stylesheet">
<link href="<%=basePath%>media/assets/css/style-responsive.css"
	rel="stylesheet">
<link href="<%=basePath%>media/assets/css/table-responsive.css"
	rel="stylesheet">
<link href="<%=basePath%>media/assets/css/zabuto_calendar.css"
	rel="stylesheet">
<link href="<%=basePath%>media/assets/css/bootstrap-select.min.css"
	rel="stylesheet">
<link href="<%=basePath%>module/css/common.css" rel="stylesheet">

<style type="text/css">
.select_input {
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
	<section class="wrapper">
		
		<div class="row mt">
			<div class="col-lg-12">
				<div class="content-panel">
					<h4 id="title"></h4>
					
					<ul	id="anserUl"	style="list-style-type:none;">
						<!-- <li>A:1111(分值：0)</li>
						<li>B:1111(分值：0)</li>
						<li>C:1111(分值：0)</li>
						<li>D:1111(分值：0)</li> -->
					</ul>
					<h5	id="str"></h5>
					<h5 id="remark"></h5>
					<button type="button" id="back" class="btn btn-default">返回</button>
				</div>
			</div>
		</div>
	</section>

	<!-- js placed at the end of the document so the pages load faster -->
	<script src="<%=basePath%>media/assets/js/jquery.js"></script>
	<script src="<%=basePath%>media/assets/js/bootstrap.min.js"></script>
	<script src="<%=basePath%>media/assets/js/jquery.dcjqaccordion.2.7.js"></script>
	<script src="<%=basePath%>media/assets/js/jquery.scrollTo.min.js"></script>
	<script src="<%=basePath%>media/assets/js/jquery.nicescroll.js"></script>
	<script src="<%=basePath%>media/assets/js/jquery.sparkline.js"></script>
	<script src="<%=basePath%>media/assets/js/bootstrap-select.min.js"></script>
	<!--common script for all pages-->
	<script src="<%=basePath%>media/assets/js/common-scripts.js"></script>
	<script
		src="<%=basePath%>media/assets/js/gritter/js/jquery.gritter.js"></script>
	<script src="<%=basePath%>media/assets/js/gritter-conf.js"></script>
	<script src="<%=basePath%>media/assets/js/sparkline-chart.js"></script>
	<script src="<%=basePath%>media/assets/js/zabuto_calendar.js"></script>

	<!-- 加载JS -->
	<script src="<%=basePath%>module/js/common.table.js"></script>
	<!-- 公用js -->
	<script src="<%=basePath%>module/js/common.js"></script>
	<!-- 加载信息提示窗口 -->
	<script src="<%=basePath%>module/js/dialog.js"></script>

	<script src="<%=basePath %>module/study/anserDetail.js"></script>
</body>
</html>
