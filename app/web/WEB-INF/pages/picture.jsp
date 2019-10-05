<%@ page import="model.*" %>
<%@ page import="services.PictureModel" %>
<%@ page import="services.SimpleUser" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User loggedUser = (User) request.getSession().getAttribute("loggedUser");
    PictureModel pictureModel = (PictureModel) request.getAttribute("pictureModel");
    Picture picture = pictureModel.getPicture();
    SimpleUser paintedBy = picture.getPaintedBy();
%>
<html>
<head>
    <meta charset="utf-8">
    <title>Picture</title>
    <style><%@include file="styles/commonStyle.css"%></style>
    <style><%@include file="styles/pictureStyle.css"%></style>
</head>
<body>
<div class="header">
    <h1>Draw and watch</h1>
    <table>
        <tr>
            <td class="path">
                <a href="/app/home">home</a>/
                <a href="/app/profile?login=<%=paintedBy.getLogin()%>">profile</a>/
                <a href="/app/picture?id=<%=picture.getId()%>">picture</a>/
            </td>
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
    <h3>Picture</h3>
    <hr>
    <img src="<%=picture.getContent()%>">
    <table>
        <tr>
            <td class="info"><%=picture.getCreatedWhen()%></td>
            <td rowspan="2">Likes: <%=pictureModel.getLikes()%></td>
            <td rowspan="2">
                <form action="likeIt" method="post">
                    <input type="hidden" name="pictureId" value="<%=picture.getId()%>">
                    <input type="submit" value="Like it">
                </form>
            </td>
        </tr>
        <tr class="info">
            <td>By: <a href="/app/profile?login=<%=paintedBy.getLogin()%>"><%=paintedBy.getName()%></a></td>
        </tr>
        <tr>
            <td colspan="3">
                <form action="comment" method="post">
                    <hr>
                    Leave a comment:<br>
                    <textarea name="comment"></textarea>
                    <input type="hidden" name="pictureId" value="<%=picture.getId()%>"><br>
                    <input type="submit" value="Post">
                </form>
            </td>
        </tr>
        <%
            for (Comment comment : pictureModel.getComments()) {
                SimpleUser author = comment.getUser();
        %>
        <tr>
            <td colspan="3" class="info">
                <hr>
                <a href="/app/profile?login=<%=author.getLogin()%>"><%=author.getName()%>:</a><br><br>
                <%=comment.getCommentData()%>
            </td>
        </tr>
        <% } %>
    </table>
</div>
</body>
</html>