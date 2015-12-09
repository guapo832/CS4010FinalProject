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
    <jsp:param name="SourcePrefix" value="RecipeList"></jsp:param>
</jsp:include>

<div class="container table-responsive">
        <h1>Recipes</h1>
                      
        <table class="table-striped" border="1">
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
                
                 <a href="readRecipe.do?ID=${Recipe.id}">
                     <img src="${Recipe.imageFilePath}" width="65"/></td>
                 </a>
                    
             <td>
                 <a href="readRecipe.do?ID=${Recipe.id}">
                     <c:out escapeXml="true" value="${Recipe.title}"/>
                 </a>
             </td>
             <td>
             <c:out escapeXml="true" value="${Recipe.author}"/><br/>
             </td>
             <td>
                <c:out escapeXml="true" value="${Recipe.shortDescription}"/><br/>
             </td>
         </tr>
        
    </c:forEach>
        </table>
</div>
        <jsp:include page="includes/BottomHtml.jsp"></jsp:include>
       