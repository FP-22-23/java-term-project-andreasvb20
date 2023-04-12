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
        return intradayPercentageChange;
    }
    public double getVolatility() {
        return volatility;
    }
    //constructors
    public TechnicalIndicators(double intradayPercentageChange, double volatility) {
        this.intradayPercentageChange = intradayPercentageChange;
        this.volatility = volatility;
    }
    @Override
    public String toString() {
        return "TechnicalIndicators [intradayPercentageChange=" + intradayPercentageChange + ", volatility="
                + volatility + "]";
    }
    
    
    
}
