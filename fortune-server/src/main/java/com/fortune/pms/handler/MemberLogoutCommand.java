package com.fortune.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import com.fortune.pms.domain.Member;

public class MemberLogoutCommand implements Command {

  List<Member> memberList;

  public MemberLogoutCommand(List<Member> list) {
    this.memberList = list;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in) {

  }
}
