
<%--
  Created by IntelliJ IDEA.
  User: kuzmavladislavvladimirovic
  Date: 2019-06-17
  Time: 21:31
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Bank</title>
    <style>
        <%@ include file="/WEB-INF/view/css/style.css" %>
    </style>
</head>
<body>
<div class="container">
    <h1>Welcome to our bank!</h1>
    <form action="/mybank/login" method="post">
        <input type="text" required placeholder="Login" name="login"/><br>
        <input type="password" required placeholder="Password" name="password"/><br>
        <input type="submit" name="login_button" value="Log in"><br>
        <p class="error"><c:out value="${requestScope.message}"/></p>
    </form>
    <form action="/mybank/register" method="get">
        <input type="text" required placeholder="Your new login" name="login"/><br>
        <input type="submit" name="registration_button" value="Register">
    </form>
</div>
</body>
</html>
