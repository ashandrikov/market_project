<#include "security.ftlh">
<#import "login.ftlh" as l>

<nav class="navbar navbar-expand-lg navbar navbar-dark bg-dark">
    <a class="navbar-brand" href="/main">Снаряжение</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/items">Товары</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/cart">Корзина</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="/orders">Заказы</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/profile">Профиль</a>
            </li>

        </ul>

        <#if isAdmin>
            <div class="navbar-text mr-3">Admin</div>
        <#else>
            <div class="navbar-text mr-3">User</div>
        </#if>

        <@l.logout />
    </div>
</nav>

