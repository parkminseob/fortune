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

    out.println("\t\t|￣￣￣￣￣￣￣￣￣￣￣￣￣|");
    out.println("\t\t|     오늘의 점심 추천     |");
    out.println("\t\t|＿＿＿＿＿＿＿＿＿＿＿＿＿| ");
    out.println("\t\t(\\__/) || ");
    out.println("\t\t(oㅅo).|| ");
    out.println("\t\t/ . . . .づ");

    //    out.println("                                     ");
    //    out.println("\t\t_                          _                       |＼＿ /|");
    //    out.println("\t\t| |                        | |                     | o x o|");
    //    out.println("\t\t| |     _   _  _ __    ___ | |__      ＼＿＿＿＿＿／　　　|");
    //    out.println("\t\t| |    | | | || '_ \\  / __|| '_ \\       |　　 　　　　  　| ");
    //    out.println("\t\t| |____| |_| || | | || (__ | | | |       ＼　　　　     ノ　");
    //    out.println("\t\t\\_____/ \\__,_||_| |_| \\___||_| |_|    ( ( ( (/￣￣￣￣(/\\)");
    //    out.println("                                     ");
    //    out.println("                                     ");

    int index = (int) (Math.random() * lunchList.size());
    String response = lunchList.get(index).getLunch();
    try {
      Thread.sleep(1000);
    }catch (Exception e) {}
    out.println("\t\t오늘의 점심은 ☆ " + response + "☆  !");
    out.println("                                     ");
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
//             |＼＿ /|
//             | ・ x ・|
//＼＿＿＿＿＿／　　　|
//  |　　 　　　　  　|
//   ＼　　　　     ノ
//( ( ( (/￣￣￣￣(/ヽ)