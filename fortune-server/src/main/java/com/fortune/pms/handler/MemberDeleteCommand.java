package com.fortune.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import com.fortune.pms.domain.Member;
import com.fortune.util.Prompt;

public class MemberDeleteCommand implements Command {

  List<Member> memberList;

  public MemberDeleteCommand(List<Member> list) {
    this.memberList = list;
  }
  @Override
  public void execute(PrintWriter out, BufferedReader in, Member loggedInmember) {

    try {
      out.println("                   ");
      out.println("\t\t|￣￣￣￣￣￣￣￣￣￣￣￣￣|");
      out.println("\t\t|        회원 탈퇴         |");
      out.println("\t\t|＿＿＿＿＿＿＿＿＿＿＿＿＿| ");
      out.println("\t\t(\\__/) || ");
      out.println("\t\t(oㅅo).|| ");
      out.println("\t\t/ . . . .づ");
      out.println("                   ");

      String id = Prompt.inputString("Id? : ", out, in);
      Member findIdmember = FindId(id);

      if(findIdmember.getId() == null) {
        out.println("해당 회원이 없습니다.");
        return;
      }

      String response = Prompt.inputString("\t\t정말 회원탈퇴를 하실건가요?(y/N) ", out, in);
      if (!response.equalsIgnoreCase("y")) {
        out.println("\t\t탁월한 선택입니다 !");
        return;
      }

      memberList.remove(findIdmember);
      out.println("\t\t여기서 기다릴게요,, 돌아오세요.");

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
  //  private int indexOf(int no) {
  //    for (int i = 0; i < memberList.size(); i++) {
  //      Member member = memberList.get(i);
  //      if (member.getNo() == no) {
  //        return i;
  //      }
  //    }
  //    return -1;
  //  }
}
