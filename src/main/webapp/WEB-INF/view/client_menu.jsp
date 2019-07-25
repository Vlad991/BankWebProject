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
        <%@ include file="/WEB-INF/view/css/client_menu.css" %>
    </style>
</head>
<body>
<header class="header">
    <h1 class="welcome">Hello, Client!</h1>
</header>

<section class="container">
    <div class="creditcards">
        <h2 class="heading">Your Credit Cards:</h2>
        <c:forEach items="${sessionScope.creditCardList}" var="creditCard">
            <div class="card">
                <div class="id">
                    <c:out value="${creditCard.getId()}"/>
                </div>
                <div class="date">
                    <c:out value="${creditCard.getDate()}"/>
                </div>
                <div class="name"><c:out value="${sessionScope.client.getSurname()}"/> <c:out
                        value="${sessionScope.client.getName()}"/></div>
            </div>
            <form class="menu-form" action="/mybank/client_menu/card_menu" method="post">
                <input class="field id-field" type="text" required placeholder="Enter Card Id" name="id" readonly
                       type="email"
                       onfocus="if
                (this.hasAttribute('readonly')) { this.removeAttribute('readonly'); this.blur(); this.focus(); }">
                <input class="password-field field" type="password" required placeholder="Pin Code" name="pin"/><br>
                <input class="menu-button button" type="submit" name="card_menu_button" value="Card Menu">
            </form>
        </c:forEach>
    </div>
    <div class="client-info">
        <h2 class="heading">Client Info:</h2>
        <ul class="info">
            <li class="info__item">Login: <span class="info__value"><c:out
                    value="${sessionScope.client.getLogin()}"/></span></li>
            <li class="info__item">Name: <span class="info__value"><c:out
                    value="${sessionScope.client.getName()}"/>
            </span></li>
            <li class="info__item">Surname: <span class="info__value"><c:out
                    value="${sessionScope.client.getSurname()}"/></span></li>
            <li class="info__item">Birthday: <span class="info__value"><c:out
                    value="${sessionScope.client.getBirthday().getDay()}.${sessionScope.client.getBirthday().getMonth()}.${sessionScope.client.getBirthday().getYear()}"/></span>
            </li>
            <li class="info__item">Address: <span class="info__value"><c:out
                    value="${sessionScope.address.getCountry()}, ${sessionScope.address.getCity()}, ${sessionScope.address.getStreet()} ${sessionScope.address.getPostCode()}"/></span>
            </li>
            <li class="info__item">Email: <span class="info__value"><c:out
                    value="${sessionScope.client.getEmail()}"/></span></li>
            <li class="info__item">Phone: <span class="info__value"><c:out
                    value="${sessionScope.client.getPhone()}"/></span></li>
        </ul>
    </div>
</section>

</body>
</html>
