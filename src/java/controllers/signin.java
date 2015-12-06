/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;

/**
 *
 * @author gyerby
 */
public class signin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       RequestDispatcher dispatcher = request.getRequestDispatcher("signin.jsp");
      dispatcher.forward(request, response);
    }

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
        processRequest(request, response);
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
        Connection con = (Connection) getServletContext().getAttribute("dbCon");
        String password = (String)request.getParameter("Password");
        String username = (String)request.getParameter("Username");
        RequestDispatcher dispatcher = null;
        User user = new User();
        if((user = Utilities.DBHelper.getUser(con, username, password))==null)
        {
            user = new User();
            String Error = "Username/Password combination is invalid. Please try again or create a new account.";
            request.setAttribute("Error", Error);
            user.setPassword(password);
            user.setUsername(username);
            request.setAttribute("CurrentUser", user);
            dispatcher = request.getRequestDispatcher("signin.jsp");
            dispatcher.forward(request, response);
        } else 
        {
            if(request.getAttribute("Error")!=null)
                request.removeAttribute("Error");
            HttpSession session = request.getSession(true);
            session.setAttribute("CurrentUser", user);
        
        dispatcher = request.getRequestDispatcher("MyRecipes.do");
     //     dispatcher.forward(request, response);
         
         response.sendRedirect("/servlet/j-yerby/" + response.encodeRedirectURL("MyRecipes.do"));
       
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
