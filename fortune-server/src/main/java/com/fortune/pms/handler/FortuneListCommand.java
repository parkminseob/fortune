package com.fortune.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import com.fortune.pms.domain.Member;

public class FortuneListCommand implements Command{
  List<Member> memberList;

  public FortuneListCommand() {}

  public FortuneListCommand(List<Member> list) {
    this.memberList = list;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in, Member loggedInmember) {
    out.println("                   ");
    out.println("\t\t|￣￣￣￣￣￣￣￣￣￣￣￣￣|");
    out.println("\t\t|  날 위로해 주었던 글귀,, |");
    out.println("\t\t|＿＿＿＿＿＿＿＿＿＿＿＿＿| ");
    out.println("\t\t(\\__/) || ");
    out.println("\t\t(oㅅo).|| ");
    out.println("\t\t/ . . . .づ");
    out.println("                   ");

    for (int i = 1; i <= loggedInmember.favoriteFortuneList.size(); i++) {
      out.println("\t\t" + i +". "+loggedInmember.favoriteFortuneList.get(i-1));
    }
    out.println("                   ");
  }
}
