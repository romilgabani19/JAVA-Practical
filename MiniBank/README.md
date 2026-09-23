# MiniBank – OOP CEUE203

JDK 17+ console banking application implementing the MiniBank project through the supplied practical list.

## Build
From the `minibank` directory:

```cmd
javac -d out src\model\annotation\*.java src\model\*.java src\exception\*.java src\util\*.java src\service\*.java src\MiniBank.java
jar cfe minibank.jar MiniBank -C out .
```

## Run
```cmd
java -jar minibank.jar
```

The application persists accounts in `data/accounts.dat`, appends transaction logs under `data/logs/`, and creates `data/report.txt`.

JUnit 5 tests are supplied in `test/MiniBankTest.java`. Download/use JUnit 5 separately in an IDE or command line; the main application itself has no external dependencies.

## Practical coverage
01 menu shell, enums, records, switch expressions
02 encapsulation, constructors, static IDs
03 toString/equals/hashCode, nested Address, clone, instanceof
04 regex validation, command parsing, StringBuilder
05 abstract Account hierarchy and polymorphism
06 interfaces, default/functional/marker interfaces, packages, JAR
07 annotations and reflection
08 custom checked exceptions, transfer, finally, try-with-resources concepts
09 threads and synchronized account operations
10 executor thread pool and deadlock-safe transfer locking
11 serialization, NIO logs and report
12 collections, Comparable/Comparator, thread-safe map
13 generic Repository and bounded generic method
14 BankService integration, menu, persistence, concurrent processing, tests and JAR
