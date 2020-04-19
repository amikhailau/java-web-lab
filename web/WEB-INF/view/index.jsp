<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">

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
  <div class="description">
    Система Интернет-магазин. Клиент осуществляет заказ из Меню.
    Заказ доставляется клиенту. Клиент производит его оплату. Система отмечает, что заказ оплачен.
    Вывести информацию обо всех заказах клиента. Вывести информацию о меню.
    Вывести информацию о просроченных заказах.
    Вывести информацию о заказах, которые необходимо доставить до определённого времени.
    Сделать заказ, оплатить заказ.
  </div>
</div>
</body>

</html>
