<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Sing Up</title>
    <style><%@include file="styles/signStyle.css"%></style>
    <style><%@include file="styles/commonStyle.css"%></style>
</head>
<body>
<form method="post">
    <table>
        <tr>
            <th>
                <h2>Sign Up</h2>
            </th>
        </tr>
        <tr><td>Name</td></tr>
        <tr>
            <td>
                <input type="text" name="name">
                <br>
                <span class="errorMsg">${msg}</span>
            </td>
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
        <tr><td>Repeat Password</td></tr>
        <tr>
            <td>
                <input type="assword" name="repeatPassword">
                <br>
                <span class="errorMsg">error text</span>
            </td>
        </tr>
        <tr>
            <td colspan="2" class="submit">
                <input type="submit" value="Sign Up">
            </td>
        </tr>
    </table>
</form>
</body>
</html>