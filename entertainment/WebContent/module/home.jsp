<%@ page language="java" import="java.util.*" pageEncoding="Utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String url = request.getRequestURL().toString();
	url = url.substring(0, url.indexOf('/', url.indexOf("//") + 2));
	String context = request.getContextPath();
	url += context;
	application.setAttribute("ctx", url);
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Dashboard">
    <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

    <title>DASHGUM - Bootstrap Admin Template</title>

    <!-- Bootstrap core CSS -->
    <link href="media/assets/css/bootstrap.css" rel="stylesheet">
    <!--external css-->
    <link href="media/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
	<link rel="stylesheet" href="http://cdn.oesmith.co.uk/morris-0.4.3.min.css">    
        
    <!-- Custom styles for this template -->
    <link href="media/assets/css/style.css" rel="stylesheet">
    <link href="media/assets/css/style-responsive.css" rel="stylesheet">
    <style>
    	html,body{
    		width:100%;
    		height:100%;
    	}
    	#container{
    		background:url(media/assets/img/newimg/bg.png);
    		background-size:100% 100%;
    		width:100%;
    		height:100%;
    	}
    </style>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

   <!--main content start-->
    <section id="container">
       
    </section>
         
<!--     js placed at the end of the document so the pages load faster
    <script src="media/assets/js/jquery.js"></script>
    <script src="media/assets/js/bootstrap.min.js"></script>
    <script class="include" type="text/javascript" src="media/assets/js/jquery.dcjqaccordion.2.7.js"></script>
    <script src="media/assets/js/jquery.scrollTo.min.js"></script>
    <script src="media/assets/js/jquery.nicescroll.js" type="text/javascript"></script>


    common script for all pages
	<script src="media/js/raphael-min.js"></script>
	 <script src="media/assets/js/common-scripts.js"></script>
    <script src="media/js/highcharts.js"></script>
	
    script for this page
    <script src="media/assets/js/morris-conf.js"></script> -->
    
  </body>
</html>
