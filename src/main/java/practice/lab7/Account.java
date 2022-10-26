package practice.lab7;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {

  private double balance;

  private final Lock lock = new ReentrantLock();

  /**
   * @param money
   */
  public void depositLock(double money) {
    try {
      lock.lock();
      double newBalance = balance + money;
      try {
        Thread.sleep(10);   // Simulating this service takes some processing time
      } catch (InterruptedException ex) {
        ex.printStackTrace();
      }
      balance = newBalance;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      lock.unlock();
    }

  }

  public synchronized void depositSync(double money) {
    try {
      double newBalance = balance + money;
      try {
        Thread.sleep(10);   // Simulating this service takes some processing time
      } catch (InterruptedException ex) {
        ex.printStackTrace();
      }
      balance = newBalance;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  public double getBalance() {
    return balance;
  }
}