import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.net.*;
import javax.net.ssl.HttpsURLConnection;

public class sendOtp extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        String phone = request.getParameter("phone"); // Example: 9720612247
        String otp = String.valueOf((int)(Math.random() * 9000) + 1000); // Random 4-digit OTP
        
        try
        {
            
            String apiKey="WPbwFD6CVOFKEsh2xjrNvL2Y9CovmlWOYJAE3GapVnz5SVYINIEgobnCRwIA";
            String sendId="FSTSMS";
            String number="9720612247";
            String language="english";
            String route="t";
            String message=URLEncoder.encode("this otp send by Deep Mahur"+otp, "UTF-8");
            String myUrl="https://www.fast2sms.com/dev/bulk?authorization="+apiKey+"&sender_id="+sendId+"&message="+message+"&language="+language+"&route="+route+"&numbers="+phone;
            URL url=new URL(myUrl);
            HttpsURLConnection con=(HttpsURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            con.setRequestProperty("cache-control", "no-cache");
            int responseCode = con.getResponseCode();
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String output;
                StringBuilder fullResponse = new StringBuilder();
                while ((output = in.readLine()) != null) {
                    fullResponse.append(output);
                }
                in.close();

                out.println("OTP Sent Successfully to " + phone + " <br>OTP: " + otp);
            } else {
                out.println("Error sending OTP. Response Code: " + responseCode);
            }
        }
        catch(Exception e)
        {
            out.println(e);
        }
    }    
}
