package com.packagecode;

import java.util.Arrays;

public class dataAnomalyMain {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		dataAnomalyAlgo obj = new dataAnomalyAlgo();	
		
		//Setter
		obj.setSampleSize(60);

		//Reading CSV data into an array
		obj.getDataFileIntoArray(args[0]);
        
		//Calculating Sample Mean & Standard Deviation for breaking data in multiple sample.
		double[][] arryMeanStd = obj.calSampleMeanStandardDeviation();
    	double meanArray[]=arryMeanStd[0];
    	double stddevArray[]=arryMeanStd[1];

		//Calculating Ceiling value of Z-Score
        obj.calZScore(meanArray,stddevArray);

		//Wring algorithm to see how many data point in sample are greater then 2 z-score 95% data
		int[] arryAnomaly = obj.algoAnomalyDetection();

		obj.getdataPoint(0,"Data");

        System.out.println("\n");            
        System.out.printf("Mean    arr[] : %s",Arrays.toString(meanArray));
        System.out.println("\n");            
        System.out.printf("Stddev  arr[] : %s",Arrays.toString(stddevArray));
		obj.getdataPoint(2,"Z-Score");
		obj.getdataPoint(3,"Anomaly");
        System.out.println("\n");            
        System.out.printf("Anomaly/Sample arr[] : %s",Arrays.toString(arryAnomaly));
        
        //If more then 5 data point then anomaly detected
        //Ideal scenario: Only 3 data point should have more then 2 z-score in sample of 60.
        //If more then 5 data point as our threshold. Then 6/60 which is 10%.
        
        int max = Arrays.stream(arryAnomaly).max().orElse(0);
        if(max>5) {
        	System.out.println("\n");            
        	System.out.printf("Failure :: Anomaly detected in data");
        	System.out.println("\n");            
            obj.showAnomalyData(max,arryAnomaly,meanArray,stddevArray);
         
        } else {
        	System.out.println("\n");            
        	System.out.printf("Success :: No anomaly found in data");
        	System.out.println("\n");            
        }
        
	}
}
