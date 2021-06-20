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
		<title>登录</title>
		<link href="media/assets/css/bootstrap.css" rel="stylesheet">
		<link href="media/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
		<link href="media/assets/css/zabuto_calendar.css" rel="stylesheet">
		<link href="media/assets/js/gritter/css/jquery.gritter.css" rel="stylesheet"/>
		<link href="media/assets/lineicons/style.css" rel="stylesheet">
		<link href="media/assets/css/style.css" rel="stylesheet">
		<link href="media/assets/css/style-responsive.css" rel="stylesheet">	
		<style>
			/*LOGIN CONFIGURATION PAGE*/
				#login-page{
					width:100% !important;
					height:100vh !important;
					overflow:hidden;
				}
				.container{
					width:100% !important;
					height:100vh !important;
					margin:0 !important;
					padding:0;
			
				}
				.login-con{
					width:300px;
					height:600px;
					position:absolute;
					
					top:50%;
					left:50%;
					
					margin-top:-300px;
					margin-left:-150px;
			
				
				}
				
				.header-con{
				height:250px;
				text-align:center;
				margin-bottom:30px;
					
				}
				.header-con img{
					width:150px;
					height:150px;
					margin:20px auto;
					
					
				}
				.header-con div.tit1{
					font-size:26px;
					font-weight:600;
					 color:#fff !important;
				}
				.header-con div.tit2{
					font-size:18px;
			
					color:#fff !important;
				}
				
				
				.form-login {
					max-width: 330px;
					margin: 0 auto ;
					background:transparent !important;
					
					border-radius: 5px;
					-webkit-border-radius: 5px;
				}
				
				.login-wrap input{
				background:transparent;
				color:#fff;
				}
				
				.save-con{
					margin-bottom:40px;
					height:20px;
					line-height:20px;
				}
				.save-con input{
				display:inline-block;
				margin:auto 0;
					
				}
				.save-con span{
				margin-left:5px;
					display:inline-block;
						color:#fff;
				}
				.sbtn{
					width:260px;
					height:40px;
					border-radius:8px;
				
					border:none;
					 background: linear-gradient(to right, rgb(255,179,105)  , rgb(202,127,42) ) ;
  					background: -moz-linear-gradient(to right, rgb(255,179,105)  , rgb(202,127,42) ) ;
				  
				    background: -webkit-linear-gradient(to right, rgb(255,179,105)  , rgb(202,127,42) );
				    background: -o-linear-gradient(to right, rgb(255,179,105)  , rgb(202,127,42) );
				    background: -ms-linear-gradient(to right, rgb(255,179,105)  , rgb(202,127,42) );  
				}
			
				
				
				.login-wrap {
					padding: 20px;
					background:transparent;
				}
				.login-wrap .registration {
					text-align: center;
				}
				.login-social-link {
					display: block;
					margin-top: 20px;
					margin-bottom: 15px;
				}
				input.form-control{
				border:1px solid #fff !important;
				}
				

				input::-webkit-input-placeholder{
				　　color:#fff;　　
				}
				input::-moz-input-placeholder{
				　　color:#fff;　　
				}
				input::-ms-input-placeholder{
				　　color:#fff;　　
				}
				input::-o-input-placeholder{
				　　color:#fff;　　
				}
			
		</style>
	</head>
	<body>
		<div id="login-page">
			<div class="container">
				<div class="login-con">
					<div class="header-con">
						<img src="media/assets/img/ui-sam.jpg" />
						<div class="tit1">教育文化娱乐系统</div>
						<div class="tit2">Entertainment System</div>
					</div>
				
				
					<form class="form-login" action="login.html" id="nmLoginForm" method="post">
						<div class="login-wrap">
							<input type="text" class="form-control" placeholder="登录账号" name="username" id="username" autofocus>
							<br>
							<input type="password" class="form-control" placeholder="密码" name="password" id="password">
							<br>
							<div class="save-con">
							<input type="checkbox" /><span>保存密码</span>
							</div>
							
							
							<button class="sbtn" type="button"  onclick="checkUser()"><i class="fa fa-lock"></i> 登录</button>
						
						</div>
					</form>
				</div>
			
			
				
				
			</div>
		</div>
		<input id ="error" type="hidden" value="${error}"/>
		<script src="media/assets/js/jquery.js"></script>
		<script src="media/assets/js/jquery-1.8.3.min.js"></script>
		<script src="media/assets/js/bootstrap.min.js"></script>
		<script src="media/assets/js/jquery.dcjqaccordion.2.7.js"></script>
	<!-- 	<script src="media/assets/js/jquery.nicescroll.js" type="text/javascript"></script> -->
		<script src="media/assets/js/jquery.sparkline.js"></script>
		<script src="media/assets/js/common-scripts.js"></script>
		<script src="media/assets/js/gritter/js/jquery.gritter.js"></script>
		<script src="media/assets/js/gritter-conf.js"></script>
		<script src="media/assets/js/jquery.backstretch.min.js"></script>
        <script src="media/js/messageModal.js"></script>
        <!-- 加载信息提示窗口 -->
        <script src="media/js/md5.js"></script>
		<script>
            if (window != top) {
                top.location.href = location.href;
            }
            $(function(){
                var error = document.getElementById("error").value;
                if(error!=null&&error!=""){
                   // alert(error);
                    messageWin.alert("提示", error);
                }
            });
            function checkUser(){
                var username = document.getElementById("username").value;
                var password = document.getElementById("password").value;
                if(password == ""  ){
                     //alert("密码不能为空");
                   messageWin.alert("提示", "密码不能为空");
                    return false;
                }
                password=hex_md5(password);
                alert(password)
                $("#password").val(password);
                if(username == ""  ){
                   //alert("用户名不能为空");
                   messageWin.alert("提示", "登录账号不能为空");
                    return false;
                }
                document.getElementById("nmLoginForm").submit();
            }
		$.backstretch("media/assets/img/newimg/bg.png", {speed: 500});
   		</script>		
	</body>
</html>
