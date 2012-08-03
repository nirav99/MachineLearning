import java.util.*;
import java.io.*;

/**
 * Class to determine the body composition by calculating the body-mass index from height in inches 
 * and weight in pounds.
 */
public class BodyCompositionDataGenerator
{
  private enum BMI
  {
	  UNDER_WEIGHT,
	  NORMAL,
	  OVER_WEIGHT,
	  OBESE
  }
  
  private class DataRecord
  {
	  private int height;      // Height in inches
	  private int weight;      // Weight in pounds
	  private BMI bodyType;    // Body type

	  public String toString()
	  {
		  StringBuffer result = new StringBuffer();
		  result.append(height + "," + weight + ",");

		  if(bodyType == BMI.UNDER_WEIGHT)
			  result.append("1");
		  else
		  if(bodyType == BMI.NORMAL)
			  result.append("2");
		  else
		  if(bodyType == BMI.OVER_WEIGHT)
			  result.append("3");
		  else
			  result.append("4");
		  return result.toString();
	  }
  }
  
  private int numSamples = 0;
  private BufferedWriter writer = null;
  private Random rn = null;
  
  private int numUnderWeightSamples = 0;
  private int numNormalSamples = 0;
  private int numOverWeightSamples = 0;
  private int numObeseSamples = 0;
  
  public BodyCompositionDataGenerator(int numSamples, String outputFileName) throws Exception
  {
	  this.numSamples = numSamples;
	  writer = new BufferedWriter(new FileWriter(new File(outputFileName)));
	  rn = new Random();
  }
  
  public void generateData() throws IOException
  {
	 DataRecord next = null;
	 for(int i = 0; i < numSamples; i++)
	 {
		 next = nextDataSet();
		// System.out.println(next);
		 writer.write(next.toString());
		 writer.newLine();
	 }
	 writer.close();

	 System.out.println("Data Distribution :");
	 System.out.println("Total Samples : " + numSamples);
	 System.out.println();
	 System.out.println("Num. Under-weight Samples : " + numUnderWeightSamples);
	 System.out.println("Num. Normal Samples       : " + numNormalSamples);
	 System.out.println("Num. Over-weight Samples  : " + numOverWeightSamples);
	 System.out.println("Num. Obese Samples        : " + numObeseSamples);
  }
  
  private DataRecord nextDataSet()
  {
    int minHtValue = 58;
    int htRange = 20;
    int minWtValue = 70;
    int wtRange = 230;
    
    DataRecord record = new DataRecord();
    record.height = rn.nextInt(htRange + 1) + minHtValue;
    record.weight = rn.nextInt(wtRange + 1) + minWtValue;
    
    double bmiValue = record.weight * 703 / (record.height * record.height);
    
    if(bmiValue < 18.5)
    {
    	record.bodyType = BMI.UNDER_WEIGHT;
    	numUnderWeightSamples++;
    }
    else
    if(bmiValue < 25)
    {
    	record.bodyType = BMI.NORMAL;
    	numNormalSamples++;
    }
    else
    if(bmiValue < 30)
    {
    	record.bodyType = BMI.OVER_WEIGHT;
    	numOverWeightSamples++;
    }
    else
    {
    	record.bodyType = BMI.OBESE;
    	numObeseSamples++;
    }
    return record;
  }
  
  public static void main(String args[])
  {
	  try
	  {
		  if(args.length != 2)
		  {
			  printUsage();
			  System.exit(-1);
		  }
		  int numSamples = Integer.parseInt(args[0]);
		  BodyCompositionDataGenerator dataGen = new BodyCompositionDataGenerator(numSamples, args[1]);
		  dataGen.generateData();
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
  }
  
  private static void printUsage()
  {
	  System.err.println("Usage :");
	  System.err.println("  numSamples - Number of data samples");
	  System.err.println("  OutputFile - Name of the output file to write data to");
  }
}
