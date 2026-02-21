import java.util.Scanner;

class UserAccount {
    double bal;

    UserAccount(double bal) {
        this.bal = bal;
    }
}

public class Task3 {
    UserAccount account;
    Scanner sc = new Scanner(System.in);

    Task3(UserAccount account) {
        this.account = account;
    }

    public void start() {
        int choice;
        do {
            System.out.println("\n1. Balance | 2. Deposit | 3. Withdraw | 4. Exit");
            System.out.print("Select: ");
            choice = sc.nextInt();

            if (choice == 1) {
                System.out.println("Balance: $" + account.bal);
            } else if (choice == 2) {
                System.out.print("Amount to deposit: ");
                double amt = sc.nextDouble();
                if (amt > 0) {
                    account.bal += amt;
                    System.out.println("Done.");
                } else {
                    System.out.println("Invalid amount.");
                }
            } else if (choice == 3) {
                System.out.print("Amount to withdraw: ");
                double amt = sc.nextDouble();
                if (amt > 0 && amt <= account.bal) {
                    account.bal -= amt;
                    System.out.println("Take your cash.");
                } else {
                    System.out.println("Insufficient funds or invalid amount.");
                }
            }
        } while (choice != 4);

        System.out.println("Bye!");
    }

    public static void main(String[] args) {
        UserAccount myAcc = new UserAccount(500.0);
        Task3 machine = new Task3(myAcc);
        machine.start();
    }
}
