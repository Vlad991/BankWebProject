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
    <p>Login: <c:out value="${sessionScope.client.getLogin()}"/></p><br>
    <p>Name: <c:out value="${sessionScope.client.getName()}"/></p><br>
    <p>Surname: <c:out value="${sessionScope.client.getSurname()}"/></p><br>
    <p>Birthday: <c:out
            value="${sessionScope.client.getBirthday().getDay()}.${sessionScope.client.getBirthday().getMonth()}.${sessionScope.client.getBirthday().getYear()}"/></p>
    <br>
    <p>Address: <c:out
            value="${sessionScope.address.getCountry()}, ${sessionScope.address.getCity()}, ${sessionScope.address.getStreet()} ${sessionScope.address.getPostCode()}"/></p>
    <br>
    <p>Email: <c:out value="${sessionScope.client.getEmail()}"/></p><br>
    <p>Phone: <c:out value="${sessionScope.client.getPhone()}"/></p><br>
</div>

<div class="creditcards">
    <h2>Your Credit Cards:</h2>
    <c:forEach items="${sessionScope.creditCardList}" var="creditCard">
        <c:out value="${creditCard.getId()}"/><br>
        <%--        <c:set var="card_id" scope="request" value="${creditCard.getId()}"/>--%>
        <c:out value="${creditCard.getDate()}"/><br>
        <form action="/mybank/client_menu/card_menu" method="post">
            <input type="text" required placeholder="Enter Card Id" name="id" readonly type="email" onfocus="if (this.hasAttribute('readonly')) {
    this.removeAttribute('readonly');
    this.blur();
    this.focus(); }">
            <input type="password" required placeholder="Pin Code" name="pin"/><br>
            <input type="submit" name="card_menu_button" value="Card Menu">
        </form>
    </c:forEach>
</div>


</body>
</html>
