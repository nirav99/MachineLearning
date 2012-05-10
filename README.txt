A repository to study machine learning concepts.

The package linearregression contains the linear regression model where the input can have one or multiple dimensions.

The package problems contains the sample problems to test various models.

1) House Price Prediction Problem: is a linear regression problems. The input data is a list of housing data (area of house in sq. feet, number of rooms). This problem predicts the price of a house from the training data.

2) Profit Prediction Problem: is also a linear regression problem. The input data is the population of the city with profit values of setting up business in that city. This problem predicts the profit of setting a business in a city based on the training data.

How to build and run these problems:

1) These problems require gnuplot to plot the trend of the cost function with the number of iterations of gradient descent algorithm. In the common package, in the Plot class, set the absolute path to the gnuplot on your system. (This will be improved soon).

2) To build the House Price Prediction Problem, run the bash script buildHousePricePredictionJar.sh.

3) To build the Profit Prediction Problem, run the bash script buildProfitPredictorJar.sh.

4) To run these problems, use the commands
  java -jar HousePricePredictor.jar ./data/HousePricePredictionData.txt

  java -jar ProfitPredictor.jar ./data/ProfitPredictionData.txt

These problems train themselves on the training data while running gradient descent algorithm and predict the outcome. These problems also plot the cost in gradient descent algorithm with each iteration (step) of the algorithm.
