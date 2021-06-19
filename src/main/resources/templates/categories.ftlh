<#import "parts/common.ftlh" as c>

<@c.page>
    <h5>Категории</h5>

    <table class="table table-bordered">
        <thead class="thead-dark">
        <tr>
            <th>ID</th>
            <th>Name</th>
        </tr>
        </thead>
        <tbody>
        <#list categories as category>
            <tr>
                <td>${category.id}</td>
                <td>${category.name}</td>
            </tr>
        </#list>
        </tbody>
    </table>



</@c.page>

