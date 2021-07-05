<#import "parts/common.ftlh" as c>

<@c.page>
<div class="container mt-5">

    <#if item??> <h5>Редактировать товар</h5>
    <#else> <h5>Новый товар</h5>
    </#if>

    <form action="/items/save" method="post" enctype="multipart/form-data">
        <#if item??> <input type="hidden" name="id" value="${item.id}"> </#if>
        <div class="form-group mt-3">
            <input type="text" class="form-control ${(nameError??)?string('is-invalid', '')}"
                   value="<#if item??>${item.name}</#if>" name="name" placeholder="Наименование">
            <#if nameError??>
                <div class="invalid-feedback">${nameError}</div>
            </#if>
        </div>

        <div class="form-group">
            <select name="category" class="form-control" required>
                <#list categories! as category>
                    <option value="${category.id}">${category.name}</option>
                </#list>
            </select>
        </div>

        <div class="form-group">
            <input type="text" class="form-control ${(descriptionError??)?string('is-invalid', '')}"
                   value="<#if item??>${item.description}</#if>" name="description" placeholder="Описание">
            <#if descriptionError??>
                <div class="invalid-feedback">
                    ${descriptionError}
                </div>
            </#if>
        </div>

        <div class="form-group">
            <input type="number" class="form-control ${(priceError??)?string('is-invalid', '')}"
                   value="<#if item??>${item.price}</#if>" name="price" placeholder="Цена">
            <#if priceError??>
                <div class="invalid-feedback">
                    ${priceError}
                </div>
            </#if>
        </div>

        <div class="input-group mb-3">
            <input type="file" class="form-control ${(imageError??)?string('is-invalid', '')}"
                   aria-describedby="button-addon2"
                   name="image" placeholder="Выберите файл">
            <button class="btn btn-outline-secondary" type="button" id="button-addon2">Выбрать файл</button>
            <#if imageError??>
                <div class="invalid-feedback">
                    ${imageError}
                </div>
            </#if>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>

        <div class="form-group">
            <button type="submit" class="btn btn-primary">Сохранить</button>
        </div>
    </form>

    <#if item??> <#if item.image??>
        <div class="card my-4" style="width: 18rem;">
            <img src="data:image/png;base64,${item.image}" class="card-img-top" alt="Image">
        </div>
    <#else> <span>No image</span>
    </#if> </#if>
</div>
</@c.page>

