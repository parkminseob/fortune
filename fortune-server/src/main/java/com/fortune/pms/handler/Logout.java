package com.fortune.pms.handler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import com.google.gson.Gson;

public class Logout implements Command {
@Override
public void execute(PrintWriter out, BufferedReader in) {
	// TODO Auto-generated method stub
	
	
	saveData(memeberList, memberFile);
	
}
private void saveData(Collection<?> list, File file) {
=======
  @Override
  public void execute(PrintWriter out, BufferedReader in) {

  }
  private void saveData(Collection<?> list, File file) {
>>>>>>> branch 'main' of https://github.com/sejunO/fortune.git
    BufferedWriter out = null;

    try {
      out = new BufferedWriter(new FileWriter(file));

      Gson gson = new Gson();
      String jsonStr = gson.toJson(list);
      out.write(jsonStr);

      out.flush();

      System.out.printf("총 %d 개의 객체를 '%s' 파일에 저장했습니다.\n",
          list.size(), file.getName());

    } catch (IOException e) {
      System.out.printf("객체를 '%s' 파일에  쓰는 중 오류 발생! - %s\n",
          file.getName(), e.getMessage());

    } finally {
      try {
        out.close();
      } catch (IOException e) {
      }
    }
  }
}
