package sec04.ex02;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/logout")
public class LogoutTest extends HttpServlet {
    ServletContext context;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doHandle(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doHandle(request, response);
    }

    private void doHandle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        context = getServletContext();
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String user_id = request.getParameter("user_id");
        session.invalidate();
        List user_list = (List) context.getAttribute("user_list");
        user_list.remove(user_id);
        context.removeAttribute("user_list");
        context.setAttribute("user_list", user_list);
        out.println("<br>로그아웃했습니다.");
    }
}
