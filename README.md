# Project Title
Data Anomaly Detection


## Algorithm
I have used z-score & one sample z-test to find data anomaly. As per central limit theorem, many test statistics are approximately normally distributed for large samples. For significance level of 5% two-tailed test 95% of data fall under a value **z-score of 1.96**. Here is algorithm logic of my application:
- We have total 60*24=1440 data points for a day where each data point represent 1 min data.
- I have divided data hourly in **24 samples** each of sample size 60.
- I then computed mean and standard deviation for each 24 samples.
- I then computed z-score for all data points using logic of **abs((x-mean)/standard-deviation)**
- Once I got z-score for all data points I checked anomaly data points for each sample bucket by checking if z-score of data point is > 1.96.
- After this I have got anomaly count for each 24 samples.
- Since 5% of sample size 60 is 3 data anomaly with a **p-value of 0.05**.
- To avoid false positive I have used anomaly **count > 5** which is 10% of two-tailed test and **p-value of 0.10**
- Above z-score will be able to detect data anomaly within same sample bucket but if data anomaly started for the data points that are at the very end of sample bucket (e.g minutes 57,58,59) then their is a probabilty it will fail to detect it and lead into false negative or Type II error scenerio.
- To overcome this scenerio I have added an algo of **one sample z-test** where it compare current sample with next sample and if the variance is quite high between both samples it will detect it.



## Installing & Running Application
The project can be executed on CLI using maven or using Eclipse IDE. Download the git project zip `ProjectData-master.zip`.

**Using CLI Maven on Mac OS:**

Go to the project workspace and built it

```unix
ProjectData mayangupta$ pwd
/Users/mayangupta/desktop/1.Project/eclipse/ProjectData-master/ProjectData

ProjectData mayangupta$ ls -lrt
total 8
drwxr-xr-x@ 4 mayangupta  110263682  136 Jul 29 10:48 target
drwxr-xr-x@ 3 mayangupta  110263682  102 Jul 29 10:48 src
-rwxr-xr-x@ 1 mayangupta  110263682  429 Jul 29 10:48 pom.xml

ProjectData mayangupta$ mvn clean compile install
```

Check if the SNAPSHOT jar file `./target/ProjectData-1.0.0-SNAPSHOT.jar`. Also see we have CSV data in `./src/main/resources/data`.
If both are available then we are ready to run application it

```java
java -cp ".:./target/ProjectData-1.0.0-SNAPSHOT.jar" com.packagecode.dataAnomalyMain ./src/main/resources/data/June13_data.csv
java -cp ".:./target/ProjectData-1.0.0-SNAPSHOT.jar" com.packagecode.dataAnomalyMain ./src/main/resources/data/June14_data.csv
java -cp ".:./target/ProjectData-1.0.0-SNAPSHOT.jar" com.packagecode.dataAnomalyMain ./src/main/resources/data/June16_data.csv
java -cp ".:./target/ProjectData-1.0.0-SNAPSHOT.jar" com.packagecode.dataAnomalyMain ./src/main/resources/data/June17_data.csv

```

**Using Eclipse:**
Import maven project in eclipse and open dataAnomalyMain.java class file.
Go to Run Configurations and pass arguments as data/June17_data.csv and Run it.


## Application Results

For data file June13_data June14_data June16_data O/P will be 
```
Success:: No anomaly found in data
```

For data file June17_data O/P will be 
```
Anomaly    arr[] : [0.0, 3.0, 2.0, 2.0, 1.0, 0.0, 0.0, 0.0, 1.0, 3.0, 4.0, 5.0, 3.0, 4.0, 3.0, 3.0, 4.0, 6.0, 3.0, 0.0, 1.0, 1.0, 0.0, 3.0]

Failure :: Anomaly detected in data

Anomaly Count : 6.0
Z-Test Value  : 65.26898150495896


z-score Anomaly happened at time : 2018-06-18 00:47:00 Data :9998.0 Mean :11276.0 StdDev :448.60136349919105
z-score Anomaly happened at time : 2018-06-18 00:54:00 Data :12326.0 Mean :11276.0 StdDev :448.60136349919105
z-score Anomaly happened at time : 2018-06-18 00:55:00 Data :12474.0 Mean :11276.0 StdDev :448.60136349919105
z-score Anomaly happened at time : 2018-06-18 00:56:00 Data :12412.0 Mean :11276.0 StdDev :448.60136349919105
z-score Anomaly happened at time : 2018-06-18 00:58:00 Data :12342.0 Mean :11276.0 StdDev :448.60136349919105
z-score Anomaly happened at time : 2018-06-18 00:59:00 Data :12876.0 Mean :11276.0 StdDev :448.60136349919105
z-test  Anomaly happened at time : 2018-06-18 01:01:00 Data :13170.0 Mean :15056.0 StdDev :1486.4861699098763
z-test  Anomaly happened at time : 2018-06-18 01:02:00 Data :12790.0 Mean :15056.0 StdDev :1486.4861699098763
z-test  Anomaly happened at time : 2018-06-18 01:03:00 Data :12622.0 Mean :15056.0 StdDev :1486.4861699098763
z-test  Anomaly happened at time : 2018-06-18 01:04:00 Data :12917.0 Mean :15056.0 StdDev :1486.4861699098763
z-test  Anomaly happened at time : 2018-06-18 01:05:00 Data :11636.0 Mean :15056.0 StdDev :1486.4861699098763
z-test  Anomaly happened at time : 2018-06-18 01:06:00 Data :12647.0 Mean :15056.0 StdDev :1486.4861699098763
z-test  Anomaly happened at time : 2018-06-18 01:07:00 Data :13533.0 Mean :15056.0 StdDev :1486.4861699098763
z-test  Anomaly happened at time : 2018-06-18 01:10:00 Data :13291.0 Mean :15056.0 StdDev :1486.4861699098763
z-test  Anomaly happened at time : 2018-06-18 01:11:00 Data :13185.0 Mean :15056.0 StdDev :1486.4861699098763
z-test  Anomaly happened at time : 2018-06-18 01:13:00 Data :16553.0 Mean :15056.0 StdDev :1486.4861699098763
z-test  Anomaly happened at time : 2018-06-18 01:17:00 Data :16915.0 Mean :15056.0 StdDev :1486.4861699098763
z-test  Anomaly happened at time : 2018-06-18 01:18:00 Data :18774.0 Mean :15056.0 StdDev :1486.4861699098763
z-test  Anomaly happened at time : 2018-06-18 01:19:00 Data :18525.0 Mean :15056.0 StdDev :1486.4861699098763
z-test  Anomaly happened at time : 2018-06-18 01:20:00 Data :17197.0 Mean :15056.0 StdDev :1486.4861699098763
z-test  Anomaly happened at time : 2018-06-18 01:22:00 Data :16947.0 Mean :15056.0 StdDev :1486.4861699098763
z-test  Anomaly happened at time : 2018-06-18 01:43:00 Data :17326.0 Mean :15056.0 StdDev :1486.4861699098763
z-test  Anomaly happened at time : 2018-06-18 01:44:00 Data :17418.0 Mean :15056.0 StdDev :1486.4861699098763
z-test  Anomaly happened at time : 2018-06-18 01:46:00 Data :16502.0 Mean :15056.0 StdDev :1486.4861699098763
z-test  Anomaly happened at time : 2018-06-18 01:55:00 Data :13418.0 Mean :15056.0 StdDev :1486.4861699098763
z-test  Anomaly happened at time : 2018-06-18 01:56:00 Data :13455.0 Mean :15056.0 StdDev :1486.4861699098763
z-test  Anomaly happened at time : 2018-06-18 01:59:00 Data :13043.0 Mean :15056.0 StdDev :1486.4861699098763
```


## Requirement
* [Java SDK8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - JDK is a development environment for building applications
* [Maven](https://maven.apache.org/) - Dependency Management
* [Eclipse](https://www.eclipse.org/downloads/) - Eclipse is an IDE used in computer programming


## Author
Mayank Gupta
