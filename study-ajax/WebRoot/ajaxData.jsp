<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <script type="text/javascript">
  
  	/* function getData(){
  		
  		var name=document.getElementById("uname").value;
  		//创建ajax引擎对象
  		var ajax;
  		if(window.XMLHttpRequest){//现代版本浏览器
 		   ajax=new XMLHttpRequest();
 	   }else if(window.AcitveXobject){//老版本浏览器
 		   ajax=new ActiveXobject();
 	   }
  		//复写onreadystatechange
  		ajax.onreadystatechange=function(){
  			if(ajax.readyState==4){
  				if(ajax.status==200){
  					var result=ajax.responseText;
  					eval("var obj="+result);
  					var td1=document.getElementById("td1");
  					td1.innerHTML=obj.name;
  					var td2=document.getElementById("td2");
  					td2.innerHTML=obj.price;
  					var td3=document.getElementById("td3");
  					td3.innerHTML=obj.loc;
  				}
  			}
  		}
		ajax.open("get","us?name="+name);
		ajax.send(null);
  		//发送请求
  	} */
	function getxmlData(){
	  		
	  		var name=document.getElementById("uname").value;
	  		//创建ajax引擎对象
	  		var ajax=getAjax();
	  		if(window.XMLHttpRequest){//现代版本浏览器
	 		   ajax=new XMLHttpRequest();
	 	   }else if(window.AcitveXobject){//老版本浏览器
	 		   ajax=new ActiveXobject();
	 	   }
	  		//复写onreadystatechange
	  		ajax.onreadystatechange=function(){
	  			if(ajax.readyState==4){
	  				if(ajax.status==200){
	  					var doc=ajax.responseXML;
	  					var td1=document.getElementById("td1");
	  					td1.innerHTML=doc.getElementsByTagName("name")[0].innerHTML;
	  					var td2=document.getElementById("td2");
	  					td2.innerHTML=doc.getElementsByTagName("price")[0].innerHTML;
	  					var td3=document.getElementById("td3");
	  					td3.innerHTML=doc.getElementsByTagName("loc")[0].innerHTML;
	  					
	  				}
	  			}
	  		}
			ajax.open("get","us?name="+name);
			ajax.send(null);
	  		//发送请求
	  	}
  	
  	
  	
  		function getAjax(){
  			var ajax;
  			if(window.XMLHttpRequest){//现代版本浏览器
 	 		   ajax=new XMLHttpRequest();
 	 	   }else if(window.AcitveXobject){//老版本浏览器
 	 		   ajax=new ActiveXobject();
 	 	   }
  			return ajax;
  		}
  
  </script>
  

  </head>
  
  <body>
  <h3>欢迎访问英雄商店</h3>
   <hr>
   	英雄名称：<input type="text" name="uname" value="" id="uname">
   		<input type="button" value="搜索" onclick="getxmlData()">
   <hr>
   	<table border="1px" >
   		<tr>
	   		<td>姓名</td>
	   		<td>价格</td>
	   		<td>位置</td>
   		</tr>
   		<tr>
	   		<td id='td1'>&nbsp</td>
	   		<td id='td2'>&nbsp</td>
	   		<td id='td3'>&nbsp</td>
   		</tr>
   	
   	</table>
  </body>
</html>
