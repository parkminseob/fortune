package com.fortune.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import com.fortune.pms.domain.Fortune;
import com.fortune.pms.domain.Member;
import com.fortune.util.Prompt;

public class FortuneResponseCommand implements Command{
  List<Fortune> fortuneList = new ArrayList<>();
  List<Member> memberList = new ArrayList<>();
  Stack<String> stack = new Stack<>();
  public FortuneResponseCommand(List<Fortune> fortuneList, List<Member> memberList) {
    this.fortuneList = fortuneList;
    this.memberList = memberList;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    out.println("                   ");
    out.println("\t\t|￣￣￣￣￣￣￣￣￣￣￣￣￣|");
    out.println("\t\t|        오늘의 운세       |");
    out.println("\t\t|＿＿＿＿＿＿＿＿＿＿＿＿＿| ");
    out.println("\t\t(\\__/) || ");
    out.println("\t\t(oㅅo).|| ");
    out.println("\t\t/ . . . .づ");
    out.println("                ");

    Member member = MemberLoginCommand.returnmember();
    try {
      Thread.sleep(1000);
      out.printf("\t\t%s님의 운세는....\n", member.getId());
    } catch (Exception e) {
      e.printStackTrace();
    }


    int index = (int) (Math.random() * fortuneList.size());
    String response = fortuneList.get(index).getFortune();

    out.printf("\t\t%s님! %s\n",member.getId(), response);
    member.fortuneList.add(response);
    try {
      out.println("\t\t그냥 나가시려면 엔터를 입력하세요.");
      String favorite = Prompt.inputString("\t\t운세가 마음에 들었다면? ===> good", out, in);

      if (favorite.equals("good") && !member.favoriteFortuneList.contains(response)) {
        member.favoriteFortuneList.add(response);
      }
    } catch (Exception e) {
      e.printStackTrace();
      out.println("\t\t운세 좋아요 표시중 오류!");
    }
  }
}

//
//|￣￣￣￣￣￣￣|
//|      메시지        |
//|＿＿＿＿＿＿＿|
//(\__/) ||
//(oㅅo).||
/// . . . .づ
//
