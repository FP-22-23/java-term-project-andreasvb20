ETFData - Documentation (MSCI Austria Index ETF)

ETFData is a Java class that represents the data of an Exchange Traded Fund (ETF). It includrs various data points like date, open, high, low, close, adjusted close, volume, month and a list of news headlines. It also provides derived variables for traders like daily change, daily range, uptrend, and technical indicators. It is made so that the historical data of the MSCI Austria ETF can be read and processed.

Classes and Enums

The project contains the following classes and enums:

ETFData: - represents a single entry of ETF data in one day
TechnicalIndicators
ETFDatas: - includes additional infos about the ETF data, like for example the  volatility or the intraday change in percent .
	  - moreover it includes methods for filtering and grouping the ETF data:
		-> allETFsUptrend(): - checks if all ETFs are in an uptrend
		-> countUptrendETFs(): - returns the number of ETFs which  are in an uptrend
		-> filterUptrendETFs(): - filters the ETFs that are uptrend  and returns a new ETFDatas object with all the ETFs which are in an uptrend
		-> groupByMonth(): - groups the ETFs by month,  returning a Map with the month as the key and the list of ETFs as the value
		-> groupByMonthWithCount(): groups the ETFs by month, returning a Map with the month as the key and the number of ETFs as the value
ETFDataFactory: - is used to read the ETF data entries from a csv file and create an ETFDatas object for collecting ETFData objects
WeekDay (Enum): - is an enum of the days of the week

Usage

To create an instance of the ETFData class, it is necessary to put the csv files with the data into the data folder.
The ETFData class provides setter and getter methods for all attributes to change or show the values of the attribute and there is a toString method to show all the attributes of an object.
The ETFDataFactory class is used to read the ETF data of the file and create an instance of all the data entries (ETFDatas), which is a collection of ETFData objects.
The TechnicalIndicators class includes additional information about the ETF data, like for example the intraday change in percent or the volatility.

Dependencies

 The java.time package is used for handling dates
 The java.nio.file package is used for reading files
 The java.util package is used for changing data

Testing

If you want to try out the application you can start the main method in the ETFDataTest class with testdata from the data folder.




