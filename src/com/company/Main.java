package com.company;

import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

  private static final Queue<Integer> queue = new ArrayDeque();

  public static void main(String[] args) throws InterruptedException {

    Task task = new Task(queue);

    Thread producer = new Thread(task::produce);
    Thread consumer = new Thread(task::consume);

    producer.start();
    consumer.start();

    producer.join();
    consumer.join();
  }
}
