package com.packagecode;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class dataAnomalyAlgo {
    private int sampleSize = 0;
    private double dataPoint[][] = new double[4][1440];
    private double dataScore[][] = new double[4][24];
    private String[] dataDate = new String[1440];

    //Setters
    public void setSampleSize(int sampleVar) {
        this.sampleSize = sampleVar;
    }
    
    //Getter
    public void getdataPoint(int i, String var){
        System.out.println("\n");
        System.out.printf(var+"    arr[] : "+ Arrays.toString(dataPoint[i]));
    }
    
    //Getter
    public void getdataScore(int i, String var){
        System.out.println("\n");
        System.out.printf(var+"    arr[] : "+ Arrays.toString(dataScore[i]));
    }

    public void getDataFileIntoArray(String fileName) {
        int i=0;
        
        //Get file from resources folder
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] dataArray = line.split(",");
                this.dataPoint[0][i] = Integer.parseInt(dataArray[1]);
                this.dataPoint[1][i] = 0;
                this.dataPoint[2][i] = 0;
                this.dataPoint[3][i] = 0;
                this.dataDate[i]=dataArray[0];
                i++;
            }
            scanner.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    public void calSampleMeanStandardDeviation() {
        double dv = 0;
        double mean=0;
        int sum=0;
        int sample=0;
        int z=0;
        
        for (int j=0; j< 1440;j++) {

            if (sample == 0 || sample == sampleSize) {
                sum = (int) Arrays.stream(dataPoint[0],j,j+sampleSize).sum();
                mean=sum/sampleSize;
                sample=0;
                dv=0;
            }
            
            if (sample < sampleSize) {
                double dm = dataPoint[0][j] - mean;
                dv += dm * dm;
                sample++;
            }
            
            if (sample == sampleSize) {
                double stddev = Math.sqrt(dv/sampleSize);
                this.dataScore[0][z]=mean;
                this.dataScore[1][z]=stddev;
                z++;
            }
        }
    }
    
       
    public void calZScore () {
        int sample=1;
        int z=0;
        for (int j=0; j< 1440;j++) {
            
            if (sample < sampleSize)
            {
                double x=(dataPoint[0][j]-this.dataScore[0][z])/this.dataScore[1][z];
                this.dataPoint[2][j]= Math.abs(x);
                sample++;
            }
            else
            {
                double x=(dataPoint[0][j]-this.dataScore[0][z])/this.dataScore[1][z];
                this.dataPoint[2][j]= Math.abs(x);
                z++;
                sample=1;
            }
        }
    }
    
    public double calZTest() {
        for (int j=0; j< 23;j++) {
            this.dataScore[2][j]=Math.abs((this.dataScore[0][j+1]-this.dataScore[0][j])/(this.dataScore[1][j]/Math.sqrt(sampleSize)));
        }
        double maxZTest=Arrays.stream(this.dataScore[2]).max().orElse(0);
        return maxZTest;
    }

    public double algoAnomalyDetection () {
        int sample=1;
        int z=0;
        int anomalyCnt=0;
        
        for (int j=0; j< 1440;j++) {
            if (sample < sampleSize)
            {   
                if (dataPoint[2][j] > 1.96) {
                    anomalyCnt++;
                    dataPoint[3][j]=anomalyCnt;
                }
                sample++;
            }
            else
            {
                if (dataPoint[2][j] > 1.96) {
                    anomalyCnt++;
                    dataPoint[3][j]=anomalyCnt;
                }
                this.dataScore[3][z]=anomalyCnt;
                z++;
                sample=1;
                anomalyCnt=0;
            }               
        }
        double maxCnt = Arrays.stream(this.dataScore[3]).max().orElse(0);
        return maxCnt;
    }
    
    public void showAnomalyData (double maxAnomalyCnt,double maxZTestVal) {
        int x= Arrays.stream(this.dataScore[3]) // IntStream
                .boxed()                        // Stream<Integer>
                .collect(Collectors.toList())   // List<Integer>
                .indexOf(maxAnomalyCnt);
        int y= Arrays.stream(this.dataScore[2]) // IntStream
                .boxed()                        // Stream<Integer>
                .collect(Collectors.toList())   // List<Integer>
                .indexOf(maxZTestVal);
        
        double dataUCL = dataScore[0][y+1]+(3*dataScore[1][y]);
        double dataLCL = dataScore[0][y+1]-(3*dataScore[1][y]);

        for (int j=0; j< 1440;j++) {
            
            if (j > x*sampleSize && dataPoint[3][j] >= 1 && j < (x+1)*sampleSize) {
                System.out.println("z-score Anomaly happened at time : " + dataDate[j] + " Data :" + dataPoint[0][j] + " Mean :" + dataScore[0][x] + " StdDev :" + dataScore[1][x]);
            }
            
            if (maxZTestVal> 60 && j > (y+1)*sampleSize && j < (y+2)*sampleSize && (dataPoint[0][j] > dataUCL || dataPoint[0][j] < dataLCL) ) {
                System.out.println("z-test  Anomaly happened at time : " + dataDate[j] + " Data :" + dataPoint[0][j] + " Mean :" + dataScore[0][y+1] + " StdDev :" + dataScore[1][y+1]);
            }
        }
    }
}
