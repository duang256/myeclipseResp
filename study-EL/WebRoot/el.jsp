<%@page import="org.omg.Dynamic.Parameter"%>
<%@ page language="java" import="java.util.*,com.bjxst.pojo.*" pageEncoding="utf-8"%>
<%--
<b><%=request.getParameter("uname") %></b>
<b><%=request.getAttribute("weather") %></b>
<b><%=((ArrayList<User>)request.getAttribute("users")).get(1).getName()%></b>
<b><%=((Map<String,String>)request.getAttribute("map")).get("a") %></b>
<b><%=((Map<String,User>)request.getAttribute("us")).get("u1").getName() %></b>
--%>



<!-- 使用EL表达式获取作用域对象数据 -->
<b>${param.uname}</b>
<b>${weather}</b>
<b>${users[1].name}</b>
<b>${map.a}</b>
<b>${us.u1.name}</b> 

${1+2} ${4/2} 
${empty a} 
<br>
${header["User-agent"]}
${headervalues["accept-language"][0]}
<br>
${cookie.JSESSIONID.name}
${cookie.JSESSIONID.value}

