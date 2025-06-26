import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;
public class Lost extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException
	{
        Connection con;
		PreparedStatement p;
		
                HttpSession sess=req.getSession(false);
                int id=(Integer)sess.getAttribute("id");	
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
        String what_lost=req.getParameter("what_lost");
        String category=req.getParameter("category");
        String brand=req.getParameter("brand");
        String p_color=req.getParameter("p_color");
		String s_color=req.getParameter("s_color");
		String option=req.getParameter("option");
		int z_code=Integer.parseInt(req.getParameter("z_code"));
        String f_name=req.getParameter("f_name");
        
        String fdate=req.getParameter("fdate");
        String sdate=req.getParameter("sdate");
        String a_info=req.getParameter("a_info");
        String l_name=req.getParameter("l_name");
        long p_no=Long.parseLong(req.getParameter("p_no"));
        String email=req.getParameter("email");
        try{
        	
        	
        Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/LostFound","root","Deep@9720");
		p=con.prepareStatement("INSERT INTO `lost`(`what_lost`, `fdate`, `category`, `sdate`, `brand`, `p_color`, `s_color`, `a_info`, `opt`, `z_code`, `f_name`, `l_name`, `email`, `p_no`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
		
		
		p.setString(1, what_lost);
		p.setString(2, fdate);
		p.setString(3, category);
		p.setString(4, sdate);
		p.setString(5, brand);
		p.setString(6, p_color);
		p.setString(7, s_color);
		p.setString(8, a_info);
		p.setString(9, option);
		p.setInt(10, z_code);
		p.setString(11, f_name);
		p.setString(12, l_name);
		p.setString(13, email);
		p.setLong(14, p_no);
                p.setInt(15, id);

		
		
        int n=p.executeUpdate();
        
        if(n!=0)
        {
            out.println("<html><script>window.alert('Thank you')</script></html>");
            res.sendRedirect("recent_posts.jsp");
        }
        else{
            out.println("<html><script>window.alert('Something wrong')</script></html>");
        }
        }catch(Exception e){out.println("<html><body>"+e+"</body></html>");}
        

        out.close();	
	}
}