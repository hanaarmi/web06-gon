package spms.servlets;

import spms.controls.*;
import spms.vo.Member;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet
{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String servletPath = request.getServletPath();
        try {
            ServletContext sc = this.getServletContext();

            HashMap<String,Object> model = new HashMap<String,Object>();
            model.put("memberDao", sc.getAttribute("memberDao"));

            String pageControllerPath = null;
            Controller pageController = null;

            if ("/member/list.do".equals(servletPath)) {
                //pageControllerPath = "/member/list";
                pageController = new MemberListController();
            } else if ("/member/add.do".equals(servletPath)) {
                //pageControllerPath = "/member/add";
                pageController = new MemberAddController();
                if (request.getParameter("email") != null) {
                    Member member = new Member()
                            .setEmail(request.getParameter("email"))
                            .setPassword(request.getParameter("password"))
                            .setName(request.getParameter("name"));
                    model.put("member", member);
                }
            } else if ("/member/update.do".equals(servletPath)) {
                //pageControllerPath = "/member/update";
                pageController = new MemberUpdateController();
                if (request.getParameter("email") != null) {
                    Member member = new Member()
                            .setNo(Integer.parseInt(request.getParameter("no")))
                            .setEmail(request.getParameter("email"))
                            .setName(request.getParameter("name"));
                    model.put("member", member);
                } else {
                    model.put("no", Integer.parseInt(request.getParameter("no")));
                }
            } else if ("/member/delete.do".equals(servletPath)) {
                //pageControllerPath = "/member/delete";
                pageController = new MemberDeleteController();
                model.put("no", Integer.parseInt(request.getParameter("no")));
                pageController.execute(model);
            } else if ("/auth/login.do".equals(servletPath)) {
                //pageControllerPath = "/auth/login";
                pageController = new LogInController();
                if (request.getParameter("email") != null) {

                }
            } else if ("/auth/logout.do".equals(servletPath)) {
                pageControllerPath = "/auth/logout";
            }

            String viewUrl = pageController.execute(model);

            for (String key : model.keySet()) {
                request.setAttribute(key, model.get(key));
            }

            if (viewUrl.startsWith("redirect:")) {
                response.sendRedirect(viewUrl.substring(9));
                return;
            } else {
                RequestDispatcher rd  = request.getRequestDispatcher(viewUrl);
                rd.include(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", e);
            RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
            rd.forward(request, response);
        }
    }
}
