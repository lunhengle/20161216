<%--
  Created by IntelliJ IDEA.
  User: lunhengle
  Date: 2017/1/7
  Time: 18:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆页面</title>
</head>
<body>
<form about="login.do" method="post">
    <table>
        <tr>
            <td>用户名：</td>
            <td><input type="text" name="username" value=""/></td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><input type="password" name="password" value=""/></td>
        </tr>
        <tr>
            <td><input type="submit" value="登陆"></td>
        </tr>
    </table>
</form>
</body>
</html>