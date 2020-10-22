package com.fortune.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import com.fortune.pms.domain.Member;

public class FortuneListCommand implements Command{
  Member member;

  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    out.println("                   ");
    out.println("\t\t|￣￣￣￣￣￣￣￣￣￣￣￣￣|");
    out.println("\t\t|  날 위로해 주었던 글귀   |");
    out.println("\t\t|＿＿＿＿＿＿＿＿＿＿＿＿＿| ");
    out.println("\t\t(\\__/) || ");
    out.println("\t\t(oㅅo).|| ");
    out.println("\t\t/ . . . .づ");
    out.println("                   ");

    this.member = MemberLoginCommand.returnmember();
    for (int i = 0; i < member.favoriteFortuneList.size(); i++) {
      out.println("\t\t" + i+1 +". "+member.favoriteFortuneList.get(i));
    }
    out.println("                   ");
  }
}
