function clearTable() {
    $('#table').html(`<tr>
        <th>X</th>
        <th>Y</th>
        <th>R</th>
        <th>Время текущее</th>
        <th>Время работы script</th>
        <th>Результат</th>
        </tr>`);
    // window.localStorage.removeItem('tableData');
}