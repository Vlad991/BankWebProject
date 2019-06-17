<%--
  Created by IntelliJ IDEA.
  User: kuzmavladislavvladimirovic
  Date: 2019-06-17
  Time: 21:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Bank</title>
    <style>
        <%@ include file="/view/css/style.css" %>
    </style>
</head>
<body>
<div class="container">
    <h1>Welcome to our bank!</h1>
    <form action="/mybank/login" method="post">
        <input type="text"required placeholder="Login" name="existing_login"/><br>
        <input type="password"required placeholder="Password" name="existing_password"/><br>
        <input type="submit" name="login_button" value="Log in">
    </form>
    <form action="/mybank/register" method="post">
        <input type="text"required placeholder="Your new login" name="new_login"/><br>
        <input type="submit" name="registration_button" value="Register">
    </form>
</div>
</body>
</html>
