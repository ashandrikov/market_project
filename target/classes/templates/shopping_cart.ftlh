<#import "parts/common.ftlh" as c>

<@c.page>

<div class="form-row">
    <h5>Корзина</h5>
</div>

<div class="row">
    <div class="col">
        <#list cartItems as cartItem>
        <div class="row border rounded">

            <div class="col">
                <div>${cartItem?counter}</div>
                <div>Remove icon</div>
            </div>

            <div class="col">
                <img src="data:image/png;base64,${cartItem.item.image}" class="card-img-top" alt="image">
            </div>

        </div>
        </#list>

    </div>

    <div class="col">

    </div>

</@c.page>