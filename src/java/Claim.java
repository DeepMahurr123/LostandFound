
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.*;
import java.sql.*;

/**
 * Servlet implementation class Claim
 */
public class Claim extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection con;
		PreparedStatement p;

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String nm = (request.getParameter("fname") + " " + request.getParameter("lname"));
		String email = request.getParameter("email");
		String item = request.getParameter("item");
		String ids = request.getParameter("id");
		int id = Integer.parseInt(ids);
		String dec = request.getParameter("dec");
                //out.print("Welcome to clim page....");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/LostFound","root","Deep@9720");
			//out.print("Welcome...");
			//p=con.createStatement();
			
			 p=con.prepareStatement("insert into Claim(`name`, `email`, `item_id`, `item`, `description`) values (?,?,?,?,?)");
//                        out.println(nm);
//                        out.println(email);
//                        out.println(id);
//                        out.println(item);
//                        out.println(dec);
                         p.setString(1, nm);
                        p.setString(2, email);
                        p.setInt(3,id);
                        p.setString(4, item);
                        p.setString(5,dec);
                        int m=p.executeUpdate();
                       // out.print("Weleocme after query");
			if(m>0)
                        {
                            out.println("<html><body><script type=\\\"text/javascript\\\">window.alert('Item claimed wait for admin response')</script></body></html>");
			response.sendRedirect("recent_posts.jsp");
//                                out.println("<html><body>");
//                                out.println("<script type='text/javascript'>");
//                                out.println("alert('Item claimed, wait for admin response');");
//                                out.println("window.location='recent_posts.jsp';");
//                                out.println("</script>");
//                                out.println("</body></html>");
                        }
			else
                        {
                            out.print("Not Save....");
                        }
		} catch (Exception e) 
                {
                    out.print(e);
		}
		

	}

}