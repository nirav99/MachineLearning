package linearregression;

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
  
  public LinearRegressionModel(double _x[], double _y[], int _numSamples)
  {
    this.numSamples = _numSamples;
    this.x = _x;
    this.y = _y;
    
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
    
    try
    {
      double predictedResults[] = new double[x.length];
      
      for(int i = 0; i < predictedResults.length; i++)
        predictedResults[i] = predict(x[i]);
	}
    catch (Exception e)
    {
	  e.printStackTrace();
	}
  }
  
  /**
   * Given an input on the trained model, return the predicted value of the output.
   * @param input
   * @return
   */
  public double predict(double input)
  {
    double result = theta[0] + theta[1] * input;
    return result;
  }
  
  /**
   * Method to calculate the cost of the prediction for given model parameters theta
   * @return
   */
  private double calculateCost()
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
}
