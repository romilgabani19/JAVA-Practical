package exception;
public class AccountNotFoundException extends BankException {
    public AccountNotFoundException(String number){ super("Account not found: " + number); }
}
