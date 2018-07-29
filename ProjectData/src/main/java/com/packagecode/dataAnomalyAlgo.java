package com.packagecode;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class dataAnomalyAlgo {
	private int sampleSize = 0;
	private double dataPoint[][] = new double[4][1440];
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
	

	public double[][] calSampleMeanStandardDeviation() {
    	double[][] arryMeanStdev=new double[2][24];
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
        		arryMeanStdev[0][z]=mean;
        		arryMeanStdev[1][z]=stddev;
        		z++;
        	}
        }
		return arryMeanStdev;
	}
	
       
	public void calZScore (double arryMean[], double arryStdev[]) {
        int sample=1;
        int z=0;
        for (int j=0; j< 1440;j++) {
        	
        	if (sample < sampleSize)
        	{
        		double x=(dataPoint[0][j]-arryMean[z])/arryStdev[z];
        		dataPoint[2][j]=(int) Math.ceil(Math.abs(x));
        		sample++;
        	}
        	else
        	{
        		double x=(dataPoint[0][j]-arryMean[z])/arryStdev[z];
        		dataPoint[2][j]=(int) Math.ceil(Math.abs(x));
                z++;
                sample=1;
        	}
        }
	}
	

	public int[] algoAnomalyDetection () {
    	int[] arryAnomaly=new int[24];
        int sample=1;
        int z=0;
        int anomalyCnt=0;
        
        for (int j=0; j< 1440;j++) {
        	if (sample < sampleSize)
        	{   
        		if (dataPoint[2][j] >= 3) {
        			anomalyCnt++;
        			dataPoint[3][j]=anomalyCnt;
        		}
        		sample++;
        	}
        	else
        	{
        		if (dataPoint[2][j] >= 3) {
        			anomalyCnt++;
        			dataPoint[3][j]=anomalyCnt;
        		}
        		arryAnomaly[z]=anomalyCnt;
        		z++;
                sample=1;
                anomalyCnt=0;
        	}            	
        }
        return arryAnomaly;
	}
	
	public void showAnomalyData (int max, int[] arryAnomaly, double[] arryMean, double[] arryStdev) {
    	int x= Arrays.stream(arryAnomaly) 		// IntStream
				.boxed()						// Stream<Integer>
				.collect(Collectors.toList())   // List<Integer>
				.indexOf(max);
        for (int j=0; j< 1440;j++) {
    		if (j > x*sampleSize && dataPoint[3][j] >= 1 && j < (x+1)*sampleSize) {
    	        System.out.println("Anomaly happened at time : " + dataDate[j] + " Data :" + dataPoint[0][j] + " Mean :" + arryMean[x] + " StdDev :" + arryStdev[x]);
    		}
        }
	}


}
