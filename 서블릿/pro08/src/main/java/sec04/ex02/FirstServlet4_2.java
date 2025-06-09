package sec04.ex02;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

@WebServlet("/first4_2")
public class FirstServlet4_2 extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        request.setAttribute("address", "서울시 성북구");
        RequestDispatcher dispatch = request.getRequestDispatcher("second4_2");
        dispatch.forward(request, response);
    }
}
