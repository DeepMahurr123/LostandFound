<%@ page import="javax.servlet.http.*, javax.servlet.*" %>
<%@ page import="java.io.OutputStream"%>
<%@ page import="java.io.InputStream"%>
<%@ page import="java.sql.*"  %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);

    HttpSession sess = request.getSession(false);
    if (sess == null || sess.getAttribute("id") == null || sess.getAttribute("email") == null) {
        response.sendRedirect("login.html?message=Please%20login%20first.");
        return;
    }

    int id = 0;
    String name = null;
    String email = null;
    long contact = 0;
    int ids = (Integer) sess.getAttribute("id");
    String username = (String) sess.getAttribute("email");

    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/LostFound", "root", "Deep@9720");
    PreparedStatement stm = con.prepareStatement("SELECT * FROM login");
    ResultSet rs = stm.executeQuery();
    while (rs.next()) {
        if (ids == rs.getInt(1) && username.equals(rs.getString(4))) {
            id = rs.getInt(1);
            email = rs.getString(4);
            name = rs.getString(3);
            contact = rs.getLong(7);
            break;
        }
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;700&display=swap" rel="stylesheet">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Poppins', sans-serif;
            background: linear-gradient(to right, #667eea, #764ba2);
            color: #333;
            min-height: 100vh;
        }

        /* Navigation Bar */
        .navbar {
            width: 100%;
            background-color: rgba(255, 255, 255, 0.95);
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 15px 40px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            position: fixed;
            top: 0;
            z-index: 999;
        }

        .navbar h1 {
            color: #764ba2;
            font-size: 22px;
        }

        .nav-links a {
            margin-left: 25px;
            text-decoration: none;
            color: #333;
            font-weight: 500;
            transition: color 0.3s ease;
        }

        .nav-links a:hover {
            color: #764ba2;
        }

        .container {
            padding-top: 100px;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .profile-container {
            background-color: #ffffff;
            padding: 40px 30px;
            border-radius: 16px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2);
            text-align: center;
            width: 400px;
            animation: slideUp 0.8s ease-in-out;
        }

        @keyframes slideUp {
            from { opacity: 0; transform: translateY(30px); }
            to { opacity: 1; transform: translateY(0); }
        }

        .profile-photo img {
            width: 120px;
            height: 120px;
            border-radius: 50%;
            object-fit: cover;
            border: 4px solid #764ba2;
            margin-bottom: 20px;
        }

        h2 {
            font-weight: 600;
            margin-bottom: 10px;
        }

        .profile-details {
            font-size: 15px;
            line-height: 1.6;
            text-align: left;
            margin-top: 20px;
        }

        .profile-details p {
            margin: 10px 0;
        }

        .logout-btn {
            display: inline-block;
            margin-top: 25px;
            padding: 10px 25px;
            background-color: #ff4c4c;
            color: white;
            border-radius: 30px;
            text-decoration: none;
            font-weight: 500;
            transition: background-color 0.3s ease;
        }

        .logout-btn:hover {
            background-color: #d73838;
        }

        @media (max-width: 600px) {
            .navbar {
                flex-direction: column;
                padding: 15px;
                text-align: center;
            }

            .nav-links {
                margin-top: 10px;
            }

            .nav-links a {
                display: inline-block;
                margin: 10px;
            }

            .profile-container {
                width: 90%;
            }
        }
    </style>
</head>
<body>
    <!-- Navigation Bar -->
    <div class="navbar">
        <h1>Lost & Found</h1>
        <div class="nav-links">
            <a href="login1.jsp">Home</a>
            <a href="feedback.html">Feedback</a>
            <a href="contact.html">Contact</a>
        </div>
    </div>

    <!-- Profile Section -->
    <div class="container">
        <div class="profile-container">
            <div class="profile-photo">
                <img src="getImage?name=<%=name%>" alt="Profile">
            </div>
            <h2>Welcome, <%=name%></h2>
            <div class="profile-details">
                <p><strong>ID:</strong> <%=id%></p>
                <p><strong>Email:</strong> <%=email%></p>
                <p><strong>Username:</strong> <%=name%></p>
                <p><strong>Phone:</strong> <%=contact%></p>
            </div>
            <a href="logout.jsp" class="logout-btn">Logout</a>
        </div>
    </div>
</body>
</html>
