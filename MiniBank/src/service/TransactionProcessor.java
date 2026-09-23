package service;
import model.Account;
import exception.*;
import java.util.concurrent.*;
import java.util.*;
public class TransactionProcessor {
    private final ExecutorService pool=Executors.newFixedThreadPool(4);
    private final ConcurrentHashMap<String,Account> accounts=new ConcurrentHashMap<>();
    public void add(Account a){accounts.put(a.getAccountNumber(),a);}
    public void submit(Runnable task){pool.execute(task);}
    public void deposit(String n,long amount){submit(()->{try{accounts.get(n).deposit(amount);}catch(Exception e){System.out.println(e.getMessage());}});}
    public void withdraw(String n,long amount){submit(()->{try{accounts.get(n).withdraw(amount);}catch(Exception e){System.out.println(e.getMessage());}});}
    public void stop() throws InterruptedException {pool.shutdown();pool.awaitTermination(10,TimeUnit.SECONDS);}
    public ConcurrentHashMap<String,Account> accounts(){return accounts;}
}
