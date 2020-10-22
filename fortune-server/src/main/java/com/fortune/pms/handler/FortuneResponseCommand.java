package com.fortune.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import com.fortune.pms.domain.Fortune;
import com.fortune.pms.domain.Member;

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
    MemberLoginCommand mm = new MemberLoginCommand(memberList);
    Member member = mm.returnmember();
    int index = (int) (Math.random() * fortuneList.size());
    String response = fortuneList.get(index).getFortune();
    out.println(member.getName());
    out.println(response);
    stack.push(response);

  }


}
