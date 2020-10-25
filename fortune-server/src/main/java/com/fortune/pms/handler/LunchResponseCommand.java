package com.fortune.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import com.fortune.pms.domain.Lunch;
import com.fortune.pms.domain.Member;

public class LunchResponseCommand implements Command{
  List<Lunch> lunchList;
  public LunchResponseCommand(List<Lunch> list) {
    this.lunchList = list;
  }
  @Override
  public void execute(PrintWriter out, BufferedReader in, Member loggedInmember) {

    out.println("\t\t|￣￣￣￣￣￣￣￣￣￣￣￣￣|");
    out.println("\t\t|     오늘의 점심 추천     |");
    out.println("\t\t|＿＿＿＿＿＿＿＿＿＿＿＿＿| ");
    out.println("\t\t(\\__/) || ");
    out.println("\t\t(oㅅo).|| ");
    out.println("\t\t/ . . . .づ");

    int index = (int) (Math.random() * lunchList.size());
    String response = lunchList.get(index).getLunch();
    try {
      Thread.sleep(1000);
    }catch (Exception e) {}
    out.println("\t\t오늘의 점심은 ☆ " + response + "☆  !");
    out.println("                                     ");
  }
}