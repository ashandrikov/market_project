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

<#--                    Разобраться со связью Один-ко-многим-->
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
                        <input type="file" name="image" id="customFile">
                        <label class="custom-file-label" for="customFile">Выберите файл</label>
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>

                <div class="form-group">
                    <button type="submit" value="Загрузить" class="btn btn-primary" >Добавить</button>
                </div>
            </form>
        </div>
    </div>

    <div class="text-success">
        <h5>${message?ifExists}</h5>
    </div>

    <h5>Каталог</h5>

    <#list items as item>
        <div class="row mt-3">
            <div class="col">
                <h5>${item.name}</h5>
                <span>${item.category_id}</span>

<#--                Заготовка на добавление категории-->
<#--                <span>${item.category.name}</span>-->

                <span>${item.description}</span>
                <div class="card-footer text-muted">
                    ${item.price}
                </div>
            </div>
            <div class="col">
                <div class="card" style="width: 18rem;">
                    <#if item.image??>

<#--                    Прежний вариант, работал, см. поле filename класса Item-->
<#--                    <#if item.filename??>-->
<#--                    <img src="/img/${item.filename}" class="card-img-top" alt="image">-->

<#--                    Не находит изображение. Или здесь надо писать с помощью js?-->
<#--                        <img src="/img/${item.image}" class="card-img-top" alt="image">-->

<#--                        Заглушка для изображений-->
                        <img src="uploads/508fbbce-4313-4040-8812-6d0fe317a822.11Df9ws8s-E.jpg" class="card-img-top" alt="image">

                    <#else> <span>No image</span>
                    </#if>
                </div>
            </div>
        </div>

    <#else>
        No items
    </#list>

</@c.page>

<#--    Проверка на админа, шаблон-->
<#--    <#if isAdmin>-->
<#--    <#else>-->
<#--    </#if>-->