<#import "parts/common.ftlh" as c>

<@c.page>
    <#if category??> <h5>Редактировать категорию</h5>
    <#else> <h5>Новая категория</h5>
    </#if>

    <form action="/categories/save" method="post" style="max-width: 600px; margin-top: 50px;">
        <#if category??> <input type="hidden" name="id" value="${category.id}"> </#if>
        <div>
            <div class="form-group row">
                <label class="col-form-label col-sm-4"> Категория : </label>
                <div class="col-sm-8">
                    <input type="text" <#if category??>value="${category.name}"</#if> name="name" class="form-control"/>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <div class="form-group mt-3">
                <button type="submit" class="btn btn-primary">Сохранить</button>
            </div>
        </div>
    </form>



</@c.page>

