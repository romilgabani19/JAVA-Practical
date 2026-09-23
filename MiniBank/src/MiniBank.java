import exception.*;
import model.*;
import model.annotation.*;
import service.*;
import util.*;
import java.nio.file.*;
import java.util.*;

public class MiniBank {
    record BankInfo(String name,String branch){}
    enum MenuOption {OPEN_ACCOUNT,DEPOSIT,WITHDRAW,TRANSFER,STATEMENT,LIST,REPORT,EXIT}
    public static void main(String[] args){
        Repository<Account> repo=new Repository<>();
        BankService bank=new BankService(repo);
        try{bank.load();}catch(Exception e){System.out.println("Load skipped: "+e.getMessage());}
        System.out.println(new BankInfo("MiniBank","Main Branch"));
        Scanner sc=new Scanner(System.in);
        boolean running=true;
        while(running){
            System.out.println("\n1.Open Account  2.Deposit  3.Withdraw  4.Transfer  5.Statement  6.List  7.Report  8.Exit");
            System.out.print("Choice: ");
            String raw=sc.nextLine();
            int choice; try{choice=Integer.parseInt(raw);}catch(NumberFormatException e){System.out.println("Enter a valid number.");continue;}
            MenuOption op=switch(choice){case 1->MenuOption.OPEN_ACCOUNT;case 2->MenuOption.DEPOSIT;case 3->MenuOption.WITHDRAW;case 4->MenuOption.TRANSFER;case 5->MenuOption.STATEMENT;case 6->MenuOption.LIST;case 7->MenuOption.REPORT;case 8->MenuOption.EXIT;default->null;};
            try{
                if(op==null){System.out.println("Invalid menu choice.");continue;}
                switch(op){
                    case OPEN_ACCOUNT -> {System.out.print("Owner: ");String owner=sc.nextLine();System.out.print("Type (savings/current/fixed): ");String type=sc.nextLine();System.out.print("Opening balance: ");long a=Long.parseLong(sc.nextLine());System.out.println("Opened: "+bank.openAccount(owner,type,a));}
                    case DEPOSIT -> {System.out.print("Account: ");String n=sc.nextLine();System.out.print("Amount: ");long a=Long.parseLong(sc.nextLine());bank.deposit(n,a);System.out.println("Deposit successful.");}
                    case WITHDRAW -> {System.out.print("Account: ");String n=sc.nextLine();System.out.print("Amount: ");long a=Long.parseLong(sc.nextLine());bank.withdraw(n,a);System.out.println("Withdrawal successful.");}
                    case TRANSFER -> {System.out.print("From: ");String f=sc.nextLine();System.out.print("To: ");String t=sc.nextLine();System.out.print("Amount: ");long a=Long.parseLong(sc.nextLine());bank.transfer(f,t,a);System.out.println("Transfer successful.");}
                    case STATEMENT -> {System.out.print("Account: ");System.out.println(bank.statement(sc.nextLine()));}
                    case LIST -> bank.listAccounts().forEach(System.out::println);
                    case REPORT -> {System.out.println(Files.readString(bank.report()));}
                    case EXIT -> {bank.save();running=false;System.out.println("Goodbye!");}
                }
            }catch(NumberFormatException e){System.out.println("Invalid number.");}
            catch(InsufficientFundsException e){System.out.println("Withdrawal failed: short by "+e.getShortfall());}
            catch(BankException|java.io.IOException e){System.out.println("Operation failed: "+e.getMessage());}
        }
        sc.close();
    }
}
