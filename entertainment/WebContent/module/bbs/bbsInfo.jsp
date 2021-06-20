<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
		<!--external css-->
		<link href="<%=basePath %>media/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />		
		<!-- Custom styles for this template -->
		<link href="<%=basePath %>media/assets/css/style.css" rel="stylesheet">
		<link href="<%=basePath %>media/assets/css/style-responsive.css" rel="stylesheet">		
		<link href="<%=basePath %>media/assets/css/table-responsive.css" rel="stylesheet">
		<link href="<%=basePath %>media/assets/css/zabuto_calendar.css" rel="stylesheet" >
		<link href="<%=basePath %>module/css/common.css" rel="stylesheet">
			<link href="<%=basePath %>module/css/newcommon.css" rel="stylesheet">
		<!-- timepick -->
		<link href="<%=basePath %>media/timepick/css/font-awesome.min.css" rel="stylesheet">
		<link href="<%=basePath %>media/timepick/css/prettify-1.0.css" rel="stylesheet">
		<link href="<%=basePath %>media/timepick/css/bootstrap-datetimepicker.css" rel="stylesheet">
		
		<!-- timepickend -->
	
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
		<div class="wrapper w1 bordertopbotton" style="background-color:transparent !important;">
		<div class="title1">
					<span>论坛管理</span><span>/</span><span class="fh">发帖管理</span><span>>></span><span id="ttt">修改</span>
			</div>
			<div class="title2">
			<!-- id="title" -->
			
					<div class="left" id="htmlTitle" ></div>
					<div class="right">
					<button type="button" class="btn btn-default fh" >返回</button>
						
					</div>	
			</div>
		
		<!-- 	<h3 class="wrapper-top" id="htmlTitle">
				
			</h3> -->
			<div class="row">
				<div class=" col-lg-12 content-panel" style="background:#fff;padding:25px 0 0 0;">
				<div class="wrapper-body">
				<input type="hidden" value="0" id="bbsId">
				<input type="hidden" value="0" id="type">
				
				<div class="inputgroup" id="hide2" style="display: none">
					<label class="control-label">帖子标题</label>
					<input id="title" type="text" maxlength="30" class="form-control widthAuto"/>
				</div>
				<div class="inputgroup " id="hide1" style="display: none">
					<label class="control-label">发帖板块：</label>
					 <select id="section"	class="form-control widthAuto">
							<option value="">全部板块</option>
					</select>
				</div>
				<div class="inputgroup" >
					<label class="control-label">帖子内容：</label>
					<textarea id="content"	rows="5" cols="80" placeholder="请在此处输入帖子内容..."	maxlength="200" style="word-wrap: break-word;word-break:break-all;">
					</textarea>
				</div>
				<div class="inputgroup" >
					<input	type="hidden"	id="imageList"	value="">
					<input type="file" id="file" multiple style="display: none;"  />
					<label class="control-label">帖子图片：</label>
					
					<span  >
						<button type="button" class="btn btn-theme03" id="xmlBtn">
								<i class="fa fa-upload"></i> 上 &nbsp;&nbsp;&nbsp;传
						</button>
					</span><br>
					<hr>
					<ul	id="imageUl"	style="list-style: none;display: inline-block;" ></ul>
					<hr>
					
				</div>
				<div class="inputgroup">
					<button type="button" id="backBtn" class="btn btn-default" data-dismiss="modal" style="display:none;">返回</button>
					<button class="btn btn-primary" type="button" id="sumbitBtn" style="display:none;">提交</button>
				</div>
			</div>
				
				</div>
			</div>
			
		</div>
		
		<!-- js placed at the end of the document so the pages load faster -->
		<script src="<%=basePath %>media/assets/js/jquery.js"></script>
		<script type="text/javascript">
		
			$('.fh').click(function(){
				$("#backBtn").trigger('click');
			})
			$('.tj').click(function(){
				$("#submitBtn").trigger('click');
			})
		</script>
		<script src="<%=basePath %>media/assets/js/bootstrap.min.js"></script>
		<script src="<%=basePath %>media/assets/js/jquery.dcjqaccordion.2.7.js"></script>
		<script src="<%=basePath %>media/assets/js/jquery.scrollTo.min.js"></script>
		<script src="<%=basePath %>media/assets/js/jquery.nicescroll.js" type="text/javascript"></script>
		
		<script src="<%=basePath %>media/assets/js/jquery-ui-1.9.2.custom.min.js"></script>
		
		<!-- timepick -->
		<script src="<%=basePath %>media/timepick/js/moment-with-locales.js"></script>
		<script src="<%=basePath %>media/timepick/js/bootstrap-datetimepicker.js"></script>
		<!-- timepickend -->
		
		<!-- 富文本框 -->
		<script type="text/javascript" src="<%=basePath %>media/editor/ueditor.config.js"></script>
		<!-- 编辑器源码文件 -->
		<script type="text/javascript" src="<%=basePath %>media/editor/ueditor.all.js"></script>
		<script type="text/javascript" src="<%=basePath %>media/editor/kityformula-plugin/addKityFormulaDialog.js"></script>
		<script type="text/javascript" src="<%=basePath %>media/editor/kityformula-plugin/getKfContent.js"></script>
		<script type="text/javascript" src="<%=basePath %>media/editor/kityformula-plugin/defaultFilterFix.js"></script>
		
		<!-- 加载JS -->
		<script src="<%=basePath %>module/js/common.table.js"></script>
		<!-- 公用js -->
		<script src="<%=basePath %>module/js/common.js"></script>		
		<!-- 加载信息提示窗口 -->
		<script src="<%=basePath %>module/js/messageModal.js"></script>
		<!-- 下拉多选框 -->
		<script src="<%=basePath %>module/js/select.js"></script>
		<script src="<%=basePath %>module/js/region.js"></script>
		<!-- 加载页面JS -->
		<script src="<%=basePath %>module/bbs/bbsInfo.js"></script>
	</body>
</html>
