<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <div class="form-group row">
            <label for="inputEmail3" class="col-sm-2 col-form-label"> Логин: </label>
            <div class="col-sm-4">
                <input type="text" name="username" class="form-control" placeholder="User name"/>
            </div>
        </div>
        <div class="form-group row">
            <label for="inputEmail3" class="col-sm-2 col-form-label"> Пароль: </label>
            <div class="col-sm-4">
                <input type="text" name="password" class="form-control" placeholder="Password"/>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <#if !isRegisterForm><a href="/registration">Зарегистрироваться</a></#if>

        <button type="submit" class="btn btn-primary"><#if isRegisterForm>Создать<#else>Войти</#if></button>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary">Выйти</button>
    </form>
</#macro>