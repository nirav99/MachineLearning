A repository to study machine learning concepts.

Linear Regression:

The package linearregression contains the linear regression model where the input can have one or multiple dimensions.

The package problems contains the sample problems to test various models.

1) Gold Price Prediction Problem: Given an input data of historical trend of gold price for 1 oz., predict the price for gold for a given month.

2) House Price Prediction Problem: is a linear regression problems. The input data is a list of housing data (area of house in sq. feet, number of rooms). This problem predicts the price of a house from the training data.

3) Profit Prediction Problem: is also a linear regression problem. The input data is the population of the city with profit values of setting up business in that city. This problem predicts the profit of setting a business in a city based on the training data.

How to build and run these problems:

1) These problems require gnuplot to plot the trend of the cost function with the number of iterations of gradient descent algorithm. In the common package, in the Plot class, set the absolute path to the gnuplot on your system. (This will be improved soon).

2) To build the House Price Prediction Problem, run the bash script buildHousePricePredictionJar.sh.

3) To build the Profit Prediction Problem, run the bash script buildProfitPredictorJar.sh.

4) To build the Gold Price Prediction Problem, run the bash script buildGoldPricePredictorJar.sh.

5) To run these problems, use the commands
  java -jar HousePricePredictor.jar ./data/HousePricePredictionData.txt

  java -jar ProfitPredictor.jar ./data/ProfitPredictionData.txt

  java -jar GoldPricePredictor.jar ./data/GoldPriceDataLast4Years.csv

These problems train themselves on the training data while running gradient descent algorithm and predict the outcome. These problems also plot the cost in gradient descent algorithm with each iteration (step) of the algorithm.
Moreover, when the input has only one feature, the trend of actual data vs predicted data is also built.

Neural Networks:

The directory BodyCompositionClassifier obtains the height, weight data and classifies different body types in under-weight, normal, obese and over-weight. Classification of the training data is determined by calculating the body-mass index (BMI).
It uses a neural network with 2 input nodes, one hidden layer with 4 nodes and an output layer of 4 nodes. This project trains the neural network data using forward and back propagation algorithm and then applies the learned parameters against the training data and computes the accuracy of the training set. 
This is a demonstration of how to represent and train the neural networks and determine the outcome using neural networks.