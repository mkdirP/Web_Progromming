<?php
include 'Validator.php';
header('Access-Control-Allow-Origin: *');
date_default_timezone_set('Europe/Moscow');


function check_coords($x, $y, $r) {
    $flag = false;
    if ($x >= 0 && $y >= 0 && pow($x, 2) + pow($y, 2) <= pow($r, 2)) {
        $flag = true;
    }
    if ($x <= 0 && $y <= 0 && abs($x - $y) <= $r/2) {
        $flag = true;
    }
    if ($x >= 0 && $y <= 0 && $x <= $r/2 && $y >= -$r) {
        $flag = true;
    }
    return $flag;
}


$current_time = date("H:i:s");
$starting_time = microtime(true);
$x_cp = $_POST["x"];
$y_cp = $_POST["y"];
$r_cp = $_POST["r"];

if (isset($_POST["x"]) && isset($_POST["y"]) && isset($_POST["r"])) {
    $validator = new Validator;
    if ($validator->validate($_POST["x"], $_POST["y"], $_POST["r"])) {
        $x = intval($_POST["x"]);
        $y = floatval($_POST["y"]);
        $r = floatval($_POST["r"]);
        
        $checked_dot = check_coords($x, $y, $r) ? "TRUE" : "FALSE";
        
        $finish_time = round((microtime(true) - $starting_time) * 1000000, 2);
        
        exit("
            <tr>
                <th>$x_cp</th>
                <th>$y_cp</th>
                <th>$r_cp</th>
                <th>$current_time</th>
                <th>$finish_time</th>
                <th>$checked_dot</th>
            </tr>");
    } else {
        exit("У сервера неправильные данные!");
    }
}
