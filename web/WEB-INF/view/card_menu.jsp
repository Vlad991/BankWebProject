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
        <%@ include file="/css/style.css" %>
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

    <div class="transfer-money">
        <h2>Transfer Money</h2>
        <form action="/mybank/client_menu/card_menu/transfer_money" method="post">
            From: <input type="text" required placeholder="Enter Card Id" name="id" readonly type="email" onfocus="if (this.hasAttribute('readonly')) {
    this.removeAttribute('readonly');
    this.blur();
    this.focus(); }"><br><br>
            <input type="password" required placeholder="Pin Code" name="pin"/><br><br>
            To: <input type="text" required placeholder="Enter Recipient Card Id" name="recipient_id"><br><br>
            <input type="text" required placeholder="Enter Sum" name="sum"><br><br>
            <input type="submit" name="transfer_button" value="Transfer Money To Another Account">
        </form>
    </div>

    <form action="/mybank/card_menu/topup_account_on_phone" method="get">
        From: <input type="text" required placeholder="Enter Card Id" name="id" readonly type="email" onfocus="if (this.hasAttribute('readonly')) {
    this.removeAttribute('readonly');
    this.blur();
    this.focus(); }"><br><br>
        <input type="password" required placeholder="Pin Code" name="pin"/><br><br>
        To: <input type="text" required placeholder="Enter Phone Number" name="phone"><br><br>
        <input type="text" required placeholder="Enter Sum" name="sum"><br><br>
        <input type="submit" name="topup_button" value="Top Up The Account On The Phone">
    </form>
</div>
</body>
</html>
