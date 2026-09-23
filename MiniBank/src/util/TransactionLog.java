package util;
import java.io.IOException;
import java.nio.file.*;
public final class TransactionLog {
    private TransactionLog(){}
    public static void append(Path file,String line) throws IOException {
        if(file.getParent()!=null) Files.createDirectories(file.getParent());
        Files.writeString(file,line+System.lineSeparator(),StandardOpenOption.CREATE,StandardOpenOption.APPEND);
    }
}
