<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Информация</title>
      <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
      <link rel="stylesheet" href="styles/styles.css">
   </head>
   <body>
      <jsp:include page="_header.jsp"></jsp:include>
      <jsp:include page="_menu.jsp"></jsp:include>
      <c:if test = "${info != null}">
          <h3 style="style:green">${info}</h3>
      </c:if>
      <c:if test = "${serve != null}">
          <h3 style="style:red">${serve}</h3>
      </c:if>
      <a href="${pageContext.request.contextPath}/${back}">Back</a>
   </body>
</html>