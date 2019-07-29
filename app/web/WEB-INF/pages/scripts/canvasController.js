var context;
var canvas;
var strokeStyle = "black";
var points = [];
var actions = 0;

function init() {
    canvas = document.getElementById("drawCanvas");
    context = canvas.getContext("2d");

    let mouse = {
        x: 0,
        y: 0
    };
    let draw = false;

    canvas.addEventListener("mousedown", function (e) {
        context.strokeStyle = strokeStyle;
        context.lineWidth = document.getElementById("brushWidth").value;
        mouse.x = e.pageX - this.offsetLeft;
        mouse.y = e.pageY - this.offsetTop;
        draw = true;
        context.beginPath();
        context.arc(mouse.x, mouse.y, context.lineWidth / 2, 0, 2 * Math.PI);
        context.stroke();
        points.push({
            x: mouse.x,
            y: mouse.y,
            style: strokeStyle,
            width: context.lineWidth / 2,
            action: actions
        });
    });
    canvas.addEventListener("mousemove", function (e) {
        if (draw == true) {
            mouse.x = e.pageX - this.offsetLeft;
            mouse.y = e.pageY - this.offsetTop;
            context.beginPath();
            context.arc(mouse.x, mouse.y, context.lineWidth / 2, 0, 2 * Math.PI);
            context.stroke();
            context.closePath();
            points.push({
                x: mouse.x,
                y: mouse.y,
                style: strokeStyle,
                width: context.lineWidth / 2,
                action: actions
            });
        }
    });
    document.addEventListener("mouseup", function (e) {
        actions += draw;
        draw = false;
    });
    printBrash();
}

function to_image() {
    document.getElementById('imgId')
        .setAttribute('value', canvas.toDataURL("image/png").replace("image/png", "image/octet-stream"));
}