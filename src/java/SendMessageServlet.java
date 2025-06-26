/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author deepr
 */
@WebServlet("/SendMessageServlet")
public class SendMessageServlet extends HttpServlet {

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
        PrintWriter out=response.getWriter();
        String send_id=request.getParameter("sender");
        int sender_id=Integer.parseInt(send_id);
        String rec=request.getParameter("receiver");
        int receiver_id=Integer.parseInt(rec);
        String message=request.getParameter("message");
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/lostfound","root","Deep@9720");
            PreparedStatement ps=con.prepareStatement("insert into message(sender_id,receiver_id,message) values(?,?,?)");
            ps.setInt(1, sender_id);
            ps.setInt(2, receiver_id);
            ps.setString(3, message);
            int status=ps.executeUpdate();
            if(status>0)
            {
                response.sendRedirect("message.jsp");
            }
            else
            {
                out.print("Message not send..try again after some time...");
            }
        }
        catch(Exception e)
        {
            out.print(e);
        }
        
    }

    
}
