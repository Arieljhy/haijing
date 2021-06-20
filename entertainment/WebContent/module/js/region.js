var regionUrl="../dic/getRegion.html";

//获取省市区
function getRegion(parentCode,callback){
	$.ajax({
		url:regionUrl,
		async:false,
		data:{parentCode:parentCode},
		type:'post',
		success:function(data){
			callback&&callback(data);
		},
		error:function(){
			
		}
	});
}

//初始化角色多选框
function regionSelect(parentCode,type,add){	
	var title = {
			"id" : "value",
			"name":"engName"
	};		
	if(type==1){	
		getRegion(parentCode,function(data){		
			cleanSelect(add+"province",add+"province_ul","省");
			cleanSelect(add+"city",add+"city_ul","市");
			cleanSelect(add+"area",add+"area_ul","区");
			creatRegionSelect(add+"province",data,title,2,add+"province_ul",add);	
		});
	}else if(type==2){	
		getRegion(parentCode,function(data){	
			cleanSelect(add+"city",add+"city_ul","市");
			cleanSelect(add+"area",add+"area_ul","区");
			creatRegionSelect(add+"city",data,title,3,add+"city_ul",add);				
		});	
	}else if(type==3){	
		getRegion(parentCode,function(data){	
			cleanSelect(add+"area",add+"area_ul","区");
			creatRegionSelect(add+"area",data,title,0,add+"area_ul",add);			
		});		
	}
	
}
function creatRegionSelect(divId,data,jsonTitle,type,divUl,add) {
	var html = "";
	for (var i = 0; i < data.length; i++) {
		html += '<li value="'
				+eval('data['+i+'].'+jsonTitle.id)
				+ '"'
				+ 'class onclick="changeRegionSelect('
				+ type
				+',\''
				+eval('data['+i+'].'+jsonTitle.id)
				+ '\',\''
				+eval('data['+i+'].'+jsonTitle.name)
				+ '\',\''
				+ divId
				+ '\',\''
				+ add
				+ '\')">'
				+ '<a href="javascript:void(0)">'
				+eval('data['+i+'].'+jsonTitle.name)
				+ '</a></li>';
	}
	$("#"+divUl).html(html);
}

function changeRegionSelect(type, id, name, divId,add) {
	$("#"+divId).attr('data',id);
	$("#"+divId).html(name);
	regionSelect(id,type,add);	
}

function cleanSelect(divId,ulId,val) {
	$("#"+divId).attr('data',"");
	$("#"+divId).html(val);
	$("#"+ulId).html("");
}
function setSelect(divId,id,val) {
	$("#"+divId).attr('data',id);
	$("#"+divId).html(val);	
}