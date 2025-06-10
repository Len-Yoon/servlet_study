package sec03.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginTest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // request.setCharacterEncoding("utf-8"); // POST 방식 한글 전송 시 인코딩 생략
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        String user_name = request.getParameter("user_name");
        String user_pw = request.getParameter("user_pw");

        out.println("<html><body>");
        out.println("이름=" + user_name + "<br>");
        out.println("비밀번호=" + user_pw + "<br>");
        out.println("</body></html>");
    }
}
