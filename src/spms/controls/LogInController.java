package spms.controls;

import spms.dao.MemberDao;

import java.util.Map;

public class LogInController implements Controller {
    @Override
    public String execute(Map<String, Object> model) throws Exception {
        if (model.get("email") == null) {
            return "/auth/LogInForm.jsp";
        } else {
            MemberDao memberDao = (MemberDao)model.get("memberDao");
            memberDao.exist()
            return "redirect:list.do";
        }
    }
}
