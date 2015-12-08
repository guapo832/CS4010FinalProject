<%-- 
    Document   : ReadRecipe
    Created on : Oct 11, 2015, 7:46:52 AM
    Author     : gyerby
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container">
<jsp:include page="includes/TopHtml.jsp">
    <jsp:param name="Title" value="${Recipe.title}"></jsp:param>
</jsp:include>

        <h2>${Recipe.title}</h2>
        <span class="author">Recipe Provided By: ${Recipe.author}</span>
        <div id="recipeDescription">
            <c:out escapeXml="true" value="${Recipe.shortDescription}"></c:out>
        </div>
    <div class="recipeImage">
        <img src="${Recipe.imageFilePath}" width="130" style="float:left;margin:4px"/>
    </div>
    <aside class="cookTime">
        <h2>
            Total Time<br/>
            <c:out escapeXml="true" value="${Recipe.cookTime}"></c:out> minutes
        </h2>
    </aside>    
        
        <aside>
            <label>Yield:</label><c:out escapeXml="true" value="${Recipe.yield}"></c:out><br/>
            <Label>Level:</Label><c:out escapeXml="true" value="${Recipe.skillLevel}"></c:out>
        </aside>
            <c:if test="${sessionScope.CurrentUser !=null}">
                <a href="/servlet/j-yerby/<%= response.encodeRedirectURL("createIngredient.do") %>?recipeID=${Recipe.id}">add ingredient</a>
            </c:if>
    
        <ul class="ingredients">
             <c:forEach var="Ingredient" items="${IngredientsList}">
                 <li>
                     <span class="ingredient"><c:out escapeXml="true" value="${Ingredient.description}"/></span>
                     <c:if test="${sessionScope.CurrentUser !=null}">
                     <span class="admin">
                        <a href="/servlet/j-yerby/<%= response.encodeRedirectURL("deleteIngredient.do") %>?ID=${Ingredient.id}&recipeID=${Recipe.id}">delete</a>&nbsp;|&nbsp;<a href="/servlet/j-yerby/<%= response.encodeRedirectURL("updateIngredient.do") %>?ID=${Ingredient.id}&recipeID=${Recipe.id}&Source=MyRecipes.do">edit</a>
                     </span>
                     </c:if>
                 </li>
     </c:forEach>
        </ul>
        <article>${Recipe.instructions}</article>
            <p> session ID: <%= request.getSession().getId() %> </p>
</div>
            <jsp:include page="includes/BottomHtml.jsp"></jsp:include>
