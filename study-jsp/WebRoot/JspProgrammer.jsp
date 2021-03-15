<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page errorPage="error.jsp" %>
<%-- Jsp的三种注释
	前端语言注释
		会被转译并且发送  (<！-- - -！>)
	java注释
		会到达Servlet，但不会被Servlet执行(//)
	Jsp注释
		不会被转译（不会到达Servlet里边） (<%-- - -%>)
		
	Jsp的page指令： 配置jsp文件转义需要的相关参数
		<%@page 属性名=“属性值” %>
		language="java" 声明jsp要被转译的语言（事实上只会转译java）	
		import：java代码需要带入的包（java.util.* , java.lang.*）
		pageEncoding:当前jsp文件保存的编码格式（同时设置了响应浏览器的编码格式）  汉字是utf-8数据，所以需要设置utf-8格式
		contentType:设置响应浏览器的编码格式，由于pageEncoding会同时设置，所以一般不用设置contentType="text/html;charset=utf-8"
		extends:转译得到的Servlet要继承哪个类（包名+类名全限定路径）
		session:设置转义的servlet中是否开启session支持，默认开启 （true）
		errorPage:设置执行报错跳转到某个页面
		
		
	Jsp的局部代码块：
	<%
		int a=4 
		if(a>3){
			
		%>
		<b>jsp学习很简单</b>
		<%} %>
	
	Jsp的全局代码块
	Jsp的脚本段语句
	Jsp的静态 动态引入
	Jsp的转发标签
		
--%>



<html>
	<head>
		<title>jsp基本语法学习</title>
		<meta charset="utf-8"> <!-- 给浏览器的字符编码格式 -->
	</head>
	
	<body>
		<h3>jsp基本语法学习</h3>
		<hr>
		<%-- 局部代码块--%>
		
		<%
		String str="学习Jsp很开心";
		int a=2 ;
		if(a<3){
		%>
		<i><%=str%></i>   <!-- 这里不能加分号，在Servlet中out.write(str) -->
		<%} %>
		<!-- jsp静态引入 ,将file中的代码拼凑到本文件中，而file文件并没有被转译 -->
		<%@include file="includeStatic.jsp" %>
		<!-- 动态引入 ：会对对象文件进行转译，然后调用转译好的这个文件-->
		<jsp:include page="includeActive.jsp"></jsp:include>
		
		
		<!-- Jsp的转发forward标签  转发到新的页面，两个标签之间可以写数据流转的标签 -->
		<%-- <jsp:forward page="forward.jsp">
		<jsp:param value="aa" name="str"/>
		</jsp:forward> --%>
		
		
		<!-- Jsp的九大内置对象学习 ,Jsp
		文件在转译成对应的Servlet文件的时候自动声明的对象，在Jsp页面直接使用即可
		要以service方法为基础，所以内置对象写在局部代码块或脚本段语句里边
		
		PageContex:页面上下文对象，封存了其他内置对象。封存了Jsp的运行信息
			注意：每个Jsp文件单独拥有一个pageContext对象
			作用域：当前页面
		request：封存请求数据对象
		response：封存响应数据对象
		application：就是ServletContext对象，一个项目只有一个，存储用户数据对象
		out：Jsp内部适用，带有缓冲区的响应对象，效率高于response
		page：代表当前Jsp页面的对象
		exception：异常对象，存储了当前运行的异常信息
			使用此对象需要在Page指令中使用属性isError="true"开启
		config：就是ServletConfig  获取web.xml中的配置数据，完成一些初始化数据的读取
		session：一个用户不同请求共享数据
		-->
		<%
			String num=request.getParameter("str");
		%>
		<b><%=num%></b>
		<br>
		<a href="a/b/b.jsp">b.jsp</a>
		<br>
		<a href="a/a.jsp">a.jsp</a>
		
		
	</body>
</html>

