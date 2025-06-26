<%--<%= "Sender: " + session.getAttribute("id") + " | Receiver: " + session.getAttribute("rev_id") %>--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Chat Page</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background: #f0f2f5;
      margin: 0;
      padding: 0;
    }

    .chat-container {
      max-width: 700px;
      margin: 40px auto;
      background: white;
      border-radius: 10px;
      box-shadow: 0 5px 15px rgba(0,0,0,0.1);
      display: flex;
      flex-direction: column;
      height: 90vh;
    }

    .chat-header {
      background: #2874f0;
      color: white;
      padding: 15px;
      font-size: 22px;
      font-weight: bold;
      border-top-left-radius: 10px;
      border-top-right-radius: 10px;
    }

    .chat-box {
      flex: 1;
      padding: 20px;
      overflow-y: auto;
    }

    .message {
      margin-bottom: 12px;
      max-width: 75%;
      padding: 10px 15px;
      border-radius: 18px;
      line-height: 1.5;
      font-size: 16px;
      clear: both;
    }

    .sent {
      background-color: #dcf8c6;
      float: right;
    }

    .received {
      background-color: #e6e6e6;
      float: left;
    }

    .chat-input {
      display: flex;
      border-top: 1px solid #ddd;
    }

    .chat-input input {
      flex: 1;
      border: none;
      padding: 15px;
      font-size: 16px;
      border-bottom-left-radius: 10px;
    }

    .chat-input button {
      background-color: #2874f0;
      color: white;
      border: none;
      padding: 15px 20px;
      cursor: pointer;
      font-size: 16px;
      border-bottom-right-radius: 10px;
    }

    .chat-input input:focus,
    .chat-input button:focus {
      outline: none;
    }

    .chat-input button:hover {
      background-color: #165dc2;
    }
  </style>
</head>
<body>

<%
    // You may get sender and receiver details from session or DB
    int sender = (Integer) session.getAttribute("id");
    int receiver = (Integer) session.getAttribute("rev_id");// logged-in user
    //String receiver = request.getParameter("receiver_id"); // passed via URL or hidden input
%>
<a href="login1.jsp">back</a>
<div class="chat-container">
  <div class="chat-header">
    Chat with <%= receiver == 0 ? receiver : "User" %>
  </div>

  <div class="chat-box">
    <%-- Example messages, dynamically loop here in real app --%>
    <%
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/lostfound","root","Deep@9720");
        PreparedStatement ps=con.prepareStatement("SELECT * FROM message WHERE (sender_id = '"+sender+"' AND receiver_id = '"+receiver+"') OR (sender_id = '"+receiver+"' AND receiver_id = '"+sender+"') ORDER BY sent_time ASC");
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        {
//            int id=rs.getInt(2);
//            if(id==sender)
//            {
              int sender_id=rs.getInt(2);
              int receiver_id=rs.getInt(3);
    
    
        
    %>    
    <!--<div class="message sent"></div>-->
    <div class="message received">
        <%
            if(sender_id==sender)
            {
        %>
        <%=rs.getString(4) %>
        <%
            }
        
        %>
    </div>
    <div class="message sent">
        <%
            if(receiver_id==receiver)
            
            {
        %>
        <b><%=rs.getString(4) %></b>
        <%
            }
        
        %>
    </div>
    <%
            //}
        }
    %>    
  </div>

  <form class="chat-input" action="SendMessageServlet" method="post">
    <input type="hidden" name="sender" value="<%= sender %>">
    <input type="hidden" name="receiver" value="<%= receiver %>">
    <input type="text" name="message" placeholder="Type a message..." required />
    <button type="submit">Send</button>
  </form>
</div>

</body>
</html>
