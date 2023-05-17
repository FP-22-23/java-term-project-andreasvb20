Second Quarter Project Programming
Fundamentals (Course 2022/23)
Author: Andreas von Bendemann uvus:MGW9827

This project revolves around the ETFData Java class, which handles the historical data of the MSCI Austria Index Exchange Traded Fund (ETF).

Project folder structure
• /src: Directory with the source code
o types: Package containing the project types
o test: Package containing the test classes of the project
o utils: Package containing the utility classes
• /data: Contains the project dataset
o EWD.csv: csv file containing historical data from MSCI Austria Index ETF

Dataset structure
The dataset for this project consists of ETF data related to the MSCI Austria Index. While the original dataset may be obtained from a specific source, it has been modified to meet the requirements of this term project. The columns included in the dataset are the following 8:

date: the date when the ETF data entry was recorded
open: the opening price of the ETF on the given date
high: the highest price reached by the ETF during the day
low: the lowest price reached by the ETF during the day
close: the closing price of the ETF on the given date
adj close: the adjusted closing price of the ETF, accounting for factors such as dividends and stock splits
volume: the trading volume of the ETF on the given date

Implemented types
The types implemented in the project are as follows:


Base Class: ETFData
Represents ETF (Exchange Traded Fund) data for a specific date.
Properties:

date (LocalDate): The date of the ETF data
open (double): The opening price of the ETF
high (double): The highest price of the ETF during the day
low (double): The lowest price of the ETF during the day
close (double): The closing price of the ETF
adjClose (double): The adjusted closing price of the ETF
volume (long): The trading volume of the ETF
month (Month): The month of the ETF data
weekDay (WeekDay): The weekday of the ETF data
dailyChange (double): The difference between the closing price and the opening price of the ETF
dailyRange (double): The difference between the highest price and the lowest price of the ETF during the day
uptrend (boolean): Indicates whether the ETF is in an uptrend or not
indicators (TechnicalIndicators): Technical indicators for the ETF
newsHeadlines (List<String>): List of news headlines related to the ETF

Constructors:
ETFData(LocalDate date, double open, double high, double low, double close, double adjClose, long volume): Creates an ETFData object with the given parameters
ETFData(String s): Creates an ETFData object by parsing the given CSV string

Equality criterion: Two items are equal if their date is equal.

Sort order: By date in ascending order, then by daily change in ascending order

Class: TechnicalIndicators
Represents technical indicators for an ETF to enlarge the properties of the ETFData base class It contains the following properties:
intradayPercentageChange (double): The percentage change in the ETF price during the day
volatility (double): The volatility of the ETF

Constructors:
TechnicalIndicators(double intradayPercentageChange, double volatility): Creates a TechnicalIndicators object with the given parameters

Class: ETFDataFactory
Provides factory methods for creating ETFData objects

Methods:
readETFDataFile(String fileName): Reads ETF data from the specified CSV file and returns an ETFDatas object containing the data
parseETFData(String line): Parses a CSV line and returns an ETFData object

Auxiliary types

Enum: WeekDay
Represents the days of the week



Container Type - Line items
Class containing objects of type ETFData

Properties
etfdatas: A set of ETFData objects contained in the ETFDatas container

Constructors
ETFDatas(): Default constructor, creates an empty ETFDatas object without any stored ETFData items
ETFDatas(Stream<ETFData> st): Constructor that takes a stream of ETFData objects as a parameter, creates an ETFDatas object with the ETFData items included in the given stream.

Equality Criterion: Two objects of the class are equal, when they are identical

Other Operations

Basic Methods
-> void addEtfData(ETFData etfData): Adds an ETFData object to the ETFDatas container
-> void addEtfDatas(Collection<ETFData> etfDatas): Adds a collection of ETFData objects to the ETFDatas container
-> void removeEtfData(ETFData etfData): Removes an ETFData object from the ETFDatas container
-> int getNumberOfEtfDatas(): Returns the number of ETFData objects in the ETFDatas container

Methods for sequential treatments
-> boolean allETFsUptrend(): Checks if all ETFs in the container are on an uptrend
-> int countUptrendETFs(): Counts the number of ETFs in the container that are on an uptrend
-> ETFDatas filterUptrendETFs(): Filters the ETFs in the container that are on an uptrend and returns a new ETFDatas object containing only those ETFs
-> Map<Month, List<ETFData>> groupByMonth(): Groups the ETFs in the container by month and returns a map where the keys are months and the values are lists of ETFData objects for each month
-> Map<Month, Long> groupByMonthWithCount(): Groups the ETFs in the container by month and returns a map where the keys are months and the values are the counts of ETFData objects for each month

Stream methods
-> boolean allETFsUptrendStream(): Checks if all ETFs in the container are on an uptrend using streams
-> long countUptrendETFsStream(): Counts the number of ETFs in the container that are on an uptrend using streams
-> ETFDatas filterUptrendETFsStream(): Filters the ETFs in the container that are on an uptrend using streams and returns a new ETFDatas object containing only those ETFs
-> Optional<ETFData> getMaxDailyChangeUptrend(): Finds the ETF with the maximum daily change among the ETFs in an uptrend within the ETFDatas container and returns it as an Optional using streams
-> List<ETFData> filterUptrendAndSortByDailyChangeDescending(): Filters the ETFs in the container that are on an uptrend, sorts them by daily change in descending order, and returns a list containing those ETFs
-> Map<Month, List<ETFData>> groupByMonthStream(): Groups the ETFs in the container by month using streams and returns a map where the keys are months and the values are lists of ETFData objects for each month
-> Map<Month, Double> averageDailyChangeByMonth(): Calculates the average daily change of ETFData objects for each month using streams and returns a map where the keys are months and the values are the average daily changes
-> Map<Month, Double> maxDailyChangeByMonth(): Finds the maximum daily change of ETFData objects for each month using streams and returns a map where the keys are months and the values are the maximum daily changes
-> NavigableMap<Month, List<ETFData>> topNByMonth(int n, Function<ETFData, Double> attributeFunction): Returns a navigable map (sorted map) where each month is associated with a list of n ETFData objects based on a given attribute function. The map is sorted by month, and the list of ETFData objects is sorted based on the attribute function in descending order
-> Object getKeyWExtremeValue(Map<Object, Double> inputMap, boolean largest): Calculates a map and returns the key associated with the largest or smallest value of the entire map, depending on the value of the largest parameter




