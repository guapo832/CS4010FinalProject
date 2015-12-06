/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Utilities.DBHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Ingredient;
import models.Recipe;

/**
 *
 * @author gyerby
 */
@WebServlet(name = "EditIngredient", urlPatterns = {"/editIngredient.do"})
public class UpdateIngredient extends HttpServlet {

    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request
     * @paramjjj request servlet request
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
       Ingredient ingredient = DBHelper.readIngredient(con, ID);
       request.setAttribute("Ingredient", ingredient);
       RequestDispatcher view = request.getRequestDispatcher("UpdateIngredient.jsp");
       view.forward(request, response);
    } catch(Exception ex){
        response.setContentType("text/html");
        response.getWriter().println(ex.toString());
    }
    }
    
    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
      Connection con = (Connection) getServletContext().getAttribute("dbCon");
      Ingredient ingredient = new Ingredient() ;
      ingredient.setDescription(request.getParameter("Description"));
      ingredient.setId(Integer.parseInt(request.getParameter("ID")));
            
      ingredient.setRecipeId(Integer.parseInt(request.getParameter("RecipeID")));
      DBHelper.updateIngredient(con, ingredient);
      String sessionid = request.getSession().getId();
      response.sendRedirect(response.encodeRedirectURL("/servlet/j-yerby/readRecipe.do") + ";jsessionid=" + sessionid + "?ID=" + ingredient.getRecipeId());
    } catch(Exception ex){
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
