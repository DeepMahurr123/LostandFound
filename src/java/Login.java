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
@WebServlet("/Login")
public class Login extends HttpServlet {
    //private static final long serialVersionUID = 1L;

    /**
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     * @see HttpServlet#HttpServlet()
     */
//    public Login() {
//        super();
//    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("email");
        String password = request.getParameter("pass");
        int id=0;

        try {
            // Load the JDBC driver (optional in newer JDBC versions)
                Class.forName("com.mysql.jdbc.Driver");

            // Establish the database connection
            
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/LostFound", "root", "Deep@9720");
                PreparedStatement pstmt = con.prepareStatement("SELECT * FROM login");

                // Set parameters for the prepared statement
//                pstmt.setString(2, username);
//                pstmt.setString(3, password);

                // Execute the query
                ResultSet rs = pstmt.executeQuery() ;
                
                    int count=0;
                    while(rs.next())
                    {
                       String em= rs.getString(4);
                       String ps= rs.getString(5);
                        if(username.equals(rs.getString(4))&&password.equals(rs.getString(5)))
                        {
                            id=rs.getInt(1);
                            HttpSession session=request.getSession();
                            session.setAttribute("email", username);
                            session.setAttribute("id",id);                      
//                          out.println("<html><script>window.alert('You are logged in')</script></html>");
//                          response.sendRedirect("profile.jsp");
                            out.println("<html><body>");
                            out.println("<script>");
                            out.println("alert('You are Logged in..!');");
                            out.println("window.location='login1.jsp';");
                            out.println("</script>");
                            out.println("</body></html>");
                            count++;
                            break;
                        }
                       
                    }
                    if(count==0)
                    {
                        //out.println("<html><script>window.alert('Login failed')</script></html>");
                        //response.sendRedirect("login.html");
                        out.println("<html><body>");
                        out.println("<script>");
                        out.println("alert('Login Failed..try again!');");
                        out.println("window.location='login.html';");
                        out.println("</script>");
                        out.println("</body></html>");
                    }
                    
//                    if (rs.next()) {
//                        out.println("<html><script>window.alert('You are logged in')</script></html>");
//                        response.sendRedirect("index.html");
//                    } else if{
//                        out.println("<html><script>window.alert('Login failed')</script></html>");
//                        response.sendRedirect("login.html");
//                    }
                }
                catch(Exception e)
                {
                    out.println(e);
                }
            
        
            // Log the exception (consider using a logging framework like Log4j)
            

            // Provide a generic error message to the user
           
//        } finally {
//            out.close();
//        }
         
    }
}