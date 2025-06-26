import java.sql.*;
//import jakarta.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.http.Part;
import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
@SuppressWarnings("unused")
@WebServlet("/Found")
@MultipartConfig
public class Found extends HttpServlet
{
	protected void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException
	{
            
                //res.setContentType("text/html");
              
		
		
		HttpSession sess=req.getSession(false);
                int id=(Integer)sess.getAttribute("id");
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
                
                out.print("<html>");
                Connection con;
                PreparedStatement p;
                InputStream input=null;
                String what_lost=req.getParameter("what_Found");
                String category=req.getParameter("category");
                String brand=req.getParameter("brand");
                
                String p_color=req.getParameter("p_color");
		String s_color=req.getParameter("s_color");
		String option=req.getParameter("option");
                
//                try
//                {
//                    Part ps1=req.getPart("photo");
//                }
//                catch(Exception e)
//                {
//                    out.print(e);
//                }
                Part ps=req.getPart("photo");
                
		int z_code=Integer.parseInt(req.getParameter("z_code"));
                String f_name=req.getParameter("f_name");
               
                String fdate=req.getParameter("fdate");
                String sdate=req.getParameter("sdate");
                String a_info=req.getParameter("a_info");
                String l_name=req.getParameter("l_name");
                String email=req.getParameter("email");
                long p_no=Long.parseLong(req.getParameter("p_no"));
                if(ps!=null)
                {
                    String names=ps.getName();
                    input=ps.getInputStream();
                }

                
        try{
        	out.print("<body>");
        	
                Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/LostFound","root","Deep@9720");
		p=con.prepareStatement("INSERT INTO `found`(`what_lost`, `fdate`, `category`, `sdate`, `brand`, `p_color`, `s_color`, `a_info`, `opt`,image, `z_code`, `f_name`, `l_name`, `email`, `p_no`,sender_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
		
		
		p.setString(1, what_lost);
		p.setString(2, fdate);
		p.setString(3, category);
		p.setString(4, sdate);
		p.setString(5, brand);
		p.setString(6, p_color);
		p.setString(7, s_color);
		p.setString(8, a_info);
		p.setString(9, option);
                p.setBlob(10,input);
		p.setInt(11, z_code);
		p.setString(12, f_name);
		p.setString(13, l_name);
		p.setString(14, email);
		p.setLong(15, p_no);
                p.setInt(16,id);

		
		
                int n=p.executeUpdate();

                if(n!=0)
                {
                   
                   
                    if(id!=0)
                    {
                            out.println("<html><body>");
                            out.println("<script>");
                            out.println("alert('Thank You..!');");
                            out.println("window.location='login1.jsp';");
                            out.println("</script>");
                            out.println("</body></html>");
                    }
                    else
                    {
                        out.print("window.location='recent_posts.jsp'");
                    }
                    
                    //res.sendRedirect("recent_posts.jsp");
                    //out.print("........lkasdf");
//                    RequestDispatcher rd = req.getRequestDispatcher("recent_posts.jsp");
//                    rd.forward(req, res);
                }
                else{
                    out.println("<html><script>window.alert('Something wrong')</script></html>");
                    //out.print(".............");
                }
        }
        catch(Exception e){out.println(e);}
        

        out.close();
        out.print("</body>");
        out.print("</html>");
	}
}