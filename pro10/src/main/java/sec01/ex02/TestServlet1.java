package sec01.ex02;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/first/test")
public class TestServlet1 extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        String context = request.getContextPath();      // 컨텍스트 이름만 가져옴
        String url = request.getRequestURL().toString(); // 전체 URL
        String mapping = request.getServletPath();      // 서블릿 매핑 이름
        String uri = request.getRequestURI();           // URI

        out.println("<html>");
        out.println("<head>");
        out.println("<title>TestServlet1</title>");
        out.println("</head>");
        out.println("<body bgcolor='green'>");
        out.println("<b>TestServlet1입니다.</b><br>");
        out.println("<b>컨텍스트 이름: " + context + "</b><br>");
        out.println("<b>전체 URL: " + url + "</b><br>");
        out.println("<b>매핑 이름: " + mapping + "</b><br>");
        out.println("<b>URI: " + uri + "</b><br>");
        out.println("</body>");
        out.println("</html>");
    }
}
