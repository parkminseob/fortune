package com.fortune.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import com.fortune.pms.domain.Member;

public class FortuneListCommand implements Command{
  Member member;

  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    this.member = MemberLoginCommand.returnmember();
    for (int i = 0; i< member.favoriteFortuneList.size(); i++) {
      out.println(member.favoriteFortuneList.get(i));
    }
  }
}
