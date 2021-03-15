<%@page import="javax.tools.JavaCompiler"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:out value="哈哈"></c:out>
<c:set var="hello" value="pageContext" scope="page"></c:set>
<c:set var="hello" value="requestContext" scope="request"></c:set>
<c:set var="hello" value="sessionContext" scope="session"></c:set>
<c:set var="hello" value="applicationContext" scope="application"></c:set>
${hello}
<br>
<c:remove var="hello"/><br>

<c:if test="${a>3}">
<b>今天又是快乐学习的一天</b>
</c:if>

<c:set var="score" value="85"></c:set>
<c:choose>
	<c:when test="${score>=90}">
		<b>奖励S级迈巴赫一台</b>
	</c:when>
	<c:when test="${score>=80&&score<90}">
			<b>奖励兰德酷路泽一台</b>
	</c:when>
	<c:when test="${score>=75&&score<80}">
			<b>奖励X5一台</b>
	</c:when>
	<c:when test="${score>=65&&score<75}"></c:when>
	<c:otherwise>
		<b>啥也不是</b>
	</c:otherwise>
</c:choose>




<%
	ArrayList<String> list=new ArrayList<String>();
	list.add("a");
	list.add("b");
	list.add("c");
	request.setAttribute("list", list);
%>

<c:forEach items="${list}" var="str">
		${str}
</c:forEach>


<c:set var="now" value="<%=new java.util.Date() %>"></c:set>

<br>
<fmt:formatDate  type="both" dateStyle="long" timeStyle="long" value="${now}" />
<br>
<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>


