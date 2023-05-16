package types;

//class for technical indicators for trader
public class TechnicalIndicators {
    //variables
    private double intradayPercentageChange;
    private double volatility;

    //set- and get-methods
    public void setIntradayPercentageChange(double intradayPercentageChange) {
        this.intradayPercentageChange = intradayPercentageChange;
    }
    public void setVolatility(double volatility) {
        this.volatility = volatility;
    }
    public double getIntradayPercentageChange() {
        return Math.round(intradayPercentageChange * 1e4) / 1e4;
    }
    public double getVolatility() {
        return Math.round(volatility * 1e5) / 1e5; // round 5 digits after decimal point so its not too long
    }
    //constructors
    public TechnicalIndicators(double intradayPercentageChange, double volatility) {
        this.intradayPercentageChange = intradayPercentageChange;
        this.volatility = volatility;
    }
    @Override
    public String toString() {
        return "TechnicalIndicators [intradayPercentageChange=" + (Math.round(intradayPercentageChange * 1e4) / 1e4) + ", volatility="
                + (Math.round(volatility * 1e5) / 1e5) + "]"; // round digits
    }
    
    
    
}

