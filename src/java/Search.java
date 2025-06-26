/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author deepr
 */
@WebServlet("/Search")
public class Search extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String item=request.getParameter("item");
        HttpSession sess=request.getSession(false);
        if(sess==null || sess.getAttribute("email")==null || sess.getAttribute("pass")==null)
        {
            out.println("<html><body>");
            out.println("<script>");
            out.println("alert('Login First');");
            out.println("window.location='login.html';");
            out.println("</script>");
            out.println("</body></html>");
     
           
        }
        else
        {
             try
            {
                out.print("Welcome this search page...");
            }
            catch(Exception e)
            {
                out.println(e);
            }
        }
        
    }
}
