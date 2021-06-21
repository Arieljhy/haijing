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
<link href="<%=basePath%>module/css/newcommon.css" rel="stylesheet">
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
<link href="<%=basePath%>module/css/newcommon.css" rel="stylesheet">

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
#ansersTable,#titleTable{
	width:100%;
	margin-left: 20px;
}
#titleTable tbody,#ansersTable tbody {
	display:block;
	overflow-y:scroll;
	-webkit-overflow-scrolling: touch;
	min-height: 10px;
	max-height: 400px;
}
#titleTable tbody::-webkit-scrollbar,#ansersTable tbody::-webkit-scrollbar  {
	/*display: none;*/
}
#titleTable thead , #titleTable tbody tr,#ansersTable thead , #ansersTable tbody tr {
	display:table;
	width:100%;
	table-layout:fixed;
	text-align: center;
	border-collapse:separate; border-spacing:0px 5px;
}
#titleTable thead th,#ansersTable thead th{
	text-align: center;
}
#titleTable thead,#ansersTable thead {
	width: calc( 100% - 1em )
}
	.delTitleStatus{
		cursor:pointer
	}
	.updateTitle{
		cursor:pointer
	}
</style>
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
		 
		 .content-panel form{
		 
		 margin-top:0 !important;
		 
		 }
		 .form-horizontal .form-group{
		 	margin:0 !important;
		 	margin-bottom:20px !important;
		 }
	
		 
		/*form group table*/
		.form-group .table-con{
		/* padding:0 16px; */
		
		/* height:calc(100% - 120px); */
		padding:0 24px;
		
		overflow-y:auto;
		
		}
		.form-group .table-con table{ 
		 border:none;
		}
		.form-group .table-con table thead  tr {
		 border-bottom:1px dotted #000;
		
		}
		.form-group .table-con table thead tr th{
		    background-color: #f9f9f9 !important;
   
   

		 border:none;
		 background-color:#f9f9f9;
		 height:34px;
		 line-height:34px;
		  padding:0;
		 
		}
		.form-group .table-con table tbody tr {
		 border-bottom:1px dotted #000 !important;
		
		}
		.form-group .table-con table tbody tr td{
		 height:34px;
		 line-height:34px;
		 border:none;
		 background-color:#fff;
		 padding:0;
		}
		
		
		
		.form-group .table-con table tbody tr td:last-child a{
			
		color:#1890FF;
		
		}
		
		.layui-layer.layui-layer-page{
		
		}
		.layui-layer-content{
		 position:relative;
		
		}
		.layui-layer-wrap{
		
		}
		
		</style>
</head>
<body>
<section class="wrapper w1">
<div class="title1">
					<span>测试管理</span><span>/</span><span class="fh">${type==1?'心理问卷管理':'在线考试管理'}</span><span>>></span><span id="ttt">${type==1?'修改问卷调查':'修改在线考试'}</span>
			</div>
			<div class="title2">
					<div class="left" id="title">${type==1?'修改问卷调查':'修改在线考试'}</div>
					<div class="right">
								<button type="button" class="btn btn-default fh" >返回</button>
								<button type="button"  class="btn btn-info tj">提交</button>
					</div>	
			</div>
	<div class="col-lg-12 content-panel" style="width:100%;background:#fff;padding:25px 0 0 0;">
	<%-- 	<h3>${type==1?'修改问卷调查':'修改在线考试'}</h3> --%>
		<form class="" action="">
			<input id="testId" type="hidden">
			<div class="row show-grid" style="width:100%;background:#fff;height:calc(100% - 75px);">
				<div class="col-sm-12  form-horizontal " style="display:inline-block;width:100%;">
					<div class="form-group" style="width:100%;" >
						<label class="col-sm-3 control-label" for="title">${type==1?'问卷标题':'考试标题'}</label>
						<div class="col-sm-10">
							<input type="text" data-trigger="" class="form-control" data-title="必填" id="studyTitle" maxlength="30">
							<input type="hidden" id="studyType" value="${type}">
						</div>
					</div>
					<div class="form-group" style="width:100%;">
						<label class="col-sm-3 control-label" for="remark">内容</label>
						<div class="col-sm-10">
							<%--<script id="remark" style="height:400px;" name="remark" type="text/plain"></script>--%>
								<textarea cols="130" id="remark" name="remark" rows="4"></textarea>
						</div>
					</div>
					<div class="form-group" style="width:100%;">
						<label class="col-sm-3 control-label" for="resultAnalyse">评分标准</label>
						<div class="col-sm-10">
							<script id="resultAnalyse" style="height:400px;" name="resultAnalyse" type="text/plain"></script>
						</div>
					</div>
					
					
					<div class="form-group" style="width:100%;">
						<label class=" col-sm-3 control-label" >题目列表</label>
						<div class="col-sm-10"  style=" text-align:right;">
							<button type="button" id="addTitle" class="btn btn-default" style="background:#1890ff;border:none; color:#fff;">添加题目</button>
						</div>
					</div>

					<div class="form-group" style="width:100%;background:#fff;">
					 <label class=" col-sm-3 control-label" ></label> 
							<div class="table-con col-sm-10" style="padding-left:10px;padding-right:0px;">
							<table id="titleTable" style="margin-left:0;">
									<thead style="background:#f9f9f9;">
									<tr>
										<th>标题</th><th>题目类型</th><th>正确答案</th><th>操作</th>
									</tr>
									</thead>
									<tbody>
		
									</tbody>
		
								</table>
							
							</div>
						
					</div>


				</div>
			</div>
			<div class="row" style="text-align: center">
				<div class="form-group">
					<div class="col-sm-16">
						<button type="button" id="back" class="btn btn-default" style="display:none;">返回</button>
						<button type="button" id="submit" class="btn btn-info" style="display:none;">提交</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</section>

<div id="addShow" style="display: none;margin:10px 10px; ">
	<div class="row show-grid">
		<div class="col-sm-12 form-horizontal" style="display:inline-block;">
			<div class="form-group">
				<label class="col-sm-2 control-label" for="title">题目标题</label>
				<div class="col-sm-10">
					<input type="text" data-trigger="" class="form-control" data-title="必填" id="title" placeholder="题目标题">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label" for="remark">题目类型</label>
				<div class="col-sm-10">
					<select id="type">
						<option value="1">单选</option>
						<option value="2">多选</option>
					</select>
				</div>
			</div>
			<div class="form-group">
			<label class="col-sm-2 control-label" >答案列表</label>
				<div class="col-sm-10" style="text-align:right;">
					<button type="button" id="addAnser" class="btn btn-default" style="background:#1890ff;border:none; color:#fff;">添加答案</button>
				</div>
			</div>
			<div class="form-group">
				<!-- <label class="col-sm-2 control-label" >答案列表</label> -->
			
				<div class="table-con col-sm-11" style="padding-left:10px;padding-right:0px;">
						<table id="ansersTable" style="width:100%;margin-left: 20px;">
							<thead>
							<tr>
								<th>选项</th><th>内容</th><th>分值</th><th>正确答案</th>
							</tr>
							</thead>
							<tbody>
		
							</tbody>
		
						</table>
				</div>
				
			</div>
		</div>
	</div>
	<div class="row" style="text-align: right; position:absolute; right:24px;left:0; bottom:20px; height:34px;">
		<div class="form-group">
			<div class="col-sm-16">
				<button type="button" id="titleBack" class="btn btn-default">返回</button>
				<button type="button" id="titleSubmit" class="btn btn-info" style="background-color:#1890ff;">提交</button>
			</div>
		</div>
	</div>

</div>




<div id="viewTitle" style="display: none;margin:10px 10px;">
	<div class="row show-grid">
		<div class="col-sm-12 form-horizontal" style="display:inline-block;">
			<div class="form-group" style="width:100%;">
				<label class="col-sm-2 control-label" for="title">题目标题</label>
				<div class="col-sm-8">
					<input type="text" data-trigger="" class="form-control" data-title="必填" readonly="readonly" id="title" placeholder="题目标题">
				</div>
			</div>
			<div class="form-group"  style="width:100%;">
				<label class="col-sm-2 control-label" for="remark">题目类型</label>
				<div class="col-sm-8">
					<select id="type" readonly="readonly">
						<option value="1">单选</option>
						<option value="2">多选</option>
					</select>
				</div>
			</div>
			<div class="form-group"  style="width:100%;">
				<label class="col-sm-2 control-label" >答案列表</label>
				<table id="ansersTable" style="width:100%;margin-left: 20px;">
					<thead>
					<tr>
						<th>11选项</th><th>内容</th><th>分值</th><th>正确答案</th>
					</tr>
					</thead>
					<tbody>

					</tbody>

				</table>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="form-group"  style="width:100%;">
			<div class="col-sm-8">
				<button type="button" id="viewBack" class="btn btn-default">返回</button>
			</div>
		</div>
	</div>

</div>



<!-- js placed at the end of the document so the pages load faster -->
<script src="<%=basePath %>media/assets/js/jquery.js"></script>
<script type="text/javascript">
		$(function(){
	
		
			
			$('.fh').click(function(){
				$("#back").trigger('click');
			})
			$('.tj').click(function(){
				$("#submit").trigger('click');
			})
			
			
		})
			
		</script>
<script src="<%=basePath %>media/assets/js/bootstrap.min.js"></script>
<!-- switch -->
<script src="<%=basePath %>media/assets/js/bootstrap-switch.js"></script>
<!-- 选择框 -->
<script src="<%=basePath %>media/assets/js/bootstrap-select.min.js"></script>

<script type="text/javascript" src="<%=basePath %>media/editor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="<%=basePath %>media/editor/ueditor.all.js"></script>

<!-- timepick -->
<script src="<%=basePath %>media/timepick/js/moment-with-locales.js"></script>
<script src="<%=basePath %>media/timepick/js/bootstrap-datetimepicker.js"></script>
<!-- timepickend -->

<!-- 加载JS -->
<script src="<%=basePath %>module/js/table.js"></script>
<!-- 公用js -->
<script src="<%=basePath %>module/js/common.js"></script>
<!-- 加载信息提示窗口 -->
<script src="<%=basePath %>module/js/dialog.js"></script>
<!-- 加载信息提示窗口 -->
<script src="<%=basePath %>module/js/messageModal.js"></script>
<script src="<%=basePath %>module/study/updateTitle.js"></script>
<!-- 加载页面JS -->
<script src="<%=basePath %>module/js/layer/layer.js"></script>
</body>
</html>
