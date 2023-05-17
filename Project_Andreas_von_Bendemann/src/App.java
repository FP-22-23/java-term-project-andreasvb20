import types.ETFDataFactory;
import types.ETFDatas;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        ETFDatas e = ETFDataFactory.readETFDataFile("data/EWD_test.csv");
        System.out.println("Size: " + e.getSizeOfETFDatas());
        System.out.println(e.toString());
    }
}
