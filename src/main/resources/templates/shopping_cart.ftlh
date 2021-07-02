<#import "parts/common.ftlh" as c>
<#import "parts/quantity_control.ftlh" as q>

<@c.page>

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
                <div><span>= </span><span class="h5">${cartItem.subtotal} ₽</span></div>
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
            <span class="h3">${estimatedTotal!} ₽</span>
        </div>
        <div class="mt-2">
            <button class="btn btn-danger p-1 mt-2">Выйти</button>
        </div>
    </div>
    </#if>

</@c.page>