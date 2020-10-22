package com.fortune.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class ShowMemberCommandListCommand implements Command {
  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    out.println("1 : 오늘의 운세 보기");
    out.println("2 : 게시글 추가하기");
    out.println("3 : 게시글 상세보기");
    out.println("4 : 게시글 수정하기");
    out.println("5 : 게시글 삭제하기");
    out.println("6 : 게시글 리스트 보기");
    out.println("7 : 회원 상세조회");
    out.println("8 : 회원 정보수정");
    out.println("9 : 회원 탈퇴");
    out.println("quit : 앱 종료");
  }
}
