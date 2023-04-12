ETFData - Documentation (MSCI Austria Index ETF)

ETFData is a Java class that represents the data of an Exchange Traded Fund (ETF). It includes various data points such as date, open, high, low, close, adjusted close, volume, and a list of news headlines. It also provides derived variables for traders like daily change, daily range, uptrend, and technical indicators. It is made so that the historical data of the MSCI Austria ETF can be read in.

Classes and Enums

ETFData
TechnicalIndicators
ETFDatas
ETFDataFactory
WeekDay (Enum)

Usage

To create an instance of the ETFData class, it is necessary to put the csv files with the data into the data folder.
The ETFData class provides setter and getter methods for all attributes to change or show the values of the attribute and there is a toString method to show all the attributes of an object.
The ETFDataFactory class is used to read the ETF data of the file and create an instance of all the data entries (ETFDatas), which is a collection of ETFData objects.
The TechnicalIndicators class includes additional information about the ETF data, like for example the intraday change in percent or the volatility.

Dependencies

 The java.time package is used for handling dates
 The java.nio.file package is used for reading files

Testing

If you want to try out the application you can start the main method in the ETFDataTest class with testdata from the data folder


