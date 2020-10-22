package com.fortune.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import com.fortune.pms.domain.Fortune;
import com.fortune.util.Prompt;

public class FortuneAddCommand implements Command {
  List<Fortune> fortuneList = new ArrayList<>();
  public FortuneAddCommand(List<Fortune> fortuneList) {
    this.fortuneList = fortuneList;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    if (!MemberLoginCommand.loginStatus) {
      out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t.");
      out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t.");
      out.println("\t\t!!!!관리자만 사용할 수 있습니다!!!!");
      out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t.");
      out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t.");
      return;
    }
    out.println("\t\t[운세를 등록합니다]");

    Fortune fortune = new Fortune();
    try {
      fortune.setFortune(Prompt.inputString("\t\t운세 문구 입력> ", out, in));

      out.println("\t\t[운세 등록 완료!]");
    } catch (Exception e) {

      out.println("\t\t[운세 등록 오류]");
    }

    fortuneList.add(fortune);
  }
}
