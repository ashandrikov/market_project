<!doctype html>
<html>
<body>
<div th:fragment="quantity_control(qtyValue, itemId)"></div>
<nav>
    <ul class="pagination">
        <li class="page-item">
            <a class="page-link minusButton" href="" pid="${itemId}"><b>-</b></a>
        </li>
        <li class="page-item">
            <input type="text" value="${qtyValue}" class="form-control text-center"
                   onkeydown="return false;" style="max-width: 55px"
            id = 'quantity' + ${itemId}>
        </li>
        <li class="page-item">
            <a class="page-link plusButton" href="" pid="${itemId}"><b>+</b></a>
        </li>
    </ul>
</nav>
</body>
</html>