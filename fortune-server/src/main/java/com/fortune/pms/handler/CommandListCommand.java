package com.fortune.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class CommandListCommand implements Command {
  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    out.println("/join : 회원가입");
    out.println("/login : 로그인");
    out.println("/member/list : 회원 리스트");
    out.println("/member/detail : 회원 상세조회");
    out.println("/member/update : 회원 정보수정");
    out.println("/member/delete : 회원 탈퇴");
    out.println("/fortune/add : 운세 등록");
    out.println("/fortune/res : 운세 보기");
    out.println("/hello : 헬로우!");
  }
}
