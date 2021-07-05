<#import "parts/common.ftlh" as c>
<#import "parts/quantity_control.ftlh" as q>
<#import "parts/modal_dialog.ftlh" as m>

<@c.page>
<div class="container mt-5">

<div class="form-row">
    <h5>Корзина</h5>
</div>

<div class="row m-1">
    <div class="col-sm-8">
        <#list cartItems as cartItem>
        <div class="row border rounded">

            <div class="col-1">
                <div>${cartItem?counter}</div>
                <div><a class="fas fa-trash-alt" href=""></a></div>
            </div>

            <div class="col-3">
                <img src="data:image/png;base64,${cartItem.item.image}" class="card-img-top" alt="image">
            </div>

            <div class="col-4">
                <div><h6>${cartItem.item.name}</h6></div>
                <div class="mt-4"><@q.quantity_control quantityValue="${cartItem.quantity}" itemId=cartItem.item.id/></div>
                <div><span>x </span><span>${cartItem.item.price} ₽</span></div>
                <div><span>= </span><span class="h5" id="subtotal${cartItem.item.id}">${cartItem.subtotal} ₽</span></div>
            </div>

        </div>
            <div class="row m-1">&nbsp;</div>
        <#else>
        <div>Ваша корзина пуста</div>
        </#list>

    </div>

    <#if (cartItems?size > 0)>
    <div class="col-sm-4">
        <div>
            <span class="h4">Всего:</span>
        </div>
        <div class="mt-2">
            <span id="total" class="h3 subtotal">${estimatedTotal!} ₽</span>
        </div>
        <div class="mt-2">
            <button class="btn btn-danger p-1 mt-2">Выйти</button>
        </div>
    </div>
    </#if>

</div>

    <@m.modal />

    <script type="text/javascript">
        var csrfHeaderName = "${_csrf.headerName}";
        var csrfValue = "${_csrf.token}"
    </script>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>

    <script type="text/javascript" src="../static/js/shopping_cart.js"></script>
    <script type="text/javascript" src="../static/js/common_modal.js"></script>

</@c.page>