function plotData(X, y)
%PLOTDATA Plots the data points X and y into a new figure 
%   PLOTDATA(x,y) plots the data points with a different color for each character class.

% Create New Figure
figure; hold on;

% ====================== YOUR CODE HERE ======================
% Instructions: Plot the positive and negative examples on a
%               2D plot, using the option 'k+' for the positive
%               examples and 'ko' for the negative examples.
%

underWeight = find(y == 1); 
normal = find(y == 2);
overWeight = find(y == 3);
obese = find(y == 4);

plot(X(underWeight, 1), X(underWeight, 2), 'k+', 'MarkerFaceColor', 'k', 'MarkerSize', 7);
plot(X(normal, 1), X(normal, 2), 'r+', 'MarkerFaceColor', 'r', 'MarkerSize', 7);
plot(X(overWeight, 1), X(overWeight, 2), 'b+', 'MarkerFaceColor', 'b', 'MarkerSize', 7);
plot(X(obese, 1), X(obese, 2), 'g+', 'MarkerFaceColor', 'g', 'MarkerSize', 7);
legend('Under-Weight', 'Normal', 'Over-Weight', 'Obese');
title("Body Composition Distribution Data");
xlabel("Height in inches");
ylabel("Weight in lbs");





% =========================================================================



hold off;

end
