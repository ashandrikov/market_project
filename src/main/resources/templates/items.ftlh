<#import "parts/common.ftlh" as c>

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

<#--    <form method="get" action="/items">-->
<#--        <input type="text" name="filter" value="${filter!}">-->
<#--        <button type="submit">Найти</button>-->
<#--    </form>-->

<#--    <#if isAdmin>-->

    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        Добавить товар
    </a>

    <div class="collapse" id="collapseExample">
        <div class="form-group">
            <form method="post" enctype="multipart/form-data">
                <div class="form-group mt-3">
                    <input type="text" class="form-control" name="name" placeholder="Наименование">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="category_id" placeholder="Категория"/>
<#--                    <input type="text" class="form-control" name="category.id" placeholder="Категория"/>-->
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="description" placeholder="Описание">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="price" placeholder="Цена">
                </div>
                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" name="file" id="customFile">
                        <label class="custom-file-label" for="customFile">Выберите файл</label>
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>

                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Добавить</button>
                </div>
            </form>
        </div>
    </div>
<#--    <#else>-->
<#--    </#if>-->

    <h5>Каталог</h5>



    <#list items as item>
        <div class="row mt-3">
            <div class="col">
                <h5>${item.name}</h5>
                <span>${item.category_id}</span>
<#--                <span>${item.category.name}</span>-->
                <span>${item.description}</span>
                <div class="card-footer text-muted">
                    ${item.price}
                </div>
            </div>
            <div class="col">
                <div class="card" style="width: 18rem;">
                    <#if item.filename??>
                        <img src="/img/${item.filename}" class="card-img-top" alt="image">
                    <#--                <div class="card-body">-->
                    <#--                    <a href="#" class="btn btn-primary">Go somewhere</a>-->
                    <#--                </div>-->
                    <#else> <span>No image</span>
                    </#if>
                </div>
            </div>
        </div>

<#--        </div>-->
    <#else>
        No items
    </#list>



</@c.page>