<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@include file="./common/taglibs.jsp"%>
<%@ page session="false"%>
<html>
<head>
    <%@include file="./common/header.jsp"%>
</head>
<body>
<a href="<%=request.getContextPath()%>/addCompany.mmx?a=abc">添加公司信息</a><br/>
<a href="<%=request.getContextPath()%>/logout">退出</a>
</body>
</html>