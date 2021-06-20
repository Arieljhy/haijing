$(function(){
	var table_config={
			tableDomId:"",
			pageDomId:"",
			total:0,//数据总数据
			index:1,//当前页
			displayNum:6,//可见页面
			pageSize:0,//每页数量
			totalPage:0,//总页数
			path:'',//跳转页面前缀
			data:'',//页面数据
			nullStr:'',//数据为空时显示字符
			titles:{},//列标题
			dataTitles:[],//列标题数据字段
			dataTitlesFunc:{},
			tdTitle:null,
			trFunc:null,
			reffunc:function(){//页面刷新方法

			},
			setTotalPage:function(){
				this.totalPage=Math.floor((this.total + this.pageSize - 1) / this.pageSize);
			},
			funcs:[],//最后一列操作实现方法
			funcsTitle:[]//最后一列操作标题

	};
	var MyTable=new Object();

	//初始化
	MyTable.init=function(config){
		var params=$.extend({},table_config,config);
		if(params['data']&&typeof params['data'] === "string"){
			params['data']=JSON.parse(params['data']);
		}

		this.params=params;
	};

	//计算总页数
	MyTable.setTotalPage=function(total,pageSize){
		return Math.floor((total + pageSize - 1) / pageSize);
	};

	//生成table
	MyTable.draw=function(tableId,pageHelperId,params){
		if(typeof params.data === "undefined"){
			params.data=[];
		}
		if(typeof params.data === "string"){
			params.data=JSON.parse(params.data);
		}

		//保存数据
		$("#"+tableId).data("data",params);
		//需要的数据
		var titles=params.titles||[];
		var dataTitles=params.dataTitles||[];
		var data=params.data||[];
		var dataTitlesFunc=params.dataTitlesFunc||[];
		var trFunc=params.trFunc||function(){

		};
		var nullStr=this.params.nullStr||'';
		var total=params.total||0;
		var page=params.index||0;
		var pageSize=params.pageSize||0;
		var totalPage=this.setTotalPage(total,pageSize);
		var disPlayNum=this.params.displayNum;
		var reffunc=params.reffunc||function(){

		};
		var disPlayPage=this.countPages(page,disPlayNum,totalPage);
		//thread
		var $thread=$("<thead></thead>");
		var $titleTr=$("<tr></tr>");
		titles.forEach(function(value){
			var $th=$('<th>'+value+'</th>');
			$titleTr.append($th);
		});
		$thread.append($titleTr);
		//tbody
		var $tbody=$("<tbody></tbody>");

		data.forEach(function(dataValue,i){
			var $tr=$("<tr></tr>");
			//点击行事件
			!function(value,index){
				$tr.click(function(){
					trFunc(value,index);
				});
			}(dataValue,i);
			dataTitles.forEach(function(value,index){
				var str=dataValue[value];
				if(typeof dataTitlesFunc[value]==='function'){
					str=dataTitlesFunc[value](dataValue,i,index);
				}
				if(typeof str === 'undefined'){
					str=nullStr;
				}

				var $td=$('<td class="numeric" data-title="'+titles[index]+'">'+str+'</td>');
				$tr.append($td);
			});
			$tbody.append($tr);
		});

		//pageHelper
		if(pageHelperId){
			var $div=$("<div class='pagination_new pagination_new-centered'></div>");
			var $ul=$("<ul></ul>");
			var $li1=$("<li class='disabled'></li>");
			var $a1=$("<a href='javascript:;'>共"+totalPage+"页／"+total+"条记录</a>");
			$li1.append($a1);
			var $li2=$("<li></li>");
			var $a2=$("<a href='javascript:;'>&nbsp;<i class='icon-fast-backward'></i></a>");
			var $prePage=$("<li></li>");
			var $preA=$("<a href='javascript:;'>上一页</a>");
			$prePage.append($preA);
			$li2.append($a2);
			if (page <= 1){
				$li2.addClass("disabled");
				$prePage.addClass("disabled");
			}else{
				$preA.click(function(){
					MyTable.goPage(page-1,reffunc);
				});
				$a2.click(function(){
					MyTable.goPage(1,reffunc);
				});
			}
			$ul.append($li1);$ul.append($li2);$ul.append($prePage);
			for (var i = disPlayPage.startPage; i <= disPlayPage.endPage; i++){
				var $li=$("<li></li>");
				var $a=$("<a href='javascript:;'>"+i+"</a>");
				if(i==page){
					$li.addClass('disabled');
				}else{
					!function(i){
						$a.click(function(){
							MyTable.goPage(i,reffunc);
						});
					}(i);

				}
				$li.append($a);
				$ul.append($li);
			}
			var $li3=$("<li></li>");
			var $a3=$("<a href='javascript:;'>下一页</a>");
			$li3.append($a3);
			var $li4=$("<li></li>");
			var $a4=$("<a href='javascript:;'>&nbsp;<i class='icon-fast-forward'></i></a>");
			$li4.append($a4);
			if(page>=totalPage){
				$li3.addClass('disabled');
				$li4.addClass('disabled');
			}else{
				$a3.click(function(){
					MyTable.goPage(page+1,reffunc);
				});
				$a4.click(function(){
					MyTable.goPage(totalPage,reffunc);
				});
			}
			$ul.append($li3);$ul.append($li4);
			$div.append($ul);
			$("#"+pageHelperId).empty();
			$("#"+pageHelperId).append($div);
		}


		$("#"+tableId).empty();
		$("#"+tableId).append($thread);
		$("#"+tableId).append($tbody);

	};

	//跳转页数
	MyTable.goPage=function(index,reffunc){
		(typeof reffunc === 'function')&&reffunc(index);
	};

	MyTable.countPages=function(index,displayNum,totalPage){
		if (index - displayNum / 2 < 1){
            startPage = 1;
            endPage = displayNum > totalPage ? totalPage : displayNum;
        }
        else if (index + displayNum / 2 > totalPage){
            var n = totalPage - displayNum + 1;
            startPage = n > 0 ? n : 1;
            endPage = totalPage;
        }
        else{
            startPage = Math.floor(index - displayNum / 2);
            endPage = startPage + displayNum - 1;
        }
        return {
        	startPage:startPage,
        	endPage:endPage
        };
	};

	//添加后面操作
	MyTable.addOperation=function(tableId,config,type){

		var data=$("#"+tableId).data("data").data;
		var $thr = $("#"+tableId+" thead tr");
		var $th = $('<th style="width:200px;text-align:center;">'+(config.title||'操作')+'</th>');
		$thr.append($th);
		var operations=config.operations;
		var $tbr = $("#"+tableId+" tbody tr");
		for(var i=0;i<$tbr.length;i++){
        	var $td = $('<td class="numeric" style="width:100px;text-align:center;" data-title='+(config.title||'操作')+'></td>');
        	$($tbr[i]).append($td);
        	operations.forEach(function(value,index){
        		if(value.index!=undefined && value.state!=undefined){
					if(data[i][value.index]!=value.state){
						return;
					}
				}
    			var $a=$('<a style="padding:0px 5px;" href="javascript:;">'+value.title+'</a>');
    			$td.append($a);
    			!function(i){
    				$a.click(function(e){
    					console.log(data,i);
    					value.func(data[i]);
    					return false;//阻止冒泡
    				});
    			}(i);
    		});
        }

	};


	//添加checkbox
	MyTable.addTableCheckbox=function(tableId,params){
		if(tableId==undefined||tableId==""){
			return;
		};
		var $thr = $("#"+tableId+" thead tr");
		var $checkAllTh = $('<th style="width:50px;text-align:center;"><input type="checkbox" id="checkAll" name="checkAll" /></th>');
        //添加到首列
        $thr.prepend($checkAllTh);
        $checkAllTh.click(function(){
            $(this).find('input').click();
          });
        var $checkAll = $thr.find('input');
        //全选
        $checkAll.click(function(event){

            $tbr.find('input').prop('checked',$(this).prop('checked'));

            if ($(this).prop('checked')) {
                $tbr.find('input').parent().parent().addClass('warning');
            } else{
                $tbr.find('input').parent().parent().removeClass('warning');
            }

            event.stopPropagation();
        });
        var $tbr = $("#"+tableId+" tbody tr");

        //每一行都在最前面插入一个选中复选框的单元格
        for(var i=0;i<$tbr.length;i++){
        	var $checkItemTd = $('<td style="width:50px;text-align:center;"><input data-check-select="'+i+'" type="checkbox" name="checkItem" /></td>');
        	$($tbr[i]).prepend($checkItemTd[0]);
        	$checkItemTd.click(function(){
              $(this).find('input').click();
            });
        }
        //点击每一行的选中复选框时
        $tbr.find('input').click(function(event){

            $(this).parent().parent().toggleClass('warning');

            $checkAll.prop('checked',$tbr.find('input:checked').length == $tbr.length ? true : false);

            event.stopPropagation();
        });

	};
	//获取checkBox的选中的情况
	MyTable.getCheck=function(tableId,dataType){
		if(tableId==undefined||tableId==""){
			return;
		}
		var data=$("#"+tableId).data("data").data;
		var $tbr = $("#"+tableId+" tbody tr");
		var $select=$tbr.find('input:checked');
		var array=new Array();
		//查找所有被选中的
		for(var i=0;i<$select.length;i++){
			var selecti=$select[i].getAttribute("data-check-select");

			if(dataType!=undefined){
				array.push(data[selecti][dataType]);
			}else{
				array.push(data[selecti]);
			};

		}
		return array;
	};

	window.MyTable=MyTable;
});