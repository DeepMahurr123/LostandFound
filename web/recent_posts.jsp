<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%! Connection con; %>
<%
    Class.forName("com.mysql.jdbc.Driver");
    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/LostFound", "root", "Deep@9720");
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT * FROM found");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Lost & Found - Recent Posts</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom Style -->
    <style>
        body {
            background: linear-gradient(120deg, #fdfbfb 0%, #ebedee 100%);
            font-family: 'Segoe UI', sans-serif;
        }

        .navbar {
            background-color: #198754;
        }

        .navbar-brand {
            font-size: 24px;
            color: white;
            font-weight: bold;
        }

        .user-login {
            color: white;
            text-decoration: none;
            font-size: 14px;
            margin-right: 20px;
        }

        .card {
            border: none;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
            transition: transform 0.2s;
        }

        .card:hover {
            transform: translateY(-5px);
        }

        .card-title {
            font-weight: 600;
            color: #198754;
        }

        .btn-contact {
            background-color: #198754;
            border: none;
        }

        .btn-contact:hover {
            background-color: #146c43;
        }

        .section-title {
            font-size: 32px;
            font-weight: bold;
            color: #343a40;
            margin-bottom: 30px;
        }

        .card-img-top {
            height: 200px;
            object-fit: cover;
        }
    </style>
</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg px-4">
    <a class="navbar-brand" href="#">Lost & Found</a>
    <div class="ms-auto">
        <a href="login1.jsp" class="user-login">|Home</a>
    </div>
</nav>

<!-- Main Section -->
<div class="container my-5">
    <h2 class="text-center section-title">Recently Reported Lost Items</h2>
    <div class="row g-4">
        <%
            while (rs.next()) {
        %>
        <div class="col-md-4">
            <div class="card h-100">
                <!-- Optional Image -->
                <!--<img src="getfoundImage?id=<%=rs.getInt(1)%>" class="card-img-top" alt="Lost Item">-->
                <img src="getfoundImage?id=<%=rs.getInt(1)%>" class="card-img-top" alt="Lost_Item">
                <div class="card-body d-flex flex-column">
                    <h5 class="card-title"><%= rs.getString(2) %></h5>
                    <p class="card-text"><%= rs.getString(9) %></p>
                    <form action="Cont.jsp" method="post" class="mt-auto">
                        <input type="hidden" name="email" value="<%= rs.getString(15) %>">
                        <input type="hidden" name="id" value="<%= rs.getString(1) %>">
                        <input type="hidden" name="item" value="<%= rs.getString(2) %>">
                        <button type="submit" class="btn btn-contact w-100 mt-3">Contact</button>
                    </form>
                </div>
            </div>
        </div>
        <%
            }
        %>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
