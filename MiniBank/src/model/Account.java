package model;
import exception.*;
import model.annotation.*;
import java.io.Serializable;
import java.util.Objects;

public abstract class Account implements Transactable, InterestBearing, Serializable, Comparable<Account> {
    private static final long serialVersionUID = 1L;
    private static long counter = 1;
    @Id private final String accountNumber;
    @MaxLength(60) private String ownerName;
    @Positive private long balance;
    private boolean active;

    protected Account(String ownerName, long openingBalance) {
        this.accountNumber = String.format("AC%04d", counter++);
        this.ownerName = ownerName;
        this.balance = openingBalance;
        this.active = true;
    }
    public String getAccountNumber(){ return accountNumber; }
    public String getOwnerName(){ return ownerName; }
    public long getBalance(){ return balance; }
    public boolean isActive(){ return active; }
    public void setActive(boolean active){ this.active=active; }

    @Override public synchronized void deposit(long amount) throws InvalidAmountException {
        if(amount<=0) throw new InvalidAmountException(amount);
        balance += amount;
    }
    @Override public synchronized boolean withdraw(long amount) throws InvalidAmountException, InsufficientFundsException {
        if(amount<=0) throw new InvalidAmountException(amount);
        if(!canWithdraw(amount)) {
            long shortfall = Math.max(0, amount - balance);
            throw new InsufficientFundsException(shortfall);
        }
        balance -= amount;
        return true;
    }
    public void transfer(Account to, long amount) throws BankException {
        if(to==null) throw new AccountNotFoundException("null");
        Account first=this.accountNumber.compareTo(to.accountNumber)<0?this:to;
        Account second=first==this?to:this;
        synchronized(first){ synchronized(second){
            try { this.withdraw(amount); to.deposit(amount); }
            catch(BankException e){ throw e; }
            finally { System.out.println("Transfer attempt completed."); }
        }}
    }
    public abstract double interestRate();
    public abstract boolean canWithdraw(long amount);

    @Override public double balanceForInterest(){ return balance; }
    @Override public int compareTo(Account other){ return accountNumber.compareTo(other.accountNumber); }
    @Override public String toString(){ return accountNumber+" | "+ownerName+" | "+balance+" | "+(active?"ACTIVE":"INACTIVE"); }
    @Override public boolean equals(Object o){ return o instanceof Account a && accountNumber.equals(a.accountNumber); }
    @Override public int hashCode(){ return Objects.hash(accountNumber); }
}
