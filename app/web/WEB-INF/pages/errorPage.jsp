<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Error page</title>
    <style>
        <%@include file="styles/commonStyle.css" %>
    </style>
</head>
<body>
<div class="header">
    <h1>Oops... Something went wrong.</h1>
    <table>
        <tr>
            <td class="path"><a href="/app/home">home</a>/</td>
        </tr>
    </table>
</div>
<div class="content">
    <h3 class="errorMsg">System cannot perform this operation. Please, contact administrator.</h3>
</div>
</body>
</html>
