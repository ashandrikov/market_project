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

    <div class="collapse <#if item??>show</#if>" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data">
                <div class="form-group mt-3">
                    <#--                    <input type="text" class="form-control ${(nameError??)?string('is-invalid', '')}"-->
                    <#--                           value="<#if item??>${item.name}</#if>" name="name" placeholder="Наименование">-->
                    <#--                    <#if nameError??>-->
                    <#--                        <div class="invalid-feedback">-->
                    <#--                            ${nameError}-->
                    <#--                        </div>-->
                    <#--                    </#if>-->


                    <input type="text" class="form-control" name="name" placeholder="Наименование"/>
                </div>

                <div class="form-group">
                    <#--                    <input type="number" class="form-control ${(category_idError??)?string('is-invalid', '')}"-->
                    <#--                           value="<#if item??>${item.category_id}</#if>" name="category_id" placeholder="Категория">-->
                    <#--                    <#if category_idError??>-->
                    <#--                        <div class="invalid-feedback">-->
                    <#--                            ${category_idError}-->
                    <#--                        </div>-->
                    <#--                    </#if>-->

                    <select name="category" class="form-control" required>
                        <#list categories! as category>
                            <option value="${category.id}">${category.id}</option>
                        </#list>
                    </select>

                    <#--                    <input type="number" class="form-control" name="category_id" placeholder="Категория"/>-->

                </div>

                <div class="form-group">
                    <#--                                        <input type="text" class="form-control ${(descriptionError??)?string('is-invalid', '')}"-->
                    <#--                                               value="<#if item??>${item.description}</#if>" name="description" placeholder="Описание">-->
                    <#--                                        <#if descriptionError??>-->
                    <#--                                            <div class="invalid-feedback">-->
                    <#--                                                ${descriptionError}-->
                    <#--                                            </div>-->
                    <#--                                        </#if>-->


                    <input type="text" class="form-control" name="description" placeholder="Описание">
                </div>

                <div class="form-group">
                    <#--                                        <input type="number" class="form-control ${(priceError??)?string('is-invalid', '')}"-->
                    <#--                                               value="<#if item??>${item.price}</#if>" name="price" placeholder="Цена">-->
                    <#--                                        <#if priceError??>-->
                    <#--                                            <div class="invalid-feedback">-->
                    <#--                                                ${priceError}-->
                    <#--                                            </div>-->
                    <#--                                        </#if>-->

                    <input type="number" class="form-control" name="price" placeholder="Цена">
                </div>
                <div class="form-group">
                    <div class="custom-file">

                        <#--                        <input type="file ${(imageError??)?string('is-invalid', '')}"-->
                        <#--                               value="<#if item??>${item.image}</#if>" name="image" id="customFile">-->
                        <#--                        <label class="custom-file-label" for="customFile">Выберите файл</label>-->
                        <#--                        <#if imageError??>-->
                        <#--                            <div class="invalid-feedback">-->
                        <#--                                ${imageError}-->
                        <#--                            </div>-->
                        <#--                        </#if>-->


                        <input type="file" name="image" id="customFile">
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

    <h5>Каталог</h5>

    <#list items as item>
        <div class="row my-5">
            <div class="col">
                <h5>${item.name}</h5>
                <div>${"Категория: " + item.category_id.name}</div>
                <div>${"Описание: " + item.description}</div>
                <div class="card-footer text-muted">
                    ${item.price + " руб."}
                </div>
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

<#--    Проверка на админа, шаблон-->
<#--    <#if isAdmin>-->
<#--    <#else>-->
<#--    </#if>-->