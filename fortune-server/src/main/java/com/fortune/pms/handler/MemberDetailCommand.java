package com.fortune.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import com.fortune.pms.domain.Member;
import com.fortune.util.Prompt;

public class MemberDetailCommand implements Command {

  List<Member> memberList;

  public MemberDetailCommand(List<Member> list) {
    this.memberList = list;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    try {
      out.println("[회원 상세보기]");
      String Id = Prompt.inputString("Id? ", out, in);
      Member member = FindId(Id);

      if (member == null) {
        out.println("해당 Id의 회원이 없습니다.");
        return;
      }

      out.printf("이름: %s\n", member.getName());
      out.printf("아이디: %s\n", member.getId());
      out.printf("등록일: %s\n", member.getRegisteredDate());

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
  //  private Member findByNo(int no) {
  //    for (int i = 0; i < memberList.size(); i++) {
  //      Member member = memberList.get(i);
  //      if (member.getNo() == no) {
  //        return member;
  //      }
  //    }
  //    return null;
  //
  //  }
}
