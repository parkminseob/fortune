package com.fortune.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import com.fortune.pms.domain.Member;
import com.fortune.util.Prompt;

public class MemberUpdateCommand implements Command {

  List<Member> memberList;

  public MemberUpdateCommand(List<Member> list) {
    this.memberList = list;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    try {
      out.println("                   ");
      out.println("\t\t|￣￣￣￣￣￣￣￣￣￣￣￣￣|");
      out.println("\t\t|     내 정보 변경하기     |");
      out.println("\t\t|＿＿＿＿＿＿＿＿＿＿＿＿＿| ");
      out.println("\t\t(\\__/) || ");
      out.println("\t\t(oㅅo).|| ");
      out.println("\t\t/ . . . .づ");
      out.println("                   ");

      String Id = Prompt.inputString("\t\t회원 Id ", out, in);
      Member member = FindId(Id);

      if (member == null) {
        out.println("\t\t해당 Id의 회원이 없습니다.");
        return;
      }

      String name = Prompt.inputString(
          String.format("\t\t이름(%s)? ", member.getName()), out, in);
      String password = Prompt.inputString("\t\t암호? ", out, in);

      String response = Prompt.inputString("\t\t정말 변경하시겠습니까?(y/N) ", out, in);
      if (!response.equalsIgnoreCase("y")) {
        out.println("\t\t회원 변경을 취소하였습니다.");
        return;
      }

      member.setName(name);
      member.setPassword(password);

      out.println("\t\t회원을 변경하였습니다.");

    } catch (Exception e) {
      out.printf("\t\t작업 처리 중 오류 발생! - %s\n", e.getMessage());
    }
  }

  private Member FindId(String Id) {
    for(Member member : memberList) {
      if(member.getId().equals(Id)) {
        return member;
      }
    }
    return null;
  }

  //  private Member findByNo(int no) {
  //    for (int i = 0; i < memberList.size(); i++) {
  //      Member member = memberList.get(i);
  //      if (member.getNo() == no) {
  //        return member;
  //      }
  //    }
  //    return null;
  //  }
}
