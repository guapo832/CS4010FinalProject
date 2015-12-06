<%-- 
    Document   : addRecipe
    Created on : Oct 11, 2015, 5:37:56 AM
    Author     : gyerby
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="models.User"%>

<jsp:include page="includes/TopHtml.jsp">
    <jsp:param name="Title" value="Add Recipe"></jsp:param>
</jsp:include>
<div class="container">
Server Version: <%= application.getServerInfo() %><br>
ServletVersion: <%= application.getMajorVersion() %>.<%= application.getMinorVersion() %><br>
JSP Version: <%= JspFactory.getDefaultFactory().getEngineInfo().getSpecificationVersion() %> <br>
Session ID = <%= request.getSession(true).getId() %><br/>
        
        <h1>Add new Recipe</h1>
        <form action="<%= response.encodeRedirectURL("createRecipe.do") %>" class="form-horizontal" role="form" method="POST" enctype="multipart/form-data">
            <input type="hidden" id="userid" value="<%= ((User)request.getSession().getAttribute("CurrentUser")).getUserid() %>"/>
            <div class="form-group">
            <label class="control-label col-sm-2">Title: </label>
            <div class="col-sm-10"> <input type="text" class="form-control" value="" name="Title" id="Title" required="true"/>
            </div></div>
            
            <div class="form-group">
            <label class="control-label col-sm-2">Author: </label>
            <div class="col-sm-10"> <input type="text" value="" class="form-control"  name="Author" id="Author" required="true"/>
            </div></div>
            
            <div class="form-group">
            <label class="control-label col-sm-2">Short Description: </label>
           <div class="col-sm-10">  <input type="text" class="form-control"  value="" name="ShortDescription" required="true" id="ShortDescription"><br>
           </div></div>
            
            <div class="form-group">
            <label class="control-label col-sm-2">Photo: </label>
           <div class="col-sm-10">  <input type="file" class="form-control"  name="RecipeImage" id="RecipeImage"/><br/>
           </div> </div>
            
            <div class="form-group">
            <label class="control-label col-sm-2">Prep Time</label>
            <div class="col-sm-10"> <input type="number" class="form-control"  name="PrepTime" id="PrepTime" required="true"/><br/>
            </div></div>
            
            <div class="form-group">
            <label class="control-label col-sm-2">Cook Time</label>
            <div class="col-sm-10"> <input type="number" class="form-control"  name="CookTime" required="true" id="CookTime"/><br/>
            </div> </div>
            
            <div class="form-group">
            <label class="control-label col-sm-2">Skill Level</label>
            <div class="col-sm-10"> <input type="text" class="form-control"  required="true" name="SkillLevel" id="SkillLevel"/><br/>
            </div></div>
            
            <div class="form-group">
            <label class="control-label col-sm-2">Yield</label>
            <div class="col-sm-10"> <input type="text" class="form-control"  required="true" name="Yield" id="Yield"/><br/>
            </div></div>
            
            <label class="control-label col-sm-2">Directions: </label><br />
           <div class="col-sm-10"> 
               <textarea name="Instructions" class="form-control"  id="Instructions" rows="10" cols="70" required="true"></textarea><br/>
           </div>
            
             <button>Submit</button>
                     </form>
</div>
      <jsp:include page="includes/BottomHtml.jsp"></jsp:include>
