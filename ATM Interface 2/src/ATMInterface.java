import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/*The ATMInterface class represents the main ATM interface program.
It handles user authentication, displays the ATM menu, and provides
functionalities like balance inquiry, withdrawal, deposit, and mini-statement.*/
public class ATMInterface {
    private Map<String, User> userMap;
    private User currentUser;

    // Constructor to initialize the ATMInterface with some sample user data.
    public ATMInterface() {
        userMap = new HashMap<>();
        userMap.put("1234", new User("1234", "1234", 150.0));
        userMap.put("5678", new User("5678", "5678", 100.0));
    }

    // Main method to start the ATM interface.
    public void start() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\nWelcome to the ATM Machine!!!"
                + "\nFirst let us verify your identity"
                + "\nInsert your card details manually");

        // User Authentication
        authenticateUser(sc);

        // Display ATM Menu
        displayMenu(sc);

        sc.close();
    }

    /*
     * Authenticates the user by prompting for user ID and PIN.
     * Keeps prompting until valid credentials are provided.
     * parameter sc Scanner object for user input.
     */

    private void authenticateUser(Scanner sc) {
        while (true) {
            System.out.print("\nEnter User ID: ");
            String userId = sc.next();

            System.out.print("Enter PIN: ");
            String pin = sc.next();

            if (userMap.containsKey(userId)) {
                User user = userMap.get(userId);

                if (user.validatePIN(pin)) {
                    System.out.println("\nAuthentication successful. \nWelcome, User " + userId + "!");
                    currentUser = user;
                    return;
                } else {
                    System.out.println("\nInvalid PIN. Please try again.");
                }
            } else {
                System.out.println("\nInvalid User ID. Please try again.");
            }
        }
    }

    /*
     * Displays the ATM menu and handles user choices.
     * parameter sc Scanner object for user input.
     */
    private void displayMenu(Scanner sc) {
        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Deposit Money");
            System.out.println("4. Account Statement");
            System.out.println("5. Logout");
            System.out.print("\nEnter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nYour Balance is: Rs." + currentUser.getBalance() + "/-");
                    break;
                case 2:
                    System.out.print("\nEnter the amount you want to withdraw: Rs.");
                    double withdrawalAmount = sc.nextDouble();
                    try {
                        currentUser.withdraw(withdrawalAmount);
                        System.out.println(
                                "Withdrawal successful. \nYour new balance is: Rs." + currentUser.getBalance()
                                        + "/-");
                        currentUser.addTransaction("Withdrawal: -Rs." + withdrawalAmount);
                    } catch (InsufficientFundsException e) {
                        System.out.println(
                                "\nWithdrawal of Rs." + withdrawalAmount
                                        + "/- amount failed, due to insufficient funds!"
                                        + "\nYour current balance is: Rs." + currentUser.getBalance() + "/-");
                    }
                    break;
                case 3:
                    System.out.print("\nEnter the amount you want to deposit: Rs.");
                    double depositAmount = sc.nextDouble();
                    currentUser.deposit(depositAmount);
                    System.out.println("\nDeposit Successfull !!"
                            + "\nAmount successfully deposited"
                            + "\nYour new balance is: Rs." + currentUser.getBalance() + "/-");
                    currentUser.addTransaction("Deposit: +Rs." + depositAmount);
                    break;
                case 4:
                    displayMiniStatement();
                    break;
                case 5:
                    System.out.println("\nDont forget to collect you ATM card"
                            + "\nLogging out"
                            + "\nHave a nice day :)");
                    return;
                default:
                    System.out.println("\nInvalid choice. Please enter a number between 1 and 5.");
            }
        }
    }

    // Displays the Account statement, showing the user's transaction history.
    private void displayMiniStatement() {
        List<String> transactions = currentUser.getTransactions();
        System.out.println("\nYour Account Statement: ");
        System.out.println("\nRemaining Balance: Rs." + currentUser.getBalance() + "/-");
        for (String transaction : transactions) {
            System.out.println(transaction);
        }
    }

    // Main method to run the ATM interface program.
    public static void main(String[] args) {
        ATMInterface atmInterface = new ATMInterface();
        atmInterface.start();
    }
}

/*
 * The User class represents a user account in the ATM system.
 * It includes attributes like userId, pin, balance, and a list of transactions.
 */
class User {
    private String userId;
    private String pin;
    private double balance;
    private List<String> transactions;

    // Constructor to initialize a user with a userId, pin, and initial balance.
    public User(String userId, String pin, double balance) {
        this.userId = userId;
        this.pin = pin;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    // Retrieves the balance of the user.
    public double getBalance() {
        return balance;
    }

    // Deposits money into the user's account.
    public void deposit(double amount) {
        balance += amount;
    }

    /*
     * Withdraws money from the user's account, throwing an exception if there are
     * insufficient funds.
     * throws InsufficientFundsException If there are insufficient funds for
     * withdrawal.
     */
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= balance) {
            balance -= amount;
        } else {
            throw new InsufficientFundsException();
        }
    }

    /*
     * Validates the user's PIN.
     * returns True if the PIN is valid, false otherwise.
     */
    public boolean validatePIN(String inputPIN) {
        return pin.equals(inputPIN);
    }

    // Adds a transaction entry to the user's transaction history.
    public void addTransaction(String transaction) {
        transactions.add(transaction);
    }

    // Retrieves the list of transactions in the user's transaction history.
    public List<String> getTransactions() {
        return transactions;
    }
}

// Custom exception class for handling insufficient funds during a withdrawal.
class InsufficientFundsException extends Exception {

}
