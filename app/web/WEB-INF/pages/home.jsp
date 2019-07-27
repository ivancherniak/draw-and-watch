<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Home</title>
    <style><%@include file="styles/commonStyle.css"%></style>
    <style><%@include file="styles/homeStyle.css"%></style>
</head>
<body>
<div class="header">
    <h1>Header</h1>
    <table>
        <tr>
            <td class="path"><a href="/app/home">home</a>/</td>
            <td class="account">
                <% if (request.getSession().getAttribute("loggedUser") != null) { %>
                <a href="/app/profile?login=<%=((User) request.getSession().getAttribute("loggedUser")).getLogin()%>">
                    <%=((User) request.getSession().getAttribute("loggedUser")).getName()%>
                </a>
                <% } %>
                <a href="/app/canvas">Paint now</a>
                <% if (request.getSession().getAttribute("loggedUser") != null) { %>
                <a href="/app/logout">Logout</a>
                <% } else { %>
                <a href="/app/signin">Login</a>
                <% } %>
            </td>
        </tr>
    </table>
</div>
<div class="content">
    <%
        List <User> list = (List<User>) request.getAttribute("favourites");
        if (list != null && list.size() != 0) {
    %>
    <h3>Favourite profiles</h3>
    <hr>
    <div class="profile">
        <% for (User user : list) { %>
        <div>
            <a href="/app/profile?login=<%=user.getLogin()%>">
                <p><img src="static/profilePhotos/photo.png"/><p>
                <%=user.getLogin()%>
            </a>
        </div>
        <% } %>
    </div>
    <% } %>
    <h3>All profiles</h3>
    <hr>
    <% if (request.getAttribute("SQLError") != null) { %>
    <span class="errorMsg"><%=request.getAttribute("SQLError")%></span>
    <% } else { %>
    <div class="profile">
        <%
            List<User> users = (List<User>) request.getAttribute("allUsers");
            if (users.size() == 0) { %>
        There are no users
        <% } else {
            	for (User user: (List<User>) request.getAttribute("allUsers")) { %>
        <div>
            <a href="/app/profile?login=<%=user.getLogin()%>">
                <p><img src="static/profilePhotos/photo.png"><p>
                <%= user.getName() %>
            </a>
        </div>
        <% }} %>
    </div>
    <% } %>
</div>
</body>
</html>