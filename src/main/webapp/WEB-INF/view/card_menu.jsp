<%--
  Created by IntelliJ IDEA.
  User: kuzmavladislavvladimirovic
  Date: 2019-07-18
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Card Menu</title>
    <style>
        <%@ include file="/WEB-INF/view/css/style.css" %>
    </style>
</head>
<body>
<p>Login: <c:out value="${sessionScope.login}"/></p><br>

<div class="card-info">
    <h2>Card Info:</h2>
    <p>Id: <c:out value="${requestScope.id}"/></p><br>
    <p>Date: <c:out value="${requestScope.date}"/></p><br>
    <p>Code: <c:out value="${requestScope.code}"/></p><br>
    <p>Status: <c:out value="${requestScope.status}"/></p><br>
</div>

<div class="card-operations">
    <h2>Your account: <c:out value="${requestScope.sum}"/></h2><br>
    <form action="/card_menu/transfer_money_to_account" method="get">
        <input type="submit" name="transfer_button" value="Transfer Money To Another Account">
    </form>
    <form action="/card_menu/topup_account_on_phone" method="get">
        <input type="submit" name="topup_button" value="Top Up The Account On The Phone">
    </form>
</div>
</body>
</html>
