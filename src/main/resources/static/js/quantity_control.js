$(document).ready(function () {
    $(".linkMinus").on("click", function (evt) {
        evt.preventDefault();
        itemId = $(this).attr("pid");
        quantityInput = $("#quantity" + itemId);
        newQuantity = parseInt(quantityInput.val()) - 1;

        if (newQuantity > 0){
            quantityInput.val(newQuantity);
        } else {
            showWarningModal("Минимальное количество: 1");
        }
    })
    $(".linkPlus").on("click", function (evt) {
        evt.preventDefault();
        itemId = $(this).attr("pid");
        quantityInput = $("#quantity" + itemId);
        newQuantity = parseInt(quantityInput.val()) + 1;

        if (newQuantity <= 5){
            quantityInput.val(newQuantity);
        } else {
            showWarningModal("Максимальное количество: 5");
        }
    })

});