var carsList = 1;
function addCar() {
    $('#dinamic-table-body').append(' <tr class="cars" >\n' +
        '                                        <td><input class="form-control" type="text" name="cars['+carsList+'].board"   id="board"  placeholder="placa" required /></td>\n' +
        '                                        <td><input class="form-control" type="text" name="cars['+carsList+'].fuel"    id="fuel"   placeholder="combustivel" required  /></td>\n' +
        '                                        <td><input class="form-control" type="text" name="cars['+carsList+'].color" id="color"  placeholder="cor"   required /></td>\n' +
        '                                        <td><input class="form-control" type="number" name="cars['+carsList+'].kmActually" id="km" placeholder="km" required></td>\n' +
        '                                        <td><input class="form-control" type="text" name="cars['+carsList+'].vehicle" id="vehicle"      placeholder="veiculo" required></td>\n' +
        '                                        <td>\n' +
        '                                            <a href="javascript:;">Remover</a>\n' +
        '                                        </td>\n' +
        '                                    </tr>');
    carsList ++;
}


$('#dinamic-table-body').on('click', 'a', function(){
    var td = $(this).parent();
    var tr = $(td).parent();
    if ($(".cars").length > 1 ) {
        tr.remove();
        carsList--;
    }
});