
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
<header class="header">
    <h1 class="welcome">Welcome to our bank!</h1>
</header>

<section class="container">
    <form class="login-form" action="/mybank/login" method="post">
        <h2 class="login-title">Log In:</h2>
        <input class="login field" type="text" required placeholder="Login" name="login"/>
        <input class="password field" type="password" required placeholder="Password" name="password"/><br>
        <input class="login-button button" type="submit" name="login_button" value="Log in"><br>
        <p class="error">
            <c:out value="${requestScope.message}"/>
        </p>
    </form>
    <form class="registation-form" action="/mybank/register" method="get">
        <input class="new-login field" type="text" required placeholder="Your new login" name="login"/><br>
        <input class="register-button button" type="submit" name="registration_button" value="Register">
    </form>
</section>

</body>
</html>
