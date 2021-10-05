$(document).ready(function () {
    $(".buttonAddToCart").on("click", function (evt) {
        addToCart();
    });
});

function addToCart(){
    quantity = $("#quantity" + itemId).val();
    url = "/cart/add/" + itemId + "/" + quantity;

    alert(url);

    $.ajax({
        type: "POST",
        url: url,
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeaderName, csrfValue);
        }
    }).done(function (response) {
        showModalDialog("Shopping cart", response);

    }).fail(function () {
        showErrorModal("Не удалось добавить товар в корзину.");

    });
}