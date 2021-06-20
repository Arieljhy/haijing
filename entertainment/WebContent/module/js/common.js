var roleUrl="../dic/getRoleList.html";
var regionUserUrl="../dic/getRegionUser.html";
var getGradeUrl = "../dic/getDicByType.html";
var getBankUrl = "../dic/getBankCodes.html";


//获取银行行号
function getBankCodes(callback){
	$.ajax({
		url:getBankUrl,
		//async:false,
		data:{},
		type:'post',
		success:function(data){
			callback&&callback(data);
		},
		error:function(){
			
		}
	});
}
//获取角色字典
function getRoleList(callback){
	$.ajax({
		url:roleUrl,
		//async:false,
		data:{},
		type:'post',
		success:function(data){
			var html = "<option value=''>未选择</option>";
			for (var i = 0; i < data.length; i++) {
				var role = data[i]
				html = html + "<option value='"+role.id+"'>"+role.value+"</option>"
			}
			$("#roleSelect").html(html);
			$("#updateRoleSelect").html(html);
			$("#roleSelect").selectpicker('refresh');
			$("#updateRoleSelect").selectpicker('refresh');

		},
		error:function(){
			
		}
	});
}

//获取区域代理
function getRegionUser(callback){
	$.ajax({
		url:regionUserUrl,
		//async:false,
		data:{},
		type:'post',
		success:function(data){
			callback&&callback(data);
		},
		error:function(){
			
		}
	});
}

//获取车主等级
function getGrade(callback){
	$.ajax({
		url:getGradeUrl,
		//async:false,
		data:{type:"grade"},
		type:'post',
		success:function(data){
			callback&&callback(data);
		},
		error:function(){
			
		}
	});
}

//初始化输入框弹出提示
function initPopover(){
	$("[data-toggle='popover']").popover('hide');
	$("[data-toggle='popover']").blur(function(){
		$(this).popover('hide');
	});
}
/**
 * 公共方法
 *
 * */
$(function(){
	//查询条件重置
	$('#searchReset').click(function(){
		$("#searchReset").siblings("input").val('');
		$("#searchReset").siblings().find( "option:first").prop("selected", 'selected')
		$("#searchReset").siblings().find( "select").selectpicker('refresh');
	})
});