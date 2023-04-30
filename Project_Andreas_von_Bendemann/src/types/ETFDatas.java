package types;

import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
  
}



