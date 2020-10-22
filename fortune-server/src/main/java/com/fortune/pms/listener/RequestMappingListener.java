package com.fortune.pms.listener;

import java.util.List;
import java.util.Map;
import com.fortune.context.ApplicationContextListener;
import com.fortune.pms.domain.Fortune;
import com.fortune.pms.domain.Lunch;
import com.fortune.pms.domain.Member;
import com.fortune.pms.handler.AdminMemberGradeCommand;
import com.fortune.pms.handler.FortuneAddCommand;
import com.fortune.pms.handler.FortuneListCommand;
import com.fortune.pms.handler.FortuneResponseCommand;
import com.fortune.pms.handler.LunchAddCommand;
import com.fortune.pms.handler.LunchResponseCommand;
import com.fortune.pms.handler.MemberDeleteCommand;
import com.fortune.pms.handler.MemberDetailCommand;
import com.fortune.pms.handler.MemberJoinCommand;
import com.fortune.pms.handler.MemberListCommand;
import com.fortune.pms.handler.MemberLoginCommand;
import com.fortune.pms.handler.ShowAdminCommandListCommand;
import com.fortune.pms.handler.ShowMemberCommandListCommand;

// 클라이언트 요청을 처리할 커맨드 객체를 준비한다.
public class RequestMappingListener implements ApplicationContextListener {

  @SuppressWarnings("unchecked")
  @Override
  public void contextInitialized(Map<String,Object> context) {
    // 옵저버가 작업한 결과를 맵에서 꺼낸다.
    List<Member> memberList = (List<Member>) context.get("memberList");
    //memberList.add(new Member());
    List<Fortune> fortuneList = (List<Fortune>) context.get("fortuneList");
    List<Lunch> lunchList = (List<Lunch>) context.get("lunchList");

    MemberListCommand memberListCommand = new MemberListCommand(memberList);

    context.put("/member/list", memberListCommand);
    context.put("/member/detail", new MemberDetailCommand(memberList));
    context.put("/member/delete", new MemberDeleteCommand(memberList));
    context.put("/member/grade", new AdminMemberGradeCommand(memberList));
    context.put("/fortune/add", new FortuneAddCommand(fortuneList));
    context.put("/fortune/res", new FortuneResponseCommand(fortuneList, memberList));
    context.put("/fortune/list", new FortuneListCommand());
    context.put("/admin/command", new ShowAdminCommandListCommand());
    context.put("/user/command", new ShowMemberCommandListCommand());
    context.put("/login", new MemberLoginCommand(memberList));
    context.put("/join", new MemberJoinCommand(memberList));
    context.put("/lunch/add", new LunchAddCommand(lunchList));
    context.put("/lunch/res", new LunchResponseCommand(lunchList));
  }

  @Override
  public void contextDestroyed(Map<String,Object> context) {
  }
}
