<%--
  Created by IntelliJ IDEA.
  User: D.J
  Date: 8/5
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/user/login" method="post">
    账号：<input name="username" type="text" placeholder="admin"/>
    密码：<input name="password" type="text" placeholder="123456"/>
    <input type="submit" value="登录">
</form>
</body>
</html>
