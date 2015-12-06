/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Utilities.DBHelper;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Ingredient;

/**
 *
 * @author gyerby
 */
public class CreateIngredient extends HttpServlet {

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
            RequestDispatcher view = request.getRequestDispatcher("CreateIngredient.jsp");
            view.forward(request, response);
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
      
            Ingredient ingredient = new Ingredient() ;
            ingredient.setDescription(request.getParameter("Description"));
            ingredient.setRecipeId(Integer.parseInt(request.getParameter("RecipeID")));
            int id = DBHelper.createIngredient(con, ingredient);
            String sessionid = request.getSession().getId();
            response.sendRedirect(response.encodeRedirectURL("/servlet/j-yerby/readRecipe.do") + ";jsessionid=" + sessionid + "?ID=" + ingredient.getRecipeId());
        } catch (SQLException ex) {
            Logger.getLogger(CreateIngredient.class.getName()).log(Level.SEVERE, null, ex);
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
