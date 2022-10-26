package practice.lab7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class Test {

  public static void main(String[] args) throws RunnerException {
    Options options = new OptionsBuilder()
        .include(Test.class.getSimpleName())
        .measurementIterations(3)
        .warmupIterations(1)
        .mode(Mode.AverageTime)
        .forks(1)
        .shouldDoGC(true)
        .build();
    new Runner(options).run();
  }

  /*
  public static void main(String[] args) {
      Account account = new Account();
      ExecutorService service = Executors.newFixedThreadPool(100);

      for (int i = 1; i <= 100; i++) {
          service.execute(new DepositThread(account, 10));
      }

      service.shutdown();

      while (!service.isTerminated()) {
      }

      System.out.println("Balance: " + account.getBalance());
  }
   */
  @Benchmark
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public void testAccountLock() {
    Account account = new Account();
    ExecutorService service = Executors.newFixedThreadPool(100);

    for (int i = 1; i <= 100; i++) {
      service.execute(new DepositThread(account, 10, true));
    }

    service.shutdown();

    while (!service.isTerminated()) {
    }

    System.out.println("Balance: " + account.getBalance());
  }

  @Benchmark
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public void testAccountSync() {
    Account account = new Account();
    ExecutorService service = Executors.newFixedThreadPool(100);

    for (int i = 1; i <= 100; i++) {
      service.execute(new DepositThread(account, 10, false));
    }

    service.shutdown();

    while (!service.isTerminated()) {
    }

    System.out.println("Balance: " + account.getBalance());
  }
}
