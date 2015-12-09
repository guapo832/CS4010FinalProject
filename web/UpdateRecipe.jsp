<%-- 
    Document   : editRecipe
    Created on : Oct 11, 2015, 6:41:40 AM
    Author     : gyerby
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<jsp:include page="includes/TopHtml.jsp">
    <jsp:param name="Title" value="Edit Recipe ${Recipe.title}"></jsp:param>
    <jsp:param name="SourcePrefix" value="UpdateRecipe"></jsp:param>
</jsp:include>
        <h1>Edit Recipe</h1>
        <form action="<%= response.encodeRedirectURL("updateRecipe.do") %>" method="POST">
           
                    <input type="hidden" name="ID" value="${Recipe.id}"/>
            <label>Title: </label>
            <input type="text" value="${Recipe.title}" required="true" name="Title" id="Title"/><br />
            
            <label>Author: </label>
            <input type="text" value="${Recipe.author}" name="Author" required="true" id="Author"/><br />
            
            <label>Short Description: </label>
            <input type="text" required="true" name="ShortDescription" value="${Recipe.shortDescription}" id="ShortDescription"/><br/>
            
             <label>Prep Time</label>
            <input type="number" name="PrepTime" value="${Recipe.prepTime}" requied="true" id="PrepTime"/><br/>
             <label>Cook Time</label>
            <input type="number" name="CookTime" required="true" id="CookTime" value="${Recipe.cookTime}"/><br/>
            
            <label>Yield</label>
            <input type="text" required="true" name="Yield" id="Yield" value="${Recipe.yield}" /><br/>
            
            <label>Skill Level</label>
            <input type="text" name="SkillLevel" id="SkillLevel" value="${Recipe.skillLevel}" required="true"/><br/>
            
            <label>Instructions: </label><br />
            <textarea name="Instructions" required="true" id="Instructions" required="true" rows="10" cols="35"><c:out escapeXml="true" value="${Recipe.instructions}" /></textarea>
                   
            <button>Submit</button>
            
        </form>
           <jsp:include page="includes/BottomHtml.jsp"></jsp:include>