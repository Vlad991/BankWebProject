<%--
  Created by IntelliJ IDEA.
  User: kuzmavladislavvladimirovic
  Date: 2019-06-08
  Time: 02:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <style>
        <%@ include file="/view/css/style.css" %>
    </style>
</head>
<body>
    <h1>Here you can register!</h1>
    <form method="post" action="/mybank/register">

        <h2>Your login: </h2><br>
        <input type="text" value="<%=request.getParameter("new_login")%>" placeholder="Login" name="login"><br>

        <h2>Your Name: </h2><br>
        <input type="text" placeholder="Name" name="name"><br>

        <h2>Your Surname: </h2><br>
        <input type="text" placeholder="Surname" name="surname"><br>

        <h2>Your Birthday: </h2>
        <input type="text" placeholder="Day" name="day">
        <input type="text" placeholder="Month" name="month">
        <input type="text" placeholder="Year" name="year"><br>

        <h2>Your Country: </h2><br>
        <input type="text" placeholder="Country" name="country"><br>

        <h2>Your City: </h2><br>
        <input type="text" placeholder="City" name="city"><br>

        <h2>Your Street: </h2><br>
        <input type="text" placeholder="Street" name="street"><br>

        <h2>Your Postcode: </h2><br>
        <input type="text" placeholder="Postcode" name="postcode"><br>

        <h2>Your Email: </h2><br>
        <input type="text" placeholder="Email" name="email"><br>

        <h2>Your Phone: </h2><br>
        <input type="text" placeholder="Phone" name="phone"><br>

        <h2>Your Password: </h2><br>
        <input type="password" placeholder="Password" name="password"><br>

        <h2>Repeat your password: </h2><br>
        <input type="password" placeholder="Repeat password" name="repeat_password"><br>

        <input type="submit" name="register_button" value="Register!">
    </form>
</body>
</html>
