package sec01.ex02;

import java.io.*;
import java.net.URLEncoder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/login2")
public class LoginServlet2 extends HttpServlet {
    public void init() {
        System.out.println("init 메서드 호출");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String user_id = request.getParameter("user_id");
        String user_pw = request.getParameter("user_pw");
        String user_address = request.getParameter("user_address");

        String user_email = request.getParameter("user_email");
        String user_hp = request.getParameter("user_hp");

        String data = "안녕하세요!<br>로그인하셨습니다.<br><br>";
        data += "<html><body>";
        data += "아이디 : " + user_id + "<br>";
        data += "패스워드 : " + user_pw + "<br>";
        data += "주소 : " + user_address + "<br>";
        data += "email : " + user_email + "<br>";
        data += "휴대전화 : " + user_hp + "<br>";
        out.print(data);

        user_address = URLEncoder.encode(user_address, "utf-8");
        out.print("<a href='/pro09/sec01/second?user_id=" + user_id + "&user_pw=" + user_pw
            + "&user_address=" + user_address + "'>두 번째 서블릿으로 보내기</a>");
        out.print("</body></html>");
        out.print(data);
    }
}
