<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${userLocale}"/>
<fmt:setBundle basename="resources.locale"/>
<html>
<head>
    <meta charset="utf-8" />
    <title><fmt:message key="onlineShop"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <link rel="stylesheet" href="styles/styles.css">
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>
<p style="color: red;">${errorString}</p>
<div class="page-container">
    <!--<div class="page-form">
        <form name="orderForm" onsubmit="return submitFunc();">
        </form>
    </div>-->
    <div class="page-table">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col"><fmt:message key="clientName"/></th>
                <th scope="col"><fmt:message key="orderValue"/></th>
                <th scope="col"><fmt:message key="deadline"/></th>
                <th scope="col"><fmt:message key="paid"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${orders}" var="order">
                <tr>
                    <td>${order.getId()}</td>
                    <td>${order.getClient().getName()}</td>
                    <td>${order.getOrderSum()}</td>
                    <td>${order.getDeliveryDeadline()}</td>
                    <td>${order.isPaid()}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
