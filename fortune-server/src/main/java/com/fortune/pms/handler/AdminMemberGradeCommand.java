package com.fortune.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import com.fortune.pms.domain.Member;
import com.fortune.util.Prompt;

public class AdminMemberGradeCommand implements Command {
  List<Member> memberList;

  public AdminMemberGradeCommand(List<Member> list) {
    this.memberList = list;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    try {
      if (!MemberLoginCommand.loginStatus) {
        out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t.");
        out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t.");
        out.println("\t\t!!!!관리자만 사용할 수 있습니다!!!!");
        out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t.");
        out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t.");
        return;
      }

      out.println("[회원등급 관리]");
      String Id = Prompt.inputString("회원 Id : ",out, in);
      Member member = FindId(Id);

      if (Id == null) {
        out.println("해당 Id의 회원이 없습니다.");
        return;
      }

      out.println("회원 등급 설정");
      int grade = Prompt.inputInt("1.우수회원 / 2.불량회원", out, in);
      String userGrade = null;
      switch(grade) {
        case 1 : userGrade = "[우수 회원]"; break;
        case 2 : userGrade = "[불량 회원]"; break;
      }

      member.setcheckMemberGrader(userGrade);
    } catch (Exception e) {
      out.printf("작업 처리 중 오류 발생! - %s\n", e.getMessage());
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
}
