var productItems = 0;
var sum = 0;
$( document ).ready(function() {


    $(".products").each(function( index ) {
        $(this).find('td').find('input').each(function (index) {
            let place = $(this).attr("placeholder");
            switch(place){
                case 'qtd':
                    $(this).attr("name","products["+productItems+"].quantity");
                    break;
                case 'valor':
                    $(this).attr("name","products["+productItems+"].price");
                    break;
                case 'nome':
                    $(this).attr("name","products["+productItems+"].name");
                    break;
                case 'idProduct':
                    $(this).attr("name","products["+productItems+"].id");
                    break;
                }
       });
           productItems++;
   });

   console.log(productItems);



  $('#dinamic-table-body').on('click', 'a', function () {
      if ($(".products").length > 1) {
          productItems--;
      }
  });
});



function calc() {
    let totalPecas = document.getElementById("tlPecas").value;
    //totalPecas = 0;
    let prds = document.getElementsByClassName("products");
    let somaLocal=0;
    for(let l = 0; l< prds.length; l++) {
        let qtd = 0; let vl = 0;
        let v = prds[l];
        let indQtd;
        let indPrc;
        console.log("total produtos "+prds.length);
        console.log(v);
        for(let i = 0;i< v.cells.length; i++){
            console.log("total de celulass: ")
            console.log(v.cells);
            if(v.cells.length === 5){
               indPrc = 3;
               indQtd = 1;
            }else{
              indPrc = 2;
              indQtd = 0;
            }
            if (i == indQtd) {
                    qtd = v.cells[i].children[0].value;
            } else if (i == indPrc) {
                    vl = v.cells[i].children[0].value;
            }
            if (qtd > 0 && vl > 0 && i == indPrc) {

                    somaLocal = somaLocal + parseInt(qtd * vl);
            }

        }

    }
    console.log(somaLocal);
    document.getElementById("tlPecas").value =  somaLocal;
    document.getElementById("maoDeObra").removeAttribute("readonly");
    calcTotal();


}
function calcTotal(){
    let totalPecas = parseInt(document.getElementById("tlPecas").value);
    let maoDeObra = parseInt(document.getElementById("maoDeObra").value);
    document.getElementById("total").value = totalPecas + maoDeObra;
}
function removeProduct(el){
     let id = el.children[0].innerText;
     $.ajax({

         type: "DELETE",
         contentType: 'application/json; charset=utf-8',
         dataType: "json",
         url: '/ajax/deleteProduct',
         data: id,
         success: function (e) {
               if(e.status === 200){
                   removeProductRow(el);
                   $('.modal-body p').text('Produto excluído com sucesso.');
                   $('#modal').modal();
               }else{
                   $('.modal-body p').text('erro ao tentar excluir produto.');
                   $('#modal').modal();
               }

         },
         error: function (e) {
               if(e.status === 200){
                    removeProductRow(el);
                    $('.modal-body p').text('Produto excluído com sucesso.');
                    $('#modal').modal();
               }else{
                    $('.modal-body p').text('erro ao tentar excluir produto.');
                    $('#modal').modal();
              }
         }
     });
}
function removeProductRow(row){
   var td = row.parentNode;
   var tr = td.parentNode;
   if ($(".products").length > 1 ) {
      tr.remove();
      productItems--;
   }

}
function addProduct() {
        $('#principal').append(' <tr class="products" >\n' +
            '                                        <td><input class="form-control" type="text" name="products[' + productItems + '].quantity"   id="name"  placeholder="qtd" required /></td>\n' +
            '                                        <td><input class="form-control" type="text" name="products[' + productItems + '].name"    id="quantity"   placeholder="nome" required  /></td>\n' +
            '                                        <td><input class="form-control" type="number" name="products[' + productItems + '].price" id="price"  placeholder="valor"   required /></td>\n' +
            '                                        <td>\n' +
            '                                            <a href="javascript:;">Remover</a>\n' +
            '                                        </td>\n' +
            '                                    </tr>');
        productItems++;
}
$('#maoDeObra').on('change',  function(){
    var maoDeObra = $(this).val();
    var totalPecas = $("#tlPecas").val();
    $("#total").val(Number(maoDeObra) + Number(totalPecas));
});
function changeCar(){
    $("#car").removeAttr("readonly");
    $("#car").css({ 'background':'#fff','pointer-events' : 'all', 'touch-action':'auto'});
    $('.change').css({'opacity':'1' , 'cursor':'auto'});
}

$('select').on('change', function (e) {
    var optionSelected = $(this).find("option:selected");
    var valueSelected = optionSelected.text();
    console.log(valueSelected);
    $("#carOrder").text(valueSelected);
    $("#car").attr("readonly",true);
    $("#car").css({ 'background':'#eee','pointer-events' : 'none', 'touch-action':'none'});
    $('.change').css({'opacity':'0.5' , 'cursor':'not-allowed'});
});