# Project Title
Data Anomaly Detection


## Algorithm
What things you need to install the software and how to install them


## Installing
The project can be executed used CLI using maven or using eclipse. Download the git project.

**Using CLI Maven:**

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
java -cp ".:./target/ProjectData-1.0.0-SNAPSHOT.jar" com.packagecode.dataAnomalyMain ./src/main/resources/data/June15_data.csv
java -cp ".:./target/ProjectData-1.0.0-SNAPSHOT.jar" com.packagecode.dataAnomalyMain ./src/main/resources/data/June17_data.csv

```

**Using Eclipse:**
Import maven project in eclipse and open dataAnomalyMain.java class file.
Go to Run Configurations and pass arguments as data/June17_data.csv and Run it.


## Running Application

For data file June13_data June14_data June16_data O/P will be 
```
Success:: No anomaly found in data
```

For data file June17_data O/P will be 
```
Anomaly arr[] : [0, 3, 2, 2, 1, 0, 0, 0, 1, 3, 2, 4, 3, 4, 3, 3, 3, 6, 3, 0, 1, 1, 0, 3]

Failure:: Anomaly detected in data

Anomaly happened at time : 2018-06-18 00:47:00 Data :9998.0 Mean :11276.0 StdDev :448.60136349919105
Anomaly happened at time : 2018-06-18 00:54:00 Data :12326.0 Mean :11276.0 StdDev :448.60136349919105
Anomaly happened at time : 2018-06-18 00:55:00 Data :12474.0 Mean :11276.0 StdDev :448.60136349919105
Anomaly happened at time : 2018-06-18 00:56:00 Data :12412.0 Mean :11276.0 StdDev :448.60136349919105
Anomaly happened at time : 2018-06-18 00:58:00 Data :12342.0 Mean :11276.0 StdDev :448.60136349919105
Anomaly happened at time : 2018-06-18 00:59:00 Data :12876.0 Mean :11276.0 StdDev :448.60136349919105
```


## Requirement
* [Java SDK8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - JDK is a development environment for building applications
* [Maven](https://maven.apache.org/) - Dependency Management
* [Eclipse](https://www.eclipse.org/downloads/) - Eclipse is an IDE used in computer programming


## Author
Mayank Gupta
