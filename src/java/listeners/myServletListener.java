/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author gyerby
 */
public class myServletListener implements ServletContextListener {

    private Connection dbcon;

    /**
     *
     * @param sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        String dbconstring = context.getInitParameter("dbconstring");
        String dbusername = context.getInitParameter("dbusername");
        String drivername = context.getInitParameter("dbdriver");
        String dbpwd = context.getInitParameter("dbpassword");
        String RecipeImageRootFilePath = context.getInitParameter("RecipeImageRootFilePath");
        context.setAttribute("RecipeImageRootFilePath", RecipeImageRootFilePath);
        try {
            Class.forName(drivername).newInstance();
            this.dbcon = DriverManager.getConnection(dbconstring, dbusername,dbpwd);
            context.setAttribute("dbCon", this.dbcon);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(myServletListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param sce
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            dbcon.close();
        } catch (SQLException ex) {
            Logger.getLogger(myServletListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
