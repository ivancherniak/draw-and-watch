<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Sign In</title>
    <style><%@include file="styles/commonStyle.css"%></style>
    <style><%@include file="styles/signStyle.css"%></style>
</head>
<body>
<form method="post">
    <table>
        <tr>
            <th>
                <h2>Sign In</h2>
            </th>
        </tr>
        <tr><td>Login</td></tr>
        <tr>
            <td>
                <input type="text" name="login">
                <br>
                <span class="errorMsg">error text</span>
            </td>
        </tr>
        <tr><td>Password</td></tr>
        <tr>
            <td>
                <input type="password" name="password">
                <br>
                <span class="errorMsg">error text</span>
            </td>
        </tr>
        <tr>
            <td colspan="2" class="submit">
                <input type="submit" value="Sign In">
            </td>
        </tr>
        <tr><td class="ref"><a href="/draw_and_watch_war_exploded/registration">Sign up</a></td></tr>
    </table>
</form>
</body>
</html>
