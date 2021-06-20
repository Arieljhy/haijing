$(function() {
	$("[data-toggle='popover']").popover('hide');
	$("[data-toggle='popover']").blur(function(){
		$(this).popover('hide');
	});
});

/**
 * form表单数据填充
 * @param data		数据
 * @param formTags   标签ID
 */
function formSubNodeSetVal(data,formTags,tagsName){
	$.each(data,function(key,value) {
		var formSubDom = $('#'+formTags+' ['+tagsName+'='+key+']');
		if(formSubDom.length>0){
			var domObjectType = formSubDom[0].tagName;
			if(domObjectType=='INPUT'){
				formSubDom.val(value);
			}
			if(domObjectType=='TEXTAREA'){
				formSubDom.val(value);
			}
			if(domObjectType=='SELECT'){
				formSubDom.val(value);
			}
			if(domObjectType=="SPAN"){
				formSubDom.text(value);
				formSubDom.attr('data',value=='正常'?1:0);
			}
		}
	});
}
/**
 * 子页面标题
 * 
 * */

$(function(){
	$('#pageTitle').html("<i class='fa fa-bookmark'></i> "+window.parent.child_title00);
	if(window.parent.haschild==1){
		$('#pageTitle').append(" <i class='fa fa-minus'></i> "+window.parent.child_title);
	}
});


