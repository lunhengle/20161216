<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lunhengle
  Date: 2017/1/18
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主页面</title>
</head>
<body>
<c:out value="${message }"/>
登陆成功!
<a href="/admin.do">跳转到管理员界面</a><br>
<a href="/user.do">跳转到用户界面</a><br>
<a href="/logout.do">退出系统</a>
</body>
</html>
