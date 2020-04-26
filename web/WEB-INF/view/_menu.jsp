<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${userLocale}"/>
<fmt:setBundle basename="resources.locale"/>

<header style="margin-right: 270px">

    <a class="nav-link" href="${pageContext.request.contextPath}/newWeb_6"><fmt:message key="main"/></a>
    <c:if test="${loginedUser != null}">
        <a class="nav-link" href="${pageContext.request.contextPath}/newWeb_6?command=client"><fmt:message key="clients"/></a>
        <c:if test="${loginedUser.getRole() != 0}"> 
            <a class="nav-link" href="${pageContext.request.contextPath}/newWeb_6?command=order"><fmt:message key="orders"/></a>
            <a class="nav-link" href="${pageContext.request.contextPath}/newWeb_6?command=orderoverdue"><fmt:message key="overdueOrders"/></a>
        </c:if>
        <c:if test="${loginedUser.getRole() == 0}">
            <a class="nav-link" href="${pageContext.request.contextPath}/newWeb_6?command=ordercreate"><fmt:message key="createOrder"/></a>
        </c:if>
        <a class="nav-link" href="${pageContext.request.contextPath}/newWeb_6?command=menu"><fmt:message key="menu"/></a>
        <a class="nav-link" href="${pageContext.request.contextPath}/newWeb_6?command=login"><fmt:message key="logout"/></a>
    </c:if>
    <c:if test="${loginedUser == null}">
        <a class="nav-link" href="${pageContext.request.contextPath}/newWeb_6?command=login"><fmt:message key="login"/></a>
        <a class="nav-link" href="${pageContext.request.contextPath}/newWeb_6?command=register"><fmt:message key="register"/></a>
    </c:if>
        
</header>  