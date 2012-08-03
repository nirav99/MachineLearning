% Code to train and predict the outcome of a neural network with body composition data, height in inches, weight in inches 
% and a label describing body type (under-weight, normal, over-weight and obese).

% The data file classifies the body composition based on BMI (Body-Mass index values. BMI values unto 18.5 is under-weight, 
% BMI values unto 25 is normal, unto 30 is over-weight and over 30 is obese.

clear ; close all; clc

input = load("BodyCompositionData.txt", "");
X = input(:, 1:2);
y = input(:, 3);

fprintf('\nPlotting input data');
plotData(X, y);
fprintf('\nPress a key to continue');
pause;

% Determine the type of neural network. Input layer with 2 nodes, one hidden layer with 4 nodes and 4 output nodes.
input_layer_size = 2;
hidden_layer_size = 4;
num_labels = 4;
m = size(X, 1);

% Randomly generate initial weights for symmetry breaking
initial_Theta1 = randInitializeWeights(input_layer_size, hidden_layer_size);
initial_Theta2 = randInitializeWeights(hidden_layer_size, num_labels);

% Unroll parameters
initial_nn_params = [initial_Theta1(:) ; initial_Theta2(:)];


fprintf('\nTraining Neural Network... \n')

% Train the neural network for specified number of iterations.
options = optimset('MaxIter', 800);

% Set regularization
lambda = 1.0;

% Create "short hand" for the cost function to be minimized
costFunction = @(p) nnCostFunction(p, ...
                                   input_layer_size, ...
                                   hidden_layer_size, ...
                                   num_labels, X, y, lambda);

% Now, costFunction is a function that takes in only one argument (the
% neural network parameters)
[nn_params, cost] = fmincg(costFunction, initial_nn_params, options);

% Obtain Theta1 and Theta2 back from nn_params
% Theta1 is the model parameters for the hidden layer and Theta2 for the hidden layer
Theta1 = reshape(nn_params(1:hidden_layer_size * (input_layer_size + 1)), ...
                 hidden_layer_size, (input_layer_size + 1));

Theta2 = reshape(nn_params((1 + (hidden_layer_size * (input_layer_size + 1))):end), ...
                 num_labels, (hidden_layer_size + 1));

% Predict the values for entire input and determine the training set accuracy.
pred = predict(Theta1, Theta2, X);
fprintf('\nTraining Set Accuracy: %f\n', mean(double(pred == y)) * 100);

% Predict the body composition for some random input
height = 68;
weight = 160;

input = [height, weight];
pred = predict(Theta1, Theta2, input);
showBodyComposition(height, weight, pred);

height = 75;
weight = 120;

input = [height, weight];
pred = predict(Theta1, Theta2, input);
showBodyComposition(height, weight, pred);

height = 58;
weight = 268;

input = [height, weight];
pred = predict(Theta1, Theta2, input);
showBodyComposition(height, weight, pred);