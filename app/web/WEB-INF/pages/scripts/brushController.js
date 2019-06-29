function printBrash() {
    let brush = document.getElementById("brush");
    let context = brush.getContext("2d");
    let w = brush.width;
    let h = brush.height;
    context.beginPath();
    context.clearRect(0, 0, w, h);
    context.strokeStyle = strokeStyle;
    context.arc(w/2, h/2, document.getElementById("brushWidth").value, 0, 2 * Math.PI);
    context.stroke();
    context.fillStyle = strokeStyle;
    context.fill();
}

function setColor(arg) {
    strokeStyle = arg;
    printBrash();
}

function undo() {
    context.clearRect(0, 0, canvas.width, canvas.height);
    points = points.filter(point => point.action < actions - 1);
    for (let i = 0; i < points.length; i++) {
        context.beginPath();
        context.strokeStyle = points[i].style;
        context.arc(points[i].x, points[i].y, points[i].width, 0, 2 * Math.PI);
        context.stroke();
        context.closePath();
    }
    actions--;
}