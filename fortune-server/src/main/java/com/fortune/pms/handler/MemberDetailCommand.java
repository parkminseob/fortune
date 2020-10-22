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
      out.println("                   ");
      out.println("\t\t|￣￣￣￣￣￣￣￣￣￣￣￣￣|");
      out.println("\t\t|       내 정보 보기       |");
      out.println("\t\t|＿＿＿＿＿＿＿＿＿＿＿＿＿| ");
      out.println("\t\t(\\__/) || ");
      out.println("\t\t(oㅅo).|| ");
      out.println("\t\t/ . . . .づ");
      out.println("                   ");

      String Id = Prompt.inputString("\t\tId: ", out, in);
      Member member = FindId(Id);

      if (member == null) {
        out.println("\t\t해당 Id의 회원이 없습니다.");
        return;
      }

      out.printf("\t\t이름: %s\n", member.getName());
      out.printf("\t\t아이디: %s\n", member.getId());
      out.printf("\t\t성별: %s\n", member.getGender());
      out.printf("\t\t나이: %d\n", member.getAge());
      out.printf("\t\t회원등급: %s\n", member.getcheckMemberGrade());

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
  //
  //  }
}
