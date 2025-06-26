/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author deepr
 */
import java.sql.*;
public class Connectionn 
{
    public static Connection con;
    public static Connection getConnection() 
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/lostfound","root","Deep@9720");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return con;
    }
}
