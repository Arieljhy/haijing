
/**
 * 创建下拉框
 * 创建HTML实例：<div class="dropdown" id="div_menus">
				<!-- 下拉多选框 -->
				<select class="selectpicker show-tick form-control" multiple data-live-search="false"></select>
		 	  </div>
 * @param divId  div的ID
 * @param data	 数据
 * @param jsonTitle	 json格式的标识 例：{"id":"id","name":"menuName"}
 */
function creatSelect(divId, data,jsonTitle,callback) {
	$("#"+divId).bind("mySelect",function(e,id,divId){
//		console.log('点击'+id);
		callback&&callback(id,divId);
	});
	var div_menus_ul = $("#" + divId).find(".inner");
	/*var demoData = '<li data-original-index="0"><a tabindex="0" class="" style="" data-tokens="null">'
			+ '<span class="text">苹果</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li>';*/
	var html = "";
	for (var i = 0; i < data.length; i++) {
		html += '<li data-original-index="'
//				+ data[i].id
				+eval('data['+i+'].'+jsonTitle.id)
				+ '"'
				+ 'class onclick="select_li(this,\''
//				+ data[i].id
				+eval('data['+i+'].'+jsonTitle.id)
				+ '\',\''
//				+ data[i].menuName
				+eval('data['+i+'].'+jsonTitle.name)
				+ '\',\''
				+ divId
				+ '\')">'
				+ '<a tabindex="'
//				+ data[i].id
				+eval('data['+i+'].'+jsonTitle.id)
				+ '" class="" style="" data-tokens="null">'
				+ '<span class="text">'
//				+ data[i].menuName
				+eval('data['+i+'].'+jsonTitle.name)
				+ '</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li>';
	}
	div_menus_ul.append(html);
}

/**
 * 点击选中事件
 */
function select_li(t, id, name, divId) {
	$("#"+divId).trigger("mySelect",id,divId);
	if ($(t).hasClass("selected")) {
		$(t).removeClass("selected");
		
	} else {
		$(t).addClass("selected");
	}
	changeText(divId);
}

function removeAllSelect(divId){
	var div_menus_ul = $("#" + divId).find(".inner");
	var uis=div_menus_ul.find("li");
	console.log(uis);
	for(var i=0;i<uis.length;i++){
		console.log(uis[i]);
		if($(uis[i]).hasClass("selected")){
			$(uis[i]).removeClass("selected");
			changeText(divId);
		}
	}
}

/**
 * 修改选中文字
 * @param tagsId
 */
function changeText(tagsId) {
	var div_menus_li = $("#" + tagsId).find(".inner").children("li");
	var button = $("#" + tagsId + " div:first-child").find("button");
	var span = $(button).find(".filter-option");
	var text = [];
	for (var i = 0; i < div_menus_li.length; i++) {
		if ($(div_menus_li[i]).hasClass("selected")) {
//			text += $(div_menus_li[i]).find(".text").text() + ",";
			text.push($(div_menus_li[i]).find(".text").text());
		}
	}
	if(text.length<1){
		//Nothing selected
		span.attr("title", "未选择");
		span.text("未选择");
	}else{
		span.attr("title", text.toString());
		span.text(text.toString().length > 25 ? text.toString().substring(0, 25) + "..." : text.toString());
	}
}


/**
 * 获取选中值
 * @param tagsId
 * @returns {String}
 */
function getSelectVal(tagsId){
	var val=new Array();
	var div_menus_li = $("#" + tagsId).find(".inner").children("li");
	for (var i = 0; i < div_menus_li.length; i++) {
		if ($(div_menus_li[i]).hasClass("selected")) {
//			val += $(div_menus_li[i]).attr('data-original-index') + ",";
			val.push($(div_menus_li[i]).attr('data-original-index'));
		}
	}
	
//	console.log(val);
	return val;
}

/**
 * 
 * @param tagsId
 * @param array   数组
 */
function setSelectVal(tagsId,array){
	var div_menus_li = $("#" + tagsId).find(".inner").children("li");
	$(div_menus_li).removeClass("selected");
	if(array==null||array==''){
		array = [];
	}
	for(var i=0;i<array.length;i++){
		for(var j=0;j<div_menus_li.length;j++){
			var menuName = $(div_menus_li[j]).find(".text").text();
			if(array[i]==menuName){
				$(div_menus_li[j]).addClass("selected");
			}
		}
	}
	changeText(tagsId);
}

/**
 * 
 * @param tagsId
 * @param array   数组
 */
function setSelectValForArray(tagsId,array,name){
	var div_menus_li = $("#" + tagsId).find(".inner").children("li");
	$(div_menus_li).removeClass("selected");
	if(array==null||array==''){
		array = [];
	}
	for(var i=0;i<array.length;i++){
		for(var j=0;j<div_menus_li.length;j++){
			var menuName = $(div_menus_li[j]).find(".text").text();
			if(eval('array[i].'+name) == menuName){
				$(div_menus_li[j]).addClass("selected");
			}
		}
	}
	changeText(tagsId);
}
function setSelectValByArray(tagsId,array){
	var div_menus_li = $("#" + tagsId).find(".inner").children("li");
	$(div_menus_li).removeClass("selected");
	if(array==null||array==''){
		array = [];
	}
	for(var i=0;i<array.length;i++){
		for(var j=0;j<div_menus_li.length;j++){
			var data_id = $(div_menus_li[j]).attr("data-original-index");
			if(array[i]==data_id){
				$(div_menus_li[j]).addClass("selected");
			}
		}
	}
	changeText(tagsId);
}

function cleanSelect(divId) {	
	var div_menus_ul = $("#" + divId).find(".inner");		
	div_menus_ul.html("");
}

function creatOneSelect(divId,data,jsonTitle,divUl) {
	var html = "";
	for (var i = 0; i < data.length; i++) {
		html += '<li value="'
				+eval('data['+i+'].'+jsonTitle.id)
				+ '"'
				+ 'class onclick="changeOneSelect(\''			
				+eval('data['+i+'].'+jsonTitle.id)
				+ '\',\''
				+eval('data['+i+'].'+jsonTitle.name)
				+ '\',\''
				+ divId				
				+ '\')">'
				+ '<a href="javascript:void(0)">'
				+eval('data['+i+'].'+jsonTitle.name)
				+ '</a></li>';
		//console.log(html);
	}
	$("#"+divUl).html(html);
}

function changeOneSelect(id, name, divId) {
	$("#"+divId).attr('data',id);
	$("#"+divId).html(name);	
}

function cleanOneSelect(divId) {
	$("#"+divId).attr('data',"");
	$("#"+divId).html("未选择");	
}