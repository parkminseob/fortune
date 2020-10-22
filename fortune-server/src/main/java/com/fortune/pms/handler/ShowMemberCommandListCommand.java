package com.fortune.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class ShowMemberCommandListCommand implements Command {
  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    out.println("/fortune/res : 오늘의 운세 보기");
    out.println("/member/detail : 회원 상세조회");
    out.println("/member/update : 회원 정보수정");
    out.println("/member/delete : 회원 탈퇴");
    out.println("quit : 앱 종료");
  }
}
