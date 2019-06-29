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
                <a href="#">home</a>/
                <a href="#">profile</a>/
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
    <h3>Profile</h3>
    <hr>
    <div class="profile">
        <div>
            <table>
                <tr>
                    <td><img src="static/profilePhotos/photo.png"></td>
                    <td>
                        <form>
                            <input type="submit" value="Add to favourites">
                        </form>
                    </td>
                </tr>
                <tr class="username"><td>Username</td></tr>
            </table>
        </div>
    </div>
    <h3>Pictures</h3>
    <hr>
    <div class="profile">
        <div>
            <a href="#">
                <p><img src="static/profilePhotos/photo.png"><p>
            </a>
        </div>
        <div>
            <a href="#">
                <p><img src="static/profilePhotos/photo.png"><p>
            </a>
        </div>
        <div>
            <a href="#">
                <p><img src="static/profilePhotos/photo.png"><p>
            </a>
        </div>
    </div>
</div>
</body>
</html>
</html>
