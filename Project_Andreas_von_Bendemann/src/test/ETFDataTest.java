package test;

import types.ETFData;
import types.ETFDataFactory;
import types.ETFDatas;

import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ETFDataTest {
    public static void main (String[] args){
        //Test of Delivery 1 and 2
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

        //Test of Delivery 3
        // Test of ETFDataFactory
        //ETFDatas etfds1 = ETFDataFactory.readETFDataFile("data/EWD_test.csv");
        //System.out.println("Size: " + etfds1.getNumberOfEtfDatas());

        // Test allETFsUptrendStream()
        boolean allUptrend = etfds1.allETFsUptrendStream();
        System.out.println("Are all the ETFs in an uptrend? " + allUptrend);

        // Test countUptrendETFsStream()
        long countUptrend = etfds1.countUptrendETFsStream();
        System.out.println("The number of ETFs in an uptrend: " + countUptrend);

        // Test filterUptrendETFsStream()
        ETFDatas filteredUptrendETFs = etfds1.filterUptrendETFsStream();
        System.out.println("Filtered ETFs, which are in an uptrend: " + filteredUptrendETFs);

        // Test getMaxDailyChangeUptrend()
        Optional<ETFData> maxDailyChangeUptrend = etfds1.getMaxDailyChangeUptrend();
        System.out.println("ETF with the maximum daily change in an uptrend: " + maxDailyChangeUptrend.orElse(null));

        // Test filterUptrendAndSortByDailyChangeDescending()
        List<ETFData> uptrendSortedByDailyChange = etfds1.filterUptrendAndSortByDailyChangeDescending();
        System.out.println("ETFs in an uptrend, sorted by daily change in descending order: " + uptrendSortedByDailyChange);

        // Test groupByMonthStream()
        Map<Month, List<ETFData>> groupByMonth = etfds1.groupByMonthStream();
        for (Map.Entry<Month, List<ETFData>> entry : groupByMonth.entrySet()) {
            System.out.println("Month: "+ entry.getKey() +", ETFs: " + entry.getValue());
        }

        // Test averageDailyChangeByMonth()
        Map<Month, Double> averageDailyChangeByMonth = etfds1.averageDailyChangeByMonth();
        for (Map.Entry<Month, Double> entry : averageDailyChangeByMonth.entrySet()) {
            System.out.println("Month: "+ entry.getKey() +", Average Daily Change: " + entry.getValue());
        }

        // Test maxDailyChangeByMonth()
        Map<Month, Double> maxDailyChangeByMonth = etfds1.maxDailyChangeByMonth();
        for (Map.Entry<Month, Double> entry : maxDailyChangeByMonth.entrySet()) {
            System.out.println("Month:  "+ entry.getKey() +", Maximum Daily Change: " + entry.getValue());
        }

        // Test topNByMonth()
        int n = 3;                    // number of elements to retrieve
        Function<ETFData, Double> attributeFunction = ETFData::getDailyChange;
        Map<Month, List<ETFData>> topNByMonth = etfds1.topNByMonth(n, attributeFunction);
        for (Map.Entry<Month, List<ETFData>> entry : topNByMonth.entrySet()) {
            System.out.println("Month: " + entry.getKey()+ ", Top " + n + " ETFs: " + entry.getValue());
        }

        // Test getKeyWExtremeValue()
        boolean largest = true; // Change this to false if you want to find the smallest value
            // Get the map of ETFData objects from etfds1
        Map<Object, Double> dataMap = etfds1.getEtfdatas().stream().collect(Collectors.toMap(ETFData::getDate, ETFData::getClose));
            // Use the getKeyWExtremeValue()
        Object keyWithExtremeValue = etfds1.getKeyWExtremeValue(dataMap, largest);
        System.out.println("Key with the "+ (largest ? "largest" : "smallest")+ " value: "+ keyWithExtremeValue);

    }
}
