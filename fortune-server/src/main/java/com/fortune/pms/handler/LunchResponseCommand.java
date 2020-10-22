package com.fortune.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import com.fortune.pms.domain.Lunch;

public class LunchResponseCommand implements Command{
  List<Lunch> lunchList;
  public LunchResponseCommand(List<Lunch> list) {
    this.lunchList = list;
  }
  @Override
  public void execute(PrintWriter out, BufferedReader in) {

    out.println("_                          _     ");
    out.println("| |                        | |    ");
    out.println("| |     _   _  _ __    ___ | |__  ");
    out.println("| |    | | | || '_ \\  / __|| '_ \\ ");
    out.println("| |____| |_| || | | || (__ | | | |");
    out.println("\\_____/ \\__,_||_| |_| \\___||_| |_|");
    out.println("                                     ");

    int index = (int) (Math.random() * lunchList.size());
    String response = lunchList.get(index).getLunch();
    out.println("오늘의 점심은 " + response + "!!!");
  }
}

//
//_                          _
//| |                        | |
//| |     _   _  _ __    ___ | |__
//| |    | | | || '_ \  / __|| '_ \
//| |____| |_| || | | || (__ | | | |
//\_____/ \__,_||_| |_| \___||_| |_|
//
//
