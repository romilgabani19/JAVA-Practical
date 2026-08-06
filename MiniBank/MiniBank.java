public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    BankInfo bank = new BankInfo("MiniBank", "Main Branch");

    System.out.println("=================================");
    System.out.println(bank.name());
    System.out.println(bank.branch());
    System.out.println("=================================");

    Customer[] customers = new Customer[3];
    Account[] accounts = new Account[3];

    // Input customer and account details
    for (int i = 0; i < 3; i++) {

        System.out.println("\nEnter Details for Customer " + (i + 1));

        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Mobile: ");
        String mobile = sc.nextLine();

        System.out.print("Opening Balance: ");
        long openingBalance = sc.nextLong();
        sc.nextLine(); // clear buffer

        customers[i] = new Customer(name, email, mobile);
        accounts[i] = new Account(name, openingBalance);

        System.out.println("Customer ID : " + customers[i].getCustomerId());
        System.out.println("Account No  : " + accounts[i].getAccountNumber());
    }

    boolean run = true;

    while (run) {

        System.out.println("\n========== MENU ==========");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Display Accounts");
        System.out.println("4. Exit");
        System.out.print("Enter Choice: ");

        int choice = sc.nextInt();

        switch (choice) {

            case 1 -> {

                System.out.print("Enter Account Number (1-3): ");
                int acc = sc.nextInt() - 1;

                if (acc >= 0 && acc < 3) {

                    System.out.print("Enter Deposit Amount: ");
                    long amount = sc.nextLong();

                    accounts[acc].deposit(amount);

                    System.out.println("Deposit Successful.");
                    System.out.println("Current Balance: ₹" + accounts[acc].getBalance());

                } else {
                    System.out.println("Invalid Account.");
                }
            }

            case 2 -> {

                System.out.print("Enter Account Number (1-3): ");
                int acc = sc.nextInt() - 1;

                if (acc >= 0 && acc < 3) {

                    System.out.print("Enter Withdrawal Amount: ");
                    long amount = sc.nextLong();

                    if (accounts[acc].withdraw(amount))
                        System.out.println("Withdrawal Successful.");
                    else
                        System.out.println("Insufficient Balance.");

                    System.out.println("Current Balance: ₹" + accounts[acc].getBalance());

                } else {
                    System.out.println("Invalid Account.");
                }
            }

            case 3 -> {

                System.out.println("\n------ Account Details ------");

                for (int i = 0; i < 3; i++) {

                    System.out.println("---------------------------");
                    System.out.println("Customer ID : " + customers[i].getCustomerId());
                    System.out.println("Name        : " + customers[i].getName());
                    System.out.println("Email       : " + customers[i].getEmail());
                    System.out.println("Mobile      : " + customers[i].getMobile());
                    System.out.println("Account No  : " + accounts[i].getAccountNumber());
                    System.out.println("Balance     : ₹" + accounts[i].getBalance());
                    System.out.println("Active      : " + accounts[i].isActive());
                }
            }

            case 4 -> {
                run = false;
                System.out.println("Thank You for using MiniBank.");
            }

            default -> System.out.println("Invalid Choice.");
        }
    }

    sc.close();
}