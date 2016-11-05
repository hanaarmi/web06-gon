package spms.controls;

import spms.bind.DataBinding;
import spms.dao.MemberDao;
import spms.vo.Member;

import java.util.Map;

public class MemberUpdateController implements Controller, DataBinding {

    private MemberDao memberDao;

    public MemberUpdateController setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
        return this;
    }

    public Object[] getDataBinders() {
        return new Object[] {
                "no", Integer.class,
                "member", spms.vo.Member.class
        };
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        Member member = (Member)model.get("member");
        if (member.getEmail() == null) {
            Integer no = (Integer)model.get("no");
            Member detailInfo = memberDao.selectOne(no);
            model.put("member", detailInfo);
            return "/member/MemberUpdateForm.jsp";
        } else {
            memberDao.update(member);
            return "redirect:list.do";
        }
    }
}