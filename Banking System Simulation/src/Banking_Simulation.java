import java.util.*;
class Account {
    protected double balance;

    public Account(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > balance) return false;
        balance -= amount;
        return true;
    }

    public double getBalance() {
        return balance;
    }
}
class SavingsAccount extends Account {
    public SavingsAccount(double balance) {
        super(balance);
    }
}
class CurrentAccount extends Account {
    public CurrentAccount(double balance) {
        super(balance);
    }

    @Override
    public boolean withdraw(double amount) {
        if (balance - amount < 1000) {
            System.out.println("Minimum balance of 1000 required");
            return false;
        }
        balance -= amount;
        return true;
    }
}
class User {
    String name, dob, username, gender, email;
    private String password;
    int age;
    long mobile;
    Account account;
    ArrayList<Transaction> history = new ArrayList<>();

    public User(String name, String dob, String username, String password,
                Account account, int age, String gender, long mobile,
                String email) {

        this.name = name;
        this.dob = dob;
        this.username = username;
        this.password = password;
        this.account = account;
        this.age = age;
        this.gender = gender;
        this.mobile = mobile;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
}

class Transaction {
    String type;
    double amount;
    String details;

    Transaction(String type, double amount, String details) {
        this.type = type;
        this.amount = amount;
        this.details = details;
    }
}
public class Banking_Simulation {
    static Scanner o=new Scanner(System.in);
    static HashMap<String, User> users = new HashMap<>();

    public static void CreateAccount(){

        System.out.print("Enter Your Full Name: ");
        String name=o.nextLine();
        System.out.print("Enter You DOB (DD/MM/YYYY): ");
        String dob=o.next();
        String username;
        while (true){
            System.out.print("Enter Username: ");
            username=o.next();
            if (!(users.containsKey(username))) {
                break;
            }
            System.out.println("Username already taken.Please choose a new one");
        }
        System.out.print("Enter Password : (It should contain 1 uppercase/ 1 lowercase / 1 digit / 1 special Character / should contain more than 8 characters ): ");
        String password="";
        while (true){
            password=o.next();
            boolean uppercase=false;
            boolean lowercase=false;
            boolean digit=false;
            boolean length=false;
            boolean special_character=false;
            char[] password_char=password.toCharArray();
            if (password.length()>=8){
                length=true;
            }
            for (char c : password_char){
                if (c >=65 && c<=90){
                    uppercase=true;
                }
                else if (c>=97 && c<=122){
                    lowercase=true;
                }
                else if (c>=48 && c<=57){
                    digit=true;
                }
                else{
                    special_character=true;
                }
            }
            if (!length) {
                System.out.println("Password must be at least 8 characters");
            }
            if (!uppercase) {
                System.out.println("Add at least one uppercase letter");
            }
            if (!lowercase) {
                System.out.println("Add at least one lowercase letter");
            }

            if (!digit) {
                System.out.println("Add at least one digit");
            }

            if (!special_character) {
                System.out.println("Add at least one special character");
            }
            if (length && uppercase && lowercase && digit && special_character){
                System.out.println("Password accepted");
                break;
            }
        }
        System.out.print("Choose Type of Account you want to create \n 1. Savings  \n 2. Current \n");
        int ntype=o.nextInt();
        String  type="";
        if (ntype==1){
            type="Savings";
        }
        else if (ntype==2){
            type="Current";
        }
        else{
            System.out.println("Invalid Account Type");
            return;
        }
        System.out.print("Enter you Age: ");
        int age=o.nextInt();
        System.out.print("Enter your Gender \n 1. Female \n 2. Male \n 3. Other \n");
        int ngender=o.nextInt();
        String gender="";
        if (ngender==1){
            gender="Female";
        }
        else if (ngender==2){
            gender="Male";
        }
        else if (ngender==3){
            gender="Other";
        }
        else{
            System.out.println("Invalid Number");
            return;
        }

        System.out.print("Enter Mobile Number: ");
        long mobile =o.nextLong();
        System.out.print("Enter Email: ");
        String email=o.next();
        System.out.print("Enter Initial Deposit Money: ");
        double deposit=o.nextDouble();
        if (deposit <= 0) {
            System.out.println("Invalid deposit amount!");
            return;
        }
        Account acc;

        if (type.equalsIgnoreCase("Savings")) {
            acc = new SavingsAccount(deposit);
        } else {
            acc = new CurrentAccount(deposit);
        }


        User u = new User(name, dob, username, password,
                acc, age, gender, mobile, email);
        users.put(username, u);
        System.out.println("------Account Created Successfully-----");
        System.out.println("Name           : " + name);
        System.out.println("Account Type   : " + type);
        System.out.println("Balance        : " + deposit);
    }
    public static void Deposit(){
        System.out.println("Enter Username: ");
        String username=o.next();
        System.out.println("Enter password: ");
        String password=o.next();
        if (!users.containsKey(username)){
            System.out.println("Username doesn't exist");
            return;
        }
        User u=users.get(username);
        if (!u.getPassword().equals(password)){
            System.out.println("Incorrect Password");
            return;
        }
        System.out.println("Enter Amount you want to deposit: ");
        double deposit_money=o.nextDouble();
        if (deposit_money<=0){
            System.out.println("Please enter valid deposit money");
            return;
        };
        u.account.deposit(deposit_money);
        u.history.add(new Transaction("Deposit", deposit_money, "Self"));
        System.out.println("Amount Deposited Successfully");
        System.out.println("Account Holder name : " + u.name);
        System.out.println("Deposit Money       : " + deposit_money);
        System.out.println("Available Balance   : "+ u.account.getBalance() );

    }
    public static void withdraw(){
        System.out.println("Enter Username: ");
        String username=o.next();
        System.out.println("Enter password: ");
        String password=o.next();
        if (!users.containsKey(username)){
            System.out.println("Username doesn't exist");
            return;
        }
        User u=users.get(username);
        if (!u.getPassword().equals(password)){
            System.out.println("Incorrect Password");
            return;
        }
        System.out.println("Enter the amount you want to Withdraw: ");
        double withdraw_money=o.nextDouble();
        if (withdraw_money<=0){
            System.out.println("Please enter Withdrawn money greater than 0");
            return;
        }
        if (withdraw_money>u.account.getBalance()){
            System.out.println("Insufficient Balance");
            return;
        }

        else{
            if (!u.account.withdraw(withdraw_money)) {
                System.out.println("Transaction failed");
                return;
            }
            u.history.add(new Transaction("Withdraw", withdraw_money, "Self"));
        }
        System.out.println("------Amount Withdrawn Successfully-----");
        System.out.println("Account Holder name  : " + u.name);
        System.out.println("Withdrawn Money      : " + withdraw_money);
        System.out.println("Available Balance    : "+ u.account.getBalance() );

    }
    public static void transfer_money(){
        System.out.println("Enter Username: ");
        String Sender_username=o.next();
        System.out.println("Enter Password: ");
        String password=o.next();
        if (!users.containsKey(Sender_username)){
            System.out.println("Invalid username");
            return;
        }
        User Sender_Details=users.get(Sender_username);
        if (!password.equals(Sender_Details.getPassword())){
            System.out.println("Invalid password");
            return;
        }
        System.out.println("Enter Receiver username");
        String Receiver_username=o.next();
        if (!users.containsKey(Receiver_username)){
            System.out.println("Invalid Receiver username");
            return;
        }
        User Receiver_Details=users.get(Receiver_username);
        System.out.println("Enter Amount you want to Transfer: ");
        double transfer_money=o.nextDouble();
        if (transfer_money<=0){
            System.out.println("Enter Valid Amount");
            return;
        }
        if (transfer_money>Sender_Details.account.getBalance()){
            System.out.println("Insufficient balance");
            return;
        }
        if (Sender_Details.account.withdraw(transfer_money)) {
            Receiver_Details.account.deposit(transfer_money);
        } else {
            System.out.println("Transaction failed");
        }
        Sender_Details.history.add(
                new Transaction("Transfer Sent", transfer_money, "To: " + Receiver_username)
        );

        Receiver_Details.history.add(
                new Transaction("Transfer Received", transfer_money, "From: " + Sender_username)
        );
        System.out.println("Money has been successfully transferred");
        System.out.println("Account Holder name   : " + Sender_Details.name);
        System.out.println("Sender username       : "+ Sender_username);
        System.out.println("Receiver username     : "+Receiver_username );
        System.out.println("Transferred Amount    : "+transfer_money);
        System.out.println("Available Balance     : " + Sender_Details.account.getBalance());
    }
    public static void Check_balance(){
        System.out.println("----- Balance Inquiry -----");
        System.out.println("Enter Username: ");
        String username=o.next();
        System.out.println("Enter password: ");
        String password=o.next();
        if (!users.containsKey(username)){
            System.out.println("Username doesn't exist");
            return;
        }
        User u=users.get(username);
        if (!u.getPassword().equals(password)){
            System.out.println("Incorrect Password");
            return;
        }
        System.out.println("----- Balance Details -----");
        System.out.println("Account Holder name  : " + u.name);
        System.out.println("Username             : " + u.username);
        System.out.println("Available Balance    : " + u.account.getBalance());
    }
    public static void transaction_history() {
        System.out.println("Enter Username: ");
        String username = o.next();

        System.out.println("Enter Password: ");
        String password = o.next();

        if (!users.containsKey(username)) {
            System.out.println("Invalid username");
            return;
        }
        User u = users.get(username);
        if (!u.getPassword().equals(password)) {
            System.out.println("Invalid password");
            return;
        }
        System.out.println("----- Transaction History -----");
        if (u.history.isEmpty()) {
            System.out.println("No transactions found");
            return;
        }
        for (Transaction t : u.history) {
            System.out.println("Type    : " + t.type);
            System.out.println("Amount  : " + t.amount);
            System.out.println("Details : " + t.details);
            System.out.println("-----------------------------");
        }
    }
}
