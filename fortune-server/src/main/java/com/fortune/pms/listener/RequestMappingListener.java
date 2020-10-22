package com.fortune.pms.listener;

import java.util.List;
import java.util.Map;
import com.fortune.context.ApplicationContextListener;
import com.fortune.pms.domain.Fortune;
import com.fortune.pms.domain.Member;
import com.fortune.pms.handler.CommandListCommand;
import com.fortune.pms.handler.FortuneAddCommand;
import com.fortune.pms.handler.FortuneResponseCommand;
import com.fortune.pms.handler.HelloCommand;
import com.fortune.pms.handler.MemberAddCommand;
import com.fortune.pms.handler.MemberDeleteCommand;
import com.fortune.pms.handler.MemberDetailCommand;
import com.fortune.pms.handler.MemberJoinCommand;
import com.fortune.pms.handler.MemberListCommand;
import com.fortune.pms.handler.MemberLoginCommand;
import com.fortune.pms.handler.MemberUpdateCommand;

// 클라이언트 요청을 처리할 커맨드 객체를 준비한다.
public class RequestMappingListener implements ApplicationContextListener {

  @SuppressWarnings("unchecked")
  @Override
  public void contextInitialized(Map<String,Object> context) {
    // 옵저버가 작업한 결과를 맵에서 꺼낸다.
    List<Member> memberList = (List<Member>) context.get("memberList");
    List<Fortune> fortuneList = (List<Fortune>) context.get("fortuneList");


    MemberListCommand memberListCommand = new MemberListCommand(memberList);
    context.put("/member/add", new MemberAddCommand(memberList));
    context.put("/member/list", memberListCommand);
    context.put("/member/detail", new MemberDetailCommand(memberList));
    context.put("/member/update", new MemberUpdateCommand(memberList));
    context.put("/member/delete", new MemberDeleteCommand(memberList));
    context.put("/fortune/add", new FortuneAddCommand(fortuneList));
    context.put("/fortune/res", new FortuneResponseCommand(fortuneList));
    context.put("/hello", new HelloCommand());
    context.put("/login", new MemberLoginCommand(memberList));
    context.put("/join", new MemberJoinCommand(memberList));
    context.put("/command", new CommandListCommand());
  }

  @Override
  public void contextDestroyed(Map<String,Object> context) {
  }
}
