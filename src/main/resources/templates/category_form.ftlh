<#import "parts/common.ftlh" as c>

<@c.page>
    <h5>Новая категория</h5>


    <form action="/categories/save" method="post" style="max-width: 600px; margin-top: 50px;">
        <div>
            <div class="form-group row">
                <label class="col-form-label col-sm-4"> Категория : </label>
                <div class="col-sm-8">
                    <input type="text" name="name" class="form-control" required/>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <div class="form-group mt-3">
                <button type="submit" class="btn btn-primary">Добавить</button>
            </div>
        </div>
    </form>



</@c.page>

