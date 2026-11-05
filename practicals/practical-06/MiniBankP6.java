public class MiniBankP6 {
    public interface Transactable {
        void deposit(long amount);
        boolean withdraw(long amount);
    }

    public interface InterestBearing {
        double interestRate();
        default double yearlyInterest(long balance) {
            return (balance * interestRate()) / 100.0;
        }
    }

    @FunctionalInterface
    public interface WithdrawRule {
        boolean allow(String accountNo, long balance, long amount);
    }

    public interface Premium {} // Marker interface

    public static class SavingsAccount implements Transactable, InterestBearing, Premium {
        private final String accountNo;
        private long balance;

        public SavingsAccount(String accountNo, long balance) {
            this.accountNo = accountNo;
            this.balance = balance;
        }
        @Override public void deposit(long amount) { balance += amount; }
        @Override public boolean withdraw(long amount) {
            if (balance >= amount) { balance -= amount; return true; }
            return false;
        }
        @Override public double interestRate() { return 4.5; }
        public long getBalance() { return balance; }
    }

    public static void main(String[] args) {
        SavingsAccount sa = new SavingsAccount("SA9001", 100000);
        System.out.println("Yearly interest for SA9001: Rs. " + sa.yearlyInterest(sa.getBalance()));

        // WithdrawRule via Anonymous Class
        WithdrawRule highValueLimit = new WithdrawRule() {
            @Override public boolean allow(String acc, long bal, long amt) {
                return amt <= 50000;
            }
        };

        // WithdrawRule via Lambda
        WithdrawRule standardRule = (acc, bal, amt) -> bal >= amt && amt > 0;

        System.out.println("Withdraw 75000 allowed (Anonymous HighValueLimit): " + highValueLimit.allow("SA9001", 100000, 75000));
        System.out.println("Withdraw 75000 allowed (Lambda StandardRule): " + standardRule.allow("SA9001", 100000, 75000));
        System.out.println("Is Premium Account: " + (sa instanceof Premium));
    }
}
