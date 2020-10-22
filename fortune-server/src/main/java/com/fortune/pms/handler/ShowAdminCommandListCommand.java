package com.fortune.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class ShowAdminCommandListCommand implements Command {
  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    out.println("a : 오늘의 운세 추가");
    out.println("b : 오늘의 운세 보기");
    out.println("c : 회원 상세조회");
    out.println("d : 전체 회원리스트");
    out.println("e : 회원 등급 설정");
    out.println("f : 게시글 추가하기");
    out.println("g : 게시글 상세보기");
    out.println("h : 게시글 수정하기");
    out.println("i : 게시글 삭제하기");
    out.println("j : 게시글 리스트 보기");
    out.println("quit : 앱 종료");
    out.println("stop : 서버 종료");
  }
}
