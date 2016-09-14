package spms.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MemberDao;

@SuppressWarnings("serial")
@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		try {
			ServletContext sc = this.getServletContext();

			MemberDao dao = (MemberDao)sc.getAttribute("memberDao");
			dao.delete(Integer.parseInt(request.getParameter("no")));

			request.setAttribute("viewUrl", "redirect:list.do");
		} catch (Exception e) {
            throw new ServletException(e);
        }
	}
}
