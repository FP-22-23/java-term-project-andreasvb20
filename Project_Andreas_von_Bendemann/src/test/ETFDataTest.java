package test;

import types.ETFData;
import types.ETFDataFactory;
import types.ETFDatas;

import java.time.Month;
import java.util.List;
import java.util.Map;

public class ETFDataTest {
    public static void main (String[] args){
        // test of ETFDataFactory
        ETFDatas etfds1 = ETFDataFactory.readETFDataFile("data/EWD_test.csv");
        System.out.println("Size: " + etfds1.getNumberOfEtfDatas());

        //  test allETFsUptrend()
        System.out.println("Are all the ETFs in an uptrend? :" + etfds1.allETFsUptrend());

        // test countUptrendETFs()
        System.out.println("The number of ETFs in an uptrend: " + etfds1.countUptrendETFs());

        //   filterUptrendETFs()
        ETFDatas etfsfilt = etfds1.filterUptrendETFs();
        System.out.println("Filtered ETFs, which are in an uptrend : " + etfsfilt.toString());

        //  Test groupByMonth()
        Map<Month, List<ETFData>> groupMonth1 = etfds1.groupByMonth();
        for (Map.Entry<Month, List<ETFData>> entry : groupMonth1.entrySet()) {
            System.out.println("Month: "+ entry.getKey() +", ETFs: " + entry.getValue());
        }

        //Test groupByMonthWithCount()
        Map<Month, Long> groupMonthwCount1 = etfds1.groupByMonthWithCount();
        for (Map.Entry<Month, Long> entry :groupMonthwCount1.entrySet()) {
            System.out.println("Month: " + entry.getKey() + ", Number: " + entry.getValue());
        }
    }
}

