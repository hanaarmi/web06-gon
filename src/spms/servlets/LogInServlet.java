package spms.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import spms.dao.MemberDao;
import spms.vo.Member;

@WebServlet("/auth/login")
public class LogInServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		RequestDispatcher rd = request.getRequestDispatcher("/auth/LogInForm.jsp");
		rd.forward(request, response);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		try {
			ServletContext sc = this.getServletContext();
			MemberDao dao = (MemberDao)sc.getAttribute("memberDao");
			
			Member member = dao.exist(
                    request.getParameter("email"),
					request.getParameter("password"));
			if (member != null) {
				HttpSession session = request.getSession();
				session.setAttribute("member", member);
                request.setAttribute("viewUrl", "redirect:../member/list.do");
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/auth/LogInFail.jsp");
				rd.forward(request, response);
			}
			
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
