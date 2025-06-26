<%-- 
    Document   : logout
    Created on : 14 May 2025, 4:38:31 pm
    Author     : deepr
--%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
     
//    // Simulating user info coming from session or servlet
//    String username = (String) session.getAttribute("username");
//    if (username == null) {
//        username = "Mahur ji"; // default name if session empty
//    }
//    // Profile image URL could also come from session or database
//    String profileImageUrl = (String) session.getAttribute("profileImageUrl");
//    if (profileImageUrl == null) {
//        profileImageUrl = "https://randomuser.me/api/portraits/men/75.jpg";
//    }
    HttpSession sess=request.getSession(false);
    int id=(Integer) sess.getAttribute("id");
    String username=(String)sess.getAttribute("email");
    String name=null;
    String email=null;
    long contact;
    if(sess!=null|| id!=0 || username!=null)
    {   
    
        Connection con=Connectionn.getConnection();
        PreparedStatement ps=con.prepareStatement("select * from login");
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        {
            if(id==rs.getInt(1) && username.equals(rs.getString(4)))
            {
                id= rs.getInt(1);
                email=rs.getString(4);
                name=rs.getString(3);
                contact=rs.getLong(7); 
            }
        }
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Welcome - Flipkart Style Home</title>
<style>
  /* Reset */
  * {
    box-sizing: border-box;
    margin: 0; padding: 0;
    font-family: Arial, sans-serif;
  }
  body {
    background: #f1f3f6;
    color: #212121;
  }

  /* Navbar */
  .navbar {
    background-color: #2874f0;
    color: white;
    padding: 10px 20px;
    display: flex;
    align-items: center;
    justify-content: space-between;
  }
  .navbar .logo {
    font-size: 28px;
    font-weight: bold;
    letter-spacing: 2px;
    cursor: pointer;
  }
  .navbar .search-bar {
    flex-grow: 1;
    max-width: 600px;
    margin: 0 20px;
  }
  .navbar .search-bar input[type="text"] {
    width: 100%;
    padding: 8px 12px;
    border: none;
    border-radius: 2px 0 0 2px;
    font-size: 16px;
  }
  .navbar .search-bar button {
    padding: 8px 15px;
    border: none;
    background-color: #ffe500;
    border-radius: 0 2px 2px 0;
    cursor: pointer;
    font-weight: bold;
    font-size: 16px;
  }
  .navbar .user-menu {
    font-size: 16px;
    cursor: pointer;
    user-select: none;
    display: flex;
    align-items: center;
    gap: 8px;
    font-weight: 600;
  }

  .profile-img {
    width: 32px;
    height: 32px;
    border-radius: 50%;
    object-fit: cover;
    border: 2px solid white;
  }

  /* Banner */
  .banner {
    background: url('https://rukminim1.flixcart.com/flap/1688/280/image/48aabff8a22e5a39.jpg?q=50') center/cover no-repeat;
    height: 280px;
    margin: 20px auto;
    max-width: 1200px;
    border-radius: 6px;
    box-shadow: 0 6px 15px rgba(0,0,0,0.15);
  }

  /* Products Grid */
  .products {
    max-width: 1200px;
    margin: 30px auto 60px;
    display: grid;
    grid-template-columns: repeat(auto-fill,minmax(220px,1fr));
    gap: 20px;
  }
  .product-card {
    background: white;
    border-radius: 6px;
    box-shadow: 0 2px 8px rgb(0 0 0 / 0.1);
    padding: 15px;
    transition: box-shadow 0.3s ease;
  }
  .product-card:hover {
    box-shadow: 0 6px 15px rgb(0 0 0 / 0.2);
  }
  .product-card img {
    max-width: 100%;
    border-radius: 6px;
    margin-bottom: 12px;
  }
  .product-card .title {
    font-weight: 600;
    font-size: 16px;
    margin-bottom: 8px;
    height: 40px;
    overflow: hidden;
  }
  .product-card .price {
    color: #388e3c;
    font-weight: bold;
    font-size: 18px;
    margin-bottom: 8px;
  }
  .product-card .rating {
    background-color: #388e3c;
    color: white;
    padding: 3px 8px;
    font-size: 14px;
    border-radius: 4px;
    display: inline-block;
  }

  /* Responsive */
  @media(max-width: 768px) {
    .navbar {
      flex-wrap: wrap;
    }
    .navbar .search-bar {
      max-width: 100%;
      margin: 10px 0;
    }
  }
</style>
</head>
<body>

  <!-- Navbar -->
  <header class="navbar">
    <div class="logo">FlipKart</div>
    <form class="search-bar" onsubmit="event.preventDefault(); alert('Search feature coming soon!');">
      <input type="text" placeholder="Search for products, brands and more" />
      <button type="submit">Search</button>
    </form>
    <div class="user-menu">
      <img src="getImage?name<%=name%>" alt="Profile" class="profile-img" />
      Hi, <%= username %> ▼
    </div>
  </header>

  <!-- Banner -->
  <section class="banner"></section>

  <!-- Products -->
  <section class="products">
    <div class="product-card">
      <img src="https://rukminim1.flixcart.com/image/312/312/xif0q/mobile/z/x/z/-original-imaghquhdf7kdpgh.jpeg?q=70" alt="Smartphone" />
      <div class="title">Smartphone XYZ with 128GB Storage</div>
      <div class="price">₹12,999</div>
      <div class="rating">4.5 ★</div>
    </div>
    <div class="product-card">
      <img src="https://rukminim1.flixcart.com/image/312/312/k2jbyq80/computer/a/8/h/lenovo-na-laptop-original-imafmyc8e8kj6vsz.jpeg?q=70" alt="Laptop" />
      <div class="title">Lenovo Laptop with Intel i5 Processor</div>
      <div class="price">₹45,999</div>
      <div class="rating">4.2 ★</div>
    </div>
    <div class="product-card">
      <img src="https://rukminim1.flixcart.com/image/312/312/kzyyv680/earbud/r/7/r/-original-imagbdvv3ykmbxsz.jpeg?q=70" alt="Earbuds" />
      <div class="title">Wireless Earbuds with Noise Cancellation</div>
      <div class="price">₹2,999</div>
      <div class="rating">4.0 ★</div>
    </div>
    <div class="product-card">
      <img src="https://rukminim1.flixcart.com/image/312/312/l3x2ljk0/watch/e/j/g/-original-imagfhgzqjzxt6fv.jpeg?q=70" alt="Smartwatch" />
      <div class="title">Smartwatch with Health Tracking Features</div>
      <div class="price">₹5,499</div>
      <div class="rating">4.3 ★</div>
    </div>
  </section>

</body>
</html>
<%
    }
else
{
    out.print("loginFaild...thanks");
}
%>
