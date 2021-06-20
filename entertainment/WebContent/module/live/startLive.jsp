<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
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
    <link href="<%=basePath %>media/assets/css/bootstrap.css" rel="stylesheet">
    <!--external css-->
    <link href="<%=basePath %>media/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>

    <!-- timepick -->
    <link href="<%=basePath %>media/timepick/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=basePath %>media/timepick/css/prettify-1.0.css" rel="stylesheet">
    <link href="<%=basePath %>media/timepick/css/bootstrap-datetimepicker.css" rel="stylesheet">
    <!-- timepickend -->

    <!-- select -->
    <link href="<%=basePath %>media/assets/css/bootstrap-select.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<%=basePath %>media/assets/css/style.css" rel="stylesheet">
    <link href="<%=basePath %>module/css/common.css" rel="stylesheet">
</head>
<body>
<section class="wrapper">
    <div class="mt col-lg-12 content-panel">
        <h3 id="title">观看直播</h3>

        <form class="" action="">
            <div class="row show-grid">
                <div class="col-sm-10 form-horizontal" style="display:inline-block;">
                    <%--<div class="form-group">
                        <label class="col-sm-2 control-label">摄像头</label>
                        <div class="col-sm-8">
                            <object>
                                <embed id="rtmp-streamer" src="<%=basePath %>module/js/rtmp-stream/RtmpStreamer.swf"
                                       bgcolor="#999999" quality="low" width="320" height="240"
                                       allowScriptAccess="localhost" type="application/x-shockwave-flash"></embed>
                            </object>
                        </div>
                    </div>--%>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">直播</label>
                        <div class="col-sm-8">
                            <%--<video id="videoElement"
                                   style="background:#eeeef3;width:320px;height: 240px;"></video>--%>
                            <div id="videoElement"  style="background:#eeeef3;width:640px;height: 480px;"></div>
                            <%-- <embed id="rtmp-player" src="<%=basePath %>module/js/rtmp-stream/RtmpStreamer.swf" bgcolor="#999999" quality="high"
                                    width="320" height="240" allowScriptAccess="sameDomain" type="application/x-shockwave-flash"></embed>--%>
                        </div>
                    </div>
                        <input type="hidden" class="form-control" name="liveLocation"
                               placeholder="服务器地址" value="${liveLocation}" readonly>
                        <input type="hidden" class="form-control" name="playLocation"
                               value="${playLocation}" readonly>
                        <input type="hidden" name="code" readonly class="form-control" placeholder="根据直播主题自动生成">
                  <%--  <div class="form-group">
                        <label class="col-sm-2 control-label">服务器地址</label>
                        <div class="col-sm-8">
                            <input type="hidden" class="form-control" name="liveLocation"
                                   placeholder="服务器地址" value="${liveLocation}" readonly>
                            <input type="hidden" class="form-control" name="playLocation"
                                   value="${playLocation}" readonly>

                        </div>
                    </div>--%>
                        <%--<div class="form-group">
                           <label class="col-sm-2 control-label">直播主题</label>
                           <div class="col-sm-8">
                               <input type="text" class="form-control" name="name"
                                      placeholder="直播主题" readonly>
                           </div>
                       </div>
                       <div class="form-group">
                           <label class="col-sm-2 control-label">视频名称</label>
                           <div class="col-sm-8">
                               <input type="text" name="code" readonly class="form-control" placeholder="根据直播主题自动生成">
                           </div>
                       </div>
                       <div class="form-group">
                           <label class="col-sm-2 control-label">直播人</label>
                           <div class="col-sm-8">
                               <input type="text" name="person" readonly class="form-control" placeholder="直播人">
                           </div>
                       </div>--%>


                </div>
            </div>
            <div class="row">
                <div class="form-group">
                    <div class="col-sm-8">
                        <button type="button" id="back" class="btn btn-default">返回</button>
                        <%--<button type="button" class="btn btn-info" onclick="startPush()">开始直播推流</button>
                        <button type="button" class="btn btn-warning" onclick="disconnectPush()">停止直播推流</button>--%>
                        <button type="button" class="btn btn-info" onclick="videoPlay()">播放直播</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
    </div>
</section>

<!-- js placed at the end of the document so the pages load faster -->
<script src="<%=basePath %>media/assets/js/jquery.js"></script>
<script src="<%=basePath %>media/assets/js/bootstrap.min.js"></script>
<%--<script src="<%=basePath %>module/js/rtmp-stream/flv.min.js"></script>--%>
<%--<script type="text/javascript" src="<%=basePath %>module/js/rtmp-stream/require.js"></script>--%>
<script src="<%=basePath %>module/live/startLive.js"></script>
<script src="<%=basePath %>module/js/rtmp-stream/ckplayer/ckplayer.js"></script>
<script type="text/javascript">
   /* let streamer = {};
    let player = {};
    const RtmpStreamerObj = document.getElementById('rtmp-streamer');
    const RtmpPlayerObj = document.getElementById('rtmp-player');
    require.config({
        paths: {
            "rtmp-streamer": '<%=basePath %>module/js/rtmp-stream/rtmp-streamer'
        }
    });
    require(["rtmp-streamer"], RtmpStreamer => {
        streamer = new RtmpStreamer(RtmpStreamerObj);
        player = new RtmpStreamer(RtmpPlayerObj);

    });

    function disconnectPush() {
        streamer.disconnect();
    }

    function startPush() {
        try {
            if (isReady) {
                let url = $("input[name='liveLocation']").val();
                let code = $("input[name='code']").val();
                //200*150
                streamer.setScreenSize(320 * 1.6, 240 * 1.6);
                streamer.publish(url, code);

            } else {
                setTimeout(_ => {
                    console.log('retry');
                    startPush();
                }, 1000)
            }
        } catch (e) {
            console.log(e)
        }
    }*/
    var player;
    function videoPlay() {
        //flush推流rtmp 直播不行
        /* let url = $("input[name='liveLocation']").val();
         let code = $("input[name='code']").val();
         if(isReady){
             player.setScreenSize(320 * 1.6, 240 * 1.6);
             player.play(url, code);
         }else{
             setTimeout(_ => {
                 console.log('retry');
                 videoPlay();
             }, 1000)
         }*/
        //flash推流视频编码格式不对不行
        let url = $("input[name='liveLocation']").val();
        let code = $("input[name='code']").val();

        var videoObject = {
            container: '#videoElement', //容器的ID或className
            // loaded: 'loadedHandler',
            variable: 'player',//播放函数名称
            autoplay: true,
            live:true,
            video: url+'/'+code

            };

        player = new ckplayer(videoObject);

    }

   /* function loadedHandler(){
        player.changeControlBarShow(false)
        player.addListener('controlBar', controlBarHandler); //监听控制栏显示隐藏事件
    }
    function controlBarHandler(show){
        if(show) {
            html = ' 显示';
        } else {
            html = ' 隐藏';
        }
        console.info(html)
    }*/
</script>
</body>
</html>
