<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <script type="text/javascript">
   function getDate(){
	 
	   //创建ajax引擎对象
	   var ajax;
	   if(window.XMLHttpRequest){//现代版本浏览器
		   ajax=new XMLHttpRequest();
	   }else if(window.AcitveXobject){//老版本浏览器
		   ajax=new ActiveXobject();
	   }
	   //复写onreadystatement函数
	   ajax.onreadystatechange=function(){
		   
		   //判断ajax状态码
		   if(ajax.readyState==4){
			   
			   if(ajax.status==200){
				   //获取响应内容
				   var  result=ajax.responseText;
				   //获取元素对象
				   var showdiv=document.getElementById("showdiv");
				   showdiv.innerHTML=result;
			   }else if(ajax.status==404){
				   var showdiv=document.getElementById("showdiv");
				   showdiv.innerHTML="请求资源不存在";
			   }else if(ajax.status==500){
				   var showdiv=document.getElementById("showdiv");
				   showdiv.innerHTML="服务器繁忙";
			   }
		   }
	   }
	   //发送请求
	   ajax.open("post", "as",false);
	   ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	   ajax.send("name=张三&pwd=123");
   }
  </script>
  <style type="text/css">
  	#showdiv{
  		width:800px;
  		height:600px;
  		border:solid 1px #00FF00;
  	}
  
  </style>
  
  </head>  
  <body>
  	<h3>欢迎登陆426峡谷</h3>
  	<hr>
  	<input type="button" value="测试" onclick="getDate()">
  	<br><br>
  	 <div id="showdiv"></div>
  </body>
</html>
