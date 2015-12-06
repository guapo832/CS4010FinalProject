import java.io.*;
import java.net.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

 
public class servlet1 extends HttpServlet{
 
        public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException{
             String the_name = request.getParameter("whoisit");
                PrintWriter out = response.getWriter();
                out.println("<html>");
                out.println("<body>");
                out.println("<h1>Hi "+the_name+"</h1>");
                out.println("</body>");
                out.println("</html>"); 
        }
 }

