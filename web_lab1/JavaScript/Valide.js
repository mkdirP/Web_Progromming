function checkInput(y){
    let validationInfoPanel = document.querySelector('.validationInfo');
    var validationInfo = '';
    var isYCorrect = false;

    if (!(y.trim() === "")) {
        if (/-?\d[\.е]\d+|-?\d/.test(y)) {
            if ((parseFloat(y) >= -3) && (parseFloat(y) <= 3)) {
                isYCorrect = true;
            } else validationInfo += "<span>Y должен быть в интервале (-3,3)!</span>";
        } else validationInfo += "<span>Y должен быть числом!</span>";
    } else validationInfo += "<span>Введите Y!</span>";

    validationInfoPanel.innerHTML = validationInfo;
    return isYCorrect;

}

function validateTextField() {
    $('.y-text').on('input', function() {
        $(this).val($(this).val().replace(/[^.-\d]/, ''));
    });
}