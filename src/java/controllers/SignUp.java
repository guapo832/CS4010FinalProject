/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Utilities.DBHelper;
import static com.sun.media.jfxmediaimpl.MediaUtils.error;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.User;

/**
 *
 * @author gyerby
 */
public class SignUp extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    

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
        RequestDispatcher dispatcher = request.getRequestDispatcher("signup.jsp");
        dispatcher.forward(request, response);
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
        String username = request.getParameter("Username");
        String password = request.getParameter("Password");
        Connection con = (Connection) getServletContext().getAttribute("dbCon");
        User user = DBHelper.getUser(con, username, password);
        RequestDispatcher dispatcher = null;
        String error = null;
        request.setAttribute("Error",error);
        if(user != null)
        {
            error="This username is already in use. Please try again";
            request.setAttribute("Error",error);
            dispatcher = request.getRequestDispatcher("signup.jsp");
            dispatcher.forward(request, response);
        }
        else 
        {
           
            try {
                user = DBHelper.createUser(con, username, password);
                 request.getSession().setAttribute("CurrentUser", user);
                 dispatcher = request.getRequestDispatcher("MyRecipes.do");
            } catch (SQLException ex) {
                Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, ex);
                error="An Error Occurred please try again later." + ex.toString();
                request.setAttribute("Error",error);
                dispatcher = request.getRequestDispatcher("signup.jsp");
                
            }
            
            
            dispatcher.forward(request, response);
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
