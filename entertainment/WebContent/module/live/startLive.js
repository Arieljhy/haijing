var addLiveUrl = "saveOrUpdateLive.html";
var getLiveUrl = "getLiveInfo.html";
var updLiveUrl = "saveOrUpdateLive.html";
var resources;
$(function () {
    var params = GetUrlParms();
    console.info(params)
    var type = params.type;
    initEvent(type);
    if (type == 'upd') {
        initUpdEvent(params.id);
    } else {
        initAddEvent();
    }

});

function initEvent() {

    //input获得焦点事件
    $("input").focus(function () {
        $("input").not(this).popover('hide');
        $(this).popover('show');
    });

    //
    $("input").on('change', function () {
        $("input").popover('hide');
    });
    $("input[name='name']").on('change', function () {
        try {
            var val = $("input[name='name']").val();
            $("input[name='code']").val(convertToPinyin(val))
        } catch (e) {
            alert("视频名称转换错误请重新命名直播主题")
        }
    });
    //返回
    $("#back").click(function () {
        window.history.go(-1);
    });

}


//修改绑定事件
function initUpdEvent(id) {
    $("#title").html('观看直播');
    writeData(id);
}

//写入数据
function writeData(id) {
    $.ajax({
        type: 'post',
        url: getLiveUrl,
        data: {id: id},
        success: function (data) {
            $("input[name='name']").val(data.name)
            $("input[name='code']").val(data.code)
            $("input[name='person']").val(data.person)
            $("input[name='time']").val(data.timeStr)
            $("input[name='record']").each(function (index, doc) {
                if ($(doc).val() == data.record) {
                    doc.checked = true;
                    return;
                }
            })
            $("#submit").one('click', function () {
                updResource(data.file.id);
            });

        },
        error: function (err) {

        }
    });
}

//新增绑定事件
function initAddEvent() {
    $("#title").html('新增直播');
    $("#submit").one('click', function () {
        addResource();
    });
}

//检查参数
function getParams(type) {
    //input 数据
    var resource = {
        name: $("input[name='name']").val(),
        code: $("input[name='code']").val(),
        time: $("input[name='time']").val(),
        record: $("input[name='record']:checked").val(),
        person: $("input[name='person']").val()
    };

    for (var key in resource) {
        if (resource[key] == null || resource[key] == '') {
            $("input[name='" + key + "']").focus();
            return;
        }
    }

    return resource;
}

//新增方法
function addResource() {
    var resource = getParams();
    if (resource == null) {
        $("#submit").one('click', addResource);//重新绑定
        return;
    }

    //提交
    $.ajax({
        type: 'post',
        data: resource,
        url: addLiveUrl,
        success: function (data) {
            if (!data.flag) {
                $("#submit").one('click', addResource);//重新绑定
                Alert({
                    msg: data.message,
                    onOk: function () {
                        console.log('ok');
                    }
                });
            } else {
                Alert({
                    msg: data.message,
                    onOk: function () {
                        window.history.go(-1);
                    }
                });
            }
        },
        error: function () {

        }
    });
}

//修改方法
function updResource(id) {
    var advrty = getParams('upd');
    if (advrty == null) {
        $("#submit").one('click', function () {
            updResource(id);
        });//重新绑定
        return;
    }
    advrty.id = id;
    //提交
    $.ajax({
        type: 'post',
        data: advrty,
        url: updLiveUrl,
        success: function (data) {
            if (!data.flag) {
                $("#submit").one('click', function () {
                    updResource(id);
                });//重新绑定
                Alert({
                    msg: data.message,
                    onOk: function () {

                    }
                });
            } else {
                Alert({
                    msg: data.message,
                    onOk: function () {
                        window.history.go(-1);
                    }
                });
            }
        },
        error: function () {

        }
    });
}

//获取URL所有参数
function GetUrlParms() {
    var args = new Object();
    var query = location.search.substring(1); //获取查询串
    var pairs = query.split("&"); //在逗号处断开
    for (var i = 0; i < pairs.length; i++) {
        var pos = pairs[i].indexOf('='); //查找name=value
        if (pos == -1) continue; //如果没有找到就跳过
        var argname = pairs[i].substring(0, pos); //提取name
        var value = pairs[i].substring(pos + 1); //提取value
        args[argname] = decodeURI(value); //存为属性
    }
    return args;
}

function setImg(url, callback) {
    var image = new Image();
    image.src = '../' + url;
    image.onload = function () {
        var base64 = getBase64Image(image);
        callback(base64);
    };
}

function getBase64Image(img) {
    var canvas = document.createElement("canvas");
    canvas.width = img.width;
    canvas.height = img.height;
    var ctx = canvas.getContext("2d");
    ctx.drawImage(img, 0, 0, img.width, img.height);
    var ext = img.src.substring(img.src.lastIndexOf(".") + 1).toLowerCase();
    var dataURL = canvas.toDataURL("image/" + ext);
    return dataURL; // return dataURL.replace("data:image/png;base64,", ""); 
}


