<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
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
    <!-- select -->
    <link href="<%=basePath %>media/assets/css/bootstrap-select.min.css" rel="stylesheet" >
    <!-- Custom styles for this template -->
    <link href="<%=basePath %>media/assets/css/style.css" rel="stylesheet">
    <link href="<%=basePath %>module/css/common.css" rel="stylesheet">
        <link href="<%=basePath %>module/css/newcommon.css" rel="stylesheet">
    <style type="text/css">

        .list-group {
            display: block;
            background: red;
        }
        a:link {
            text-decoration: none;
        }
        a:visited {
            text-decoration: none;
        }
        a:hover {
            color: #515151;
        }
        a:active {
            text-decoration: none;
        }

    </style>
</head>


<body class="fixed-left">
<!-- Begin page -->

<section class="wrapper w1">


			<div class="title1">
					<span>配置管理</span><span>/</span><span>配置列表</span>
			</div>
			<div class="title2">
					配置列表
			</div>
			
			
			<div class="row">
				<div class="col-lg-12">
					<div class="content-panel">
					
			<section style="width: 30%;float: left;margin-right: 3%">

    <div class="panel panel-default" id="first8" style="overflow-y: auto;height:240px">
        <div class="panel-heading" >
            <a <%--onclick="showSecond(8)"--%>  href="#">用户管理-部门</a>
            <a style="margin-left: 50px;" onclick="show(8,'部门')"  href="#">+</a>
            <%--<ul class="list-group" id="show'+num+'">
                <c:forEach items="${list}" var="item">
                    <li class="list-group-item">
                        <div style="width:30%;height:2px">+${item.name}+</div>
                        <a style="margin-left: 35%" onclick="edit('+${item.id}+','+${item.name}+','+${item.typeName}+','+${item.type}+')"  href="#">编辑</a><a style="margin-left: 20%" onclick="deleteConfig( '+${item.id}+','+${item.type}+')"  href="#">删除</a>
                    </li>
                </c:forEach>

            </ul>--%>
        </div>
    </div>
</section>

<section style="width: 30%;float: left;margin-right: 3%">
    <div class="panel panel-default" id="first7" style="overflow-y: auto;height:240px">
        <div class="panel-heading" >
            <a <%--onclick="showSecond(7)"--%>  href="#">音乐管理-音乐分类</a>
            <a style="margin-left: 50px;" onclick="show(7,'音乐分类')"  href="#">+</a>
        </div>

    </div>
</section>

<section style="width: 30%;float: left;margin-right: 3%">
    <div class="panel panel-default" id="first6" style="overflow-y: auto;height:240px">
        <div class="panel-heading" >
            <a <%--onclick="showSecond(6)"--%>  href="#">书籍管理-书籍分类</a>
            <a style="margin-left: 50px;" onclick="show(6,'书籍分类')"  href="#">+</a>
        </div>

    </div>
</section>
<%--<section style="width: 30%;float: left;margin-right: 3%">
    <div class="panel panel-default" id="first9" style="overflow-y: auto;height:240px">
        <div class="panel-heading" >
            <a &lt;%&ndash;onclick="showSecond(9)"&ndash;%&gt;  href="#">游戏管理-游戏分类</a>
            <a style="margin-left: 50px;" onclick="show(9,'游戏类型')"  href="#">+</a>
        </div>

    </div>
</section>--%>
<section style="width: 30%;float: left;margin-right: 3%">
    <div class="panel panel-default" id="first4" style="overflow-y: auto;height:240px">
        <div class="panel-heading"  >
            <a <%--onclick="showSecond(4)"--%>  href="#">影视管理-影视年份</a>
            <a style="margin-left: 50px;" onclick="show(4,'影视年份')"   href="#" >+</a>
        </div>
    </div>
</section>
<section style="width: 30%;float: left;margin-right: 3%">
    <div class="panel panel-default" id="first3" style="overflow-y: auto;height:240px">
        <div class="panel-heading" >
            <a <%--onclick="showSecond(3)"--%>  href="#">影视管理-影视地区</a>
            <a style="margin-left: 50px;" onclick="show(3,'影视地区')"  href="#">+</a>
        </div>

    </div>
</section>
<section id="showCate">


    <section style="width: 30%;margin-right: 3%;float: left;" id="video">
        <div class="panel panel-default" id="first1" style="overflow-y: auto;height:240px">
            <div class="panel-heading"  >
                <a <%--onclick="showSecond(1)"--%>   href="#">影视管理-影视分类</a>
                <a style="margin-left: 50px;" onclick="show(1,'影视分类')"  href="#">+</a>
            </div>
        </div>
        <%--    <div class="panel panel-default" id="first2"  style="display: none">--%>
        <%--        <div class="panel-heading"  >--%>
        <%--            <a onclick="showSecond(2)" >(电影)影视类型</a>--%>
        <%--&lt;%&ndash;            <a style="margin-left: 50px;" onclick="show(2,'视频类型')">+</a>&ndash;%&gt;--%>
        <%--        </div>--%>
        <%--    </div>--%>
        <%--    <div class="panel panel-default" id="first18" style="display: none">--%>
        <%--        <div class="panel-heading"  >--%>
        <%--            <a onclick="showSecond(18)" >(电视剧)影视类型</a>--%>
        <%--&lt;%&ndash;            <a style="margin-left: 50px;" onclick="show(18,'视频类型')">+</a>&ndash;%&gt;--%>
        <%--        </div>--%>
        <%--    </div>--%>
    </section>
</section>
			
			
			</div>
		</div>
	</div>
</div>
	

</section>





<!-- END wrapper -->

<!-- 一级分类 -->
<div id="add-menu-modal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true" style="display: none;"  data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">分类信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="pageform" >
                    <input type="hidden" name="id" id="pageId"/>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-inline">
                                <label for="typeName" class="control-label col-md-3">上级分类</label>
                                <input type="text" class="form-control col-md-8" id="typeName" value="" disabled="disabled">
                                <input type="hidden" value="" id="type" name="type">
                                <input type="hidden" value="" id="configId" name="type">
                            </div>
                        </div>
                    </div>
                    <div class="row" style="padding-top: 15px;">
                        <div class="col-md-12">
                            <div class="form-inline">
                                <label for="name" class="control-label col-md-3">分类名称</label>
                                <input type="text" class="form-control col-md-8" id="name" name="name"
                                       maxlength="4" placeholder="长度不超过4位">
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary waves-effect" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-info waves-effect waves-light" onclick="saveData()">保存</button>
            </div>
        </div>
    </div>
</div><!-- /.modal -->



<!--  基础js类库-->
<script src="<%=basePath %>media/assets/js/jquery.js"></script>
<script src="<%=basePath %>media/assets/js/bootstrap.min.js"></script>
<!-- 选择框 -->
<script src="<%=basePath %>media/assets/js/bootstrap-select.min.js"></script>
<!-- 加载JS -->
<script src="<%=basePath %>module/js/table.js"></script>
<!-- 公用js -->
<script src="<%=basePath %>module/js/common.js"></script>
<!-- 加载信息提示窗口 -->
<script src="<%=basePath %>module/js/dialog.js"></script>
<script src="<%=basePath %>module/js/messageModal.js"></script>
<script src="<%=basePath %>module/config/configList.js"></script>

<script src="<%=basePath %>media/js/jqtreetable/jqtreetable.js"></script>
<script src="<%=basePath %>media/js/zTree_v3/js/jquery.ztree.core.js"></script>
</body>

<script>
    var resizefunc = [];
</script>


<script type="text/javascript">


</script>

</body>
</html>