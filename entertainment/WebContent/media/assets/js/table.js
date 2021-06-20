
	var total=0;
	
	function creatTable (result,a,b,id) {
		var list = eval(result);//result为上面返回的json字符串
		$("#"+id).empty();	
		var html = "";
		html = html + "<thead class='cf'><tr>";
		for(var j=0;j<b.length;j++){
			html = html + "<th class='numeric'>"+b[j]+"</th>";  
		}
		html = html + "</tr></thead><tbody>";
		total=0;
		selectnum=-1;
		preobj=null;
		for(var obj in list){
			total++;
			html = html + "<tr onclick='select(this,"+total+")'>"; 
			for(var i=0;i<a.length;i++){
				var str=eval('list['+obj+'].'+a[i]);
				if(str==null||str.length<1){
					str="&nbsp";
				}
				html = html + "<td class='numeric' data-title="+b[i]+">"+str+"</td>";   
			}
			html = html + "</tr>";
		} 
		html = html + "</tbody>";  
		$("#"+id).append(html);  
	}
	var selectnum=-1;
	var preobj=null;
	function select(obj,i){
		selectnum=i;
		if(preobj!=null){
			var child1=preobj.childNodes;
			for(var j1=0;j1<child1.length;j1++){
				child1[j1].style.backgroundColor='white';
			}
		}
		preobj=obj;
		var child=obj.childNodes;
		for(var j1=0;j1<child.length;j1++){
			child[j1].style.backgroundColor='yellow';
		}
	}
	
	

	var displayNum = 5;
	var startPage;
	var endPage;
	var pageSize0;

	function creatPage (total,pageSize,index,path,id,name) {
		pageSize0 =pageSize;
		var totalPage =parseInt((parseInt(total) + parseInt(pageSize) - 1) /parseInt(pageSize));	
		$("#"+id).empty();	
		var html = "";
		if (totalPage != 0 && pageSize != 0){
		html = html + "<div class='pagination_new pagination_new-centered'> <ul>";
		html = html + "<li class='disabled'><a href='#'>共" + totalPage + "页/" + total + "条记录</a></li>";
		   if (index <= 1)
	       {
			   html = html + "<li class='disabled'><a href='#'><i class='icon-fast-backward'></i></a></li>";
			   html = html + "<li class='disabled'><a href='#'>上一页</a></li>";
	       }
	       else
	       {
	    	   html = html + "<li ><a href='#' onclick='goTo(\"" + path + "1\",\""+name+"\")'><i class='icon-fast-backward'></i></a></li>";
	    	   html = html + "<li ><a href='#' onclick='goTo(\"" + path + (parseInt(index) - 1) + "\",\""+name+"\")'>上一页</a></li>";               
	       }

	       countPages(index,totalPage);        
	       for (var i = startPage; i <= endPage; i++)
	       {
	           if (i == index)
	           {
	        	   html = html + "<li class='disabled'><a href='#'>" + i + "</a></li>";             
	           }
	           else
	           {
	        	   html = html + "<li ><a href='#' onclick='goTo(\"" + path + i + "\",\""+name+"\")'>" + i + "</a></li>";
	           }
	       }
	     
	       if (index >= totalPage)
	       {
	    	   html = html + "<li class='disabled'><a href='#'>下一页</a></li>";
	    	   html = html + "<li class='disabled'><a href='#'><i class='icon-fast-forward'></i></a></li>";        
	       }
	       else
	       {
	    	   html = html + "<li ><a href='#' onclick='goTo(\"" + path + (parseInt(index) + 1) + "\",\""+name+"\")' >下一页</a></li>";
	    	   html = html + "<li ><a href='#' onclick='goTo(\"" + path + totalPage + "\",\""+name+"\")'><i class='icon-fast-forward'></i></a></li>";
	       }
	       html = html + "<li ><select id='pageselect'  class='pageselect' onchange='changeSize(\"" +path+"1\",\""+name+"\")'>";	       
	       		if(pageSize==10){
		           html = html + "<option selected='selected'>10</option>";
		           }else{       
		           html = html + "<option >10</option>";
		           }
	           
	           if(pageSize==30){
	               html = html + "<option selected='selected'>30</option>";
	            }else{       
	               html = html + "<option >30</option>";
	             }
	           
	           
	           if(pageSize==50){
	               html = html + "<option selected='selected'>50</option>";
	            }else{       
	               html = html + "<option >50</option>";
	            }
	           
	           if(pageSize==100){
	               html = html + "<option selected='selected'>100</option>";
	            }else{       
	               html = html + "<option >100</option>";
	            }
	           html = html + "</select></li>";
	      /* <option >10</option><option >30</option><option >50</option><option >100</option></select></li>
	       * if(pageSize==10){
	       html = html + "<option selected='selected'>10</option>";
	       }else{       
	       html = html + "<option >10</option>";
	       }
	       
	       if(pageSize==30){
	           html = html + "<option selected='selected'>10</option>";
	        }else{       
	           html = html + "<option >30</option>";
	         }
	       
	       
	       if(pageSize==50){
	           html = html + "<option selected='selected'>10</option>";
	        }else{       
	           html = html + "<option >50</option>";
	        }
	       
	       if(pageSize==100){
	           html = html + "<option selected='selected'>10</option>";
	        }else{       
	           html = html + "<option >100</option>";
	        }
	       
	       
	       html = html + "</select></li>";*/
	       html = html + "<li><input type='text' id='pagenum' onkeyup='this.value=this.value.replace(/\\D/g,\"\")'  onpaste='return false' class='input pageinput'></li>";
	       html = html + "<li><a href='javascript:void(0);' onclick='goPage()'>Go</a></li>"; 
	       html = html + "</ul></div>";       
	       html = html + "<script type='text/javascript'> function goPage(){var num = document.getElementById('pagenum').value;  if(num>0&&num<="+totalPage+"){   goTo('"+path+"'+num,'"+name+"');  } else{ document.getElementById('pagenum').value = ''} } </script>";
		 }

		$("#"+id).append(html);  
	}

	function  countPages(index,totalPage)
	{

	    if (index - parseInt(displayNum / 2) < 1)
	    {
	        startPage = 1;
	        endPage = displayNum > totalPage ? totalPage : displayNum;
	    }
	    else if (index + parseInt(displayNum / 2) > totalPage)
	    {
	        var n = totalPage - displayNum + 1;
	        startPage = n > 0 ? n : 1;
	        endPage = totalPage;
	    }
	    else
	    {
	        startPage = index - parseInt(displayNum / 2);
	        endPage = startPage + displayNum - 1;
	    }
	}

	function goTo(url,name) {	
		url=url+"&pageSize="+pageSize0;
		eval(name+"('"+url+"');")
	}
	function changeSize(url,name){
		pageSize0 = document.getElementById("pageselect").options[document.getElementById("pageselect").selectedIndex].text;
		goTo(url,name);
		
	}
	
	
	/**
	 * 创建表格
	 * @param data		[{"id":13,"roleName":"dddddddd","menus":[{"id":3,"menuName":"积分转账日志"}]]
	 * @param title
	 * @param thead
	 * @param id
	 * @param subTitle  子集合是标签 {
									"name" : "menus",	//集合中的子集合元素
									"keyName":"menuName"   //子集合中的元素名
								};
	 */
	function creatTable_sub(data,title,thead,id,subTitle){
		var list = eval(data);//result为上面返回的json字符串
		$("#"+id).empty();	
		var html = "";
		html = html + "<thead class='cf'><tr>";
		for(var j=0;j<thead.length;j++){
			html = html + "<th class='numeric'>"+thead[j]+"</th>";  
		}
		html = html + "</tr></thead><tbody>";
		total=0;
		selectnum=-1;
		preobj=null;
		for(var obj in list){
			total++;
			html = html + "<tr onclick='select(this,"+total+")'>"; 
			for(var i=0;i<title.length;i++){
				if(title[i]==subTitle.name){
					var sub_data = eval('list['+obj+'].'+title[i]);
					var text = [];
					if(sub_data==null||sub_data.length<1){
						text.push("&nbsp;");
					}else{
						for(var j=0;j<sub_data.length;j++){
//						menuName += sub_data[j].menuName + ",";
							text.push(eval('sub_data['+j+'].'+subTitle.keyName));
						}
					}
					html = html + "<td class='numeric' data-title="+title[i]+">"+text.toString()+"</td>";
				}else{
					var text = typeof(eval('list['+obj+'].'+title[i]))=='undefined'?"&nbsp;":eval('list['+obj+'].'+title[i]);
//					console.log(text);
					html = html + "<td class='numeric' data-title="+thead[i]+">"+text+"</td>";   
				}
			}
			html = html + "</tr>";
		} 
		html = html + "</tbody>";  
		$("#"+id).append(html);  
	}
	
	
	function creatTable_son(data,title,thead,id,subTitle){
		var list = eval(data);//result为上面返回的json字符串
		$("#"+id).empty();	
		var html = "";
		html = html + "<thead class='cf'><tr>";
		for(var j=0;j<thead.length;j++){
			html = html + "<th class='numeric'>"+thead[j]+"</th>";  
		}
		html = html + "</tr></thead><tbody>";
		total=0;
		var keyId=0;
		selectnum=-1;
		preobj=null;
		var to = 0;
		var j = 0;
//		for(var obj in list){
		for(var z=0;z<list.length;z++){
			var d = eval('list['+z+']');
			var url = d.url;
			if(url!=null&&url!=""&&url!="#"){
				total++;
				/*无子菜单时*/
				html = html + "<tr onclick='select(this,"+d.id+")' data-title='pid'>"; 
				for(var i=0;i<title.length;i++){
					var str=eval('list['+z+'].'+title[i]);
					if(str==null||str.length<1){
						str="&nbsp";
					}
					html = html + "<td class='numeric' data-title="+thead[i]+">"+str+"</td>";
				}
				html = html + "</tr>";
				continue;
			}
			if(url!=null&&url!=""&&url=="#"){
				total++;
				html = html + "<tr onclick='select(this,"+d.id+")' data-title='pid'>"; 
				for(var i=0;i<title.length;i++){
					var str=eval('list['+z+'].'+title[i]);
					if(str==null||str.length<1){
						str="&nbsp";
					}
					html = html + "<td class='numeric' data-title="+thead[i]+">"+str+"</td>";
				}
				html = html + "</tr>";
				if(d.sonName!=null){
					for (j = z; j < data.length; j++) {
						if (data[j].id == d.id) {
							to++;
							html = html + "<tr onclick='select(this,"+data[j].sonid+")' style='display:'>"; 
							for(var i=0;i<title.length;i++){
								var str=eval('list['+j+'].'+subTitle[i]);
								if(str==null||str.length<1){
									str="&nbsp";
								}
								html = html + "<td class='numeric' data-title="+thead[i]+">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+str+"</td>";
							}
							html = html + "</tr>";
						} else {
							break;
						}
					}
					z=j-1;
				}
				
			}
		} 
		html = html + "</tbody>";  
		$("#"+id).append(html);  
	}
	
	
	