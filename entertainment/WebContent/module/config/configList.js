var listUrl='configList.html';
var secondListUrl='secondListUrl.html';
var saveConfig='saveConfig.html';
var removeUrl='removeConfig.html';

var isReName='isReName.html';
var isData='isData.html';

$(function(){
    $("#first1").click(function(){
        if($(this).next().is(":hidden")){
            $(this).next().show();
        }else{
            $(this).next().hide();
        }
    });
   showAll();


});
function showAll() {

  showSecond(8,'部门');
  showSecond(7,'音乐分类');
  showSecond(6,'书籍分类');
  showSecond(9,'游戏类型');
  showSecond(4,'影视年份');
  showSecond(3,'影视地区');
  showSecond(1,'影视分类');
}

function showSecond(num,name) {
  $("#vid").remove();
  $("ul").find("li").remove();
  $.ajax({
    url: secondListUrl,
    method: "post",
    data: {type:num},
    success: function (data) {
      console.log(data.list);
      var content='';
      if(data.list.length>0){
        content+='<ul class="list-group" id="show'+num+'">';
        var typName="影视类型";
        for(var i=0;i<data.list.length;i++){
          if(data.list[i].type=='1'){
            content+='<li class="list-group-item"  id="secondCate'+data.list[i].id+'" onclick="showThird(' + data.list[i].id +',\''+data.list[i].name+'\')"><div style="width:25%;height:2px">'+data.list[i].name+'</div><a style="margin-left: 20%;" onclick="show(' + data.list[i].id +',\''+data.list[i].name+'\')"  href="#">+</a>'
            content+= '<a style="margin-left: 15%" onclick="edit('+data.list[i].id+',\''+data.list[i].name+'\',\''+data.list[i].typeName+'\',\''+data.list[i].type+'\')"  href="#">编辑</a>'
            content+='<a style="margin-left: 20%" onclick="deleteConfig(' + data.list[i].id +',\''+data.list[i].type+'\')"  href="#">删除</a></li>';
          }else{
            content+='<li class="list-group-item"><div style="width:30%;height:2px">'+data.list[i].name+'</div><a style="margin-left: 35%" onclick="edit('+data.list[i].id+',\''+data.list[i].name+'\',\''+data.list[i].typeName+'\',\''+data.list[i].type+'\')"  href="#">编辑</a><a style="margin-left: 20%" onclick="deleteConfig(' + data.list[i].id +',\''+data.list[i].type+'\')"  href="#">删除</a></li>';
          }

        }
        content+='</ul>';
      }
      $("#first"+num).append(content);
    }
  })

}
function showThird(num,name) {

 $("#vid").remove();


  $.ajax({
    url: secondListUrl,
    method: "post",
    data: {type:num},
    success: function (data) {
      console.log(data.list);
      var content='';
      var typeName="("+name+")影视类型";
      if(data.list.length>0){
        content+='<section style="width: 30%;margin-right: 3%;float: left" id="vid">';
        content+='<div class="panel panel-default" id="first'+num+'" style="overflow-y: auto;height:230px">';
        content+='<div class="panel-heading">';
        content+='<a onclick="showSecond('+num+')"   href="#">'+typeName+'</a>';
        content+='<a style="margin-left: 50px;" onclick="show('+num+','+name+')"  href="#"></a></div>' +
            '<ul class="list-group" id="show'+num+'">';

        for(var i=0;i<data.list.length;i++){
          content+='<li class="list-group-item"><div style="width: 30%;height: 2px;">'+data.list[i].name+'</div><a style="margin-left: 35%" onclick="edit('+data.list[i].id+',\''+data.list[i].name+'\',\''+data.list[i].typeName+'\',\''+data.list[i].type+'\')"  href="#">编辑</a><a style="margin-left: 20%" onclick="deleteConfig(' + data.list[i].id +',\''+data.list[i].type+'\')"  href="#">删除</a></li>';

          //content+='<section style="width: 30%;float:left;margin-top: 3%">';
          // content+=' <div class="panel panel-default" id="first'+num+'">';
          // content+='<div class="panel-heading"><a onclick="showSecond('+num+')">('+name+')'+data.list[i].typeName+'</a>';
          // content+='<a style="margin-left: 50px;" onclick="show('+num+',\''+data.list[i].typeName+'\')">+</a></div>';
          //content+='</section>';
        }
        content+='</ul></div></section>';
      }
      $("#showCate").append(content);
    }
  })
}


function show(type,typeName) {
    $('#configId').val('');
    $('#name').val('');
    $('#type').val('');
    $('#typeName').val('');


  $('#name').val('');
  $('#type').val(type);
  $('#typeName').val(typeName);
  $("#add-menu-modal").modal("show");
}

function saveData(){
  var configId=$('#configId').val();
  var name=$('#name').val();
  var type=$('#type').val();
  var typeName=$('#typeName').val();

  if(name==''){
    messageWin.alert("提示", "请输入分类名称");
    return;
  } else{
    $.ajax({
      url: isReName,
      method: "post",
      data: {type:type,name:name,configId:configId},
      success: function (data) {
        console.log(data);
        if(data.flag){
          messageWin.alert("提示", data.msg);
          return;
        }else {
          $.ajax({
            url: saveConfig,
            method: "post",
            data: {type:type,name:name,typeName:typeName,configId:configId},
            success: function (data) {
              console.log(data);
              if (data.flag) {
                messageWin.alert("提示", data.msg);
                //window.location.reload();
                  $("#add-menu-modal").modal("hide");
                  /*showSecond(type);*/
                showAll();
              } else {
                messageWin.alert("提示", data.msg);
                return;
              }
            }
          })
            /*showSecond(type);*/
          //showAll();
        }
      }
    })
  }
}

function edit(id,name,typeName,type) {
  console.log("typeName",typeName)
    $('#configId').val('');
    $('#name').val('');
    $('#type').val('');
    $('#typeName').val('');

  $('#name').val(name);
  $('#configId').val(id);
  $('#type').val(type);
  $('#typeName').val(typeName);
  $("#add-menu-modal").modal("show");
}
function deleteConfig(id,type) {
  if(id==2 && type==1){
    messageWin.alert("提示", "该分类不能删除");
    return
  }else{
    $.ajax({
      type:'post',
      url:isData,
      data:{id:id},
      success:function(data){
        if(data.flag){
          Alert({
            msg:data.msg,
            onOk:function(){

            }
          });
        }else{
          messageWin.confirm("提示", "确定删除分类吗？", function() {
            $.ajax({
              type: 'post',
              url: removeUrl,
              data: {id: id},
              success: function (data) {
                if (data.flag) {

                  messageWin.alert("提示", data.msg);
                  /*showSecond(type);*/
                  showAll();
                } else {
                  messageWin.alert("提示", data.msg);
                  return;
                }
              },
              error: function () {

              }
            });
          })
        }
      },
      error:function(){

      }
    });
  }

}

