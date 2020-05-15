var sum = 0;
function calc() {
    let totalPecas = document.getElementById("tlPecas").value;
    //totalPecas = 0;
    let prds = document.getElementsByClassName("produtos");
    let somaLocal=0;
    for(let l = 0; l< prds.length; l++) {
        let qtd = 0; let vl = 0;
        let v = prds[l];

        console.log("total produtos "+prds.length);
        for(let i = 0;i< v.cells.length; i++){

            console.log("total de celllsss "+v.cells.length);

            if (i == 0) {
                    qtd = v.cells[i].children[0].value;
            } else if (i == 2) {
                    vl = v.cells[i].children[0].value;
            }
            if (qtd > 0 && vl > 0 && i == 2) {

                    somaLocal = somaLocal + parseInt(qtd * vl);
            }

        }

    }
    console.log(somaLocal);
    document.getElementById("tlPecas").value =  somaLocal;console.log(sum);


}
function addProduct() {
    $('#principal').append(' <tr class="produtos" >\n' +
        '                                            <td><input class="form-control qtd" placeholder="Ex: 1" value="1" name="qtd[]" type="text" /></td>\n' +
        '                                            <td><input class="form-control" placeholder="Ex: cilindro" value="" name="piece[]" type="text" /></td>\n' +
        '                                            <td><input class="form-control valorPeca" placeholder="Ex: 25,00"  value="" name="value[]" type="number" /></td>\n' +
        '                                            <td>\n' +
        '                                                <a href="javascript:;">Remover</a>\n' +
        '                                            </td>\n' +
        '                                        </tr>');
}

$('#principal').on('click', 'a', function(){
    var td = $(this).parent();
    var tr = $(td).parent();
    if ($(".produtos").length > 1 ) {
        tr.remove();
    }
});

$('#maoDeObra').on('change',  function(){
    var maoDeObra = $(this).val();
    var totalPecas = $("#tlPecas").val();
    $("#total").val(Number(maoDeObra) + Number(totalPecas));
});
