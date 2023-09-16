<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>web_lab2</title>
    <link rel="stylesheet" href="css/style.css">
    <script type="text/javascript"> <%@include file='js/script.js' %> </script>
</head>
<body onload="init()">
<%--onload="init()"--%>
<jsp:useBean id="pointBean" class="Lab_2.PointsTableBean" scope="session"/>

<table id="head" style="width: 100%">
    <tr>
        <th>Wang Mingzhi<br>Group:P32151<br>[Variant:12994]</th>
    </tr>
</table>

<div class="block auto-margin flex">
    <div class="centered">
        <table id="info" >
            <tr>
                <td id="xyr">
                    <div class="auto-margin" id="xblock">
                        <p>Выберите значение X:
                            <output name="x_output" id="x_out" class="output">_</output>
                        </p>
                        <label for="xSelect"></label>
                        <select name="x1" id="xSelect" autocomplete="off" onclick="setX()">
                            <%-- 禁用表单字段的自动完成功能--%>
                            <option name="x11" id="x_11" value="-2">-2</option>
                            <option name="x11" id="x_12" value="-1.5">-1.5</option>
                            <option name="x11" id="x_13" value="-1">-1</option>
                            <option name="x11" id="x_21" value="-0.5">-0.5</option>
                            <option name="x11" id="x_22" value="0">0</option>
                            <option name="x11" id="x_23" value="0.5">0.5</option>
                            <option name="x11" id="x_31" value="1">1</option>
                            <option name="x11" id="x_32" value="1.5">1.5</option>
                            <option name="x11" id="x_33" value="2">2</option>
                        </select>
                    </div>

                    <div class="auto-margin" id="yblock">
                        <p>Введите значение Y(-5,5):
                            <output name="y_output" id="y_out" class="output">_</output>
                        </p>
                        <span class="tooltip">
                        <label for="y_in"></label>
                        <input type="text" name="y_input" autocomplete="off" maxlength="5" id="y_in" value="0"
                               onblur="return verifyY(this);" oninput="return verifyY(this);">
                        </span>
                    </div>

                    <div class="auto-margin" id="rblock">
                        <p>Выберите значение R:
                            <output name="r_output" id="r_out" class="output">_</output>
                        </p>
                        <input type="button" name="r11" id="r_11" value="1" class="rb" autocomplete="off"
                               onclick="setR(r_11.value)">
                        <input type="button" name="r12" id="r_12" value="2" class="rb" autocomplete="off"
                               onclick="setR(r_12.value)">
                        <input type="button" name="r13" id="r_13" value="3" class="rb" autocomplete="off"
                               onclick="setR(r_13.value)">
                        <input type="button" name="r21" id="r_21" value="4" class="rb" autocomplete="off"
                               onclick="setR(r_21.value)">
                        <input type="button" name="r22" id="r_22" value="5" class="rb" autocomplete="off"
                               onclick="setR(r_22.value)">
                        <div id="formblock">
                            <form method="GET" action="check" target="result">
                                <input type="hidden" autocomplete="off" name="x_h" id="x_h_id" value="0">
                                <input type="hidden" autocomplete="off" name="r_h" id="r_h_id" value="0">
                                <input type="hidden" autocomplete="off" name="y_h" id="y_h_id" value="0">
                                <input type="hidden" autocomplete="off" name="load" id="load" value="0">
                                <p><input type="submit" value="Submit"></p>
                            </form>
                        </div>
                    </div>
                </td>
                <td id="err">
                    <p class="centered" id="error"></p>
                </td>
                <td id="graph">
                    <div class="graphic centered">
                        <canvas id="canvas" onclick="clickCanvas(r_out.value)" width="300" height="300"></canvas>
                    </div>
                </td>
            </tr>
        </table>
    </div>

</div>
<div class="block auto-margin">
    <iframe id="iFrame" name="result" src="check?x_h=1&r_h=2&y_h=1&load=1"></iframe>
</div>
<footer>
    <div>
        <p>Web_Lab2</p>
    </div>
</footer>
</body>
</html>