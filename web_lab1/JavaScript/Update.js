function update(){
    // x-复选框
    let xvals = document.getElementsByName("x_values");
    for (var i=0; i<xvals.length; i++){
        if (xvals[i].checked){
            var xval = xvals[i].value;
        }
    }

    // y-text
    let yval = document.getElementById("Y").value;

    // r-radio
    let rvals = document.getElementsByName("r_values");
    for (var i=0; i<rvals.length; i++){
        if (rvals[i].checked){
            var rval = rvals[i].value;
        }
    }

    checkInput(yval);
    if (checkInput(yval)) {
        $.ajax({
            type: "POST",
            url: '../php/index.php',
            async: false,
            data: { "x": xval, "y": yval, "r": rval },
            success: function (data) {
                updateTable(data);
            },
            error: function (xhr, textStatus, err) {
                alert("readyState: " + xhr.readyState + "\n"+
                    "responseText: " + xhr.responseText + "\n"+
                    "status: " + xhr.status + "\n"+
                    "text status: " + textStatus + "\n" +
                    "error: " + err);
            }
        });

        console.log(xval, yval, rval);
    }
}

function updateTable(data) {
    let storage = window.localStorage;
    storage.setItem('tableData', (storage.getItem('tableData') != null ? storage.getItem('tableData') : '') + data);
    $('#table tr:last').after(data);
}