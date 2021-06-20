<%@ page language="java" import="java.util.*" pageEncoding="Utf-8"%>
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
		<!--external css-->
		<link href="<%=basePath %>media/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />		
		<!-- Custom styles for this template -->
		<link href="<%=basePath %>media/assets/css/style.css" rel="stylesheet">
		<link href="<%=basePath %>media/assets/css/style-responsive.css" rel="stylesheet">		
		<link href="<%=basePath %>media/assets/css/table-responsive.css" rel="stylesheet">
		<link href="<%=basePath %>media/assets/css/zabuto_calendar.css" rel="stylesheet">
		<link href="<%=basePath %>media/assets/css/bootstrap-select.min.css" rel="stylesheet">
		<link href="<%=basePath %>module/css/common.css" rel="stylesheet">	
		<script type="text/javascript">
			function ok(){
			  if( $("#pwd3").val()!= $("#pwd2").val()){
			  	  alert("两次密码输入不一致");
			  	  return;
			  }
			  $.ajax({
				type:"post",
				url:"updatePassword.html",
				data:{pwd1:$("#pwd1").val(),pwd2:$("#pwd2").val()},
				success:function(data){
					if(data.flag){				
						messageWin.alertback("提示", data.message, function(){					
							window.location.href="logout.html";	
						});
					}else{
						messageWin.alertback("提示", data.message);
					}
				},
				error:function(err){
					
			}
	});
			}
		</script>
	</head>
	
	<body>
		<section>
			<section class="wrapper">
				<h4 id="pageTitle"></h4>
				<div class="row mt">
					<div class="col-lg-12">
						<div class="content-panel">
							<h4>
								<div class="form-group service_input">
								
									密码修改
									
									<div class="spacet_10">
									    <div class="spacet_10">
									   		 <input id="pwd1" type="password" class="form-control" placeholder="原密码" style="width: 250px;">
									    </div>
									    <div class="spacet_10">
									   		 <input id="pwd2" type="password" class="form-control" placeholder="新密码" style="width: 250px;">
									    </div>
									    <div class="spacet_10">
									   		 <input id="pwd3" type="password" class="form-control" placeholder="确认新密码" style="width: 250px;">
									    </div>									    
									</div>							
									
								
									<button type="button" class="btn btn-theme03 spacet_10" onclick="ok()">
										<i class="fa fa-search"></i> 确定
									</button>
								</div>
							</h4>
						
						</div>
						
					</div>
				</div>
			</section>
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
		<!-- 加载JS -->
		<script src="<%=basePath %>module/js/common.table.js"></script>
		<!--数据验证 -->
		<script src="<%=basePath %>module/js/validator.js"></script>
		<!-- 公用js -->
		<script src="<%=basePath %>module/js/common.js"></script>		
		<!-- 加载信息提示窗口 -->
		<script src="<%=basePath %>module/js/messageModal.js"></script>			
	</body>
</html>
