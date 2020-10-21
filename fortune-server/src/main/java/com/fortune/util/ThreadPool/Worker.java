package com.fortune.util.ThreadPool;

import java.util.ArrayList;
import java.util.List;

public class Worker extends Thread{

  List<Worker> workers = new ArrayList<>();
  Runnable task;
  public void setTask(Runnable task) {
    this.task = task;
    synchronized (this) {
      this.notify();
    }
  }

  @Override
  public void run() {
    while (true) {
      System.out.printf(" %s 대기중 \n", this.getName());
      try {
        synchronized (this) {
          this.wait();
        }
        System.out.printf(" %s 작업시 \n", this.getName());
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

      task.run();


      System.out.printf(" %s 작업완료 \n", this.getName());

      workers.add(this);

      System.out.printf(" %s 복귀 완료 \n", this.getName());

    }
  }

  public void execute(Runnable task) throws Exception {
    Worker t;
    if (workers.isEmpty()) {
      t = new Worker();
      t.start();
      Thread.sleep(200);
    } else {
      t = workers.remove(0);
      t.setTask(task);
    }
  }
}
