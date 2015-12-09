<%-- 
    Document   : addRecipe
    Created on : Oct 11, 2015, 5:37:56 AM
    Author     : gyerby
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="includes/TopHtml.jsp">
    <jsp:param name="Title" value="Add Ingredient"></jsp:param>
   <jsp:param name="SourcePrefix" value="CreateIngredient"></jsp:param>
</jsp:include>
<div class="container-fluid">
    




        <h1>Add new Ingredient</h1>
        <form action="<%= response.encodeRedirectURL("createIngredient.do") %>" role="form" class="form-horizontal" method="POST">
          <input type="hidden" name="RecipeID" value="${param['recipeID']}"/>
          <div class="form-group">
          <label class="control-label col-sm-2">Description: </label>
            <div class="col-sm-10"> <input type="text" value="" class="form-control" name="Description" id="Description"><br>
            </div> </div> 
                             
            <button class="btn btn-default">Submit</button>
                     </form>
          
</div>
<jsp:include page="includes/BottomHtml.jsp"></jsp:include>