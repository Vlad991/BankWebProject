<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kuzmavladislavvladimirovic
  Date: 2019-06-08
  Time: 01:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
    <style>
        <%@ include file="/WEB-INF/view/css/style.css" %>
    </style>
</head>
<body>
    <h1>Hello, Admin!</h1>
    <c:forEach items="${requestScope.clientList}" var="client">
        <c:out value="${client.getId()}"/>
        <c:out value="${client.getLogin()}"/>
        <c:out value="${client.getName()}"/>
        <c:out value="${client.getSurname()}"/>
        <c:out value="${client.getBirthday()}"/>
        <c:out value="${client.getAddressId()}"/>
        <c:out value="${client.getEmail()}"/>
        <c:out value="${client.getPhone()}"/>
        <c:out value="${client.getPassword()}"/>
        <form action="/mybank/client_menu/card_menu" method="post">
            <input type="submit" name="admin-button" value="Admin Button">
        </form><br>
    </c:forEach>
</body>
</html>
