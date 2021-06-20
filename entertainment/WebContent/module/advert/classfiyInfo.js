var addClassfiyUrl='addClassfiy.html';
var updClassfiyUrl='updClassfiy.html';
var getClassfiyUrl="getClassfiyById.html"
var isData='isData.html';

$(function(){
	var params=GetUrlParms();
	var type=params.type;
	initEvent();
	if(type=='upd'){
		initUpdEvent(params.id);
	}else{
		initAddEvent();
	}
});


//默认事件
function initEvent(){
	//input获得焦点事件
	$("input").focus(function(){
		$("input").not(this).popover('hide');
		$(this).popover('show');
	});
	//
	$("input").on('change',function(){
		$("input").popover('hide');
		console.log('change');
	});
	//返回
	$("#back").click(function(){
		window.history.go(-1);
	});
}

//写入数据
function writeData(id){
	$.ajax({
		type:'post',
		url:getClassfiyUrl,
		data:{id:id},
		success:function(data){
			if(data.flag){
				var classfiy=data.data;
				$("#name").val(classfiy.name);
				$("#sequence").val(classfiy.sequence);
				$("#classFyId").val(classfiy.id);
				$("#state").bootstrapSwitch('setState',classfiy.state==1,classfiy.state==1);

				console.log($("#state").prop('checked'),classfiy.state==1);
				$("#remark").val(classfiy.remark);
				
				$("#submit").one('click',function(){


					updClassfiy(classfiy.id);
				});
			}
		},
		error:function(err){
			
		}
	});
}
//修改绑定事件
function initUpdEvent(id){
	$("#title").html('修改资讯板块');
	$("#ttt").html('修改资讯板块');
	writeData(id);
}

//新增绑定事件
function initAddEvent(){
	$("#title").html('新增资讯板块');
	$("#ttt").html('新增资讯板块');
	$("#submit").one('click',function(){
		addClassfiy();
	});
}



//检查参数
function getParams(type){
	//input 数据
	var classfiy={
		name:$("#name").val(),
		sequence:$("#sequence").val(),
		remark:$("#remark").val()
	};
	
	for(var key in classfiy){
		if(classfiy[key]==null||classfiy[key]==''){
			if(key=='password'&&type=='upd'){
				continue;
			}
			$("#"+key).focus();
			return;
		}
	}
	
	classfiy.state=$("#state").prop('checked')?1:2;
	//资讯类型为1
	classfiy.type=1;
	return classfiy;
}

//新增方法
function addClassfiy(){
	var name=$("#name").val();
	var sequence=$("#sequence").val();
	$.ajax({
		type:'post',
		url:'./isReName.html',
		data:{name:name},
		success:function(data){
			console.log(data)

			if(data.flag=="1") {

                console.log("重复")
				Alert({
					msg: data.msg,
					onOk: function () {
						$("#submit").one('click',addClassfiy);//重新绑定
					}
				});
				return;
			}else {
                console.log("没重复")
				$.ajax({
					type:'post',
					url:'./isReQuence1.html',
					data:{sequence:sequence},
					success:function(data){
						if(data.flag=="1") {

							console.log("重复")
							Alert({
								msg: data.msg,
								onOk: function () {
									$("#submit").one('click',addClassfiy);//重新绑定
								}
							});
							return;
						}else {

							var classfiy=getParams();
							console.log(classfiy)
							if(classfiy==null){
								$("#submit").one('click',addClassfiy);//重新绑定
								return;
							}
//提交
							$.ajax({
								type:'post',
								data:classfiy,
								url:addClassfiyUrl,
								success:function(data){
									if(!data.flag){
										$("#submit").one('click',addClassfiy);//重新绑定
										Alert({
											msg:data.msg,
											onOk:function(){
												console.log('ok');
											}
										});
									}else{
										Alert({
											msg:data.msg,
											onOk:function(){
												window.history.go(-1);
											}
										});
									}
								},
								error:function(){

								}
							});

						}
					},
					error:function(){
					}
				});
			}
		},
		error:function(err){
		}
	});

}


//修改方法
function updClassfiy(id){
	var name=$("#name").val();
	var sequence=$("#sequence").val();
	$.ajax({
		type:'post',
		url:'./isReName.html',
		data:{name:name,id:id},
		success:function(data){
			console.log(data)

			if(data.flag=="1") {
				Alert({
					msg: data.msg,
					onOk: function () {
						$("#submit").one('click',function(){
							updClassfiy(id);
						});//重新绑定
					}
				});
				return;
			}else {

				$.ajax({
					type:'post',
					url:'./isReQuence1.html',
					data:{sequence:sequence,id:id},
					success:function(data){
						if(data.flag=="1") {

							console.log("重复")
							Alert({
								msg: data.msg,
								onOk: function () {
									$("#submit").one('click',function(){
										updClassfiy(id);
									});//重新绑定
								}
							});
							return;
						}else {

							var user=getParams('upd');
							if(user==null){
								$("#submit").one('click',function(){
									updClassfiy(id);
								});//重新绑定
								return;
							}
							user.id=id;
							//提交
							$.ajax({
								type:'post',
								data:user,
								url:updClassfiyUrl,
								success:function(data){
									if(!data.flag){
										$("#submit").one('click',function(){
											updClassfiy(id);
										});//重新绑定
										Alert({
											msg:data.msg,
											onOk:function(){

											}
										});
									}else{
										Alert({
											msg:data.msg,
											onOk:function(){
												window.history.go(-1);
											}
										});
									}
								},
								error:function(){

								}
							});

						}
					},
					error:function(){

					}
				});

			}
		},
		error:function(err){
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

function checkboxOnclick(){
	var id=$("#classFyId").val();

	if ( $("#state").val() == "on"){
		$.ajax({
			type:'post',
			url:isData,
			data:{ids:id},
			success:function(data){
				if(data.flag){
					Alert({
						msg:"该板块有数据，不能禁用",
						onOk:function(){

						}
					});
					$("#state").bootstrapSwitch('setState',1,1);
					$("#state").prop("checked",1);
				}
			},
			error:function(){

			}
		});

	}

}

