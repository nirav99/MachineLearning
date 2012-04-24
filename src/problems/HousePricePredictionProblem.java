package problems;

import java.io.*;
import java.util.*;

import common.*;
import linearregression.*;

public class HousePricePredictionProblem
{
  public static void main(String args[])
  {
    String line;
    
    System.out.println("Given data of population of a town in thousands and Profit in thousands, build linear regression model");
    String inputFile = args[0];
    try
    {
      String []tokens = null;
        
      ArrayList<Double> inputLabel = new ArrayList<Double>();
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
        inputLabel.add(Double.valueOf(tokens[0]));
        outputLabel.add(Double.valueOf(tokens[1]));
      }
      reader.close();
        
      numSamples = inputLabel.size();
      x = new double[numSamples];
      y = new double[numSamples];
      predictedY = new double[numSamples];
      
      for(int i = 0; i < numSamples; i++)
      {
        x[i] = inputLabel.get(i);
        y[i] = outputLabel.get(i);
      }
      
      LinearRegressionModel model = new LinearRegressionModel(x, y, numSamples);
      model.setLearningRate(0.01);
      model.setNumIterations(1500);
      model.trainModel();
      
      double population = 3.5;
      System.out.println("Profit for population for " + (population * 10000) + " = " + model.predict(population) * 10000);
      
      population = 7;
      System.out.println("Profit for population for " + (population * 10000) + " = " + model.predict(population) * 10000);
      
      for(int i = 0; i < numSamples; i++)
        predictedY[i] = model.predict(x[i]);

      Plot p = new Plot("ProfitPrediction.png", "Profit Prediction", "Population", "Profit", "Actual Data", "Predicted Data", x, y, predictedY);
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
