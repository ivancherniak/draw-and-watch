<%@ page import="model.User" %>
<%@ page import="model.Picture" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Profile</title>
    <style><%@include file="styles/commonStyle.css"%></style>
    <style><%@include file="styles/profileStyle.css"%></style>
</head>
<body>
<div class="header">
    <h1>Header</h1>
    <table>
        <tr>
            <td class="path">
                <a href="/app/home">home</a>/
                <a href="/app/profile?login=<%=((User) request.getAttribute("userProfile")).getLogin()%>">profile</a>/
            </td>
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
    <h3>Profile</h3>
    <hr>
    <div class="profile">
        <div>
            <table>
                <tr>
                    <td><img src="static/profilePhotos/photo.png"></td>
                    <% if (request.getSession().getAttribute("loggedUser") != null && !((User) request.getAttribute("userProfile")).getLogin().equals(((User) request.getSession().getAttribute("loggedUser")).getLogin())) { %>
                    <td>
                        <% if ((boolean) request.getAttribute("isProfileInFavourites")) { %>
                        <a href="deleteFromFavourites?login=<%=((User) request.getAttribute("userProfile")).getLogin()%>">
                            <input type="button" value="Delete from favourites">
                        </a>
                        <% } else { %>
                        <a href="addToFavourites?login=<%=((User) request.getAttribute("userProfile")).getLogin()%>">
                            <input type="button" value="Add to favourites">
                        </a>
                        <% } %>
                    </td>
                    <% } %>
                </tr>
                <tr class="username"><td><%=((User) request.getAttribute("userProfile")).getName()%></td></tr>
            </table>
        </div>
    </div>
    <% List<Picture> pictures = (List<Picture>) request.getAttribute("userPictures");
    if (pictures.size() != 0) { %>
    <h3>Pictures</h3>
    <hr>
    <div class="profile">
        <% for (Picture picture : pictures) { %>
        <div>
            <a href="/app/picture?id=<%=picture.getId()%>">
                <p><img src="<%=picture.getContent()%>"><p>
            </a>
        </div>
        <% } %>
    </div>
    <% } %>
</div>
</body>
</html>
</html>
