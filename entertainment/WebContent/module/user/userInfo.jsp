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
		<link href="<%=basePath %>media/assets/css/bootstrap.css" rel="stylesheet">
		<!--external css-->
		<link href="<%=basePath %>media/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />		
		
		<!-- timepick -->
		<link href="<%=basePath %>media/timepick/css/font-awesome.min.css" rel="stylesheet">
		<link href="<%=basePath %>media/timepick/css/prettify-1.0.css" rel="stylesheet">
		<link href="<%=basePath %>media/timepick/css/bootstrap-datetimepicker.css" rel="stylesheet">
		<!-- timepickend -->
		
		<!-- Custom styles for this template -->
		<link href="<%=basePath %>media/assets/css/style.css" rel="stylesheet">
		<link href="<%=basePath %>module/css/common.css" rel="stylesheet">
		<link href="<%=basePath %>module/css/newcommon.css" rel="stylesheet">
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
		<section class="wrapper w1">
		
		<div class="title1">
					<span>系统管理</span><span>/</span><span class="fh">用户管理</span><span>>></span><span>新增用户</span>
			</div>
			<div class="title2">
					<div class="left">新增用户</div>
					<div class="right">
								<button type="button" class="btn btn-default fh" >返回</button>
								<button type="button"  class="btn btn-info tj">提交</button>
					</div>	
			</div>
			
			
		<div class="row">
			<div class="col-lg-12 content-panel" class="clee" style="background:#fff;padding:25px 0 0 0;">
		
				<form class="myform" action="" id="updateUserForm">
					<div class="row show-grid">
						<div class="col-xs-12 col-sm-9 form-horizontal col-lg-9" style="display:inline-block;">
							<div class="form-group">
								<label class="col-sm-2 control-label" for="name">姓名</label>
								<div class="col-sm-10">
									<input type="text" data-trigger="" class="form-control" data-content="必填" data-placement="top" id="name" placeholder="姓名">
								</div>
							</div>
							<input value="${department[0].id}" type="hidden"/>
							<div class="form-group">
								<label class="col-sm-2 control-label" for="department">部门</label>
								<div class="col-sm-10">
									<select class="form-control widthAuto" id="department">
										<c:forEach items="${department}" varStatus="i" var="item" >
											<option value="${item.id}">${item.name}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label" for="userCode">账号</label>
								<div class="col-sm-10">
									<input type="text" data-trigger="" class="form-control" data-content="必填" id="userCode" placeholder="账号"
										   data-options="required:true,validType:isRightfulString,length:20"
										   data-toggle="popover" data-placement="top" >
									<input type="hidden" id="userCode1" >
									<p id="userCodeP" style="color:red;font-size:14px;display:none;"></p>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label" for="password">密码</label>
								<div class="col-sm-10">
									<input type="password" data-trigger="" data-content="必填" data-placement="top" class="form-control" id="password" placeholder="密码"
										   data-options="required:true,validType:isPwd"
										   data-toggle="popover" data-placement="top" >
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label" for=militaryDate>入伍时间</label>
								<div class="col-sm-10">
									<div class="datepicker">
										<input type="datetime" data-trigger="" data-content="必填" data-placement="top" class="form-control" id="militaryDate" placeholder="入伍时间">
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label" >证件类型</label>
								<div class="col-sm-10" id="certificateType">
									<button type="button" data-type="0" class="btn btn-primary active">身份证</button>
									<button type="button" data-type="1" class="btn btn-default">军官证</button>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label" for="certificateCode">证件号码</label>
								<div class="col-sm-10">
									<input type="text" data-trigger="" data-content="必填" class="form-control" id="certificateCode" placeholder="证件号码"
                                           data-options="required:true,validType:isRightfulString1,length:20"
                                           data-toggle="popover" data-placement="top" >
                                    <input id="certificateCodeP" type="hidden" >
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label" for="state">状态</label>
								<div class="col-sm-10">
									<div class="switch" off-label="禁用" on-label="启用">
										<input class="switch-small" id="state" type="checkbox" checked="true" data-size="small">
									</div>
									
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">身份</label>
								<div class="col-sm-10" id="userType">
									<button type="button" data-type="1" class="btn btn-primary active">普通</button>
									<button type="button" data-type="2" class="btn btn-default">管理员</button>
								</div>
							</div>
							
						</div>
						<div class="col-xs-12  col-sm-3 form-horizontal col-lg-3" style="display:inline-block; background:#fff;">
							<div class="form-group">

								<div class="uploadFile">
									<img id="icon" src="<%=basePath %>media/img/2.0_icon_11.png" />
									<a class="btn btn-default" href="javascript:;" role="button">
										上传图片
										<input id="icon_input" type="file"/>
									</a>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group">
							<div class="col-sm-8">
								<button type="button" id="back" class="btn btn-default" style="display:none;">返回</button>
								<button type="button" id="submit" class="btn btn-info" style="display:none;">提交</button>
							</div>	
						</div>
					</div>
				</form>
			</div>
		</div>
		</section>
		
		<!-- js placed at the end of the document so the pages load faster -->
		<script src="<%=basePath %>media/assets/js/jquery.js"></script>
		<script type="text/javascript">
		
			$('.fh').click(function(){
				$("#back").trigger('click');
			})
			$('.tj').click(function(){
				$("#submit").trigger('click');
			})
		</script>
		<script src="<%=basePath %>media/assets/js/bootstrap.min.js"></script>
		<!-- switch -->
		<script src="<%=basePath %>media/assets/js/bootstrap-switch.js"></script>
		
		<!-- timepick -->
		<script src="<%=basePath %>media/timepick/js/moment-with-locales.js"></script>
		<script src="<%=basePath %>media/timepick/js/bootstrap-datetimepicker.js"></script>
		<!-- timepickend -->
        <!--数据验证 -->
        <script src="<%=basePath %>module/js/validator.js"></script>
		<!-- 加载JS -->
		<script src="<%=basePath %>module/js/table.js"></script>
		<!-- 公用js -->
		<script src="<%=basePath %>module/js/common.js"></script>		
		<!-- 加载信息提示窗口 -->
		<script src="<%=basePath %>module/js/dialog.js"></script>
		<!-- 加载信息提示窗口 -->
		<script src="<%=basePath %>module/js/messageModal.js"></script>
		<script src="<%=basePath %>media/js/md5.js"></script>

		<!-- 加载页面JS -->
		<script src="<%=basePath %>module/user/userInfo.js"></script>

	</body>
</html>
