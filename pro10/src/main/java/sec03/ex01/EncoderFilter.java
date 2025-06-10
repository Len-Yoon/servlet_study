package sec03.ex01;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class EncoderFilter implements Filter {
    ServletContext context;

    public void init(FilterConfig fc) throws ServletException {
        System.out.println("utf-8 인코딩............");
        context = fc.getServletContext();
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        System.out.println("doFilter 호출");
        request.setCharacterEncoding("utf-8");

        String path = ((HttpServletRequest)request).getContextPath();
        String pathinfo = ((HttpServletRequest)request).getRequestURI();
        String realPath = request.getRealPath(pathinfo);
        String msg = "Context 정보: " + context + "\n요청 URI: " + pathinfo + "\n실제 경로: " + realPath;
        System.out.println(msg);

        long begin = System.currentTimeMillis(); // 요청 처리 전 시간

        chain.doFilter(request, response);

        long end = System.currentTimeMillis(); // 요청 처리 후 시간
        System.out.println("작업 시간:" + (end - begin) + "ms"); // 작업 소요 시간 출력
    }

    public void destroy() {
        System.out.println("destroy 호출");
    }
}
