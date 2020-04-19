<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<header>

    <a class="nav-link" href="${pageContext.request.contextPath}/newWeb_6">Главная</a>
    <c:if test="${loginedUser != null}">
        <a class="nav-link" href="${pageContext.request.contextPath}/newWeb_6?command=client">Клиенты</a>
        <a class="nav-link" href="${pageContext.request.contextPath}/newWeb_6?command=order">Заказы</a>
        <a class="nav-link" href="${pageContext.request.contextPath}/newWeb_6?command=ordercreate">Создать заказ</a>
        <a class="nav-link" href="${pageContext.request.contextPath}/newWeb_6?command=orderoverdue">Просроченные заказы</a>
        <a class="nav-link" href="${pageContext.request.contextPath}/newWeb_6?command=menu">Меню</a>
        <a class="nav-link" href="${pageContext.request.contextPath}/newWeb_6?command=login">Выйти</a>
    </c:if>
    <c:if test="${loginedUser == null}">
        <a class="nav-link" href="${pageContext.request.contextPath}/newWeb_6?command=login">Войти</a>
        <a class="nav-link" href="${pageContext.request.contextPath}/newWeb_6?command=register">Зарегистрироваться</a>
    </c:if>
        
</header>  