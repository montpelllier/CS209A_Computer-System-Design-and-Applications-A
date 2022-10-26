package practice.lab7;


public class DepositThread implements Runnable {

  private final Account account;
  private final double money;

  private final boolean isLock;

  public DepositThread(Account account, double money, boolean isLock) {
    this.account = account;
    this.money = money;
    this.isLock = isLock;
  }

  @Override
  public void run() {
    if (isLock) {
      account.depositLock(money);
    } else {
      account.depositSync(money);
    }
  }
}
