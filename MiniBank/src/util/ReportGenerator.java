package util;
import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
public final class ReportGenerator {
    private ReportGenerator(){}
    public static String generate(Path logs,Path report) throws IOException {
        long deposits=0,withdrawals=0; StringBuilder b=new StringBuilder("END-OF-DAY REPORT\n");
        if(Files.exists(logs)) try(DirectoryStream<Path> ds=Files.newDirectoryStream(logs,"*.log")){
            for(Path p:ds){
                if(Files.size(p)==0)continue;
                try(BufferedReader r=Files.newBufferedReader(p)){
                    String line; while((line=r.readLine())!=null){
                        String[] x=line.trim().split("\\s+");
                        if(x.length>=3){ long a=Long.parseLong(x[2]); if(x[0].equalsIgnoreCase("DEPOSIT"))deposits+=a; else if(x[0].equalsIgnoreCase("WITHDRAW"))withdrawals+=a; }
                    }
                }
                BasicFileAttributes a=Files.readAttributes(p,BasicFileAttributes.class);
                b.append(p.getFileName()).append(" size=").append(a.size()).append(" modified=").append(a.lastModifiedTime()).append("\n");
            }
        }
        b.append("Total deposits: ").append(deposits).append("\nTotal withdrawals: ").append(withdrawals).append("\nNet change: ").append(deposits-withdrawals).append("\n");
        Files.createDirectories(report.toAbsolutePath().getParent());
        Files.writeString(report,b.toString());
        return b.toString();
    }
}
