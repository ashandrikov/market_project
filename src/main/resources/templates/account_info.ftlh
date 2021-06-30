<#import "parts/common.ftlh" as c>

<@c.page>
    <h5>Инфрмация об аккуанте</h5>
    ${message!}

    <div class="row my-5">
        <div class="col">
            <div>${"Логин: " + user.username}</div>
            <div>${"Пароль: " + user.password}</div>
<#--                <a href="/account/update/">Edit</a>-->
        </div>
    </div>

<#--    <form action="/account" method="get" style="max-width: 600px; margin-top: 50px;">-->

<#--        <input type="hidden" name="id" value="${user.id}">-->
<#--        <div class="form-group mt-3">-->
<#--        <input type="text" class="form-control" name="username" value="${user.username}">-->
<#--        </div>-->

<#--        <div class="form-group mt-3">-->
<#--        <input type="text" class="form-control" name="password" value="${user.password}">-->
<#--        </div>-->

<#--        <input type="hidden" value="${_csrf.token}" name="_csrf">-->

<#--        <div class="form-group mt-3">-->
<#--            <button type="submit" class="btn btn-primary">Сохранить</button>-->
<#--        </div>-->
<#--    </form>-->

</@c.page>
