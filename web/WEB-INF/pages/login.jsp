<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <form:form method="post" modelAttribute="user" action="/draw_and_watch_war_exploded/logged">
        <form:label path="login">Login</form:label>
        <form:input path="login"/>
        <input type="submit" value="Login">
    </form:form>
</body>
</html>
