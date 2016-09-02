package spms.servlets;

import spms.vo.Member;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet
{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String servletPath = request.getServletPath();
        try {
            String pageControllerPath = null;

            if ("member/list.do".equals(servletPath)) {
                pageControllerPath = "member/list";
            } else if ("member/add.do".equals(servletPath)) {
                pageControllerPath = "member/add";
                if (request.getParameter("email") != null) {
                    request.setAttribute("member", new Member().);
                }
            }
        }


    }
}
