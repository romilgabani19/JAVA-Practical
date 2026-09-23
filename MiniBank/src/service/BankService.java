package service;
import exception.*;
import model.*;
import util.*;
import java.nio.file.*;
import java.io.*;
import java.util.*;
public class BankService {
    private final Repository<Account> repo;
    private final Path dataFile=Paths.get("data/accounts.dat");
    private final Path logFile=Paths.get("data/logs/transactions.log");
    public BankService(Repository<Account> repo){this.repo=repo;}
    public Account openAccount(String owner,String type,long opening) throws InvalidAmountException, IOException {
        if(opening<0)throw new InvalidAmountException(opening);
        Account a=switch(type.toLowerCase()){
            case "savings" -> new SavingsAccount(owner,opening,1000);
            case "current" -> new CurrentAccount(owner,opening,5000);
            case "fixed" -> new FixedDepositAccount(owner,opening);
            default -> throw new IllegalArgumentException("Unknown account type");
        };
        repo.save(a); log("OPEN "+a.getAccountNumber()+" "+opening); return a;
    }
    public void deposit(String n,long amount) throws BankException,IOException {Account a=need(n);a.deposit(amount);repo.save(a);log("DEPOSIT "+n+" "+amount);}
    public void withdraw(String n,long amount) throws BankException,IOException {Account a=need(n);a.withdraw(amount);repo.save(a);log("WITHDRAW "+n+" "+amount);}
    public void transfer(String from,String to,long amount) throws BankException,IOException {Account a=need(from),b=need(to);a.transfer(b,amount);repo.save(a);repo.save(b);log("TRANSFER "+from+" "+amount+" TO "+to);}
    private Account need(String n)throws AccountNotFoundException{Account a=repo.findById(n);if(a==null)throw new AccountNotFoundException(n);return a;}
    private void log(String s)throws IOException{TransactionLog.append(logFile,s);}
    public String statement(String n)throws AccountNotFoundException{return need(n).toString();}
    public ArrayList<Account> listAccounts(){return repo.findAll();}
    public void save()throws IOException{StatePersister.save(repo.findAll().toArray(Account[]::new),dataFile);}
    public void load()throws IOException,ClassNotFoundException{if(Files.exists(dataFile))for(Account a:StatePersister.load(dataFile))repo.save(a);}
    public Path report()throws IOException{Path p=Paths.get("data/report.txt");ReportGenerator.generate(Paths.get("data/logs"),p);return p;}
}
