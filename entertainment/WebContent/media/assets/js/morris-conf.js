//全局 
var url_year="getChartsYear.html";		//图标年度信息地址
var url_month="getChartsMonth.html";	//图标月份信息地址
var url_day="getChartsDay.html";		//图标当日信息地址

var yConsignorMonth=[];		//最近一个月注册趋势图 y轴	货主数据
var yDriverMonth=[];		//最近一个月注册趋势图 y轴	车主数据

var yDisputeOrderMonth=[];	//最近一个月订单趋势图 y轴	争议订单数据
var yPassageOrderMonth=[];	//最近一个月订单趋势图 y轴	在途订单数据
var yKillOrderMonth=[];		//最近一个月订单趋势图 y轴	取消订单数据
var yCompleteOrderMonth=[];	//最近一个月订单趋势图 y轴	完成订单书数据

var yInflowCapitalMonth=[];	//最近一个月资金趋势图y轴	资金流入数据
var yOutflowCapitalMonth=[];//最近一个月资金趋势图y轴	资金流出数据
var	yTotalCapitalMonth=[];	//最近一个月资金趋势图y轴	总资金数据

var yConsignorYear=[];		//年度注册柱状图 y轴	货主数据
var yDriverYear=[];			//年度注册柱状图 y轴	车主数据

var YearDate
var yInflowCapitalYear=[];	//年度资金柱状图y轴	资金流入数据
var yOutflowCapitalYear=[];	//年度资金柱状图y轴	资金流出数据
var	yTotalCapitalYear=[];	//年度资金柱状图y轴	总资金数据
var xYear=[];			//年度x轴日期
var disputeOrderPie;	//最近一个月订单饼图	争议订单数据
var passageOrderPie;	//最近一个月订单饼图	在途订单数据
var killOrderPie;		//最近一个月订单饼图	取消订单数据
var completeOrderPie;	//最近一个月订单饼图	完成订单书数据

$(function() {
	getChartsYear(url_year);
	getChartsMonth(url_month);
	//init();
});

function getChartsYear(url) {
	$.ajax({
		url : url,
		data : {
		},
		type : 'post',
		datatype : 'json',
		beforeSend : function() {
		},
		success : function(data) {
			for(var i=0;i<data.length;i++){
				//日期
				xYear.push(data[i].addDate);
				//年度注册柱状图
				yConsignorYear.push(data[i].consignor);
				yDriverYear.push(data[i].driver);
				//年度资金柱状图
				yInflowCapitalYear.push(data[i].inflowCapital);
				yOutflowCapitalYear.push(data[i].outflowCapital);
				yTotalCapitalYear.push(data[i].totalCapital);
				//最近一个月订单饼图
				if(i==11){
					disputeOrderPie=data[i].disputeOrder;	//最近一个月订单饼图	争议订单数据
					passageOrderPie=data[i].passageOrder;	//最近一个月订单饼图	在途订单数据
					killOrderPie=data[i].killOrder;			//最近一个月订单饼图	取消订单数据
					completeOrderPie=data[i].completeOrder;	//最近一个月订单饼图	完成订单书数据
				}
			}
			orderChartsColumn();	//年度注册柱状图
			capitalChartsColumn();	//资金柱状图
			orderChartsPie();
			/*
		 	MorrisBar1(data);//货主车主柱状图
			MorrisBar2(data);//资金柱状图
			MorrisDonut1(data);//订单饼图
*/		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
		}
	});
}
function getChartsMonth(url){
	$.ajax({
		url : url,
		data : {
		},
		type : 'post',
		datatype : 'json',
		beforeSend : function() {
		},
		success : function(data) {
			//--------解析数据--------
			var milliseconds;
			for(var ii=0;ii<data.length;ii++){
				milliseconds=null;
				var regEx = new RegExp("\\-","gi");
				var addDate=data[ii].addDate;
				addDate=addDate.replace(regEx,"/");
				milliseconds=Date.parse(addDate);
				
				yConsignorMonth.push([milliseconds,data[ii].consignor]);
				yDriverMonth.push([milliseconds,data[ii].driver]);
				
				yDisputeOrderMonth.push([milliseconds,data[ii].disputeOrder]);	//最近一个月订单趋势图 y轴	争议订单数据
				yPassageOrderMonth.push([milliseconds,data[ii].passageOrder]);	//最近一个月订单趋势图 y轴	在途订单数据
				yKillOrderMonth.push([milliseconds,data[ii].killOrder]);		//最近一个月订单趋势图 y轴	取消订单数据
				yCompleteOrderMonth.push([milliseconds,data[ii].completeOrder]);	//最近一个月订单趋势图 y轴	完成订单书数据

				yInflowCapitalMonth.push([milliseconds,data[ii].inflowCapital]);	//最近一个月资金趋势图y轴	资金流入数据
				yOutflowCapitalMonth.push([milliseconds,data[ii].outflowCapital]);//最近一个月资金趋势图y轴	资金流出数据
				yTotalCapitalMonth.push([milliseconds,data[ii].totalCapital]);	//最近一个月资金趋势图y轴	总资金数据
				
			}
			//---解析数据  end ----
			registerChartsLine();		//货主车主趋势图
			orderChartsLine();			//订单趋势图
			capitalChartsLine();		//资金趋势图
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
		}
	});
}

//年度注册柱状图
function  orderChartsColumn() {
	 $('#register_columns').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: '年度用户注册柱状图'
        },
        subtitle: {
            text: ''
        },
        xAxis: {
            categories: xYear,
            crosshair: true
        },
        yAxis: {
            min: 0,
            title: {
                text: '用户数'
            }
        },
        tooltip: {
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [{
        	name: '货主',
            data: yConsignorYear
        }, {
            name: '车主',
            data: yDriverYear
        }]
    });
}

//资金柱状图
function  capitalChartsColumn() {
	 $('#capital_columns').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: '年度资金柱状图'
        },
        subtitle: {
            text: ''
        },
        xAxis: {
            categories: xYear,
            crosshair: true
        },
        yAxis: {
            min: 0,
            title: {
                text: '资金数'
            }
        },
        tooltip: {
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [{
        	name: '流入资金',
            data: yInflowCapitalYear
        }, {
            name: '流出资金',
            data: yOutflowCapitalYear
        }, {
            name: '总资金',
            data: yTotalCapitalYear
        }]
    });
}

//最近一个月货主车主注册趋势图
function registerChartsLine(){
	 chart = new Highcharts.Chart({ 
	        chart: { 
	            renderTo: 'register_line', //图表放置的容器，DIV 
	            defaultSeriesType: 'line', //图表类型line(折线图), 
	          //  zoomType: 'x'   //x轴方向可以缩放 
	        }, 
	        credits: { 
	            enabled: false   //右下角不显示LOGO 
	        }, 
	        title: { 
	            text: '最近一个月注册趋势图' //图表标题 
	        }, 
	        subtitle: { 
	            text: ''  //副标题 
	        }, 
	        xAxis: {  //x轴 
	        	 type: 'datetime',
	        	 tickInterval : 24 * 3600 * 1000 * 2,
                 dateTimeLabelFormats:
                 {
                	 day: '%Y-%m-%d'
                 }
	        }, 
	        yAxis: {  //y轴 
	            title: {text: '注册人数'}, //标题 
	            lineWidth: 2 //基线宽度 
	        }, 
	        plotOptions:{ //设置数据点 
	            line:{ 
	                dataLabels:{ 
	                    enabled:true  //在数据点上显示对应的数据值 
	                }, 
	                enableMouseTracking: false //取消鼠标滑向触发提示框 
	            } 
	        }, 
	        legend: {  //图例 
	        	 margin:100,
	        	layout: 'horizontal',  //图例显示的样式：水平（horizontal）/垂直（vertical） 
	            backgroundColor: '#ffc', //图例背景色 
	            align: 'right',  //图例水平对齐方式 
	            verticalAlign: 'top',  //图例垂直对齐方式 
	            x:0, //间隔x轴的间隔
	            y:20, //间隔Y轴的间隔
	            floating: true, //设置可浮动 
	            shadow: true  //设置阴影 
	        }, 
	        exporting: { 
	            enabled: false  //设置导出按钮不可用 
	        }, 
	        series: [
	        {  //数据列 
	            name: '货主', 
	            data:yConsignorMonth
	        }, 
	        { 
	            name: '车主', 
	            data: yDriverMonth 
	        }]
	    });
	 Highcharts.setOptions({global: {useUTC: false}});
}
//最近一个月订单趋势图
function orderChartsLine(){
	 chart = new Highcharts.Chart({ 
	        chart: { 
	        	 renderTo: 'order_line', //图表放置的容器，DIV 
	            defaultSeriesType: 'line', //图表类型line(折线图), 
	          //  zoomType: 'x'   //x轴方向可以缩放 
	        }, 
	        credits: { 
	            enabled: false   //右下角不显示LOGO 
	        }, 
	        title: { 
	            text: '最近一个月订单趋势图' //图表标题 
	        }, 
	        subtitle: { 
	            text: ''  //副标题 
	        }, 
	        xAxis: {  //x轴 
	        	 type: 'datetime',
	        	 tickInterval : 24 * 3600 * 1000 * 2,
                dateTimeLabelFormats:
                {
               	 day: '%Y-%m-%d'
                }
	        }, 
	        yAxis: {  //y轴 
	            title: {text: '订单个数'}, //标题 
	            lineWidth: 2 //基线宽度 
	        }, 
	        plotOptions:{ //设置数据点 
	            line:{ 
	                dataLabels:{ 
	                    enabled:true  //在数据点上显示对应的数据值 
	                }, 
	                enableMouseTracking: false //取消鼠标滑向触发提示框 
	            } 
	        }, 
	        legend: {  //图例 
	            layout: 'horizontal',  //图例显示的样式：水平（horizontal）/垂直（vertical） 
	            backgroundColor: '#ffc', //图例背景色 
	            align: 'right',  //图例水平对齐方式 
	            verticalAlign: 'top',  //图例垂直对齐方式 
	            x: 0,  //相对X位移 
	            y: 20,   //相对Y位移 
	            floating: true, //设置可浮动 
	            shadow: true  //设置阴影 
	        }, 
	        exporting: { 
	            enabled: false  //设置导出按钮不可用 
	        }, 
	        series: [
	        {  //数据列 
	            name: '争议订单', 
	            data:yDisputeOrderMonth
	        },
	        {
	            name: '取消订单', 
	            data:yKillOrderMonth
	        }, 
	        { 
	            name: '完成订单', 
	            data: yCompleteOrderMonth 
	        }]
	    }); 
}
//最近一个月资金趋势图
function capitalChartsLine(){
	 chart = new Highcharts.Chart({ 
	        chart: { 
	        	 renderTo: 'capital_line', //图表放置的容器，DIV 
	            defaultSeriesType: 'line', //图表类型line(折线图), 
	          //  zoomType: 'x'   //x轴方向可以缩放 
	        }, 
	        credits: { 
	            enabled: false   //右下角不显示LOGO 
	        }, 
	        title: { 
	            text: '最近一个月资金趋势图' //图表标题 
	        }, 
	        subtitle: { 
	            text: ''  //副标题 
	        }, 
	        xAxis: {  //x轴 
	        	 type: 'datetime',
	        	 tickInterval : 24 * 3600 * 1000 * 2,
               dateTimeLabelFormats:
               {
              	 day: '%Y-%m-%d'
               }
	        }, 
	        yAxis: {  //y轴 
	            title: {text: '资金数'}, //标题 
	            lineWidth: 2 //基线宽度 
	        }, 
	        plotOptions:{ //设置数据点 
	            line:{ 
	                dataLabels:{ 
	                    enabled:true  //在数据点上显示对应的数据值 
	                }, 
	                enableMouseTracking: false //取消鼠标滑向触发提示框 
	            } 
	        }, 
	        legend: {  //图例 
	            layout: 'horizontal',  //图例显示的样式：水平（horizontal）/垂直（vertical） 
	            backgroundColor: '#ffc', //图例背景色 
	            align: 'right',  //图例水平对齐方式 
	            verticalAlign: 'top',  //图例垂直对齐方式 
	            x: 0,  //相对X位移 
	            y: 20,   //相对Y位移 
	            floating: true, //设置可浮动 
	            shadow: true  //设置阴影 
	        }, 
	        exporting: { 
	            enabled: false  //设置导出按钮不可用 
	        }, 
	        series: [
	        {  //数据列 
	            name: '流出资金', 
	            data:yInflowCapitalMonth
	        },
	        {
	            name: '流入资金', 
	            data:yOutflowCapitalMonth
	        }, 
	        { 
	            name: '总资金', 
	            data: yTotalCapitalMonth 
	        }]
	    }); 
}

//最近一个月订单饼图
function orderChartsPie(){
    $('#order_pie').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: '最近一个月订单饼图'
        },
        tooltip: {
           /* headerFormat: '{series.name}<br>',
            pointFormat: '{point.name}: <b>{point.data}%</b>'*/
        	 formatter: function() {
        	      return '<b>'+ this.point.name +'</b>: '+ Highcharts.numberFormat(this.percentage, 1) +'% ('+
        	             Highcharts.numberFormat(this.y, 0, ',') +' 个)';
        	     }
        },
        plotOptions: {
        	 pie: {
                 allowPointSelect: true,
                 cursor: 'pointer',
                 dataLabels: {
                     enabled: false
                 },
                 showInLegend: true
             
            }
        },
        dataLabels: {
            enabled: true,
            formatter: function() {
               if (this.percentage > 4) return this.point.name;
            },
            color: 'white',
            style: {
               font: '13px Trebuchet MS, Verdana, sans-serif'
            }
         },
        series: [{
            type: 'pie',
            name: '订单饼图',
            data: [
                ['在途订单',  passageOrderPie],
                ['争议订单',  disputeOrderPie],
                ['完成订单',  completeOrderPie],
                ['取消订单',  killOrderPie],
            ]
        }]
    });
}



