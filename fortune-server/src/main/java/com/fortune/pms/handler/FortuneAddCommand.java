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
    System.out.printf("운세 등록합니다");

    Fortune fortune = new Fortune();
    try {
      fortune.setFortune(Prompt.inputString("입력", out, in));
    } catch (Exception e) {

      System.out.println("운세 등록 오류");
    }

    fortuneList.add(fortune);
  }
}
