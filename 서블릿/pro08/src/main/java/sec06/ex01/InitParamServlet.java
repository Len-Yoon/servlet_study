package sec06.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(
    name = "InitParamServlet",
    urlPatterns = {"/sInit1", "/sInit2"},
    initParams = {
        @WebInitParam(name = "email", value = "admin@web.com"),
        @WebInitParam(name = "tel", value = "010-1111-2222")
    }
)
public class InitParamServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        String email = getInitParameter("email");
        String tel = getInitParameter("tel");

        out.print("<html><body>");
        out.print("<table border=1>");
        out.print("<tr><td>email</td><td>" + email + "</td></tr>");
        out.print("<tr><td>tel</td><td>" + tel + "</td></tr>");
        out.print("</table></body></html>");
    }
}
