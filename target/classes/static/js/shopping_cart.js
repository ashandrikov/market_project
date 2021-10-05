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
        updateQuantity(itemId, newQuantity)
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
        updateQuantity(itemId, newQuantity)
    } else {
        showWarningModal("Максимальное количество: 5");
    }

}

function updateQuantity(itemId, quantity){
    url = "/cart/update/" + itemId + "/" + quantity;
    $.ajax({
        type: "POST",
        url: url,
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeaderName, csrfValue);
        }
    }).done(function (updatedSubtotal) {
        updatedSubtotal(updatedSubtotal, itemId);
        updateTotal()

    }).fail(function () {
        showErrorModal("Не удалось обновить количество.");

    });

}

function updateSubtotal(updatedSubtotal, itemId) {
    $("subtotal" + itemId).text(updatedSubtotal);
}

function updateTotal() {
    total = 0;
    $(".subtotal").each(function (index, element) {
        total += parseInt(element.innerHTML);
    })

    $("#total").text(total);
}