<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Welcome - Lost and Found</title>
<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
<style>
  * {
    box-sizing: border-box;
    margin: 0; padding: 0;
    font-family: 'Poppins', sans-serif;
  }

  body {
    background: #f0f2f5;
    color: #333;
    padding-top: 70px; /* space for fixed navbar */
  }

  .navbar {
    background-color: #2a52be;
    color: white;
    padding: 12px 24px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    box-shadow: 0 2px 10px rgba(0,0,0,0.1);
    position: fixed;
    top: 0;
    width: 100%;
    z-index: 999;
  }

  .navbar .logo {
    font-size: 28px;
    font-weight: 600;
    letter-spacing: 1px;
  }

  .navbar .search-bar {
    flex-grow: 1;
    max-width: 500px;
    margin: 0 20px;
    display: flex;
  }

  .navbar input[type="text"] {
    flex: 1;
    padding: 10px;
    border: none;
    border-radius: 5px 0 0 5px;
    font-size: 16px;
  }

  .navbar button {
    padding: 10px 16px;
    border: none;
    background-color: #ffd700;
    border-radius: 0 5px 5px 0;
    cursor: pointer;
    font-weight: bold;
    font-size: 16px;
    color: #000;
  }

  .navbar .user-menu {
    font-size: 16px;
    display: flex;
    align-items: center;
    gap: 10px;
  }

  .profile-img {
    width: 36px;
    height: 36px;
    border-radius: 50%;
    border: 2px solid white;
    object-fit: cover;
  }

  .banner {
    background: url('https://rukminim1.flixcart.com/flap/1688/280/image/48aabff8a22e5a39.jpg?q=50') center/cover no-repeat;
    height: 280px;
    margin: 30px auto;
    max-width: 1200px;
    border-radius: 8px;
    box-shadow: 0 8px 20px rgba(0,0,0,0.15);
  }

  .quick-links {
    max-width: 1200px;
    margin: 30px auto;
    text-align: center;
  }

  .quick-links a {
    display: inline-block;
    background-color: #007bff;
    color: white;
    padding: 12px 24px;
    margin: 10px;
    border-radius: 6px;
    text-decoration: none;
    font-weight: bold;
    transition: background-color 0.3s;
  }

  .quick-links a:hover {
    background-color: #0056b3;
  }

  .quick-links a.red {
    background-color: #dc3545;
  }

  .quick-links a.red:hover {
    background-color: #a71d2a;
  }

  .section-title {
    text-align: center;
    margin: 40px auto 20px;
    font-size: 24px;
    font-weight: bold;
    color: #333;
  }

  .products {
    max-width: 1200px;
    margin: 20px auto 40px;
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(240px, 1fr));
    gap: 24px;
  }

  .product-card {
    background: #fff;
    border-radius: 10px;
    box-shadow: 0 4px 12px rgba(0,0,0,0.1);
    padding: 16px;
    text-align: center;
    transition: transform 0.2s ease;
  }

  .product-card:hover {
    transform: translateY(-5px);
  }

  .product-card img {
    width: 100%;
    height: 180px;
    object-fit: cover;
    border-radius: 6px;
    margin-bottom: 12px;
  }

  .name {
    font-size: 18px;
    font-weight: 600;
    margin-bottom: 6px;
  }

  .price {
    color: #ff5722;
    font-weight: 500;
    margin-bottom: 6px;
  }

  .rating {
    background-color: #388e3c;
    color: white;
    padding: 4px 10px;
    font-size: 14px;
    border-radius: 4px;
    display: inline-block;
    margin-bottom: 12px;
  }

  .my-button a {
    background-color: #4CAF50;
    color: white;
    padding: 8px 16px;
    text-decoration: none;
    border-radius: 5px;
    display: inline-block;
    transition: background-color 0.3s;
  }

  .my-button a:hover {
    background-color: #45a049;
  }

  @media(max-width: 768px) {
    .navbar {
      flex-wrap: wrap;
    }

    .navbar .search-bar {
      margin: 10px 0;
      width: 100%;
    }
  }
</style>
</head>
<body>
<%
    HttpSession sess = request.getSession(false);
    int id = (Integer) sess.getAttribute("id");

    String username = (String) sess.getAttribute("email");
    String name = null;
    String email = null;
    long contact;
    if (sess != null || id != 0 || username != null) {
        Connection con = null;
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/LostFound", "root", "Deep@9720");
        PreparedStatement ps = con.prepareStatement("select * from login");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            if (id == rs.getInt(1) && username.equals(rs.getString(4))) {
                id = rs.getInt(1);
                sess.setAttribute("sender_id", id);
                email = rs.getString(4);
                name = rs.getString(3);
                contact = rs.getLong(7);
            }
        }
%>

<header class="navbar">
  <div class="logo">Lost & Found</div>
  <form class="search-bar" onsubmit="event.preventDefault(); alert('Search feature coming soon!');">
    <input type="text" placeholder="Search for items...">
    <button type="submit">Search</button>
  </form>
  <div class="user-menu">
    <a href="profile.jsp"><img src="getImage?name=<%=name%>" alt="Profile" class="profile-img"></a>
    Hi, <%= name %>
  </div>
</header>

<section class="banner"></section>

<div class="quick-links">
  <a href="Found_Info.html">Post Found Items</a>
  <a href="Lost_Info.html" class="red">Post Lost Items</a>
</div>

<hr style="margin: 40px auto; width: 90%; border-top: 2px solid #ccc;">
<div class="section-title">Here are recent found items</div>

<%
    PreparedStatement ps1 = con.prepareStatement("select * from found");
    ResultSet rs1 = ps1.executeQuery();
%>
<section class="products">
<%
  while(rs1.next()) {
    int idd = rs1.getInt(1);
    int rev_id = rs1.getInt(17);
    sess.setAttribute("rev_id", rev_id);
    String pname = rs1.getString(2);
    String pdate = rs1.getString(3);
    String pbrand = rs1.getString(6);
%>
  <div class="product-card">
    <img src="getfoundImage?id=<%=idd%>" alt="Item Image">
    <div class="name"><%=pname%></div>
    <div class="price">Found on: <%=pdate%></div>
    <div class="rating"><%=pbrand%></div>
    <div class="my-button"><a href="message.jsp">Contact</a></div>
  </div>
<%
  }
%>
</section>

<%
    rs.close();
    ps.close();
    con.close();
  } else {
    out.print("Login failed. Please try again.");
  }
%>
</body>
</html>
