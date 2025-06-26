/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author deepr
 */
public class verifyotpServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
       PrintWriter out = response.getWriter();
       String otps=request.getParameter("otp");
       int otp=Integer.parseInt(otps);
       HttpSession session =request.getSession(false);
       int otp1=(int)session.getAttribute("otp");
       if(otp==otp1)
       {
           response.sendRedirect("changePassword.jsp");//directly send to change password page if both are match
       }
       else
       {
           out.print("Invalid otp try again...");
       }
        
    }
}
