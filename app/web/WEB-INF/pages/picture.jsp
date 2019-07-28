<%@ page import="model.Picture" %>
<%@ page import="model.User" %>
<%@ page import="model.Comment" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Picture</title>
    <style><%@include file="styles/commonStyle.css"%></style>
    <style><%@include file="styles/pictureStyle.css"%></style>
</head>
<body>
<div class="header">
    <h1>Header</h1>
    <table>
        <tr>
            <td class="path">
                <a href="#">home</a>/
                <a href="#">profile</a>/
                <a href="#">picture</a>/
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
    <h3>Picture</h3>
    <hr>
    <img src="<%=((Picture) request.getAttribute("picture")).getContent()%>">
    <table>
        <tr>
            <td class="info"><%=((Picture) request.getAttribute("picture")).getCreatedWhen()%></td>
            <td rowspan="2">Likes: 5</td>
            <td rowspan="2">
                <form>
                    <input type="submit" value="Like it">
                </form>
            </td>
        </tr>
        <tr class="info">
            <td>By: <a href="/app/profile?login=<%=((Picture) request.getAttribute("picture")).getPaintedBy().getLogin()%>"><%=((Picture) request.getAttribute("picture")).getPaintedBy().getName()%></a></td>
        </tr>
        <tr>
            <td colspan="3">
                <form action="comment" method="post">
                    <hr>
                    Leave a comment:<br>
                    <textarea name="comment"></textarea>
                    <input type="hidden" name="pictureId", value="<%=((Picture) request.getAttribute("picture")).getId()%>"><br>
                    <input type="submit" value="Post">
                </form>
            </td>
        </tr>
        <% for (Comment comment : (List<Comment>) request.getAttribute("comments")) { %>
        <tr>
            <td colspan="3" class="info">
                <hr>
                <a href="/app/profile?login=<%=comment.getLogin()%>"><%=comment.getName()%>:</a><br><br>
                <%=comment.getCommentData()%>
            </td>
        </tr>
        <% } %>
    </table>
</div>
</body>
</html>