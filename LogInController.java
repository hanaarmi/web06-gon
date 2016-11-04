package spms.controls;

import spms.bind.DataBinding;
import spms.dao.MemberDao;
import spms.vo.Member;

import javax.servlet.http.HttpSession;
import java.util.Map;

public class LogInController implements Controller, DataBinding {

    private MemberDao memberDao;

    public LogInController setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
        return this;
    }

    public Object[] getDataBinders() {
        return new Object[] {
            "loginInfo", spms.vo.Member.class
        };
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        Member loginInfo = (Member)model.get("loginInfo");
        if (loginInfo.getEmail() == null) {
            return "/auth/LogInForm.jsp";
        } else {
            Member member = memberDao.exist(loginInfo.getEmail(), loginInfo.getPassword());
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
