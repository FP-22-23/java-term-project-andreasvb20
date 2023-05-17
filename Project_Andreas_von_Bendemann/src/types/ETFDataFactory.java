package types;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class ETFDataFactory {
    public static ETFDatas readETFDataFile(String fileName){

        ETFDatas res = null;

        try{
            Stream<ETFData> st = Files.lines(Path.of(fileName)).skip(1).map(ETFDataFactory::parseETFData);
            res = new ETFDatas(st);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return res;

    }

    public static ETFData parseETFData(String line){
        return new ETFData(line);
    }
}


