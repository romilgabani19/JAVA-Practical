package service;
import model.Account;
import java.util.*;
public class AccountStore {
    private final HashMap<String,Account> accounts=new HashMap<>();
    private final ArrayList<String> history=new ArrayList<>();
    public synchronized void add(Account a){accounts.put(a.getAccountNumber(),a);}
    public synchronized Account get(String n){return accounts.get(n);}
    public synchronized ArrayList<Account> getAll(){return new ArrayList<>(accounts.values());}
    public synchronized ArrayList<Account> sortedByBalance(){ArrayList<Account> x=getAll();x.sort(Comparator.comparingLong(Account::getBalance).reversed());return x;}
    public synchronized void record(String s){history.add(s);}
    public synchronized ArrayList<String> history(){return new ArrayList<>(history);}
    public synchronized Map<String,Account> snapshot(){return new HashMap<>(accounts);}
}
