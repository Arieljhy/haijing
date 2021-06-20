function messageModal(tagsId){
	this.tagsId = tagsId;
	this.html = '<div class="modal fade" id="'+tagsId+'" '
		+'tabindex="-1"role="dialog"aria-labelledby="myModalLabel"aria-hidden="true">'
		+'<div class="modal-dialog"><div class="modal-content"><div class="modal-header">'
		+'<button type="button"class="close"data-dismiss="modal"aria-hidden="true">&times;</button>'
		+'<h4 class="modal-title"id="'+tagsId+'_myModalLabel">提示窗口</h4></div>'
		+'<div class="modal-body"id="'+tagsId+'_messageContent"></div><div class="modal-footer">'
		+'<button id="'+tagsId+'button_2" type="button"class="btn btn-primary">提交更改</button>'
		+'<button id="'+tagsId+'button_1" type="button"class="btn btn-default"data-dismiss="modal">关闭</button></div></div></div>';
	
	messageModal.prototype.create = function(){
		$("body").append(this.html);
	};

	messageModal.prototype.load = function (title,messageContent){
		if(title!=null&&title!=''){
			$("#"+tagsId+"_myModalLabel").text(title);
		}
		else{
			$("#"+tagsId+"_myModalLabel").text("提示");
		}
		if(messageContent!=null&&messageContent!=""){
			$("#"+tagsId+"_messageContent").text(messageContent);
		}
		else{
			$("#"+tagsId+"_messageContent").text("");
		}
	};
	/**
	 * @param title		提示窗口的标题
	 * @param messageContent	提示信息
	 */
	messageModal.prototype.alert = function(title,messageContent){
		this.load(title, messageContent);
		$("#"+tagsId+"button_2").hide();
		$("#"+tagsId).modal("show");
	};
	
	/**
	 * @param title		提示窗口的标题
	 * @param messageContent	提示信息
	 * @param callback	回调方法
	 */
	messageModal.prototype.alertback = function(title,messageContent,callback){
		console.log('=======');
		this.load(title, messageContent);
		$("#"+tagsId+"button_1").hide();
		$("#"+tagsId).modal("show");
        $("#"+tagsId+"button_2").show();
		$("#"+tagsId+"button_2").html('确认');
		$("#"+tagsId+"button_2").off('click');
		$("#"+tagsId+"button_2").one('click',function(){
			console.log($("#"+tagsId).modal("hide"));
			console.log('hide');
			setTimeout(callback,200);});
	};
	
	/**
	 * @param title		提示窗口的标题
	 * @param messageContent	提示信息
	 * @param callback	回调方法
	 */
	messageModal.prototype.confirm = function (title,messageContent,callback){
		this.load(title, messageContent);
		$("#"+tagsId).modal("show");
		$("#"+tagsId+"button_2").text('确认');
		$("#"+tagsId+"button_2").show();
		$("#"+tagsId+"button_1").show();
		$("#"+tagsId+"button_1").text('关闭');
		$("#"+tagsId+"button_2").unbind('click');
		$("#"+tagsId+"button_2").bind('click',function(){callback();});
	}

}
var messageWin = new messageModal("message_Moal");
messageWin.create();