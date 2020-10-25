package com.fortune.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import com.fortune.pms.domain.Member;

public class MemberListCommand implements Command {

  List<Member> memberList;

  public MemberListCommand(List<Member> list) {
    this.memberList = list;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in, Member loggedInmember) {
    out.println("\t\t[회원 목록]");
    if (!loggedInmember.getId().equals("admin")) {
      out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t.");
      out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t.");
      out.println("\t\t!!!!관리자만 사용할 수 있습니다!!!!");
      out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t.");
      out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t.");
      return;
    }

    // 전체 목록을 조회할 때 `Iterator` 객체를 사용한다.
    // 만약 목록의 일부만 조회하면다면 인덱스를 직접 다루는 이전 방식을 사용해야 한다.
    Iterator<Member> iterator = memberList.iterator();

    while (iterator.hasNext()) {
      Member member = iterator.next();
      out.printf("\t\t%s, %s, %d, %s, %s\n",
          member.getName(),
          member.getId(),
          member.getAge(),
          member.getGender(),
          member.getcheckMemberGrade());
    }
  }

  public Member findByName(String name) {
    for (int i = 0; i < memberList.size(); i++) {
      Member member = memberList.get(i);
      if (member.getName().equals(name)) {
        return member;
      }
    }
    return null;
  }
}
