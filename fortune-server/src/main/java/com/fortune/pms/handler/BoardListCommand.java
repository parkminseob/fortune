package com.fortune.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import com.fortune.pms.domain.Board;
import com.fortune.pms.domain.Member;

public class BoardListCommand implements Command {

  List<Board> boardList;

  public BoardListCommand(List<Board> list) {
    this.boardList = list;
  }


  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    out.println("[게시물 목록]");
    Member member = MemberLoginCommand.returnmember();

    // 전체 목록을 조회할 때 `Iterator` 객체를 사용한다.
    // 만약 목록의 일부만 조회하면다면 인덱스를 직접 다루는 이전 방식을 사용해야 한다.
    Iterator<Board> iterator = boardList.iterator();

    while (iterator.hasNext()) {
      Board board = iterator.next();
      out.printf("%d, %s, %s, %s, %s, %d\n",
          board.getNo(),
          board.getTitle(),
          board.getWriter(),
          member.getcheckMemberGrade(),
          board.getRegisteredDate(),
          board.getViewCount());
    }
  }

}
