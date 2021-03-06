package com.fortune;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import com.fortune.util.Prompt;

public class ClientApp {

  static String clientId = "!@!";
  static String host;
  static int port;

  public static void main(String[] args) {
    if (args.length != 2) {
      System.out.println("프로그램 사용법:");
      System.out.println("  java -cp ... ClientApp 서버주소 포트번호");
      System.exit(0);
    }

    host = args[0];
    port = Integer.parseInt(args[1]);
    System.out.println("                          ");
    System.out.println("                          ");
    System.out.println("\t\t로그인이나 회원가입을 해주세요. 제발 부탁드립니다.");
    System.out.println("                          ");
    System.out.println("\t\t로그인 : /login , 회원가입 : /join");
    System.out.println("                          ");
    System.out.println("                          ");


    while (true) {
      String[] arr = new String[5];
      arr[0] = "\t\t__        __   _";
      arr[1] = "\t\t\\ \\      / /__| | ___ ___  _ __ ___   ___ ";
      arr[2] = "\t\t \\ \\ /\\ / / _ \\ |/ __/ _ \\|  _   _ \\ / _ \\";
      arr[3] = "\t\t  \\ V  V /  __/ | (_| (_) | | | | | |  __/";
      arr[4] = "\t\t   \\_/\\_/ \\___|_|\\___\\___/|_| |_| |_|\\___|";
      for (int i = 0; i < arr.length; i++) {
        System.out.println(arr[i]);
      }
      String[] arr2 = new String[10];
      arr2[0] = "\t\t----------------------------------------------------";
      arr2[1] = "\t\t\t\t☆ ☆ ☆ 땅    콩 ☆ ☆ ☆ ";
      arr2[2] = "\t\t-------------------------------------관리자 메뉴---";
      arr2[3] = "\t\t   1. 오늘의 운세! \t\t|    a. 운세 추가하기 ";
      arr2[4] = "\t\t   2. 점심 뭐먹지? \t\t|    b. 회원 리스트  ";
      arr2[5] = "\t\t   3. 날 위로해줬던,, \t\t|    c. 회원 관리 ";
      arr2[6] = "\t\t   4. 내 정보\t\t \t|    d. 점심 메뉴 추가 ";
      arr2[7] = "\t\t   5. 내 정보 바꾸기\t\t|    quit. 나가기 ";
      arr2[8] = "\t\t   6. 날 지우기,, \t\t|    ";
      arr2[9] = "\t\t----------------------------------------------------";
      for (int i = 0; i < arr2.length; i++) {
        System.out.println(arr2[i]);
        System.out.println();
      }
      System.out.println("\t\t현재 로그인 된 아이디 : " + clientId);
      String input = Prompt.inputString("\t\t 명령을 선택하세요 => ");
      System.out.println("                          ");

      if (input.equalsIgnoreCase("quit"))
        break;

      request(input);

      if (input.equalsIgnoreCase("stop"))
        break;
    }
    System.out.println("\t\t안녕!");
  }

  private static void request(String message) {
    // 클라이언트가 서버에 stop 명령을 보내면 다음 변수를 true로 변경한다.
    boolean stop = false;

    try (Socket socket = new Socket(host, port);
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

      // 메시지 이전 줄에 아이디 보내기
      // ClientId가 true면 로그아웃된 상태.
      // false면 로그인 된 상태.
      out.println(clientId + "," + message);
      out.flush();

      receiveResponse(out, in);

      if (message.equalsIgnoreCase("stop")) {
        stop = true;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    if (stop) {
      // 서버를 멈추기 위해 그냥 접속했다가 끊는다.
      try (Socket socket = new Socket(host, port)) {
        // 아무것도 안한다.
        // 서버가 stop 할 기회를 주기 위함이다.
      } catch (Exception e) {
        // 아무것도 안한다.
      }
    }
  }

  private static void receiveResponse(PrintWriter out, BufferedReader in) throws Exception {
    while (true) {
      String response = in.readLine();
      if (response.length() == 0) {
        break;
      } else if (response.length() > 3 && response.substring(0, 3).equals("!@!")) {

        clientId = response.substring(3, response.length());

      } else if (response.equals("!{}!")) {
        // 사용자로부터 값을 입력을 받아서 서버에 보낸다.
        out.println(Prompt.inputString(""));
        out.flush(); // 주의! 출력하면 버퍼에 쌓인다. 서버로 보내고 싶다면 flush()를 호출하라!
      } else {
        System.out.println(response);
      }
    }
  }
}
