let prev_y = 0;
let res = 0;
let count = 0;

const correctR = [1, 2, 3, 4, 5];
let is_default_graphic = false;

function init() {
    [r_out, x_out, y_out].forEach(f=>f.value='_');
    createGraphic('canvas', r_out.value);
}

// 错误信息的输出
function error(message) {
    let errorField = document.getElementById('error');
    errorField.innerText = message;
    setTimeout(()=>errorField.innerText='', 2000);
}

function clickCanvas(R) {
    console.log("Click on canvas");
    let canvas = document.getElementById("canvas");
    console.log('is default graphic: ' + is_default_graphic);
    if (is_default_graphic) {
        console.log('error: R is not set');
        createGraphic('canvas', 0);
        let canvas = document.getElementById("canvas"), context = canvas.getContext("2d");
        context.strokeStyle = "#da7f8f";
        context.fillStyle = "#ff0014";
        context.font = '20px Arial';
        context.fillText('You have to set ', 20, 50);
        context.fillText('R parameter', 20, 70);
        return;
    }

    let br = canvas.getBoundingClientRect();
    let left = br.left;
    let top = br.top;

    let event = window.event;
    let x = event.clientX - left;
    let y = event.clientY - top;

    markPointFromServer((x - 150) / 130 * R, (-y + 150) / 130 * R, R);
}

// 点击时后台的输出
function markPointFromServer(x, y, r) {
    console.log('try to mark point from server with x:' + x + ', y:' + y + ', r:' + r);
    if (!checkAllParameters(x, y, r)) {
        error('Wrong parameters');
        return false;
    } else {
        fetch("./check?&x_h=" + encodeURI(x) + "&y_h=" + encodeURI(y) + "&r_h=" + encodeURI(r), {
            method: 'GET',
            headers: {
                'Content-Type': 'text/plain;charset=UTF-8'
            }
        })


        document.getElementById('iFrame').src = document.getElementById('iFrame').src
        return true;
    }
}

// 控制台打印最近五条列表信息的输出
function markPoint(x, y, r, hit) {
    console.log('Marking point ' + x + ', ' + y + ', ' + r + ', ' + hit);
    //createGraphic('canvas', r);
    let canvas = document.getElementById("canvas"), context = canvas.getContext("2d");

    context.beginPath();
    context.rect(Math.round(150 + ((x / r) * 130)) - 3, Math.round(150 - ((y / r) * 130)) - 3, 6, 6);
    context.closePath();
    context.strokeStyle = 'black';

    let color = 'red';
    hit = hit.toString();

    if (hit.trim() === "true") {
        color = 'lime';
    }

    context.fillStyle = color;
    context.fill();
    context.stroke();
}

function egg() {
    let canvas = document.getElementById("canvas"),
        context = canvas.getContext("2d");
    let img = new Image();

    img.src = "./img/angryMushroom.png";

    context.drawImage(img, 25, 97);

    let currentAngle = 0.1;
    let vx = 0;
    let vy = 0;

    let int = setInterval(function () {
        vx = Math.cos(currentAngle) * 100 - 100;
        vy = Math.sin(currentAngle) * 100;

        createGraphic("canvas", r_out.value);
        context.drawImage(img, 25 - vx, 97 - vy);
        currentAngle += 0.05;
        if (currentAngle > 3.1) {
            clearInterval(int);
            createGraphic("canvas", r_out.value);
        }
    }, 25);
}

// 绘制图表
function createGraphic(id, r) {
    if (r === 0 || r === '_') {
        is_default_graphic = true;
        r = 1;
    }else{
        is_default_graphic = false;
    }
    let canvas = document.getElementById(id), context = canvas.getContext("2d");
    context.clearRect(0, 0, canvas.width, canvas.height);

    // rectangle
    context.beginPath();
    context.rect(20, 20, 130, 130);
    context.closePath();
    context.strokeStyle = "#2f9aff";
    context.fillStyle = "#2f9aff";
    context.fill();
    context.stroke();

    // sector -circle
    context.beginPath();
    context.moveTo(150, 150);
    context.arc(150, 150, 130, Math.PI / 2,Math.PI);
    context.closePath();
    context.strokeStyle = "#2f9aff";
    context.fillStyle = "#2f9aff";
    context.fill();
    context.stroke();

    // triangle
    context.beginPath();
    context.moveTo(150, 150);
    context.lineTo(150, 85);
    context.lineTo(215, 150);
    context.lineTo(150, 150);
    context.closePath();
    context.strokeStyle = "#2f9aff";
    context.fillStyle = "#2f9aff";
    context.fill();
    context.stroke();

    // axes
    context.beginPath();
    context.font = "10px Verdana";
    context.strokeStyle = "black";
    context.fillStyle = "black";
    context.moveTo(150, 0);
    context.lineTo(150, 300);
    context.moveTo(150, 0);
    context.lineTo(145, 15);
    context.moveTo(150, 0);
    context.lineTo(155, 15);
    context.fillText("Y", 160, 10);
    context.moveTo(0, 150);
    context.lineTo(300, 150);
    context.moveTo(300, 150);
    context.lineTo(285, 145);
    context.moveTo(300, 150);
    context.lineTo(285, 155);
    context.fillText("X", 290, 130);

    // Y parts
    context.moveTo(145, 20);
    context.lineTo(155, 20);
    context.fillText(is_default_graphic ? 'R' : String(r), 160, 20);
    context.moveTo(145, 85);
    context.lineTo(155, 85);
    context.fillText(is_default_graphic ? 'R/2' : String(r / 2), 160, 78);
    context.moveTo(145, 215);
    context.lineTo(155, 215);
    context.fillText(is_default_graphic ? '-R/2' : String(-(r / 2)), 160, 215);
    context.moveTo(145, 280);
    context.lineTo(155, 280);
    context.fillText(is_default_graphic ? '-R' : String(-r), 160, 280);

    // X parts
    context.moveTo(20, 145);
    context.lineTo(20, 155);
    context.fillText(is_default_graphic ? '-R' : String(-r), 15, 140);
    context.moveTo(85, 145);
    context.lineTo(85, 155);
    context.fillText(is_default_graphic ? '-R/2' : String(-(r / 2)), 70, 140);
    context.moveTo(215, 145);
    context.lineTo(215, 155);
    context.fillText(is_default_graphic ? 'R/2' : String(r / 2), 215, 140);
    context.moveTo(280, 145);
    context.lineTo(280, 155);
    context.fillText(is_default_graphic ? 'R' : String(r), 280, 140);

    context.closePath();
    context.strokeStyle = "black";
    context.fillStyle = "black";
    context.stroke();
}

function isXcorrect(x){
    if (count < 2) {
        count++;
        res = Number(res) + Number(r);
    } else {
        res = Number(res) + Number(r);
        count = 0;
        if (Number(res) === 2)
            egg();
        res = 0;
    }
}

function isRcorrect(r) {
    return isNumber(r) && correctR.includes(Number(r));
}

function isYcorrect(y) {
    let yFloat = parseFloat(y.replace(/,/, '.'));
    return isNumber(y.replace(/,/, '.')) && yFloat > -5 && yFloat < 5;
}
// 用户可以点击，如果增加这个布尔值，则无法点击图片
// let isXcorrect = false;

function setX() {
    let mySelect = document.getElementById("xSelect");
    let index = mySelect.selectedIndex ;
    x_h_id.value = mySelect.options[index].value;
    x_out.value = mySelect.options[index].value;
    isXcorrect = true;
}

function setR(r) {
    // if (count < 2) {
    //     count++;
    //     res = Number(res) + Number(r);
    // } else {
    //     res = Number(res) + Number(r);
    //     count = 0;
    //     if (Number(res) === 2)
    //         egg();
    //     res = 0;
    // }
    console.log('setting radius: ' + r);
    r_h_id.value = r;
    r_out.value = r;
    createGraphic('canvas', r);
}

// 检查y
function verifyY(y) {
    let y1 = parseFloat(y.value.replace(/,/, '.'));
    let elem = document.getElementById("y_in");
    if (y.value !== '' && y.value !== '-') {
        if (!isYcorrect(y.value)) {
            y.focus();
            elem.style.backgroundColor = "red";
            y.value = prev_y;
            return false;
        }
        prev_y = y1;
        y_h_id.value = y1;
        y_out.value = y1;
        elem.style.backgroundColor = "#E0FFFF";
        return true;
    }
    elem.style.backgroundColor = "#E0FFFF";
    prev_y = y.value;
    return true;
}

// 返回：n和n-0转换为float后是否是数字
function isNumber(n) {
    return !isNaN(parseFloat(n)) && !isNaN(n - 0)
}

// 检查xyr
function checkAllParameters(x, y, r) {
    return isNumber(x) && isNumber(y) && isRcorrect(r) ;
}
