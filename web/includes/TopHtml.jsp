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
     <link href="bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap-theme.min.css"/>


    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript" src="js/jquery-1.11.3.js"></script>
   <script src="bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
   
    



       
   </head>
   
    <body>
         <script>
            $(document).ready(function(){
                
                $.get( "${param.SourcePrefix}ModelSource.html", function( data ) {$( ".model-body" ).html( data );$("#btnModelSource").show();}).fail(function(){$("#btnModelSource").hide();});
                  $.get( "${param.SourcePrefix}ViewSource.html", function( data ) {$( ".view-body" ).html( data );$("#btnViewSource").show();}).fail(function(){$("#btnViewSource").hide();});;
                    $.get( "${param.SourcePrefix}ControllerSource.html", function( data ) {$( ".controller-body" ).html( data );$("#btnControllerSource").show();}).fail(function(){$("#btnControllerSource").hide();});;
                 
            });
        </script>
  
        
        
        <!-- Button trigger modal -->

        
        
        <div id="ModelModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
         <h4 class="modal-title">Model</h4>
      </div>
      <div class="modal-body">
          <p class="model-body">
 


          </p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
   

  </div>
        </div>      
 
         <div id="ViewModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
         <h4 class="modal-title">View</h4>
      </div>
      <div class="modal-body">
          <p class="view-body">
 


          </p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
   

  </div>
        </div>      
        
         <div id="ControllerModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="controller">&times;</button>
         <h4 class="modal-title">Controller</h4>
      </div>
      <div class="modal-body">
          <p class="controller-body">
 


          </p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
   

  </div>
        </div>      
        
        
        <div style="width:auto;text-align:center;margin-bottom:15px;margin-top:10px">
            <button type="button" id="btnModelSource" style="visible:none" class="btn btn-info btn-lg" data-toggle="modal" data-target="#ModelModal">See Source Code For Model</button>&nbsp;
            <button type="button" id="btnViewSource" style="visible:none" class="btn btn-info btn-lg" data-toggle="modal" data-target="#ViewModal">See Source Code For View</button>&nbsp;
            <button type="button" id="btnControllerSource" style="visible:none" class="btn btn-info btn-lg" data-toggle="modal" data-target="#ControllerModal">See Source Code For Controller</button>&nbsp;
        </div>
        <div class="container-fluid">
        
            <a href="<%= response.encodeRedirectURL("Index.do")%>">Home</a> | <a href="<%= response.encodeRedirectURL("MyRecipes.do")%>">My Recipes</a> |
  <c:if test="${sessionScope.CurrentUser != null}">
     <a href="signout.do">sign out</a>
  </c:if>
  <c:if test="${sessionScope.CurrentUser == null}">
    <a href="SignUp.do">Sign Up</a> 
  </c:if>
        </div>  
        <div class="container-fluid" style="width:auto">
            
 
