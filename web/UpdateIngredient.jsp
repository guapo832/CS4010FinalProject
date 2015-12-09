<%-- 
    Document   : editIngredient.jsp
    Created on : Oct 12, 2015, 5:58:28 AM
    Author     : gyerby
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="includes/TopHtml.jsp">
    <jsp:param name="Title" value="Edit Ingredient"></jsp:param>
    <jsp:param name="SourcePrefix" value="UpdateIngredient"></jsp:param>
</jsp:include>
        <h1>Edit new Ingredient</h1>
        <form action="<%= response.encodeRedirectURL("updateIngredient.do") %>" method="POST">
            <input type="hidden" name="RecipeID" value="${Ingredient.recipeId}"/>
            <input type="hidden" name="ID" value="${Ingredient.id}"/>        
            <label>Description: </label>
            <input type="text" value="${Ingredient.description}" name="Description" id="Description"><br>                         
            <button>Submit</button>
                     </form>
     <jsp:include page="includes/BottomHtml.jsp"></jsp:include>