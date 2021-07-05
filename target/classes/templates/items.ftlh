<#include "parts/security.ftlh">
<#import "parts/common.ftlh" as c>
<#import "parts/quantity_control.ftlh" as q>
<#import "parts/modal_dialog.ftlh" as m>

<@c.page>
<div class="container mt-5">

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

                <script type="text/javascript">
                    itemId = ${item.id};
                    var csrfHeaderName = "${_csrf.headerName}";
                    var csrfValue = "${_csrf.token}"
                </script>

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
                <div class="mt-2"><input type="button" id="buttonAddToCart${item.id}" value="В корзину" class="btn btn-primary"></div>
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

</div>

    <@m.modal />

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>

    <script type="text/javascript" src="../static/js/quantity_control.js"></script>
    <script type="text/javascript" src="../static/js/common_modal.js"></script>
    <script type="text/javascript" src="../static/js/add_to_cart.js"></script>
</@c.page>
