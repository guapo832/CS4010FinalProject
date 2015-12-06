/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Utilities.DBHelper;
import java.io.IOException;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Recipe;
import models.User;

/**
 *
 * @author gyerby
 */
@WebServlet(name = "UpdateRecipe", urlPatterns = {"/UpdateRecipe.do"})
public class UpdateRecipe extends HttpServlet {

    

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
        try{
         Connection con = (Connection) getServletContext().getAttribute("dbCon");
       int ID = Integer.parseInt(request.getParameter("ID"));
        // Object user = Membership.getUserBean(request.getSession());
            User user = (User)request.getSession().getAttribute("CurrentUser");
            if(user==null)
            {
                RequestDispatcher dispatcher = request.getRequestDispatcher("signin.do");
                response.sendRedirect("signin.do");
                                
            } else
            {
                Recipe recipe = DBHelper.readRecipe(con, ID, Boolean.TRUE);
                request.setAttribute("Recipe", recipe);
                RequestDispatcher view = request.getRequestDispatcher("UpdateRecipe.jsp");
                view.forward(request, response);
            }
        }
         catch(Exception ex)
         {
             response.setContentType("text/html");
             response.getWriter().println(ex.toString());
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
        try{
         Connection con = (Connection) getServletContext().getAttribute("dbCon");
        int ID = Integer.parseInt(request.getParameter("ID"));
        Recipe recipe = DBHelper.readRecipe(con, ID,Boolean.TRUE);
        recipe.setTitle(request.getParameter("Title"));
        recipe.setAuthor(request.getParameter("Author"));
        recipe.setInstructions(request.getParameter("Instructions"));
        recipe.setShortDescription(request.getParameter("ShortDescription"));
        recipe.setYield(request.getParameter("Yield"));
        recipe.setSkillLevel(request.getParameter("SkillLevel"));
        String strPrepTime = (String)request.getParameter("PrepTime");
        String strCookTime = (String)request.getParameter("CookTime");
      
        recipe.setCookTime(Integer.parseInt(strCookTime));
        recipe.setPrepTime(Integer.parseInt(strPrepTime));
      //  recipe.setImageFilePath(setImageFilePath(request));
        
        DBHelper.updateRecipe(con, recipe);
        
         response.sendRedirect(response.encodeRedirectURL("/servlet/j-yerby/MyRecipes.do"));
        } catch(Exception ex)
        {
            response.setContentType("text/html");
            response.getWriter().println(ex.toString());
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
