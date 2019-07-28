<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Sign In</title>
    <style><%@include file="styles/commonStyle.css"%></style>
    <style><%@include file="styles/signStyle.css"%></style>
    <script src="https://cdn.jsdelivr.net/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
    <script><%@include file="scripts/formValidator.js"%></script>
</head>
<body>
<form action="signin" method="post" name="loginForm">
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
                <% if(request.getAttribute("invalidLogin") != null) { %>
                <span class="errorMsg">
                    <%=request.getAttribute("invalidLogin")%>
                </span>
                <% } %>
            </td>
        </tr>
        <tr><td>Password</td></tr>
        <tr>
            <td>
                <input type="password" name="password">
                <br>
                <% if(request.getAttribute("invalidPassword") != null) { %>
                <span class="errorMsg">
                    <%=request.getAttribute("invalidPassword")%>
                </span>
                <% } %>
            </td>
        </tr>
        <tr>
            <td colspan="2" class="submit">
                <input type="submit" value="Sign In">
            </td>
        </tr>
        <tr><td class="ref"><a href="/app/registration">Sign up</a></td></tr>
        <% if(request.getAttribute("SQLError") != null) { %>
        <tr><td colspan="2">
            <span class="errorMsg">
                <%=request.getAttribute("SQLError")%>
            </span>
        </td></tr>
        <% } %>
        <% if(request.getAttribute("invalidUser") != null) { %>
        <tr><td colspan="2">
            <span class="errorMsg">
                <%=request.getAttribute("invalidUser")%>
            </span>
        </td></tr>
        <% } %>
    </table>
</form>
</body>
</html>
