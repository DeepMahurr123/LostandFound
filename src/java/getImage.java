import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/getImage")
public class getImage extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String name = request.getParameter("name");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/LostFound", "root", "Deep@9720");

            String sql = "SELECT profile FROM login WHERE name ='"+name+"'";
            PreparedStatement statement = conn.prepareStatement(sql);
            //statement.setString(1, name);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                response.setContentType("image/jpeg");
                InputStream inputStream = rs.getBinaryStream("profile");
                OutputStream outputStream = response.getOutputStream();

                byte[] buffer = new byte[4096];
                int bytesRead;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                inputStream.close();
                outputStream.close();
            } else {
                response.setContentType("text/html");
                response.getWriter().println("Image not found!");
            }

            conn.close();
        } catch (Exception e) {
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
