$(document).ready(function () {
    $(".linkMinus").on("click", function (evt) {
        evt.preventDefault();
        decreaseQuantity($(this));
    })
    $(".linkPlus").on("click", function (evt) {
        evt.preventDefault();
        increaseQuantity($(this));
    })

});

function decreaseQuantity(link) {
    itemId = link.attr("pid");
    quantityInput = $("#quantity" + itemId);
    newQuantity = parseInt(quantityInput.val()) - 1;

    if (newQuantity > 0){
        quantityInput.val(newQuantity);
    } else {
        showWarningModal("Минимальное количество: 1");
    }

}

function increaseQuantity(link) {
    itemId = link.attr("pid");
    quantityInput = $("#quantity" + itemId);
    newQuantity = parseInt(quantityInput.val()) + 1;

    if (newQuantity <= 5){
        quantityInput.val(newQuantity);
    } else {
        showWarningModal("Максимальное количество: 5");
    }

}