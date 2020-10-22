package com.fortune.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import com.fortune.pms.domain.Member;
import com.fortune.util.Prompt;

public class MemberJoinCommand implements Command {

  List<Member> memberList;
  public MemberJoinCommand(List<Member> list) {
    this.memberList = list;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    try {
      out.println("[아이디에 빈 문자열을 입력하면 회원가입이 취소됩니다.]");
      String Id = Prompt.inputString("ID : ", out, in);
      if(Id.equals("")) {
        return;
      }
      String Password = Prompt.inputString("Password : ", out, in);
      String Password2 = Prompt.inputString("Password Confirm : ", out, in);
      String Name = Prompt.inputString("Name : ", out, in);
      //String gender = Prompt.inputString("Gender : (1.남자/2.여자)");


      if(TestId(Id)) {
        out.println("중복된 ID입니다.");
      } else if(Password.equals(Password2)) {
        memberList.add(new Member(Id, Password, Name));
        out.println("회원가입이 완료되었습니다.");
      } else {
        out.println("비밀번호를 다시 입력하시오.");
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

  public boolean TestId(String Id) {
    boolean check = true;
    Member member = FindId(Id);
    if (member == null)
      check = false;
    return check;
  }
}
