package linearregression;

import java.io.*;
import java.util.*;

/**
 * Class to build linear regression model with one variable
 * @author nirav99
 *
 */
public class LinearRegressionModel
{
  private double x[];              // Represents the input training data  
  private double y[];              // Represents the input training result
  private int numSamples = 0;      // Number of samples
  private double learningRate = 0; // Learning rate
  private int numIter = 0;         // Number of iterations for gradient descent algorithm
  private double theta[] = null;   // Model parameters
  
  private double costHistory[] = null; // History of cost values for gradient descent
  
  public LinearRegressionModel(String inputFile) throws IOException
  {
    String line;
    String []tokens = null;
    
    ArrayList<Double> inputLabel = new ArrayList<Double>();
    ArrayList<Double> outputLabel = new ArrayList<Double>();
    
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
    
    for(int i = 0; i < numSamples; i++)
    {
      x[i] = inputLabel.get(i).doubleValue();
      y[i] = outputLabel.get(i).doubleValue();
    }
    
    theta = new double[2];
    theta[0] = theta[1] = 0;
    
    System.out.println("Num Training Samples : " + numSamples);
  }
  
  public void setLearningRate(double lRate)
  {
    this.learningRate = lRate;
  }
  
  public void setNumIterations(int nIter)
  {
    this.numIter = nIter;
  }
  
  /**
   * Train the model using gradient descent algorithm
   */
  public void trainModel()
  {
    double temp0, temp1, sum0, sum1;
    
    costHistory = new double[numIter];
    
    for(int i = 0; i < numIter; i++)
    {
      sum0 = 0;
      sum1 = 0;
      for(int j = 0; j < numSamples; j++)
      {
        sum0 = sum0 + ((theta[0] + theta[1] * x[j]) - y[j]);
        sum1 = sum1 + ((theta[0] + theta[1] * x[j]) - y[j]) * x[j];
      }
      temp0 = theta[0] - learningRate * 1.0 / numSamples * sum0;
      temp1 = theta[1] - learningRate * 1.0 / numSamples * sum1;
      theta[0] = temp0;
      theta[1] = temp1;
      costHistory[i] = calculateCost();
    //  System.out.println("Iteration : " + i + " Cost : "  + costHistory[i]);
    }
    System.out.println("Model Parameters :");
    System.out.println(theta[0] + " " + theta[1]);
  }
  
  /**
   * Given an input on the trained model, return the predicted value of the output.
   * @param input
   * @return
   */
  private double predict(double input)
  {
    double result = theta[0] + theta[1] * input;
    return result;
  }
  
  /**
   * Method to calculate the cost of the prediction for given model parameters theta
   * @return
   */
  public double calculateCost()
  {
    double cost = 0;
    
    double temp;
    
    for(int i = 0; i < numSamples; i++)
    {
      temp = (theta[0] + theta[1] * x[i]) - y[i];
      temp = Math.pow(temp, 2);
      cost = cost + temp;
    }
    cost = cost / (2.0 * numSamples);
    return cost;
  }
  
  public static void main(String args[])
  {
    try
    {
      System.out.println("Given data of population of a town in thousands and Profit in thousands, build linear regression model");
      LinearRegressionModel model = new LinearRegressionModel(args[0]);
      model.setLearningRate(0.01);
      model.setNumIterations(1500);
      model.trainModel();
      
      double population = 3.5;
      System.out.println("Profit for population for " + (population * 10000) + " = " + model.predict(population) * 10000);
      
      population = 7;
      System.out.println("Profit for population for " + (population * 10000) + " = " + model.predict(population) * 10000);
      
    }
    catch(Exception e)
    {
      System.err.println(e.getMessage());
      e.printStackTrace();
    }
  }
}
