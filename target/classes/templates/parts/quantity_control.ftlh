<#macro quantity_control (quantityValue, itemId)>

<nav>
    <ul class="pagination">
        <li class="page-item">
            <a class="page-link linkMinus" pid=${itemId} href=""><b>-</b></a>
        </li>
        <li class="page-item">
            <input type="text" value="${quantityValue}" id="quantity${itemId}"
                   class="form-control text-center"
                   onkeydown="return false;" style="max-width: 55px">
        </li>
        <li class="page-item">
            <a class="page-link linkPlus" pid=${itemId} href=""><b>+</b></a>
        </li>
    </ul>
</nav>
</#macro>