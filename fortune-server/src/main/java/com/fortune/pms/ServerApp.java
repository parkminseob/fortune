package com.fortune.pms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import com.fortune.context.ApplicationContextListener;
import com.fortune.pms.domain.Member;
import com.fortune.pms.handler.Command;
import com.fortune.pms.listener.AppInitListener;
import com.fortune.pms.listener.DataHandlerListener;
import com.fortune.pms.listener.RequestMappingListener;

public class ServerApp {
  static Member defaultMember = new Member("default", "default", "default", "default", 0);

  // 클라이언트가 "stop" 명령을 보내면 이 값이 true로 변경된다.
  // - 이 값이 true 이면 다음 클라이언트 접속할 때 서버를 종료한다.
  static boolean stop = false;

  ExecutorService pool = Executors.newCachedThreadPool();
  // 옵저버와 공유할 맵 객체
  static Map<String,Object> context = new Hashtable<>();

  // 옵저버를 보관할 컬렉션 객체
  List<ApplicationContextListener> listeners = new ArrayList<>();

  // 옵저버를 등록하는 메서드
  public void addApplicationContextListener(ApplicationContextListener listener) {
    listeners.add(listener);
  }

  // 옵저버를 제거하는 메서드
  public void removeApplicationContextListener(ApplicationContextListener listener) {
    listeners.remove(listener);
  }

  // 옵저버에게 통지한다.
  private void notifyApplicationContextListenerOnServiceStarted() {
    for (ApplicationContextListener listener : listeners) {
      listener.contextInitialized(context);
    }
  }

  // 옵저버에게 통지한다.
  private void notifyApplicationContextListenerOnServiceStopped() {
    for (ApplicationContextListener listener : listeners) {
      listener.contextDestroyed(context);
    }
  }

  public void service(int port) {

    notifyApplicationContextListenerOnServiceStarted();

    try (ServerSocket serverSocket = new ServerSocket(port)) {
      System.out.println("서버 실행 중...");

      while (true) {
        Socket clientSocket = serverSocket.accept();

        if (stop) {
          break;
        }

        // 람다 문법 사용
        pool.execute(() -> handleClient(clientSocket));
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

    notifyApplicationContextListenerOnServiceStopped();

    pool.shutdown();
    try {
      if (!pool.awaitTermination(10,TimeUnit.SECONDS)) {
        System.out.println("아직 종료 안된 작업이 있다.");
        System.out.println("남아있는 작업의 강제 종료를 시도하겠다.");
        pool.shutdownNow();
        if (!pool.awaitTermination(10,TimeUnit.SECONDS)) {
          System.out.println("스레드풀의 강제 종료를 완료하지 못했다.");
        } else {
          System.out.println("모든 작업을 강제 종료했다.");
        }
      }
    } catch (Exception e) {
    }
  }

  public static void main(String[] args) {
    ServerApp server = new ServerApp();

    // 리스너(옵저버/구독자) 등록
    server.addApplicationContextListener(new AppInitListener());
    server.addApplicationContextListener(new DataHandlerListener());
    server.addApplicationContextListener(new RequestMappingListener());

    server.service(8888);
  }

  private static void handleClient(Socket clientSocket) {
    InetAddress address = clientSocket.getInetAddress();
    System.out.printf("클라이언트(%s)가 연결되었습니다.\n",
        address.getHostAddress());

    try (Socket socket = clientSocket; // try 블록을 떠날 때 close()가 자동 호출된다.
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream())) {

      // 클라이언트가 보낸 요청을 읽는다.
      String fullRequest = in.readLine();
      String[] requests = fullRequest.split(",");
      String clientId = requests[0];
      String request = requests[1];

      if (request.equalsIgnoreCase("stop")) {
        stop = true; // 서버의 상태를 멈추라는 의미로 true로 설정한다.
        out.println("\t\t서버를 종료하는 중입니다!");
        out.println();
        out.flush();
        return;
      }

      Member currentMember = defaultMember;
      if(clientId.equals("!@!")) {
        System.out.println("[비회원 입니다.]");

      } else {
        System.out.println("[현재 로그인된 Id : " + clientId + "]");
        currentMember = FindClientId(clientId);
      }
      System.out.println("[클라이언트 요청 메시지 : " + request + "]");

      switch (request) {
        case "1": request = "/fortune/res"; break;
        case "2": request = "/lunch/res"; break;
        case "3": request = "/fortune/list"; break;
        case "4": request = "/member/detail"; break;
        case "5": request = "/member/update"; break;
        case "6": request = "/member/delete"; break;


        case "a": request = "/fortune/add"; break;
        case "b": request = "/member/list"; break;
        case "c": request = "/member/grade"; break;
        case "d": request = "/lunch/add"; break;
      }

      Command command = (Command) context.get(request);
      if (command != null) {
        command.execute(out, in, currentMember);
      } else {
        out.println("해당 명령을 처리할 수 없습니다!");
      }

      // 응답의 끝을 알리는 빈 문자열을 보낸다.
      out.println();
      out.flush();


    } catch (Exception e) {
      System.out.println("클라이언트와의 통신 오류!");
    }

    System.out.printf("클라이언트(%s)와의 연결을 끊었습니다.\n",
        address.getHostAddress());
  }

  @SuppressWarnings("unchecked")
  private static Member FindClientId(String clientId) {
    List<Member> list = (List<Member>) context.get("memberList");
    for(Member member : list) {
      if(member.getId().equals(clientId)) {
        return member;
      }
    }
    return null;
  }
}

