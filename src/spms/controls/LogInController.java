package spms.controls;

import spms.dao.MemberDao;
import spms.vo.Member;

import javax.servlet.http.HttpSession;
import java.util.Map;

public class LogInController implements Controller {
    @Override
    public String execute(Map<String, Object> model) throws Exception {
        if (model.get("email") == null) {
            return "/auth/LogInForm.jsp";
        } else {
            MemberDao memberDao = (MemberDao)model.get("memberDao");
            Member member = memberDao.exist((String)model.get("email"), (String)model.get("password"));
            if (member != null) {
                HttpSession session = (HttpSession)model.get("session");
                session.setAttribute("member", member);
                return "redirect:../member/list.do";
            } else {
                return "/auth/LogInFail.jsp";
            }
        }
    }
}
