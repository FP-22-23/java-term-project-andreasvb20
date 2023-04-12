package test;

import types.ETFDataFactory;
import types.ETFDatas;

public class ETFDataTest {
    public static void main (String[] args){
        ETFDatas e = ETFDataFactory.readETFDataFile("data/EWD_test.csv");
        System.out.println("Size: " + e.getSizeOfETFDatas());
        System.out.println(e.toString());
    }
}
