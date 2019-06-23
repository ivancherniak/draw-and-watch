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
                <a href="#">Username</a>
                <a href="#">Paint now</a>
                <a href="#">Logout</a>
            </td>
        </tr>
    </table>
</div>
<div class="content">
    <h3>Picture</h3>
    <hr>
    <img src="static/profilePhotos/photo.png">
    <table>
        <tr>
            <td class="info">Created: 23 June 2019 19:09</td>
            <td rowspan="2">Likes: 5</td>
            <td rowspan="2">
                <form>
                    <input type="submit" value="Like it">
                </form>
            </td>
        </tr>
        <tr class="info">
            <td>By: <a href="#">Test_user</a></td>
        </tr>
    </table>
</div>
</body>
</html>
