package com.fortune.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import com.fortune.pms.domain.Member;
import com.fortune.util.Prompt;

public class MemberLoginCommand implements Command {
  List<Member> memberList;
  Member member;
  public MemberLoginCommand(List<Member> list) {
    this.memberList = list;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    try {
      out.println("[아이디에 빈 문자열을 입력하면 로그인이 취소됩니다.]");
      while (true) {
        String Id = Prompt.inputString("ID : ", out, in);

        if(Id.equals("")) {
          break;
        }
        this.member = FindId(Id);

        String Password = Prompt.inputString("Password : ", out, in);

        if (member == null) {
          out.println("입력하신 아이디가 일치하지 않습니다.");
          out.println("다시 입력하시오.");
        } else if(member.getId().equals(Id) && member.getPassword().equals(Password)) {

          if(Id.equals("admin")) {
            out.println("[관리자 계정으로 로그인 했습니다.]");
            break;
          }

          out.println(returnmember().getId());
          out.printf("%s님 안녕하세요!\n", Id);
          out.println("Fortune에 접속하였습니다.");
          break;
        } else {
          out.println("입력하신 패스워드가 일치하지 않습니다.");
          out.println("다시 입력하시오.");
        }
      }
    } catch (Exception e) {
      out.printf("작업 처리 중 오류 발생! - %s\n", e.getMessage());
    }
  }
  public Member returnmember() {
    return this.member;
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
