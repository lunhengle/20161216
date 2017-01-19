<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lunhengle
  Date: 2017/1/18
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆界面</title>
</head>
<body>
<c:out value="${message }"/>
<form action="/login.do" method="post">
    <table>
        <tr>
            <td>用户名：<input type="text" id="username" name="username" value=""/></td>
            <td>密码：<input type="password" id="password" name="password" value=""/></td>
            <td><input type="submit" value="登陆"/></td>
        </tr>
    </table>
</form>
<a href="/admin.do">跳转到管理员用户界面</a>
</body>
</html>
