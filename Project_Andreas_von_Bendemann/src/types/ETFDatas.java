package types;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ETFDatas{
    private Set<ETFData> etfdatas;

    public ETFDatas(){
        etfdatas = new HashSet<>();
    }

    public ETFDatas(Stream<ETFData> s){
        etfdatas = s.collect(Collectors.toSet());
    }

    public Integer getSizeOfETFDatas(){
        return etfdatas.size();
    }

    @Override
    public String toString() {
        return "ETFDatas [etfdatas=" + etfdatas + "]";
    }
    
}

