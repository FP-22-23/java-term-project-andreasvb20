package types;

import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ETFDatas{
    //Instance variables
    private Set<ETFData> etfdatas;

    public ETFDatas(){
        etfdatas = new HashSet<>();
    }
    //Constructors
    public ETFDatas(Stream<ETFData> st){
        etfdatas = st.collect(Collectors.toSet());
    }
    //info method
    @Override
    public String toString() {
        return "ETFDatas [etfdatas=" + etfdatas + "]";
    }
    //get-/set-/add-methods etc.
    public void addEtfData (ETFData etfData){
        etfdatas.add( etfData);
    }
    
    public void addEtfDatas(Collection<ETFData> etfDatas){
        etfdatas.addAll(etfDatas);
    }
    
    public void removeEtfData(ETFData etfData){
        etfdatas.remove(etfData);
    }
    
    public int getNumberOfEtfDatas(){
        return etfdatas.size();
    }
    public Set<ETFData> getEtfdatas() {
        return etfdatas;
    }
    
    
    // a) checks if all ETFs are on uptrend
    public boolean allETFsUptrend() {
        for (ETFData etfdata : etfdatas) {
            if (!etfdata.isUptrend()) {
                return false;
            }
        }
        return true;
    }

    // b) countnumber of ETFs which are uptrend
    public int countUptrendETFs() {
        int count = 0;
        for (ETFData etfdata : etfdatas) {
            if (etfdata.isUptrend()) {
                count = count+1;
            }
        }
        return count;
    }

    // c) filter the ETFs that are uptrend and returnnew ETFDatas object with all the ETFs which are in an uptrend
    public ETFDatas filterUptrendETFs() {
        List<ETFData>  uptrendETFs = new ArrayList<>();
        for (ETFData etfdata : etfdatas) {
            if (etfdata.isUptrend() ) {
                uptrendETFs.add(etfdata);
            }
        }
        return new ETFDatas(uptrendETFs.stream());
    }

    // d) group the ETFs by month
    public Map<Month, List<ETFData>> groupByMonth() {
        Map<Month, List<ETFData>> group1 = new HashMap<>();
        for (ETFData etfdata : etfdatas) {
            Month month1 = etfdata.getMonth();
            if (!group1.containsKey(month1)) {
                group1.put(month1, new ArrayList<>());
            }
            group1.get(month1).add(etfdata);
        }
        return group1;
    }

    // e) group the ETFs by month with count
    public Map<Month, Long> groupByMonthWithCount() {
        Map<Month, Long> group2 = new HashMap<>();
        for (ETFData etfdata : etfdatas) {
            Month month2 = etfdata.getMonth();
            group2.put(month2, group2.getOrDefault(month2, 0L) + 1);
        }
        return group2;
    }

    //Delivery 3

    //Block 1 stream methods

     //1. checks if all ETFs are on uptrend (from a) with streams
     public boolean allETFsUptrendStream(){
        return etfdatas.stream().allMatch(ETFData::isUptrend);
    }

    //2. countnumber of ETFs which are in an uptrend and returns it (from b) with streams
    public long countUptrendETFsStream(){ 
        return etfdatas.stream().filter(ETFData::isUptrend).count();
    }

    //3. (from c) filter the ETFs that are uptrend and return new ETFDatas object with all the ETFs which are in an uptrend with streams
    public ETFDatas filterUptrendETFsStream(){
        Set<ETFData> uptrendETFs = etfdatas.stream().filter(ETFData::isUptrend).collect(Collectors.toSet());
        ETFDatas etfs1 = new ETFDatas(uptrendETFs.stream());
        return etfs1;
    }

    //4. find ETFs with maximum dailychange among the ETFs in an uptrend within the ETFData Collection and returns it with streams
    public Optional<ETFData> getMaxDailyChangeUptrend() {
        return etfdatas.stream().filter(ETFData::isUptrend).max(Comparator.comparing(ETFData::getDailyChange));
    }

    //5. filters ETFs which are in an uptrend and sorts them by dailychange in an descending order and returns a List containing them
    public List<ETFData> filterUptrendAndSortByDailyChangeDescending() {
        return etfdatas.stream().filter(ETFData::isUptrend)
            .sorted(Comparator.comparing(ETFData::getDailyChange).reversed()).collect(Collectors.toList());
    }

    //Block 2 methods

    //6. group the ETFs by month from d) with streams
    //returns Map with Month as key and List of ETFData objects as value
    public Map<Month, List<ETFData>> groupByMonthStream() {
        return etfdatas.stream()
                .collect(Collectors.groupingBy(ETFData::getMonth));
    }

    //7. method using the Collector mapping
    //calculate average daily change of ETFData objects for each month.
    //returns Map with Month as key and average daily change as value
    public Map<Month, Double> averageDailyChangeByMonth() {
        return etfdatas.stream()
            .collect(Collectors.groupingBy(ETFData::getMonth,
                Collectors.mapping(ETFData::getDailyChange, Collectors.averagingDouble(Double::doubleValue))));
    }

    //8.  method that returns a Map
    //find maximum daily change of ETFData objects for each month
    //returns a Map with Month as key and maximum daily change as value
    public Map<Month, Double> maxDailyChangeByMonth() {
        return etfdatas.stream()
            .collect(Collectors.groupingBy(ETFData::getMonth,
                Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(ETFData::getDailyChange)),
                    opt1 -> opt1.map(ETFData::getDailyChange).orElse(Double.NaN))));
    }

    //9. method that returns a NavigableMap (SortedMap)
    // for each month based on a given attribute function.
    // The TreeMap is sorted by Month, and the List of ETFData objects is sorted by function
    public NavigableMap<Month, List<ETFData>> topNByMonth(int n, Function<ETFData, Double> attributeFunction) {
        return etfdatas.stream()
            .collect(Collectors.groupingBy(ETFData::getMonth, TreeMap::new,
                Collectors.collectingAndThen(Collectors.toMap(Function.identity(),attributeFunction,
                    (a, b) -> a,
                    () -> new TreeMap<>(Comparator.comparing(attributeFunction).reversed())),
                    map -> map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .limit(n).map(Map.Entry::getKey).collect(Collectors.toList()))));
    }

    //10. method that calculates a Map and returns the key with the associated value (largest or smallest) of the entire Map
    //finds the key-Month in the input Map that has the largest or smallest value,
    // depending on if largest is true or false it returns the key associated with the largest or smallest value
    public Object getKeyWExtremeValue(Map<Object, Double> inputMap, boolean largest) {
        Map.Entry<Object, Double> extremeEntry = null;
        
        for (Map.Entry<Object, Double> entry : inputMap.entrySet()) {
            if (extremeEntry== null) {
                extremeEntry= entry;
            } else {
                if (largest) {
                    if (entry.getValue() > extremeEntry.getValue()) {
                        extremeEntry = entry;
                    }
                } else {
                    if (entry.getValue() < extremeEntry.getValue()) {
                        extremeEntry = entry;
                    }
                }
            }
        }
        return extremeEntry!= null? extremeEntry.getKey() : null;
    }

  
}




