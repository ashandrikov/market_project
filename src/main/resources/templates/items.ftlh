<#include "parts/security.ftlh">
<#import "parts/common.ftlh" as c>
<#import "parts/quantity_control.ftlh" as q>

<@c.page>

    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/items" class="form-inline">
                <input type="text" name="filter" class="form-control" value="${filter?ifExists}"
                       placeholder="Найти">
                <button type="submit" class="btn btn-primary ml-2">Найти</button>
            </form>
        </div>
    </div>

    <h5>Каталог</h5>

    <#list items as item>
        <div class="row my-5">
            <div class="col">
                <h5>${item.name}</h5>
                <div>${"Категория: " + item.category.name}</div>
                <div>${"Описание: " + item.description}</div>
                <div class="card-footer text-muted">
                    ${item.price + " руб."}
                </div>

                <#if !isAdmin>
                <div class="mt-4">
                    <@q.quantity_control quantityValue="1" itemId=item.id/>
                </div>
                <div class="mt-2"><input type="button" id="buttonAddtoCart" value="В корзину" class="btn btn-primary"></div>
                </#if>

                <#if isAdmin>
                <a href="/items/edit/${item.id}">Редактировать</a>
                &nbsp
                <a href="/items/delete/${item.id}">Удалить</a>
                </#if>
            </div>
            <div class="col">
                <div class="card" style="width: 18rem;">
                    <#if item.image??>
                        <img src="data:image/png;base64,${item.image}" class="card-img-top" alt="image">
                    <#else> <span>No image</span>
                    </#if>
                </div>
            </div>
        </div>

    <#else>
        No items
    </#list>

</@c.page>
