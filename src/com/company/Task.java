package com.company;

import java.util.Queue;
import java.util.Random;

class Task {

  private final Queue<Integer> queue;

  public Task(Queue<Integer> queue) {
    this.queue = queue;
  }

  public void produce() {
    try {
      while (true) {
        synchronized (this) {
          Random random = new Random();
          int randomInt = random.ints(1, 10).findFirst().getAsInt();
          randomInt = randomInt > 0 ? randomInt : -randomInt;
          queue.add(randomInt);
          notify();
          Thread.sleep(randomInt * 100L);
        }
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void consume() {
    try {
      while (true) {
        synchronized (this) {
          while (queue.size() == 0) {
            wait();
          }
          System.out.println("Message form producer: " + queue.remove());
          notify();
        }
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
