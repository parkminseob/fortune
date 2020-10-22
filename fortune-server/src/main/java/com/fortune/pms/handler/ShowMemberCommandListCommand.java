package com.fortune.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class ShowMemberCommandListCommand implements Command {
  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    out.println("/fortune/res : 오늘의 운세 보기");
    out.println("/board/add : 게시글 추가하기");
    out.println("/board/detail : 게시글 상세보기");
    out.println("/board/update : 게시글 수정하기");
    out.println("/board/delete : 게시글 삭제하기");
    out.println("/board/list : 게시글 리스트 보기");
    out.println("/member/detail : 회원 상세조회");
    out.println("/member/update : 회원 정보수정");
    out.println("/member/delete : 회원 탈퇴");
    out.println("quit : 앱 종료");
  }
}
