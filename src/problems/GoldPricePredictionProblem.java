package problems;

import java.io.*;
import java.util.*;

import common.*;
import linearregression.*;

public class GoldPricePredictionProblem
{
  public static void main(String args[])
  {
	 String line;
	    
     System.out.println("Given historical data of gold price for each month, build linear regression to and predict gold price");
     String inputFile = args[0];
     System.out.println("Input file = " + inputFile);
     
     try
     {
         String []tokens = null;
         
         ArrayList<Double> outputLabel = new ArrayList<Double>();
         double x[] = null;
         double y[] = null;
         double predictedY[] = null;
         
         int numSamples;
         BufferedReader reader = new BufferedReader(new FileReader(new File(inputFile)));
         
         while((line = reader.readLine()) != null)
         {
           tokens = line.split(",");
           
           if(tokens.length != 2)
             throw new IllegalArgumentException();
           outputLabel.add(Double.valueOf(tokens[1]));
         }
         reader.close();
           
         numSamples = outputLabel.size();
         x = new double[numSamples];
         y = new double[numSamples];
         predictedY = new double[numSamples];
         
         for(int i = 0; i < numSamples; i++)
         {
           x[i] = i + 1;
           y[i] = outputLabel.get(i);
         }
         
         //LinearRegressionModel model = new LinearRegressionModel(x, y, numSamples);
         LinearRegressionModel model = new LinearRegressionModel(x, y, numSamples);
         model.setLearningRate(0.005);
       //  model.setNumIterations(6000);
         model.trainModel();
         
         double month = 3;
         System.out.println("Gold Price for Month : " + month + " = " + model.predict(new double[]{month}));
         month = 5;
         System.out.println("Gold Price for Month : " + month + " = " + model.predict(new double[]{month}));
         
         for(int i = 0; i < numSamples; i++)
             predictedY[i] = model.predict(new double[]{x[i]});

          Plot p = new Plot("GoldPricePrediction.png", "Gold Price", "Month", "Gold Price for 1 oz.", "Actual Data", "Predicted Data", x, y, predictedY);
     	  Plot.setPlotStyle(PlotStyle.POINTS, PlotStyle.LINE);
     	  p.plotGraph();
     }
     catch(Exception e)
     {
       System.err.println(e.getMessage());
       e.printStackTrace();
     }
  }
}
