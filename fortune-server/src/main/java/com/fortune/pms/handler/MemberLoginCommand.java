package com.fortune.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import com.fortune.pms.domain.Member;
import com.fortune.util.Prompt;

public class MemberLoginCommand implements Command {
  List<Member> memberList;
  static Member member;
  static boolean loginStatus = false;

  public MemberLoginCommand(List<Member> list) {
    this.memberList = list;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    try {
      out.println("\t\t[아이디에 빈 문자열을 입력하면 로그인이 취소됩니다.]");
      while (true) {
        String Id = Prompt.inputString("\t\tID : ", out, in);

        if(Id.equals("")) {
          break;
        }

        String Password = Prompt.inputString("\t\tPassword : ", out, in);
        member = FindId(Id);

        if (member == null) {
          out.println("\t\t입력하신 아이디가 일치하지 않습니다.");
          out.println("\t\t다시 입력하세요.");
        } else if(member.getId().equals(Id) && member.getPassword().equals(Password)) {

          if(Id.equals("admin")) {
            loginStatus = true;
            out.println("\t\t[관리자 계정으로 로그인 했습니다.]");
          } else {
            out.println("\t\tFortune에 [일반 회원]으로 접속하였습니다.");
            out.printf("\t\t[%s]님 안녕하세요!\n", returnmember().getId());
          }
          break;
        } else {
          out.println("\t\t입력하신 패스워드가 일치하지 않습니다.");
          out.println("\t\t다시 입력하세요.");
        }
      }
    } catch (Exception e) {
      out.printf("\t\t작업 처리 중 오류 발생! - %s\n", e.getMessage());
    }
  }

  public static boolean loginStatus() {
    return loginStatus;
  }

  public static Member returnmember() {
    return member;
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
