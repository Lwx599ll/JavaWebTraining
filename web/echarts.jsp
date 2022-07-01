<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="./header.jsp"%>
</head>
<body>
	<!-- 为 ECharts 准备一个定义了宽高的 DOM -->
	<div id="main1" style="width: 600px; height: 400px;"></div>

	<script src="${path}/static/echarts.min.js" type="text/javascript"
		charset="utf-8"></script>
		<script type="text/javascript" >
			// 基于准备好的dom，初始化echarts实例
			$(function() {
				// 基于准备好的dom，初始化echarts实例
				var myChart = echarts.init(document.getElementById('main1'));
				$.post('${path}/dept?method=selectDeptEmpCount', function(
						jsonResult) {
					console.log(jsonResult);
					var list = jsonResult.data;
					var xArray = new Array();
					var yArray = new Array();
					for (var i = 0; i < list.length; i++) {
						xArray.push(list[i].name);
						yArray.push(list[i].value);
					}

					// 指定图表的配置项和数据
					var option = {
						title : {
							text : '部门人数统计'
						},
						tooltip : {},
						legend : {
							data : [ '销量' ]
						},
						xAxis : {
							data : xArray
						},
						yAxis : {},
						series : [ {
							name : '销量',
							type : 'bar',
							data : yArray
						} ]
					};

					// 使用刚指定的配置项和数据显示图表。
					myChart.setOption(option);
				}, 'json');

			});
		</script>
	
		<div id="main2" style="width: 600px; height: 400px;"></div>
		<script type="text/javascript">
			// 基于准备好的dom，初始化echarts实例
			$(function() {
				// 基于准备好的dom，初始化echarts实例
				var myChart = echarts.init(document.getElementById('main2'));
				$.post('${path}/dept?method=selectDeptEmpCount', function(
						jsonResult) {
					console.log(jsonResult);
					var list = jsonResult.data;
					// 指定图表的配置项和数据
					option = {
						title : {
							text : '部门人数分布',
							subtext : 'Fake Data',
							left : 'center'
						},
						tooltip : {
							trigger : 'item'
						},
						legend : {
							orient : 'vertical',
							left : 'left'
						},
						series : [ {
							name : 'Access From',
							type : 'pie',
							radius : '50%',
							data : list,
							emphasis : {
								itemStyle : {
									shadowBlur : 10,
									shadowOffsetX : 0,
									shadowColor : 'rgba(0, 0, 0, 0.5)'
								}
							},
							label: {
				                normal: {
				                    show: true,
				                    formatter: '{b}: {c}({d}%)' 
				                }
				            }
						} ]
					};

					// 使用刚指定的配置项和数据显示图表。
					myChart.setOption(option);
				}, 'json');

			});
		</script>

</body>
</html>