<%-- 
    Document   : signin
    Created on : Nov 15, 2015, 6:11:56 AM
    Author     : gyerby
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" import="models.User"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up</title>
    </head>
    <body>
        <h1>Sign Up</h1>
        <c:if test="${requestScope.Error!=null}">
            <h2 style="color:red"><c:out value="${requestScope.Error}"></c:out></h2>
        
        </c:if>
          <form action="SignUp.do" method="POST">
           
                    
            <label>Username: </label>
            <input type="text" value="${User.username}" required="true" name="Username" id="Username"/><br />
            
            <label>Author: </label>
            <input type="password" value="${User.password}" name="Password" required="true" id="Password"/><br />
            <button>Submit</button>
            
        </form>
            
    </body>
</html>
