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

    <title>教育文化娱乐系统</title>
    <!-- Bootstrap core CSS -->
     <style>
    a:hover, a:focus {
    color: #2a6496;
    text-decoration: none !important;
}
   html,body{
    background:#fff;
    height:100vh;
    }
    #container{
    width:100%;
    height:100vh;
    }
    #main-content{
    height:calc(100vh - 48px);
    padding:12px 24px 24px 24px;
    background:#f5f5f5;
    }
    .header{
    	min-height:48px;
   
    }
    .menu{
    	position:absolute;
    	right:0;
    	top:4px;
    	height:40px;
    	line-height:40px;
    	padding-right:20px;
    	color:#fff;
    	
    	display:flex;
    }
    
    .nav-collapse{
    background:#fff !important;
    margin-top:64px !important;
    }
    
    aside{
    position:absolute;
    left:0;
    top:0;
    bottom:0;
    
    width:208px;
    }
#sidebar{
width:208px;
background:#fff;

}
    ul.sidebar-menu {
    margin-top: 0px;
}

ul.sidebar-menu, ul.sidebar-menu li ul.sub {
    margin: -2px 0 0;
    padding: 0;
}
#sidebar ul li {
    position: relative;
    background-color: #fff;
    
}
#sidebar ul li a span{
   
 
}
ul.sidebar-menu li {
    /* line-height: 20px !important; */
    margin-bottom: 0px;
    margin-left: 0px;
    margin-right: 0px;
   
    padding: 0;
    background-color:#fff;
    
}
.dcjq-parent .dcjq-icon{
display:inline-block;
width:15px;
height:15px;
float:right;
margin:17.5px 0;
  
   background: url("media/assets/img/newimg/simg/down.png") 0 0 no-repeat;
            background-size: 100% 100%;
}
.dcjq-parent.active .dcjq-icon{

  
   background-image: url("media/assets/img/newimg/simg/up.png") !important;
            background-size: 100% 100%;
}

ul.sidebar-menu li a{
display:block;
width:208px;
height:50px;
line-height:50px;

font-size: 14px;
font-family: PingFangSC-Regular, PingFang SC;
font-weight: 400;
color: #666;
padding:0 18px 0 42px;

position:relative;

z-index:10;


}
ul.sidebar-menu li a span{
color:#666;
}
ul.sidebar-menu li a i{
display:inline-block;
width:16px;
height:16px;

color:#666;
position:absolute;
left:18px;
top:16px;

}
i.fa{
 background-image: url("media/assets/img/newimg/simg/0.png") !important;
background-size: 100% 100%;
	
}
i.el-icon-setting{
 background-image: url("media/assets/img/newimg/simg/1.png") !important;
background-size: 100% 100%;
	
}
i.el-icon-info{
 background-image: url("media/assets/img/newimg/simg/2.png") !important;
background-size: 100% 100%;
	
}
i.el-icon-news{
 background-image: url("media/assets/img/newimg/simg/3.png") !important;
background-size: 100% 100%;
	
}
i.el-icon-chat-line-square{
 background-image: url("media/assets/img/newimg/simg/4.png") !important;
background-size: 100% 100%;
	
}
i.el-icon-suitcase-1{
 background-image: url("media/assets/img/newimg/simg/5.png") !important;
background-size: 100% 100%;
	
}
i.el-icon-edit-outline{
 background-image: url("media/assets/img/newimg/simg/6.png") !important;
background-size: 100% 100%;
	
}
i.el-icon-s-grid{
 background-image: url("media/assets/img/newimg/simg/7.png") !important;
background-size: 100% 100%;
	
}
i.el-icon-video-camera{
 background-image: url("media/assets/img/newimg/simg/8.png") !important;
background-size: 100% 100%;
	
}
ul.sub{
width:208px;
}
ul.sub li{
width:208px;
}

ul.sub li a{
width:208px;
height:50px;
line-height:50px;

font-size: 14px;
font-family: PingFangSC-Regular, PingFang SC;
font-weight: 400;
color: #666;
padding:0 18px 0 42px;
 text-decoration:none;

}
ul.sub li a span{
color:#666;
z-index:100;
}
.mt {
    margin-top: 0px;
}
 ul.sidebar-menu li > a:active,ul.sidebar-menu li > a:hover,ul.sidebar-menu li > a:focus{
    background: #E6F7FF;
    color: #1890FF !important;
    display: block;
    text-decoration:none;
    border-right:2px solid #1890ff;
}
ul.sidebar-menu li.dcjq-parent-li > a:active,ul.sidebar-menu li.dcjq-parent-li >a:focus{
    background: #fff !important;
    border:none !important;
   
}

 ul.sidebar-menu li.dcjq-parent-li a:v{
 }

 ul.sidebar-menu li a:hover span{
  underline:none;
    color: #1890FF !important;
  
}


ul.sub li a.active, ul.sub li a:hover, ul.sub li a:focus {
    background: #E6F7FF;
    color: #1890FF !important;
    display: block;
    text-decoration:none;
   

     border-right:2px solid #1890ff;
}
ul.sub li a.active span, ul.sub li a:hover span, ul.sub li a:focus span{
 
    color: #1890FF !important;
     text-decoration:none;
  
}

 
    </style>
    <link href="media/assets/css/bootstrap.css" rel="stylesheet">
    <!--external css-->
    <link rel="stylesheet" type="text/css" href="media/assets/font-awesome/css/font-awesome.css" />
    <link rel="stylesheet" type="text/css" href="media/assets/css/zabuto_calendar.css">
    <link rel="stylesheet" type="text/css" href="media/assets/js/gritter/css/jquery.gritter.css" />
    <link rel="stylesheet" type="text/css" href="media/assets/lineicons/style.css">        
    <!-- Custom styles for this template -->
    <!-- <link href="media/assets/css/style.css" rel="stylesheet"> -->
    <link href="media/assets/css/style-responsive.css" rel="stylesheet">
    <link href="<%=basePath %>module/css/newcommon.css" rel="stylesheet">
    <script src="media/assets/js/chart-master/Chart.js"></script>  
    <style>
    a:hover, a:focus {
    color: #2a6496;
    text-decoration: none !important;
}
   html,body{
    background:#fff;
    height:100vh;
    }
    #container{
    width:100%;
    height:100vh;
    }
    #main-content{
    height:calc(100vh - 48px);
    padding:12px 24px 24px 24px;
    background:#f5f5f5;
    }
    .header{
    	min-height:48px;
   
    }
    .menu{
    	position:absolute;
    	right:0;
    	top:4px;
    	height:40px;
    	line-height:40px;
    	padding-right:20px;
    	color:#fff;
    	
    	display:flex;
    }
    
    .nav-collapse{
    background:#fff !important;
    margin-top:64px !important;
    }
    
    aside{
    position:absolute;
    left:0;
    top:0;
    bottom:0;
    
    width:208px;
    }
#sidebar{
width:208px;
background:#fff;

}
    ul.sidebar-menu {
    margin-top: 0px;
}

ul.sidebar-menu, ul.sidebar-menu li ul.sub {
    margin: -2px 0 0;
    padding: 0;
}
#sidebar ul li {
    position: relative;
    background-color: #fff;
    
}
#sidebar ul li a span{
   
 
}
ul.sidebar-menu li {
    /* line-height: 20px !important; */
    margin-bottom: 0px;
    margin-left: 0px;
    margin-right: 0px;
   
    padding: 0;
    background-color:#fff;
    
}
.dcjq-parent .dcjq-icon{
display:inline-block;
width:15px;
height:15px;
float:right;
margin:17.5px 0;
  
   background: url("media/assets/img/newimg/simg/down.png") 0 0 no-repeat;
            background-size: 100% 100%;
}
.dcjq-parent.active .dcjq-icon{

  
   background-image: url("media/assets/img/newimg/simg/up.png") !important;
            background-size: 100% 100%;
}

ul.sidebar-menu li a{
display:block;
width:208px;
height:50px;
line-height:50px;

font-size: 14px;
font-family: PingFangSC-Regular, PingFang SC;
font-weight: 400;
color: #666;
padding:0 18px 0 42px;

position:relative;

z-index:10;


}
ul.sidebar-menu li a span{

 color: 666;
}
ul.sidebar-menu li a i{
display:inline-block;
width:16px;
height:16px;

color:#666;
position:absolute;
left:18px;
top:16px;

}
i.fa{
 background-image: url("media/assets/img/newimg/simg/0.png") !important;
background-size: 100% 100%;
	
}
i.el-icon-setting{
 background-image: url("media/assets/img/newimg/simg/1.png") !important;
background-size: 100% 100%;
	
}
i.el-icon-info{
 background-image: url("media/assets/img/newimg/simg/2.png") !important;
background-size: 100% 100%;
	
}
i.el-icon-news{
 background-image: url("media/assets/img/newimg/simg/3.png") !important;
background-size: 100% 100%;
	
}
i.el-icon-chat-line-square{
 background-image: url("media/assets/img/newimg/simg/4.png") !important;
background-size: 100% 100%;
	
}
i.el-icon-suitcase-1{
 background-image: url("media/assets/img/newimg/simg/5.png") !important;
background-size: 100% 100%;
	
}
i.el-icon-edit-outline{
 background-image: url("media/assets/img/newimg/simg/6.png") !important;
background-size: 100% 100%;
	
}
i.el-icon-s-grid{
 background-image: url("media/assets/img/newimg/simg/7.png") !important;
background-size: 100% 100%;
	
}
i.el-icon-video-camera{
 background-image: url("media/assets/img/newimg/simg/8.png") !important;
background-size: 100% 100%;
	
}
ul.sub{
width:208px;
}
ul.sub li{
width:208px;
}

ul.sub li a{
width:208px;
height:50px;
line-height:50px;

font-size: 14px;
font-family: PingFangSC-Regular, PingFang SC;
font-weight: 400;
color: #666;
padding:0 18px 0 42px;
 text-decoration:none;

}
ul.sub li a span{
color:#666;
z-index:100;
}
.mt {
    margin-top: 0px;
}
 ul.sidebar-menu li > a:active,ul.sidebar-menu li > a:hover,ul.sidebar-menu li > a:focus{
    background: #E6F7FF;
    color: #1890FF !important;
    display: block;
    text-decoration:none;
    border-right:2px solid #1890ff;
}
ul.sidebar-menu li.dcjq-parent-li > a:active,ul.sidebar-menu li.dcjq-parent-li >a:focus{
    background: #fff !important;
    border:none !important;
   
}
/* ul.sidebar-menu li.dcjq-parent > a:active span,ul.sidebar-menu li.dcjq-parent >a:focus span{
color:#666 !important;

} */

 ul.sidebar-menu li a:hover span{
  underline:none;
    color: #1890FF !important;
  
}


ul.sub li a.active, ul.sub li a:hover, ul.sub li a:focus {
    background: #E6F7FF;
    color: #1890FF !important;
    display: block;
    text-decoration:none;
   

     border-right:2px solid #1890ff;
}
ul.sub li a.active span, ul.sub li a:hover span, ul.sub li a:focus span{
 
    color: #1890FF !important;
     text-decoration:none;
  
}

 
    </style>
  </head>
  <body>
   <section id="container" >
      <!-- **********************************************************************************************************************************************************
      TOP BAR CONTENT & NOTIFICATIONS
      *********************************************************************************************************************************************************** -->
      <!--header start-->
      <header class="header black-bg"  style="height: 48px;    line-height: 48px;
background: #001529;">
             <!--  <div class="sidebar-toggle-box">
                  <div class="fa fa-bars tooltips" data-placement="right" ></div>data-original-title="Toggle Navigation"
              </div> -->
            <!--logo start-->
           <a href='javascript:void(0)' target='h_content' style="height:48px; line-height:48px; margin-left:10px;">
           <img src='media/assets/img/ui-sam.jpg' class='img-circle' style='width:34px;height:36px;margin-top: auto 0 auto 0;' >
          </a>
            <a href="index.html" class="logo" style="margin:auto 0 auto 12px;font-size: 18px;
font-family: WeibeiSC-Bold, WeibeiSC;
font-weight: bold;
color: #FFFFFF;">教育文化娱乐后台管理系统</a>
            <!--logo end-->
          
            <div class="menu">
            	<div class="">
            	<img style="border:1px solid #fff; border-radius:50%;"  width="15px" height="15px">
            		<span style="font-size: 15px;margin-right: 20px; text-transform: none;color: #fff">${userName}</span>
            	</div>
                   <a class="logout" href="logout.html" style="text-decoration: none;color:#fff;"><img src="media/assets/img/newimg/simg/logout.png" width="13px" height="13px">退出</a>
            	
            </div>
        </header>
      <!--header end-->
      
      <!-- **********************************************************************************************************************************************************
      MAIN SIDEBAR MENU
      *********************************************************************************************************************************************************** -->
      <!--sidebar start-->
      <aside>
          <div id="sidebar"  class="nav-collapse " >
              <!-- sidebar menu start-->
			  <!--     <ul class='sidebar-menu' id='nav-accordion'><p class='centered'><a href='profile.html'><img src='media/assets/img/ui-sam.jpg' class='img-circle' width='60'></a></p><h5 class='centered'>Marcel Newman</h5><li class='mt'><a class='active' href='touserlist.html'  target='h_content'><i class='fa fa-dashboard'></i><span>首页</span></a></li><shiro:hasAnyRoles name="管理员"><li class='sub-menu'><a href='touserlist.html'  target='h_content'><i class='fa fa-th'></i><span>权限管理</span></a></li></shiro:hasAnyRoles><shiro:hasAnyRoles name="管理员"><li class='sub-menu'><a href=javascript:;><i class='fa fa-bar-chart-o'></i><span>日志管理</span></a><ul class='sub'><li><a  href='touserlist1.html' target='h_content'>积分转账日志</a></li><li><a  href='touserlist.html' target='h_content'>积分交易日志</a></li></ul></li></shiro:hasAnyRoles><shiro:hasAnyRoles name="管理员"><li class='sub-menu'><a href='touserlist1.html'  target='h_content'><i class='fa fa-tasks'></i><span>交易管理</span></a></li></shiro:hasAnyRoles><shiro:hasAnyRoles name="普通用户,管理员"><li class='sub-menu'><a href='memberManage.html'  target='h_content'><i class='fa fa-tasks'></i><span>会员管理</span></a></li></shiro:hasAnyRoles></ul> -->
               ${menu}
              <!-- sidebar menu end-->
          </div>
      </aside>
      <!--sidebar end-->
      
      <!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->
      <!--main content start-->
	<section id="main-content" style="margin-left:208px;">
		<iframe src="home.html" name="h_content" width="100%" height="100%" align="center" scrolling="auto" frameborder="0" allowTransparency="true"></iframe>
	</section>
      <!--main content end-->
      <!--footer start-->
    <!--   <footer class="site-footer">
          <div class="text-center">
              2014 - Alvarez.is  
              <a href="index.html#" class="go-top">
                  <i class="fa fa-angle-up"></i>
              </a>
          </div>
      </footer> -->
      <!--footer end-->
  </section>
  
  	<script src="media/assets/js/jquery.js"></script>
    <script src="media/assets/js/jquery-1.8.3.min.js"></script>
    
   <!--  <script type="text/javascript">
    $(function(){
    	$('.sidebar-menu').find('i').each(function(index,e){
			console.log("e",e)
		}); 
    	
    	
    	/* $('.sidebar-menu').children() */
    	
    })
    
    
    
    </script> -->
    <script src="media/assets/js/bootstrap.min.js"></script>
    <script src="media/assets/js/jquery.dcjqaccordion.2.7.js"></script>
    <script src="media/assets/js/jquery.scrollTo.min.js"></script>
    <script src="media/assets/js/jquery.nicescroll.js"></script>
    <script src="media/assets/js/jquery.sparkline.js"></script>
    <!--common script for all pages-->
    <script src="media/assets/js/common-scripts.js"></script>    
    <script src="media/assets/js/gritter/js/jquery.gritter.js"></script>
    <script src="media/assets/js/gritter-conf.js"></script>
    <!--script for this page-->
    <script src="media/assets/js/sparkline-chart.js"></script>    
	<script src="media/assets/js/zabuto_calendar.js"></script>  	
    <!-- <script src="media/assets/js/jquery.js"></script>
    <script src="media/assets/js/jquery-1.8.3.min.js"></script>
    <script src="media/assets/js/bootstrap.min.js"></script> 
    <script src="media/assets/js/jquery.scrollTo.min.js"></script>
    <script src="media/assets/js/jquery.nicescroll.js" type="text/javascript"></script>
    <script src="media/assets/js/jquery.sparkline.js"></script>
    <script src="media/assets/js/common-scripts.js"></script>
    <script type="text/javascript" src="media/assets/js/jquery.dcjqaccordion.2.7.js"></script>
    <script type="text/javascript" src="media/assets/js/gritter/js/jquery.gritter.js"></script>
    <script type="text/javascript" src="media/assets/js/gritter-conf.js"></script> -->
  </body>
</html>
