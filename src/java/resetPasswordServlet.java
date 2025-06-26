/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import java.sql.*;
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
public class resetPasswordServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session=request.getSession(false);
        long phone=(long)session.getAttribute("phone");
        String pass1=request.getParameter("newPassword");
        String pass2=request.getParameter("newPassword1");
        if(pass1.equals(pass2))
        {
            try
            {
                Class.forName("com.mysql.jc.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/lostfound","root","Deep@9720");
                PreparedStatement ps=con.prepareStatement("update login set password='"+pass1+"',repassword='"+pass1+"' where CNo='"+phone+"'");
                int m=ps.executeUpdate();
                if(m>0)
                {
                    out.println("<html><body>");
                    out.println("<script>");
                    out.println("alert('your Password Reset successfullty..!');");
                    out.println("window.location='login.html';");
                    out.println("</script>");
                    out.println("</body></html>");
                }
                else
                {
                    out.println("not Reset password");
                }
            }
            catch(Exception e)
            {
                out.println(e);
            }
        }
        else
        {
            out.println("<html><body>");
            out.println("<script>");
            out.println("alert('Both password are not same..try again!');");
            out.println("window.location='verify.jsp';");
            out.println("</script>");
            out.println("</body></html>");
        }
    }
}
