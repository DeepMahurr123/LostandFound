import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
@WebServlet("/Signup")
@MultipartConfig
public class Signup extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String fileName = "";
        InputStream input = null;
        Part p = req.getPart("profile");

        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");
        String name = fname + " " + lname;
        String email = req.getParameter("email");
        Long cno = Long.parseLong(req.getParameter("cno"));
        String pass = req.getParameter("pass");
        String repass=req.getParameter("repass");

        if (p != null) {
            fileName = p.getSubmittedFileName();
            input = p.getInputStream();
        }

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/LostFound", "root", "Deep@9720");

            String sql = "INSERT INTO login(`profile`, `name`, `email`, `password`,repassword ,`CNo`) VALUES (?,?,?,?,?,?)";
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setBlob(1, input);
                pstmt.setString(2, name);
                pstmt.setString(3, email);
                pstmt.setString(4, pass);
                pstmt.setString(5, repass);
                pstmt.setLong(6, cno);

                int n = pstmt.executeUpdate();

                if (n != 0) {
                    out.println("<html><body>");
                    out.println("<script>");
                    out.println("alert('You are Register  successfully!');");
                    out.println("window.location='login.html';");
                    out.println("</script>");
                    out.println("</body></html>");
                } else {
                    out.println("<html><body><script>alert('Something went wrong');</script></body></html>");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<html><body><b>" + e + "</b></body></html>");
        }
    }
}
