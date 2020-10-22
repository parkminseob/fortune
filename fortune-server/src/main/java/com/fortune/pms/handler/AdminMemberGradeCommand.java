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

      out.println("[불량회원 관리]");
      String Id = Prompt.inputString("회원 Id : ");
      Member member = FindId(Id);

      if (Id == null) {
        out.println("해당 번호의 회원이 없습니다.");
        return;
      }

      out.println("회원 등급 설정");
      int grade = Prompt.inputInt("1.우수회원 / 2.불량회원");

      switch(grade) {
        case 1 : "[불량 회원]"; break;
      }

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
