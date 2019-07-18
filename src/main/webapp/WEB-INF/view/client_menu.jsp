<%--
  Created by IntelliJ IDEA.
  User: kuzmavladislavvladimirovic
  Date: 2019-06-08
  Time: 01:23
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Client</title>
    <style>
        <%@ include file="/WEB-INF/view/css/style.css" %>
    </style>
</head>
<body>

<h1>Hello, Client!</h1>

<div class="client-info">
    <h2>Client Info:</h2>
    <p>Login: <c:out value="${requestScope.getLogin()}"/></p><br>
    <p>Name: <c:out value="${requestScope.client.getName()}"/></p><br>
    <p>Surname: <c:out value="${requestScope.client.getSurname()}"/></p><br>
    <p>Birthday: <c:out
            value="${requestScope.client.getBirthday().getDay()}.${requestScope.client.getBirthday().getMonth()}.${requestScope.client.getBirthday().getYear()}"/></p>
    <br>
    <p>Address: <c:out
            value="${requestScope.address.getCountry()}, ${requestScope.address.getCity()}, ${requestScope.address.getStreet()} ${requestScope.address.getPostCode()}"/></p>
    <br>
    <p>Email: <c:out value="${requestScope.client.getEmail()}"/></p><br>
    <p>Phone: <c:out value="${requestScope.client.getPhone()}"/></p><br>
</div>

<div class="creditcards">
    <h2>Your Credit Cards:</h2>
    <c:forEach items="${requestScope.creditCardList}" var="creditCard">
        <c:out value="${creditCard.getId()}"/><br>
        <c:out value="${creditCard.getDate()}"/><br>
        <form action="/mybank/card-menu" method="get">
            <c:set var="card_id" scope="request" value="${creditCard.getId()}"/>
            <input type="submit" name="card_menu_button" value="Card Menu">
        </form>
    </c:forEach>
</div>


</body>
</html>
