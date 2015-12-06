<%-- 
    Document   : RecipeList
    Created on : Oct 10, 2015, 3:10:11 PM
    Author     : gyerby
--%>

<%@page import="models.Recipe, models.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.ArrayList" %>


<jsp:include page="includes/TopHtml.jsp">
     <jsp:param name="Title" value="Recipes"></jsp:param>
</jsp:include>
<div class="container">
  <div style="width:auto;text-align:right">Hello <%= ((User)request.getSession().getAttribute("CurrentUser")).getUsername() %><br /></div>
        <h1>My Recipes</h1>
       
        <a href='<%=response.encodeRedirectURL("createRecipe.do") %>'>
           add recipe
        </a>
       
        
        <table border="1">
            <thead>
            <th></th>
            <th>Title</th>
            <th>Author</th>
            <th>Short Description</th>
            <th></th>
            <th></th>
            </thead>
    
                 <c:forEach var="Recipe" items="${RecipeList}">
                              
                     
         <tr>
             <td>
                 <%-- <c:if test="${Recipe.imageFilePath} != null"> --%>
                 <a href="<%= response.encodeRedirectURL("readRecipe.do") %>?ID=${Recipe.id}">
                     <img src="${Recipe.imageFilePath}" width="65"/></td>
                 </a>
                     <%-- </c:if> --%>
             <td>
                 <a href="/servlet/j-yerby/<%= response.encodeRedirectURL("readRecipe.do") %>?ID=${Recipe.id}">
                     <c:out escapeXml="true" value="${Recipe.title}"/>
                 </a>
             </td>
             <td>
             <c:out escapeXml="true" value="${Recipe.author}"/><br/>
             </td>
             <td>
                <c:out escapeXml="true" value="${Recipe.shortDescription}"/><br/>
             </td>
             
           
                 
             <td>
                 <a href="/servlet/j-yerby/<%= response.encodeRedirectURL("deleteRecipe.do")%>?ID=${Recipe.id}">delete</a>
             </td>
             <td>
                 <a href="/servlet/j-yerby/<%= response.encodeRedirectURL("updateRecipe.do") %>?ID=${Recipe.id}">edit</a>
             </td>
        </tr>
        
    </c:forEach>
        </table>
 Session ID = <%= request.getSession(true).getId() %><br/>
</div>
        <jsp:include page="includes/BottomHtml.jsp"></jsp:include>
       