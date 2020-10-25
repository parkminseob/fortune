package com.fortune.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import com.fortune.pms.domain.Member;

public class MemberLogoutCommand implements Command {

  @Override
  public void execute(PrintWriter out, BufferedReader in, Member member) {
    out.println("!@!!@!");
    out.printf("\t\t%s님, 또 만나요!\n", member.getId());
    out.println();
    out.flush();
  }
}
