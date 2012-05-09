package linearregression;

import common.*;

public class LinearRegressionModel
{
  private double x[][];            // Represents the input training data  
  private double y[];              // Represents the input training result
  private int numSamples = 0;      // Number of samples
  private double learningRate = 0; // Learning rate
  private int numIter = 0;         // Number of iterations for gradient descent algorithm
  private double theta[] = null;   // Model parameters
  private int numDimensions = 0;   // Number of dimensions in input X
  
  private double costHistory[] = null; // History of cost values for gradient descent
  
  // Max and min values for feature scaling
  private double maxList[] = null;
  private double minList[] = null;
  private double avgList[] = null;
  
  public LinearRegressionModel(double _x[], double _y[], int numSamples)
  {
    double input[][] = new double[numSamples][1];
    
    for(int i = 0; i < _x.length; i++)
      input[i][0] = _x[i];
    LinearRegressionHelper(input, _y, 1, numSamples);
  }
  
  public LinearRegressionModel(double _x[][], double _y[], int numDimensions, int numSamples)
  {
    LinearRegressionHelper(_x, _y, numDimensions, numSamples);
  }
  
  private void LinearRegressionHelper(double _x[][], double _y[], int numDimensions, int numSamples)
  {
	this.numDimensions = numDimensions;
	this.numSamples = numSamples;
	
	y = new double[numSamples];
	x = new double[numSamples][numDimensions + 1];
	theta = new double[numDimensions + 1];
	
	maxList = new double[numDimensions];
	minList = new double[numDimensions];
	avgList = new double[numDimensions];
	
	for(int i = 0; i < numSamples; i++)
	{
      y[i] = _y[i];
      x[i][0] = 1;
      
	  for(int j = 0; j < numDimensions; j++)
        x[i][j + 1] = _x[i][j];
	}
	scaleInput();
  }
  
  public void setLearningRate(double lRate)
  {
    this.learningRate = lRate;
  }
  
  public void setNumIterations(int nIter)
  {
    this.numIter = nIter;
  }
  
  public void trainModel()
  {
    costHistory = new double[numIter];
    
    double sum[] = null;
    
    for(int k = 0; k < numIter; k++)
    {
      sum = new double[theta.length];
      
      for(int i = 0; i < numSamples; i++)
      {
        for(int j = 0; j < theta.length; j++)
        {
          sum[j] = sum[j] + (predictionHelper(x[i]) - y[i]) * x[i][j];
        }
      }
      
      for(int j = 0; j < theta.length; j++)
      {
        theta[j] = theta[j] - learningRate * 1.0 / numSamples * sum[j];
      }
      sum = null;
      
      costHistory[k] = calculateCost();
    }
    double iterationValues[] = new double[costHistory.length];
    for(int i = 0; i < iterationValues.length; i++)
      iterationValues[i] = i + 1;
    
    try
    {
      Plot p = new Plot("CostFunctionTrend.png", "Cost Function Trend", "Iterations", "Cost", "", iterationValues, costHistory);
      p.setPlotStyle(PlotStyle.LINE);
      p.plotGraph();
	} 
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public double predict(double _input[])
  {
    double input[] = new double[theta.length];
    input[0] = 1;
    
    for(int j = 0; j < _input.length; j++)
      input[j + 1] = _input[j];
    
    if(input.length > 2)
    {
      for(int j = 1; j < theta.length; j++)
        input[j] = (input[j] - avgList[j -1]) / (1.0 * (maxList[j -1] - minList[j -1]));
    }
    return predictionHelper(input);
  }
  
  private void scaleInput()
  {
    if(numDimensions <= 1)
      return;
    
    for(int j = 1; j <= numDimensions; j++)
	{
      maxList[j -1] = x[0][j];
      minList[j -1] = x[0][j];
      avgList[j -1] = 0;
      
	  for(int i = 0; i < numSamples; i++)
	  {
        if(maxList[j -1] < x[i][j])
          maxList[j -1] = x[i][j];      
        if(minList[j -1] > x[i][j])
          minList[j -1] = x[i][j];
        avgList[j -1] += x[i][j];
      }
	}
	
	for(int j = 0; j < numDimensions; j++)
      avgList[j] = 1.0 * avgList[j] / numSamples;
	
	for(int i = 0; i < numSamples; i++)
	{
      for(int j = 1; j <= numDimensions; j++)
      {
        x[i][j] = (x[i][j] - avgList[j -1]) / (1.0 * (maxList[j -1] - minList[j-1]));
      }
	}
  }
  
  private double predictionHelper(double input[])
  {
    double result = 0;
    
    for(int j = 0; j < input.length; j++)
    {
      result = result + theta[j] * input[j];
    }
    return result;
  }
  
  private double calculateCost()
  {
    double cost = 0;
    double temp;
    
    for(int i = 0; i < numSamples; i++)
    {
      temp = predictionHelper(x[i]) - y[i];
      cost = cost + Math.pow(temp, 2);
    }
    cost = cost / ( 2.0 * numSamples);
    return cost;
  }
}
