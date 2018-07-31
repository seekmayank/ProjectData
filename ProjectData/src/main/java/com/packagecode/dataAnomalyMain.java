package com.packagecode;

public class dataAnomalyMain {

    public static void main(String[] args) {
        
        // TODO Auto-generated method stub
        dataAnomalyAlgo obj = new dataAnomalyAlgo();    
        
        //Setting Sample Size
        obj.setSampleSize(60);

        //Reading CSV data into a data array
        obj.getDataFileIntoArray(args[0]);
        
        //Calculating Sample Mean & Standard Deviation for multiple sample
        obj.calSampleMeanStandardDeviation();

        //Calculating absolute value of Z-Score for all data point
        obj.calZScore();

        //Calculating one sample Z-Test
        double maxZTestVal=obj.calZTest();

        //Wring algorithm to see how many data point are either > 1.96 z-score or 60 z-test value
        double maxAnomalyCnt=obj.algoAnomalyDetection();

        obj.getdataPoint(0,"Data   ");
        obj.getdataScore(0,"Mean   ");
        obj.getdataScore(1,"Stddev ");
        obj.getdataScore(2,"Z-Test ");
        obj.getdataScore(3,"Anomaly");

        if(maxAnomalyCnt >= 6 || maxZTestVal >= 60) {
            System.out.println("\n");            
            System.out.printf("Failure :: Anomaly detected in data");
            System.out.println("\n");            
            System.out.println("Anomaly Count : "+maxAnomalyCnt);
            System.out.println("Z-Test Value  : "+maxZTestVal);
            System.out.println("\n");            
            obj.showAnomalyData(maxAnomalyCnt,maxZTestVal);
            
        } else {
            System.out.println("\n");            
            System.out.printf("Success :: No anomaly found in data");
            System.out.println("\n");           
            System.out.println("Anomaly Count : "+maxAnomalyCnt);
            System.out.println("Z-Test Value  : "+maxZTestVal);
            System.out.println("\n");           
        }
        
    }
}
