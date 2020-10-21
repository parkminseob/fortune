package com.fortune.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import com.fortune.pms.domain.Fortune;

public class FortuneResponseCommand implements Command{
  List<Fortune> fortuneList = new ArrayList<>();
  public FortuneResponseCommand(List<Fortune> fortuneList) {
    this.fortuneList = fortuneList;
  }
  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    int index = (int) Math.random() * fortuneList.size();
    String response = fortuneList.get(index).getFortune();
    out.println(response);
  }
}
