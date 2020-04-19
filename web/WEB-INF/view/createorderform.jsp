<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <meta charset="utf-8" />
    <title>Интернет-магазин</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <link rel="stylesheet" href="styles/styles.css">

</head>

<body>
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>
<p style="color: red;">${errorString}</p>
<div class="page-container">
    <div class="page-form">
        <form name="orderForm" method="POST" action="${pageContext.request.contextPath}/newWeb_6?command=ordercreate" onsubmit="return submitFunc();">
            <!--<div class="form-group">
                <label>Идентификатор клиента</label>
                <input type="text" class="form-control" placeholder="Введите идентификатор клиента">
            </div>
            <div class="form-group">

                <label>Идентификатор администратора</label>
                <input type="text" class="form-control" placeholder="Введите идентификатор администратора">
            </div>
            <div class="form-group">
                <label>Сумма заказа</label>
                <input type="number" class="form-control" placeholder="Введите сумму заказа">
            </div>
            <div class="form-group">
                <input type="checkbox">
                <label class="form-check-label">Оплачен ли заказ?</label>
            </div>
            <button type="submit" class="btn btn-primary">Подтвердить</button>-->
        </form>
    </div>
</div>
<script src="scripts/orders.js"></script>
</body>

</html>