package pro07;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/member") //주석 해제
//public class MemberServlet extends HttpServlet {
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//        throws IOException, ServletException {
//        response.setContentType("text/html;charset=utf-8");
//        PrintWriter out = response.getWriter();
//
//        MemberDAO dao = new MemberDAO(); // SQL문으로 조회할 MemberDAO 객체를 생성합니다.
//        List list = dao.listMembers();   // listMembers() 메서드로 회원 정보를 조회합니다.
//        out.print("<html><body>");
//        out.print("<table border=1 align='center' bgcolor='lightgreen'>");
//        out.print("<td>아이디</td><td>비밀번호</td><td>이름</td><td>이메일</td><td>가입일</td></tr>");
//        for (int i = 0; i < list.size(); i++) {
//            MemberVO vo = (MemberVO) list.get(i);
//            out.print("<tr bgcolor='white'>");
//            out.print("<td>" + vo.getId() + "</td>");
//            out.print("<td>" + vo.getPwd() + "</td>");
//            out.print("<td>" + vo.getName() + "</td>");
//            out.print("<td>" + vo.getEmail() + "</td>");
//            out.print("<td>" + vo.getJoinDate() + "</td>");
//            out.print("</tr>");
//        }
//        out.print("</table>");
//        out.print("</body></html>");
//    }
//}

@WebServlet("/member3")
public class MemberServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

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
        MemberDAO dao = new MemberDAO();
        PrintWriter out = response.getWriter();
        
        String _id = request.getParameter("id");
        String _pwd = request.getParameter("pwd");
        String _name = request.getParameter("name");
        String _email = request.getParameter("email");
        MemberVO vo = new MemberVO();
        System.out.println(request.getParameter("id"));
        vo.setId(_id);
        vo.setPwd(_pwd);
        vo.setName(_name);
        vo.setEmail(_email);
        dao.addMember(vo);

        // 회원 목록 출력
        List<MemberVO> list = dao.listMembers();
        out.print("<html><body>");
        out.print("<table border=1><tr align='center' bgcolor='lightgreen'>");
        out.print("<td>아이디</td><td>비밀번호</td><td>이름</td><td>이메일</td><td>가입일</td><td>삭제</td></tr>");
        for (int i = 0; i < list.size(); i++) {
            MemberVO memberVO = list.get(i);
            String id = memberVO.getId();
            String pwd = memberVO.getPwd();
            String name = memberVO.getName();
            String email = memberVO.getEmail();
            Date joinDate = memberVO.getJoinDate();
            out.print("<tr><td>" + id + "</td><td>" + pwd + "</td><td>"
                    + name + "</td><td>" + email + "</td><td>" + joinDate
                    + "</td><td>" + "<a href='/pro07/member3?command=delMember&id="
                    + id + "'>삭제</a></td></tr>");
        }
        out.print("</table></body></html>");
        out.print("<a href='/pro07/memberForm.html'>새 회원 가입하기</a>");
        
        String command = request.getParameter("command");
        if (command != null && command.equals("delMember")) {
            String id = request.getParameter("id");
            System.out.println("id == " + id);
            if (id != null && !id.trim().isEmpty()) {
                dao.delMember(id);
                out.println("<script>alert('삭제되었습니다.'); location.href='/pro07/member3';</script>");
                return;
            }
        }
    }
}
