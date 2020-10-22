package com.fortune.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import com.fortune.pms.domain.Lunch;
import com.fortune.util.Prompt;

public class LunchAddCommand implements Command {
  List<Lunch> lunchList;
  public LunchAddCommand(List<Lunch> list) {
    this.lunchList = list;
  }
  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    System.out.printf("점심을 등록합니다");

    Lunch lunch = new Lunch();
    try {
      lunch.setLunch(Prompt.inputString("입력", out, in));
    } catch (Exception e) {

      System.out.println("운세 등록 오류");
    }

    lunchList.add(lunch);

  }
}
