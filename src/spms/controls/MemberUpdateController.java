package spms.controls;

import spms.dao.MemberDao;
import spms.vo.Member;

import java.util.Map;

public class MemberUpdateController implements Controller {

    private MemberDao memberDao;

    public MemberUpdateController setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
        return this;
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        //MemberDao memberDao = (MemberDao)model.get("memberDao");
        if (model.get("member") == null) {
            Member member = memberDao.selectOne((int)model.get("no"));
            model.put("member", member);
            return "/member/MemberUpdateForm.jsp";
        } else {
            Member member = (Member)model.get("member");
            memberDao.update(member);
            return "redirect:list.do";
        }
    }
}
