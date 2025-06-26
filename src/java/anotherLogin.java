import java.io.*;
import java.sql.*;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
//@WebServlet("/Login")
public class anotherLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
//    public Login() {
//        super();
//    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("email");
        String password = request.getParameter("pass");
        int id=0;

        try {
            // Load the JDBC driver (optional in newer JDBC versions)
            Class.forName("com.mysql.jdbc.Driver");

            // Establish the database connection
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/LostFound", "root", "Deep@9720");
                 PreparedStatement pstmt = con.prepareStatement("SELECT * FROM login WHERE email=? AND password=?")) {

                // Set parameters for the prepared statement
                pstmt.setString(1, username);
                pstmt.setString(2, password);

                // Execute the query
                try (ResultSet rs = pstmt.executeQuery()) 
                {
                    int count=0;
                    while(rs.next())
                    {
                       String em= rs.getString(3);
                       String ps= rs.getString(4);
                        if(em.equals(username)&&ps.equals(password))
                        {
                            id=rs.getInt(1);
                            HttpSession session=request.getSession();
                            session.setAttribute("email", username);
                            session.setAttribute("id", id);
                        
                            out.println("<html><script>window.alert('You are logged in')</script></html>");
                            response.sendRedirect("profile.jsp");
                            count++;
                            break;
                        }
                        else
                        {
                            out.println("<html><script>window.alert('Login failed')</script></html>");
                            response.sendRedirect("login.html");
                        }
                    }
                    if(count==0)
                    {
                        out.println("<html><script>window.alert('Login failed')</script></html>");
                        response.sendRedirect("login.html");
                    }
                    
//                    if (rs.next()) {
//                        out.println("<html><script>window.alert('You are logged in')</script></html>");
//                        response.sendRedirect("index.html");
//                    } else if{
//                        out.println("<html><script>window.alert('Login failed')</script></html>");
//                        response.sendRedirect("login.html");
//                    }
                }
            }
        } catch (Exception e) {
            // Log the exception (consider using a logging framework like Log4j)
            e.printStackTrace();

            // Provide a generic error message to the user
            out.println(e);
        } finally {
            out.close();
        }
    }
}