<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="model.SimpleUser" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% User loggedUser = (User) request.getSession().getAttribute("loggedUser"); %>
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
                <% if (loggedUser != null) { %>
                <a href="/app/profile?login=<%=loggedUser.getLogin()%>">
                    <%=loggedUser.getName()%>
                </a>
                <% } %>
                <a href="/app/canvas">Paint now</a>
                <% if (loggedUser != null) { %>
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
        List <SimpleUser> users = (List<SimpleUser>) request.getAttribute("favourites");
        if (users != null && users.size() != 0) {
    %>
    <h3>Favourite profiles</h3>
    <hr>
    <div class="profile">
        <% for (SimpleUser user : users) { %>
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
            users = (List<SimpleUser>) request.getAttribute("allUsers");
            if (users.size() == 0) { %>
        There are no users
        <% } else {
            	for (SimpleUser user: users) { %>
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