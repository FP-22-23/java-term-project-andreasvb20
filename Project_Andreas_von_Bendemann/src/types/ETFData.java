package types;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ETFData implements Comparable <ETFData>{

    //variables
    private LocalDate date; 
    private double open;
    private double high;
    private double low;
    private double close;
    private double adjClose;
    private long   volume;
    private Month month;

    // variable of type enum
    private WeekDay weekDay;
    
    // derived variables for traders
    private double dailyChange; // Close - Open
    private double dailyRange; // High - Low 
    private boolean uptrend; //  if Close > Open
    
    // more variables for traders in an auxiliary type
    private TechnicalIndicators indicators;
    
    // List for newsHeadlines
    private List<String> newsHeadlines;

    //constructors
    public ETFData(LocalDate date, double open, double high, double low, double close, double adjClose, long volume) {
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.adjClose = adjClose;
        this.volume = volume;
        this.month = date.getMonth();
        this.newsHeadlines = new ArrayList<String>();
        this.weekDay = calculateWeekday(date);

        if(close>open){
            this.uptrend = true;
        }else{
            this.uptrend = false;
        }

        double iPchange = ((this.high - this.low) / this.low) * 100;
        double vola = (this.high - this.low) / this.close;
        this.indicators = new TechnicalIndicators(iPchange, vola);
    }
    //constructor for reading in data from csv file
    public ETFData(String s){
        String[] parts = s.split(",");
        LocalDate date = LocalDate.parse(parts[0], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Double open = Double.parseDouble(parts[1]);
        Double high = Double.parseDouble(parts[2]);
        Double low = Double.parseDouble(parts[3]);
        Double close = Double.parseDouble(parts[4]);
        Double adjClose = Double.parseDouble(parts[5]);
        Integer volume = Integer.parseInt(parts[6]);

        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.adjClose = adjClose;
        this.volume = volume;
        this.month = date.getMonth();
        this.newsHeadlines = new ArrayList<String>();
        this.weekDay = calculateWeekday(date);

        if(close>open){
            this.uptrend = true;
        }else{
            this.uptrend = false;
        }

        double iPchange = ((this.high - this.low) / this.low) * 100;
        double vola = (this.high - this.low) / this.close;
        this.indicators = new TechnicalIndicators(iPchange, vola);
    }

    //set- and get- methods for all attributes
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public void setOpen(double open) {
        this.open = open;
    }
    public void setHigh(double high) {
        this.high = high;
    }
    public void setLow(double low) {
        this.low = low;
    }
    public void setClose(double close) {
        this.close = close;
    }
    public void setAdjClose(double adjClose) {
        this.adjClose = adjClose;
    }
    public void setVolume(long volume) {
        this.volume = volume;
    }
    public void setMonth(Month month) {
        this.month = month;
    }
    public void setWeekDay(WeekDay weekDay) {
        this.weekDay = weekDay;
    }
    public void setDailyChange(double dailyChange) {
        this.dailyChange = dailyChange;
    }
    public void setDailyRange(double dailyRange) {
        this.dailyRange = dailyRange;
    }
    public void setUptrend(boolean uptrend) {
        this.uptrend = uptrend;
    }
    public void setIndicators(TechnicalIndicators indicators) {
        this.indicators = indicators;
    }
    public void setNewsHeadlines(List<String> newsHeadlines) {
        this.newsHeadlines = newsHeadlines;
    }
    public void addNewsHeadline(String news){
        newsHeadlines.add(news);
    }

    public LocalDate getDate() {
        return date;
    }
    public double getOpen() {
        return open;
    }
    public double getHigh() {
        return high;
    }
    public double getLow() {
        return low;
    }
    public double getClose() {
        return close;
    }
    public double getAdjClose() {
        return adjClose;
    }
    public long getVolume() {
        return volume;
    }
    public Month getMonth() {
        return month;
    }
    public WeekDay getWeekDay() {
        return weekDay;
    }
    public double getDailyChange() {
        return dailyChange;
    }
    public double getDailyRange() {
        return dailyRange;
    }
    public boolean isUptrend() {
        return uptrend;
    }
    public TechnicalIndicators getIndicators() {
        return indicators;
    }
    public List<String> getNewsHeadlines() {
        return newsHeadlines;
    }
    
    //info method
    @Override
    public String toString() {
        return "ETFData [date=" +date + ", open=" + open + ", high=" + high + ", low=" + low + ", close=" + close
                + ", adjClose=" + adjClose +", volume=" + volume + ", month="+month+", weekDay=" + weekDay + ", dailyChange="
                + dailyChange + ", dailyRange=" +dailyRange + ", uptrend=" +uptrend + ", indicators=" + indicators
                + "]";
    }
    // helper method for constructor
    private WeekDay calculateWeekday(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        switch (dayOfWeek) {
            case MONDAY:
                return WeekDay.MONDAY;
            case TUESDAY:
                return WeekDay.TUESDAY;
            case WEDNESDAY:
                return WeekDay.WEDNESDAY;
            case THURSDAY:
                return WeekDay.THURSDAY;
            case FRIDAY:
                return WeekDay.FRIDAY;
            case SATURDAY:
                return WeekDay.SATURDAY;
            case SUNDAY:
                return WeekDay.SUNDAY;
            default:
                throw new Error();
        }
    }
    @Override
	public int hashCode() {
		return Objects.hash(date);
	}
    @Override
    public int compareTo(ETFData o) {
        int res = this.date.compareTo(o.date);
        if (res == 0) {
            res = Double.compare(this.dailyChange, o.dailyChange);
        }
        return res;
    }
    @Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true; }
		if (obj == null){
			return false;}
		if (getClass() != obj.getClass()){
			return false;}
		ETFData other = (ETFData) obj;

		return Objects.equals(date, other.date);
	}
    

}





