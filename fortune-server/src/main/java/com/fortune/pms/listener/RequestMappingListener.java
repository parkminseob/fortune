package com.fortune.pms.listener;

import java.util.List;
import java.util.Map;
import com.fortune.context.ApplicationContextListener;
import com.fortune.pms.domain.Board;
import com.fortune.pms.domain.Fortune;
import com.fortune.pms.domain.Lunch;
import com.fortune.pms.domain.Member;
import com.fortune.pms.handler.BoardAddCommand;
import com.fortune.pms.handler.BoardDeleteCommand;
import com.fortune.pms.handler.BoardDetailCommand;
import com.fortune.pms.handler.BoardListCommand;
import com.fortune.pms.handler.BoardUpdateCommand;
import com.fortune.pms.handler.CommandListCommand;
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
import com.fortune.pms.handler.MemberLogoutCommand;
import com.fortune.pms.handler.MemberUpdateCommand;

// 클라이언트 요청을 처리할 커맨드 객체를 준비한다.
public class RequestMappingListener implements ApplicationContextListener {

  @SuppressWarnings("unchecked")
  @Override
  public void contextInitialized(Map<String,Object> context) {
    // 옵저버가 작업한 결과를 맵에서 꺼낸다.
    List<Member> memberList = (List<Member>) context.get("memberList");
    List<Fortune> fortuneList = (List<Fortune>) context.get("fortuneList");
    List<Board> boardList = (List<Board>) context.get("boardList");
    List<Lunch> lunchList = (List<Lunch>) context.get("lunchList");

    MemberListCommand memberListCommand = new MemberListCommand(memberList);
    context.put("/board/add", new BoardAddCommand(boardList));
    context.put("/board/list", new BoardListCommand(boardList));
    context.put("/board/detail", new BoardDetailCommand(boardList));
    context.put("/board/update", new BoardUpdateCommand(boardList));
    context.put("/board/delete", new BoardDeleteCommand(boardList));
    context.put("/member/list", memberListCommand);
    context.put("/member/detail", new MemberDetailCommand(memberList));
    context.put("/member/update", new MemberUpdateCommand(memberList));
    context.put("/member/delete", new MemberDeleteCommand(memberList));
    context.put("/fortune/add", new FortuneAddCommand(fortuneList));
    context.put("/fortune/res", new FortuneResponseCommand(fortuneList, memberList));
    context.put("/fortune/list", new FortuneListCommand());
    context.put("/login", new MemberLoginCommand(memberList));
    context.put("/join", new MemberJoinCommand(memberList));
    context.put("/logout", new MemberLogoutCommand(memberList));
    context.put("/command", new CommandListCommand());
    context.put("/lunch/add", new LunchAddCommand(lunchList));
    context.put("/lunch/res", new LunchResponseCommand(lunchList));
  }

  @Override
  public void contextDestroyed(Map<String,Object> context) {
  }
}
