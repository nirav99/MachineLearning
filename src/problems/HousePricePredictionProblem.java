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
    
    System.out.println("Given data of house square feet, number of rooms and price of house, build linear regression model");
    String inputFile = args[0];
    System.out.println("Input file = " + inputFile);
    try
    {
      String []tokens = null;
        
      ArrayList<Double> inputLabel1 = new ArrayList<Double>();
      ArrayList<Double> inputLabel2 = new ArrayList<Double>();
      ArrayList<Double> outputLabel = new ArrayList<Double>();
      double x[][] = null;
      double y[] = null;
      
      int numSamples;
      BufferedReader reader = new BufferedReader(new FileReader(new File(inputFile)));
     
      
      while((line = reader.readLine()) != null)
      {
        tokens = line.split(",");
          
        if(tokens.length != 3)
          throw new IllegalArgumentException();
        inputLabel1.add(Double.valueOf(tokens[0]));
        inputLabel2.add(Double.valueOf(tokens[1]));
        outputLabel.add(Double.valueOf(tokens[2]));
      }
      reader.close();
        
      numSamples = inputLabel1.size();
      x = new double[numSamples][2];
      y = new double[numSamples];
      
      for(int i = 0; i < numSamples; i++)
      {
        x[i][0] = inputLabel1.get(i);
        x[i][1] = inputLabel2.get(i);
        y[i] = outputLabel.get(i);
      }
      
      LinearRegressionModel model = new LinearRegressionModel(x, y, 2, numSamples);
      model.setLearningRate(0.01);
      model.setNumIterations(5000);
      model.trainModel();
      
      double houseData[] = new double[]{1650.0, 3.0};
      houseData = new double[]{852.0, 2.0};
      System.out.println("House Sq. feet : " + houseData[0] + " Num Rooms : " + houseData[1] + " Predicted Price : "+ model.predict(houseData));
    }
    catch(Exception e)
    {
      System.err.println(e.getMessage());
      e.printStackTrace();
    }
  }
}
