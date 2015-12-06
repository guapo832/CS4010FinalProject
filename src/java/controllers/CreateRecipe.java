/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Utilities.DBHelper;
import Utilities.FileUploadHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import models.Recipe;
import models.User;

/**
 *
 * @author gyerby
 * 
 */

@MultipartConfig
public class CreateRecipe extends HttpServlet {

    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          Connection con = (Connection) getServletContext().getAttribute("dbCon");
       
           // Object user = Membership.getUserBean(request.getSession());
            User user = (User)request.getSession().getAttribute("CurrentUser");
            if(user==null)
            {
              //  RequestDispatcher dispatcher = request.getRequestDispatcher("signin.do");
                response.sendRedirect(response.encodeRedirectURL("/servlet/j-yerby/signin.do"));
                                
            } else
            {
                RequestDispatcher view = request.getRequestDispatcher("CreateRecipe.jsp");
                view.forward(request, response);
            }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter writer = response.getWriter();
      
        
        Connection con = (Connection) getServletContext().getAttribute("dbCon");
              
           // Object user = Membership.getUserBean(request.getSession());
            User user = (User)request.getSession().getAttribute("CurrentUser");
                if(user==null)
            {
               // RequestDispatcher dispatcher = request.getRequestDispatcher("signin.do");
             //   response.sendRedirect("/servlet/j-yerby/signin.do");
               response.setContentType("text/html");
               response.getWriter().println("sessionID=" + request.getSession().getId());
               
               response.getWriter().println("no user found");
            } else
            {
      try
      {  
        Recipe recipe = new Recipe() {};
       recipe.setTitle(request.getParameter("Title"));
       
       writer.println("title=" + recipe.getTitle() + "<br />");
        recipe.setAuthor(request.getParameter("Author"));
        recipe.setInstructions(request.getParameter("Instructions"));
        recipe.setYield(request.getParameter("Yield"));
       String strPrepTime = (String)request.getParameter("PrepTime");
       writer.println("PrepTime=" + strPrepTime + "~<br />");
        String strCookTime = (String)request.getParameter("CookTime");
        writer.println("Cooktime=" + strCookTime + "~<br />");
        recipe.setCookTime(Integer.parseInt(strCookTime));
        recipe.setPrepTime(Integer.parseInt(strPrepTime));
        recipe.setSkillLevel(request.getParameter("SkillLevel"));
        writer.println(recipe.getSkillLevel());
        recipe.setShortDescription(request.getParameter("ShortDescription"));
        recipe.setUserid(user.getUserid());
       int id = DBHelper.createRecipe(con, recipe);
       recipe.setId(id);
            Part filePart = request.getPart("RecipeImage");
            
           for(String o :filePart.getHeaderNames()){
               writer.println(o);
           }
       if (id>0){
          ServletContext context = getServletContext();
          
            String ImageFilePath = FileUploadHelper.setImageFilePath(context, request,id);
            if(ImageFilePath != null) recipe.setImageFilePath(ImageFilePath);
            DBHelper.updateRecipe(con, recipe);
       }
       String sessionid = request.getSession().getId();
        response.sendRedirect(response.encodeRedirectURL("/servlet/j-yerby/MyRecipes.do;jsessionid=") + sessionid); 
       
       
        } catch(NumberFormatException | SQLException ex){
            response.setContentType("text/html");
            response.getWriter().println(ex.getStackTrace().toString());
            ex.printStackTrace(writer);
        }
   
       }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
