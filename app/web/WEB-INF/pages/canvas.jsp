<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Draw</title>
    <style><%@include file="styles/commonStyle.css"%></style>
    <style><%@include file="styles/drawStyle.css"%></style>
    <script><%@include file="scripts/canvasController.js"%></script>
    <script><%@include file="scripts/brushController.js"%></script>
</head>
<body onload="init()">
<div class="header">
    <h1>Header</h1>
    <table>
        <tr>
            <td class="path">
                <a href="#">home</a>/
                <a href="#">profile</a>/
                <a href="#">draw</a>/
            </td>
            <td class="account">
                <a href="#">
                    <% if (request.getSession().getAttribute("loggedUser") != null) { %>
                    <%=((User) request.getSession().getAttribute("loggedUser")).getName()%>
                    <% } %>
                </a>
                <a href="#">Paint now</a>
                <a href="#">Logout</a>
            </td>
        </tr>
    </table>
</div>
<div class="content">
    <div class="canvasAndColors">
        <div class="canvas">
            <canvas id="drawCanvas" width="800" height="600">
                Your browser does not support Canvas
            </canvas>

        </div>
        <div class="colors">
            <div class="colorPicker brown" onclick="setColor('brown');"></div>
            <div class="colorPicker black" onclick="setColor('black');"></div>
            <div class="colorPicker red" onclick="setColor('red');"></div>
            <div class="colorPicker grey" onclick="setColor('grey');"></div>
            <div class="colorPicker orange" onclick="setColor('orange');"></div>
            <div class="colorPicker silver" onclick="setColor('silver');"></div>
            <div class="colorPicker yellow" onclick="setColor('yellow');"></div>
            <div class="colorPicker lightBlue" onclick="setColor('lightBlue');"></div>
            <div class="colorPicker pink" onclick="setColor('pink');"></div>
            <div class="colorPicker white" onclick="setColor('white');"></div>
            <div class="colorPicker navajowhite" onclick="setColor('navajowhite');"></div>
            <div class="colorPicker deepskyblue" onclick="setColor('deepskyblue');"></div>
            <div class="colorPicker lime" onclick="setColor('lime');"></div>
            <div class="colorPicker blue" onclick="setColor('blue');"></div>
            <div class="colorPicker turquoise" onclick="setColor('turquoise');"></div>
            <div class="colorPicker green" onclick="setColor('green');"></div>
        </div>
    </div>
    <div class="brushWidth">
        <form>
            <input type="submit" value="Save">
        </form>
        <div class="scroller">
            Brush width:
            <input type="range" id="brushWidth" min="1" max="30" step="2" oninput="printBrash()">
        </div>
        <canvas id="brush" width="60" height="60"></canvas>
        <input type="button" onclick="undo()" value="undo">
    </div>
</div>
</body>
</html>
