var addResourceUrl = "addResource.html";
var getResourceUrl = "getResourceById.html";
var updResourceUrl = "udpResource.html";

var getConfigUrl = "getConfig.html";

var resources;
$(function () {

    MyTable.init();
    var params = GetUrlParms();
    var type = params.type;
    initEvent(type);
    if (type == 'upd') {
        initUpdEvent(params.id);
    } else {
        initAddEvent();
    }

});

function getConfigByType(type, callback) {
    $.ajax({
        type: 'post',
        data: {type: type},
        async: false,
        url: getConfigUrl,
        success: function (data) {
            callback && callback(data);
        },
        error: function (err) {

        }
    });
}

function initType(type) {
    $("#type").empty();
    if (type == 1) {
        type = 2;
    }
    $.ajax({
        type: 'post',
        data: {type: type},
        async: false,
        url: getConfigUrl,
        success: function (data) {
            data.forEach(function (value) {
                var $option = $('<option value="' + value.id + '">' + value.name + '</option>');
                $("#type").append($option);
            });
        },
        error: function (err) {

        }
    });
    $("#type").selectpicker("refresh");
}

function initEvent(type) {
    if(type!='upd'){
        getFiles()
        $("#category").on('change', function (e) {
            initType($(this).val());
            getFiles()
        });
    }else{
        $("#category").on('change', function (e) {
            initType($(this).val());
        });
        $("#nameInput").css('display','block')
        $('#name').selectpicker('hide');
        $("#getUrlBtn").css('display','none')
    }


    //时间控件
    $("#addDate").datetimepicker({
        format: "YYYY-MM-DD HH:mm:ss"
    });
    //select初始化
    $('select').selectpicker();
    //input获得焦点事件
    $("input").focus(function () {
        $("input").not(this).popover('hide');
        $(this).popover('show');
    });
    //
    $("input").on('change', function () {
        $("input").popover('hide');
        console.log('change');
    });
    //返回
    $("#back").click(function () {
        window.history.go(-1);
    });

    $("#fileUpload").bind("change", function (e) {
        var files = e.target.files || e.dataTransfer.files;
        if(files[0].size >1024*1024){
            Alert({
                msg: "请选择小于1MB的图片上传"
            });

            $("#fileUpload").val('')
            return;
        }
        readFile(files[0]);
    });
}


//修改绑定事件
function initUpdEvent(id) {
    $("#title").html('修改影视');
    $("#name").attr("disabled", "disabled");
    $("#getUrlBtn").attr("disabled", "disabled");
    writeData(id);
}

//写入数据
function writeData(id) {
    $.ajax({
        type: 'post',
        url: getResourceUrl,
        data: {id: id},
        success: function (data) {
            $("#userName").val(data.file.userName);
            $("#nameInput").val(data.file.name);
            $("#addDate").val(data.file.addDate);
            $("#category").val(data.file.category);
            initType(data.file.category);

            $("#type").val(data.file.type);
            $("#videoRegion").val(data.file.videoRegion);
            $("#videoYear").val(data.file.videoYear);
            $("#author").val(data.file.author);
            $("#actor").val(data.file.actor);
            $("#introduction").val(data.file.introduction);
            $('select').selectpicker('refresh');
            var params = {
                titles: ['视频路径'],
                dataTitles: ['url'],
                dataTitlesFunc: {},
                data: data.resources
            };
            MyTable.draw('content', null, params);
            setImg(data.file.image, function (base64) {
                $("#icon")[0].src = base64;
            });
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
    initType(1);
    $("#title").html('新增影视');
    $("#submit").one('click', function () {
        addResource();
    });
}

function getUrl() {
    var text = $("#name").val()
    if(text != ''){
        $.ajax({
            type: 'post',
            data: {name: $("#name").val(), category: $("#category  option:selected").text(), fileType: 1},
            url: "getFileList.html",
            success: function (data) {
                if (data.flag) {
                    var params = {
                        titles: ['视频路径'],
                        dataTitles: ['url'],
                        dataTitlesFunc: {},
                        data: data.data
                    };
                    MyTable.draw('content', null, params);
                    resources = JSON.stringify(data.files);
                } else {
                    Alert({
                        msg: "没有找到这个文件：" + data.url
                    });
                }

            },
            error: function () {

            }
        });
    }else{
        Alert({
            msg: "请选择名称"
        });
    }

}

function getFiles() {
    $.ajax({
        type: 'post',
        data: { category: $("#category  option:selected").text(), fileType: 1},
        url: "getDocumentFileList.html",
        success: function (data) {
            if (data.files.length>0) {
                var html = "<option value=''>未选择</option>";
                for (var i = 0; i < data.files.length; i++) {
                    var file = data.files[i]
                    html = html + "<option value='" + file + "'>" + file + "</option>"
                }
                $("#name").html(html);
                $("#name").selectpicker('refresh');
            }else{
                var html = "<option value=''>暂无数据</option>";
                $("#name").html(html);
                $("#name").selectpicker('refresh');
            }

        },
        error: function () {

        }
    });
}

//检查参数
function getParams(type) {
    //input 数据
    var resource = {
        category: $("#category").val(),
        addDate: $("#addDate").val(),
        type: $("#type").val(),
        videoRegion: $("#videoRegion").val(),
        videoYear: $("#videoYear").val(),
        author: $("#author").val(),
        actor: $("#actor").val(),
    };
    var image = $("#icon")[0].src == window.location.href ? '' : $("#icon")[0].src;
    if (image == null || image == '') {
        Alert('请选择图片');
        return null;
    }
    if (($("#name").val() == null || $("#name").val() == '')&& type != 'upd') {
        Alert('请选择名称');
        return null;
    }

    if((resources == null || resources=='')&& type != 'upd'){
        Alert('请确认名称');
        return null;
    }
    for (var key in resource) {
        if (resource[key] == null || resource[key] == '') {
            $("#" + key).focus();
            return;
        }
    }
    if( $("#author").val().length>10){
        Alert('请填写导演，限10个字');
        return null;
    }
    if( $("#actor").val().length>10){
        Alert('请填写演员，限10个字');
        return null;
    }
    if($("#introduction").val() == null || $("#introduction").val()==''){
        Alert('请填写简介');
        return null;
    }
    if($("#introduction").val().length>500){
        Alert('简介内容应不大于500个字符');
        return null;
    }
    resource.image = image;
    resource.resources = resources;
    resource.fileType = 1;
    resource.introduction=$("#introduction").val()
    resource.name=$("#name").val()
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
        url: addResourceUrl,
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
        url: updResourceUrl,
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

function readFile(file) {
    var reader = new FileReader();
    reader.onload = function (e) {
        $("#icon")[0].src = e.target.result;
        $("#icon").css("margin-bottom", "10px");
        $("#uploadLabel").css('vertical-align', 'top');
    };
    reader.readAsDataURL(file);
}

