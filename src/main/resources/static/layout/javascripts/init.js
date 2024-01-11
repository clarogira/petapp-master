(function($){
  $(function(){

    $('.button-collapse').sideNav();

  }); // end of document ready
})(jQuery); // end of jQuery name space

// select
$(document).ready(function() {
    $('select').material_select();

    $("select[required]").css({position: 'absolute', display: 'inline', height: 0, padding: 0, width: 0});
});

// init modal
$(document).ready(function(){
    // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
    $('.modal').modal();
});

$(document).ready(function () {
    $('.tooltipped').tooltip({delay: 50});
});

$(document).ready(function () {
    $('input#input_text, textarea#textarea').characterCounter();
});

// Formatador para moedas
$(function () {
    $('.js-moeda').maskMoney({
        decimal : ',',
        thousands : '.',
        allowZero : true
    });
});

// Formatador para numeros
$(function () {
    $('.js-numero').maskMoney({
        thousands : '.',
        allowZero : true
    });
});


$(document).ready(function(){
    $('.collapsible').collapsible();
});


