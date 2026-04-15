import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner o=new Scanner(System.in);
        while (true) {
            System.out.println("\n===== BANKING OPERATIONS =====");
            System.out.println("1. Create Account");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Deposit Money");
            System.out.println("4. Transfer Money");
            System.out.println("5. Balance Inquiry");
            System.out.println("6. Transaction History");
            System.out.println("7. Exit");

            System.out.print("Choose an option: ");
            int choice = o.nextInt();
            switch (choice) {
                case 1:
                    Banking_Simulation.CreateAccount();
                    break;

                case 2:
                    Banking_Simulation.withdraw();
                    break;

                case 3:
                    Banking_Simulation.Deposit();
                    break;

                case 4:
                    Banking_Simulation.transfer_money();
                    break;

                case 5:
                    Banking_Simulation.Check_balance();
                    break;

                case 6:
                    Banking_Simulation.transaction_history();
                    break;

                case 7:
                    System.out.println("Thank you! Exiting...");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}