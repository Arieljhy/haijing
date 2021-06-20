var table_array=[];
function myTable(config){
	var that=this;
	this.length=table_array.length;
	var table_config={
			tableDomId:"",
			pageDomId:"",
			total:0,//数据总数据
			index:0,//当前页
			displayNum:6,//可见页面
			pageSize:0,//每页数量
			totalPage:0,//总页数
			path:'',//跳转页面前缀
			data:'',//页面数据
			nullStr:'',//数据为空时显示字符
			title:{},//列标题
			tdTitle:null,
			tdFunc:null,
			reffunc:function(){//页面刷新方法
				
			},
			setTotalPage:function(){
				this.totalPage=Math.floor((this.total + this.pageSize - 1) / this.pageSize);
			},
			funcs:[],//最后一列操作实现方法
			funcsTitle:[]//最后一列操作标题
			
	};
	this.table_config=table_config;
	for(var key in table_config){
		if(config[key]){
			table_config[key]=config[key];
		}
		
		if(key==="data"){
			if(typeof(config[key])==="string"){
				table_config[key]=eval('('+config[key]+')');
				
			}
			
		}
		
	}
	
	table_array.push(this);
	this.createPageHelper=function(pageId){
		table_config.setTotalPage();
		if(pageId==undefined||pageId===''){
			console.log('请输入分页id');
			return;
		}
		if(!document.getElementById(pageId)){
			console.log('分页id错误');
			return;
		}
		table_config.pageDomId=pageId;
		var htmlStr='';
		htmlStr+="<div class='pagination_new pagination_new-centered'> <ul>"
				+"<li class='disabled'><a href='#'>共" + table_config.totalPage + "页/" + table_config.total + "条记录</a></li>";
		if (table_config.index <= 1){
			htmlStr+="<li class='disabled'><a href='#'>&nbsp;<i class='icon-fast-backward'></i></a></li>"
				+"<li class='disabled'><a href='#'>上一页</a></li>";
		}else{
			htmlStr+="<li ><a href='javascript:goPage("+that.length+",1)'>&nbsp;<i class='icon-fast-backward'></i></a></li>"
				+"<li ><a href='javascript:tableClick_ref(" +that.length+",&quot;"+ table_config.path + (table_config.index - 1) + "&quot;)'>上一页</a></li>";
		}
		var tt=countPages();
		for (var i = tt.startPage; i <= tt.endPage; i++){
            if (i == table_config.index){
            	htmlStr+="<li class='disabled'><a href='#'>" + i + "</a></li>";             
            }
            else{
            	htmlStr+="<li ><a href='javascript:tableClick_ref(" +that.length+",&quot;"+ table_config.path + i + "&quot;)'>" + i + "</a></li>";
            }
        }
		if (table_config.index >= table_config.totalPage){
            htmlStr+=("<li class='disabled'><a href='#'>下一页</a></li>");
            htmlStr+=("<li class='disabled'><a href='#'>&nbsp;<i class='icon-fast-forward'></i></a></li>");        
        }
        else{
        	htmlStr+=("<li ><a href='javascript:tableClick_ref("+that.length+",&quot;"+ table_config.path + (table_config.index + 1) + "&quot;)'>下一页</a></li>");
        	htmlStr+=("<li ><a href='javascript:tableClick_ref(" +that.length+",&quot;"+ table_config.path + table_config.totalPage + "&quot;)'>&nbsp;<i class='icon-fast-forward'></i></a></li>");
        }
		htmlStr+=("<li><input type='text' id='pagenum"+that.length+"' onkeyup='this.value=this.value.replace(/\\D/g,\"\")'  onpaste='return false' class='input pageinput'></li>");
		htmlStr+=("<li><a href='javascript:goPage("+that.length+")'>Go</a></li>"); 
		htmlStr+=("</ul></div>");
	//htmlStr+=("<script type='text/javascript'> function goPage(){var num = document.getElementById('pagenum').value;  if(num>0&&num<="+table_config.totalPage+"){   window.location.href='"+table_config.path+"'+num;  } else{ document.getElementById('pagenum').value = ''} } </script>");
        $('#'+pageId).html('');
        $('#'+pageId).html(htmlStr);
	};
	function countPages(){

		
        if (table_config.index - table_config.displayNum / 2 < 1)
        {
            startPage = 1;
            endPage = table_config.displayNum > table_config.totalPage ? table_config.totalPage : table_config.displayNum;
        }
        else if (table_config.index + table_config.displayNum / 2 > table_config.totalPage)
        {
            var n = table_config.totalPage - table_config.displayNum + 1;
            startPage = n > 0 ? n : 1;
            endPage = table_config.totalPage;
        }
        else
        {
            startPage = Math.floor(table_config.index - table_config.displayNum / 2);
            endPage = startPage + table_config.displayNum - 1;
        }
     //   console.log(startPage,endPage,table_config.displayNum);
        return {
        	startPage:startPage,
        	endPage:endPage
        };
    }
	
	this.createTable=function(tableId,titles,dataTitles,operationTitle,callbackOption){
		if(tableId==undefined||tableId===''){
			console.log('请输入表格id');
			return;
		}
		if(!document.getElementById(tableId)){
			console.log('表格id错误');
			return;
		}
		if(titles==undefined||titles.length==0){
			console.log('标题内容不能为空');
			return;
		}
		if(titles.length!=dataTitles.length){
			console.log('标题长度与内容长度不同');
		}
		table_config.tableDomId =tableId;
		if(callbackOption==undefined){
			callbackOption={};
		}
		var htmlStr='';
		htmlStr+='<thead><tr>';
		for(var i=0;i<titles.length;i++){
			htmlStr+='<th>'+titles[i]+'</th>';
		}
		if(table_config.funcs.length!=0){
			operationTitle=operationTitle?operationTitle:'操作';
			htmlStr+='<th>'+operationTitle+'</th>';
		}
		htmlStr+='</tr></thread><tbody>';
		for(var i=0;i<table_config.data.length;i++){
			htmlStr+='<tr>';
			for(var j=0;j<dataTitles.length;j++){
				var tdStr=table_config.data[i][dataTitles[j]];
				tdStr=callbackOption[dataTitles[j]]?callbackOption[dataTitles[j]](table_config.data[i]):tdStr;
				if(tdStr==null||tdStr=="null"){
					htmlStr+='<td class="numeric" data-title='+titles[j]+'>'+table_config.nullStr+'</td>';
				}else{
					if(table_config.tdTitle===dataTitles[j]){
						htmlStr+='<td class="numeric" data-title='+titles[j]+'>'+"<a href='javascript:tableClickTD("+that.length+","+i+","+j+")'>"+tdStr+"</a>"+'</td>';
					}else{
						htmlStr+='<td class="numeric" data-title='+titles[j]+'>'+tdStr+'</td>';
					}
					
				}
			}
			if(table_config.funcs.length!=0){
				htmlStr+='<td class="numeric" data-title='+operationTitle+'>';
				for(var t=0;t<table_config.funcs.length;t++){
					var hrefTitle;
					
					if(typeof(table_config.funcsTitle[t])=='function'){
						hrefTitle=table_config.funcsTitle[t](table_config.data[i]);
					}else{
						hrefTitle=table_config.funcsTitle[t];
					}
					hrefTitle=hrefTitle ?hrefTitle: '操作';
					htmlStr+="&nbsp;<a href='javascript:tableClick("+that.length+","+i+","+t+")'>"+hrefTitle+"</a>";
				}
				htmlStr+='</td>';
			}
			
			htmlStr+='</tr>';
		}
		$('#'+tableId).html('');
		$('#'+tableId).html(htmlStr);
	};
	
	this.addTableCheckbox=function(){
		tableId=table_config.tableDomId;
		if(tableId==undefined||tableId==""){
			return;
		}
		var $thr = $("#"+tableId+" thead tr");
		var $checkAllTh = $('<th><input type="checkbox" id="checkAll" name="checkAll" /></th>');  
        //添加到首列
        $thr.prepend($checkAllTh);
        $checkAllTh.click(function(){  
            $(this).find('input').click();  
          });
        var $checkAll = $thr.find('input');  
        $checkAll.click(function(event){  
            /*将所有行的选中状态设成全选框的选中状态*/  
            $tbr.find('input').prop('checked',$(this).prop('checked'));  
            /*并调整所有选中行的CSS样式*/  
            if ($(this).prop('checked')) {  
                $tbr.find('input').parent().parent().addClass('warning');  
            } else{  
                $tbr.find('input').parent().parent().removeClass('warning');  
            }  
            /*阻止向上冒泡，以防再次触发点击操作*/  
            event.stopPropagation();  
        });  
        /*点击全选框所在单元格时也触发全选框的点击操作*/  
//        $checkAllTh.click(function(){  
//            $(this).find('input').click();  
//        });  
        var $tbr = $("#"+tableId+" tbody tr");  
        
        /*每一行都在最前面插入一个选中复选框的单元格*/
        for(var i=0;i<$tbr.length;i++){
        	var $checkItemTd = $('<td><input data-check-select="'+i+'" type="checkbox" name="checkItem" /></td>');
        //	console.log($tbr[i]);
        	$($tbr[i]).prepend($checkItemTd[0]);
        	$checkItemTd.click(function(){  
              $(this).find('input').click();  
            });
        } 
        /*点击每一行的选中复选框时*/  
        $tbr.find('input').click(function(event){  
            /*调整选中行的CSS样式*/  
            $(this).parent().parent().toggleClass('warning');  
            /*如果已经被选中行的行数等于表格的数据行数，将全选框设为选中状态，否则设为未选中状态*/  
            $checkAll.prop('checked',$tbr.find('input:checked').length == $tbr.length ? true : false);  
            /*阻止向上冒泡，以防再次触发点击操作*/  
            event.stopPropagation();  
        });  
        /*点击每一行时也触发该行的选中操作*/  
//        $tbr.click(function(){  
//            $(this).find('input').click();  
//        });
	};
	
	this.getCheck=function(dataType){
		tableId=table_config.tableDomId;
		if(tableId==undefined||tableId==""){
			return;
		}
		var $tbr = $("#"+tableId+" tbody tr");
		var $select=$tbr.find('input:checked');
		var array=new Array();
		for(var i=0;i<$select.length;i++){
			var selecti=$select[i].getAttribute("data-check-select");
			
			if(dataType!=undefined){
				array.push(table_config.data[selecti][dataType]);
			}else{
				array.push(table_config.data[selecti]);
			}
			
		}
		return array;
	};
	
	return this;
}
function tableClick(table_length,i,callbackNum){
	var table_config=table_array[table_length].table_config;
	table_config.funcs[callbackNum](table_config.data[i]);
}
function tableClickTD(table_length,i,callbackNum){
	table_array[table_length].table_config.tdFunc(i);
}
function tableClick_ref(table_length,url){
	table_array[table_length].table_config.reffunc(url);
}
var goPage=function(table_length,n){
	var num = document.getElementById('pagenum'+table_length).value;
	num=n?n:num;
//	console.log("num"+num);
	if(num>0&&num<=table_array[table_length].table_config.totalPage){
		table_array[table_length].table_config.reffunc(table_array[table_length].table_config.path+num);
		
	}else{
		document.getElementById('pagenum').value = '';
	}
};

//时间工具
function CommonTime(){
	CommonTime.prototype.init=function(t,format){
		format||(format="yyyy-MM-dd HH:mm:ss");
		if(typeof(t)=='string'){
			t=parseInt(t);
		}
		var time=getTimeMap(new Date(t));
//		console.log(time);
		for(var key in time){
			format=format.replace(key,time[key]);
		}
		return format;
	};
	function getTimeMap(date){
		
		return {
			yyyy:date.getFullYear(),
			MM:fullStr(date.getMonth()+1),
			dd:fullStr(date.getDate()),
			HH:fullStr(date.getHours()),
			mm:fullStr(date.getMinutes()),
			ss:fullStr(date.getSeconds())
		};
	}
	function fullStr(t){
//		console.log(t);
		if(t<10){
			t='0'+t;
		}
		return t;
	}
}
var commonTime = new CommonTime();