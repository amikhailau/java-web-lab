<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Зарегистрироваться</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
        crossorigin="anonymous">
        <link rel="stylesheet" href="styles/styles.css">
    </head>
    <body>
        <jsp:include page="_header.jsp"></jsp:include>
        <jsp:include page="_menu.jsp"></jsp:include>
        <p style="color: red;">${errorString}</p>
        <div class="page-container">
            <h2><c:if test="${errorCode!=null}">
                <c:if test="${errorCode.equals('userExists')}">Пользователь уже существует</c:if>
                <c:if test="${errorCode.equals('passwordFormad')}">Некорректный пароль. Должен быть больше 5 символов.</c:if>
                <c:if test="${errorCode.equals('loginForm')}">Некорректное имя пользователя. Должно быть больше 4 символов.</c:if>
            </c:if></h2>
        <div class="page-form">
        <form method="POST" action="${pageContext.request.contextPath}/newWeb_6?command=register">
            <table border="0">
                <tr>
                    <td>Имя пользователя</td>
                    <td><input type="text" name="username" value= "" /> </td>
                </tr>
                <tr>
                    <td>Пароль</td>
                    <td><input type="password" name="password" value= "" /> </td>
                </tr>
                <tr>
                    <td>Телефон</td>
                    <td><input type="number" pattern=".{10}" name="phone" value= "" /> </td>
                </tr>
                <tr>
                    <td colspan ="2">
                        <input type="submit" value= "Submit" />
                    </td>
                </tr>
            </table>
        </form>
        </div>
        </div>
    </body>
</html>