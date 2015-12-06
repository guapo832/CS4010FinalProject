<%-- 
    Document   : TopHtml
    Created on : Oct 12, 2015, 10:05:48 PM
    Author     : gyerby
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><c:out value="${param.Title}"></c:out></title>
        <!-- Bootstrap -->
    <link href="bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript" src="js/jquery-1.11.3.js"/>
   <script src="<%= request.getContextPath() %>/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
   </head>
    <body>
        <div style="text-align:right;width:640px">
            <a href="<%= response.encodeRedirectURL("Index.do")%>">Home</a> | <a href="<%= response.encodeRedirectURL("MyRecipes.do")%>">My Recipes</a> |
  <c:if test="${sessionScope.CurrentUser != null}">
     <a href="signout.do">sign out</a>
  </c:if>
  <c:if test="${sessionScope.CurrentUser == null}">
    <a href="SignUp.do">Sign Up</a> 
  </c:if>
        </div>  
        <div class="maincontent" style="width:auto">