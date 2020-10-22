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
    Member member = MemberLoginCommand.returnmember();
    int index = (int) (Math.random() * fortuneList.size());
    String response = fortuneList.get(index).getFortune();
    out.printf(member.getId()+ "님! " + response);
    member.fortuneList.add(response);
    try {
      String favorite = Prompt.inputString("운세가 맘에 들면 다음에 또 보자 ===> good", out, in);
      if (favorite.equals("good") && !member.favoriteFortuneList.contains(response)) {
        member.favoriteFortuneList.add(response);
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("운세 좋아요 표시중 오류!");
    }

  }


}
