package util;
public final class CommandParser {
    private CommandParser(){}
    public static Command parse(String line){
        String[] p=line.trim().split("\\s+");
        if(p.length!=3) throw new IllegalArgumentException("Expected: TYPE ACCOUNT AMOUNT");
        return new Command(TransactionType.valueOf(p[0].toUpperCase()),p[1],Long.parseLong(p[2]));
    }
}
