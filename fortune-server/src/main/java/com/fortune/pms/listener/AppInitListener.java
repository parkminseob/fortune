package com.fortune.pms.listener;

import java.util.Map;
import com.fortune.context.ApplicationContextListener;

public class AppInitListener implements ApplicationContextListener {
  @Override
  public void contextInitialized(Map<String,Object> context) {
    System.out.println("[** Fortune앱에 오신걸 환영합니다. **]");
  }

  @Override
  public void contextDestroyed(Map<String,Object> context) {
    System.out.println("[** Fortune앱을 종료합니다! **]");
  }
}
