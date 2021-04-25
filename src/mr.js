window.onload = function () {
  
    var canvas = document.getElementById("mycanvas");
    var ctx = canvas.getContext("2d");

    var w = window.innerWidth;
    var h = window.innerHeight;

    var r = 300;

    ctx.canvas.width = w;
    ctx.canvas.height = h;

    var d = 0;
    var n = 0;

    setInterval(onTimerTick, 10);

    function onTimerTick() {
        update();
        render();
    }

    function update() {
        d += 0.00001;
        n += 0.000001;
    }

    function render() {
        ctx.fillStyle = "#33130c";
        ctx.fillRect(0, 0, w, h);
        ctx.translate(w / 2, h / 2);
        ctx.lineWidth = 1;
        ctx.strokeStyle = "#ffffff";
        for (let i = 0; i <= 360; i++) {
            let k1 = i * d;
            let r1 = r * Math.sin(R2D(n * k1));
            let x1 = r1 * Math.cos(R2D(k1));
            let y1 = r1 * Math.sin(R2D(k1));
            let k2 = (i + 1) * d;
            let r2 = r * Math.sin(R2D(n * k2));
            let x2 = r2 * Math.cos(R2D(k2));
            let y2 = r2 * Math.sin(R2D(k2));
            ctx.strokeStyle = "hsl(" + i + ", 100%, 50%)";
            ctx.beginPath();
            ctx.moveTo(parseInt(x1), parseInt(y1));
            ctx.lineTo(parseInt(x2), parseInt(y2));
            ctx.stroke();
        }
        ctx.translate(-w / 2, -h / 2);
    }

    function R2D(radians) {
        var pi = Math.PI;
        return radians * (180 / pi);
    }
}
