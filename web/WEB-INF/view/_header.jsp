<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${userLocale}"/>
<fmt:setBundle basename="resources.locale"/>
<div style="background: #E0E0E0; height: 60px; padding: 5px;">
    
    <form style="float: left; padding: 10px; text-align: right;">
        <div  class="form-group">
            <select class="form-control" name='lang' onchange='this.form.submit()'>
                <c:choose>
                <c:when test="${userLocale.language == 'ru'}">
                <option value='ru' selected>Русский
                <option value='en'>English
                    </c:when>
                    <c:otherwise>
                <option value='ru'>Русский
                <option value='en' selected>English
                    </c:otherwise>
                    </c:choose>
            </select>
        </div>
    </form>
 
  <div style="float: right; padding: 10px; text-align: right;">
 
     <!-- User store in session with attribute: loginedUser -->
     <b>${loginedUser.name}</b>

  </div>
 
</div>