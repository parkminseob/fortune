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
  public void execute(PrintWriter out, BufferedReader in, Member loggedIntmember) {
    try {
      out.println("[회원가입]");
      out.println("\t\t[아이디에 빈 문자열을 입력하면 회원가입이 취소됩니다.]");
      String Id = Prompt.inputString("\t\tID : ", out, in);
      if(Id.equals("")) {
        return;
      }
      String Password = Prompt.inputString("\t\tPassword : ", out, in);
      String Password2 = Prompt.inputString("\t\tPassword Confirm : ", out, in);
      String Name = Prompt.inputString("\t\tName : ", out, in);
      int response = Prompt.inputInt("\t\tGender : (1.남자/2.여자)", out, in);
      String gender = null;
      switch (response) {
        case 1 : gender = "남자"; break;
        case 2 : gender = "여자"; break;
      }
      int age = Prompt.inputInt("\t\tAge : ",out, in);

      if(TestId(Id)) {
        out.println("\t\t중복된 ID입니다.");
      } else if(Password.equals(Password2)) {
        Member member = new Member(Id, Password, Name, gender, age);
        member.setcheckMemberGrader("일반 회원");
        memberList.add(member);
        out.println("\t\t회원가입이 완료되었습니다.");
      } else {
        out.println("\t\t비밀번호를 다시 입력하시오.");
      }
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

  public boolean TestId(String Id) {
    boolean check = true;
    Member member = FindId(Id);
    if (member == null)
      check = false;
    return check;
  }
}
