<%@ page language="java" import="java.util.*,java.text.SimpleDateFormat"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!--
	目前的需求问题：
		1导航的按钮样式
		2导航路线规划的窗口位置
	锦上添花：
		在天气功能中加入：点击地图上某处，显示其所在地区天气信息(由一个div框表示)



  -->





<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>浮动巴士</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="initial-scale=1.0, user-scalable=no, width=device-width">
<!--本jsp中的所有样式  -->
<link rel="stylesheet" href="css/map.css" />
</head>

<body>


	<div id="container"></div>

	<div class="info">
		<h4 id='time'></h4>
		<hr>
		<p id='forecast'></p>
		<!--起点输入框  -->
		<div class="input-item">
			<div class="input-item-prepend">
				<span class="input-item-text" style="width:8rem;">请输入起点</span>
			</div>
			<input id='beginLoc' type="text">
		</div>
		<!--终点输入框  -->
		<div class="input-item">
			<div class="input-item-prepend">
				<span class="input-item-text" style="width:8rem;">请输入目的地</span>
			</div>
			<input id='endLoc' type="text">
		</div>
		<!--导航键  -->
		<div>
			<input id="navigate" type="submit" value="导航" onclick="getCode()">
		</div>
	</div>


	<!--导航路线框  -->
	<div id="panel"></div>












	<!-- 
   	 	地图的加载  异步加载需要将插件的引入写在后边，否则会报错 Cannot read property 'plugin' of undefined  
   	 	地图的加载采用异步加载，这样比较妥当，之前采用同步加载出现了资源加载顺序问题导致地图不显示情况
   	 	-->
	<script type="text/javascript">
		var map;
		window.init = function() {
			map = new AMap.Map('container', {
				resizeEnable : true,
				center : [ 114.431803, 30.462671 ],
				zoom : 14
			});
			if (location.href.indexOf('guide=1') !== -1) {
				map.setStatus({
					scrollWheel : false
				});
				map.plugin([ "AMap.ToolBar" ], function() {
					map.addControl(new AMap.ToolBar({
						liteStyle : true
					}))
				});
			}
		}
	</script>
	<!--加载地图  -->
	<script
		src="http://webapi.amap.com/maps?v=1.4.15&key=e2357116c63a7fc0279c3d22da61ed63&callback=init"></script>




	<!--4天内天气预报和时间的实时更新  -->
	<script type="text/javascript">
		/*武汉市四天的天气预报  */
		AMap.plugin('AMap.Weather', function() {
			var weather = new AMap.Weather();
			//未来4天天气预报
			weather.getForecast('江夏区', function(err, data) {
				if (err) {
					return;
				}
				var str = [];
				for (var i = 0, dayWeather; i < data.forecasts.length; i++) {
					dayWeather = data.forecasts[i];
					str.push(dayWeather.date + ' <span class="weather">'
							+ dayWeather.dayWeather + '</span> '
							+ dayWeather.nightTemp + '~' + dayWeather.dayTemp
							+ '℃');
				}
				document.getElementById('forecast').innerHTML = str
						.join('<br>');
			});
		});

		/* 实时刷新时间  */
		setInterval(refresh, 1000);
		function refresh() {
			//创建ajax引擎对象
			var ajax;
			if (window.XMLHttpRequest) {//现代版本浏览器
				ajax = new XMLHttpRequest();
			} else if (window.AcitveXobject) {//老版本浏览器
				ajax = new ActiveXobject();
			}
			//复写onreadystatement函数
			ajax.onreadystatechange = function() {

				//判断ajax状态码
				if (ajax.readyState == 4) {

					if (ajax.status == 200) {
						//获取响应内容
						var timeStr = ajax.responseText;
						//获取元素对象
						var showdiv = document.getElementById("time");
						showdiv.innerHTML = "天气预报\t" + timeStr;
					} else if (ajax.status == 404) {
						var showdiv = document.getElementById("time");
						showdiv.innerHTML = "请求资源不存在";
					} else if (ajax.status == 500) {
						var showdiv = document.getElementById("time");
						showdiv.innerHTML = "服务器繁忙";
					}

				}

			}
			//发送请求
			ajax.open("get", "ts", false);
			ajax.send(null);
		}
	</script>



	<!--导航地址的输出提示  -->
	<script type="text/javascript"
		src="https://webapi.amap.com/maps?v=1.4.15&key=e2357116c63a7fc0279c3d22da61ed63&plugin=AMap.Autocomplete"></script>
	<!--导航工具条，拉动工具条可以控制地图比例  -->
	<script type="text/javascript"
		src="https://cache.amap.com/lbs/static/addToolbar.js"></script>
	<script type="text/javascript">
		/*输入提示  */
		AMap.plugin('AMap.Autocomplete', function() {
			//输入提示
			var auto = new AMap.Autocomplete({
				input : "beginLoc",
			});
			var auto = new AMap.Autocomplete({
				input : "endLoc"
			});

		})
	</script>




	<script
		src="https://a.amap.com/jsapi_demos/static/demo-center/js/demoutils.js"></script>
	<script type="text/javascript"
		src="https://webapi.amap.com/maps?v=1.4.15&key=e2357116c63a7fc0279c3d22da61ed63&plugin=AMap.Driving"></script>
	<script type="text/javascript">
		/*路线规划  */
		function getCode() {
			var beginLoc = document.getElementById("beginLoc").value;
			var endLoc = document.getElementById("endLoc").value;
			//测试正确
			alert(beginLoc);
			alert(endLoc);
			//构造路线导航类
			AMap.plugin('AMap.Driving', function() {
				var driving = new AMap.Driving({
					map : map,
					panel : "panel"
				});
				// 根据起终点名称规划驾车导航路线
				driving.search([ {
					keyword : beginLoc,
					city : '武汉'
				}, {
					keyword : endLoc,
					city : '武汉'
				} ], function(status, result) {
					// result 即是对应的驾车导航信息，相关数据结构文档请参考  https://lbs.amap.com/api/javascript-api/reference/route-search#m_DrivingResult
					if (status === 'complete') {
						log.success('绘制驾车路线完成');
					} else {
						log.error('获取驾车数据失败：' + result);
					}
				});
			})

		}
	</script>


</body>
</html>
