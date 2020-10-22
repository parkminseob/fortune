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
import com.fortune.pms.handler.Command;
import com.fortune.pms.listener.AppInitListener;
import com.fortune.pms.listener.DataHandlerListener;
import com.fortune.pms.listener.RequestMappingListener;

public class ServerApp {

  // �겢�씪�씠�뼵�듃媛� "stop" 紐낅졊�쓣 蹂대궡硫� �씠 媛믪씠 true濡� 蹂�寃쎈맂�떎.
  // - �씠 媛믪씠 true �씠硫� �떎�쓬 �겢�씪�씠�뼵�듃 �젒�냽�븷 �븣 �꽌踰꾨�� 醫낅즺�븳�떎.
  static boolean stop = false;

  ExecutorService pool = Executors.newCachedThreadPool();
  // �샃��踰꾩� 怨듭쑀�븷 留� 媛앹껜
  static Map<String,Object> context = new Hashtable<>();

  // �샃��踰꾨�� 蹂닿��븷 而щ젆�뀡 媛앹껜
  List<ApplicationContextListener> listeners = new ArrayList<>();

  // �샃��踰꾨�� �벑濡앺븯�뒗 硫붿꽌�뱶
  public void addApplicationContextListener(ApplicationContextListener listener) {
    listeners.add(listener);
  }

  // �샃��踰꾨�� �젣嫄고븯�뒗 硫붿꽌�뱶
  public void removeApplicationContextListener(ApplicationContextListener listener) {
    listeners.remove(listener);
  }

  // �샃��踰꾩뿉寃� �넻吏��븳�떎.
  private void notifyApplicationContextListenerOnServiceStarted() {
    for (ApplicationContextListener listener : listeners) {
      listener.contextInitialized(context);
    }
  }

  // �샃��踰꾩뿉寃� �넻吏��븳�떎.
  private void notifyApplicationContextListenerOnServiceStopped() {
    for (ApplicationContextListener listener : listeners) {
      listener.contextDestroyed(context);
    }
  }

  public void service(int port) {

    notifyApplicationContextListenerOnServiceStarted();

    try (ServerSocket serverSocket = new ServerSocket(port)) {
      System.out.println("�꽌踰� �떎�뻾 以�...");

      while (true) {
        Socket clientSocket = serverSocket.accept();

        if (stop) {
          break;
        }
        // �엺�떎 臾몃쾿 �궗�슜
        pool.execute(() -> handleClient(clientSocket));
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

    notifyApplicationContextListenerOnServiceStopped();

    pool.shutdown();
    try {
      if (!pool.awaitTermination(10,TimeUnit.SECONDS)) {
        System.out.println("�븘吏� 醫낅즺 �븞�맂 �옉�뾽�씠 �엳�떎.");
        System.out.println("�궓�븘�엳�뒗 �옉�뾾�쓽 媛뺤젣 醫낅즺瑜� �떆�룄�븯寃좊떎.");
        pool.shutdownNow();
        if (!pool.awaitTermination(10,TimeUnit.SECONDS)) {
          System.out.println("�뒪�젅�뱶���쓽 媛뺤젣 醫낅즺瑜� �셿猷뚰븯吏� 紐삵뻽�떎.");
        } else {
          System.out.println("紐⑤뱺 �옉�뾽�쓣 媛뺤젣 醫낅즺�뻽�떎.");
        }
      }
    } catch (Exception e) {
    }
  }

  public static void main(String[] args) {
    ServerApp server = new ServerApp();

    // 由ъ뒪�꼫(�샃��踰�/援щ룆�옄) �벑濡�
    server.addApplicationContextListener(new AppInitListener());
    server.addApplicationContextListener(new DataHandlerListener());
    server.addApplicationContextListener(new RequestMappingListener());

    server.service(8888);
  }

  private static void handleClient(Socket clientSocket) {
    InetAddress address = clientSocket.getInetAddress();
    System.out.printf("�겢�씪�씠�뼵�듃(%s)媛� �뿰寃곕릺�뿀�뒿�땲�떎.\n",
        address.getHostAddress());

    try (Socket socket = clientSocket; // try 釉붾줉�쓣 �뼚�궇 �븣 close()媛� �옄�룞 �샇異쒕맂�떎.
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream())) {

      // �겢�씪�씠�뼵�듃媛� 蹂대궦 �슂泥��쓣 �씫�뒗�떎.
      String request = in.readLine();

      if (request.equalsIgnoreCase("stop")) {
        stop = true; // �꽌踰꾩쓽 �긽�깭瑜� 硫덉텛�씪�뒗 �쓽誘몃줈 true濡� �꽕�젙�븳�떎.
        out.println("�꽌踰꾨�� 醫낅즺�븯�뒗 以묒엯�땲�떎!");
        out.println();
        out.flush();
        return;
      }

      Command command = (Command) context.get(request);
      if (command != null) {
        command.execute(out, in);
      } else {
        out.println("�빐�떦 紐낅졊�쓣 泥섎━�븷 �닔 �뾾�뒿�땲�떎!");
      }

      // �쓳�떟�쓽 �걹�쓣 �븣由щ뒗 鍮� 臾몄옄�뿴�쓣 蹂대궦�떎.
      out.println();
      out.flush();


    } catch (Exception e) {
      System.out.println("�겢�씪�씠�뼵�듃���쓽 �넻�떊 �삤瑜�!");
    }

    System.out.printf("�겢�씪�씠�뼵�듃(%s)���쓽 �뿰寃곗쓣 �걡�뿀�뒿�땲�떎.\n",
        address.getHostAddress());
  }
}