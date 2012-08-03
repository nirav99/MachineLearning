% This function outputs the body composition as determined by the neural network and 
% it also calculates the BMI for the given input and obtains the actual body-composition and prints them.

function showBodyComposition(height, weight, pred)

  fprintf('\nDetermining body composition for Weight %d lb and Height %d inches\n', weight, height);
  
  fprintf('\nCalculated Body Composition :');

  if (pred == 1)
    fprintf(' Under-weight\n');
  elseif (pred == 2)
    fprintf(' Normal\n');
  elseif (pred == 3)
    fprintf(' Over-Weight\n');
  else
    fprintf(' Obese\n');
  end

  bmiIndex = weight * 703 / (height * height);

  fprintf('\nExpected Body-Composition :');

  if( bmiIndex < 18.5)
    fprintf(' Under-Weight\n');
  elseif (bmiIndex < 25)
    fprintf(' Normal\n');
  elseif (bmiIndex < 30)
    fprintf(' Over-Weight\n');
  else
   fprintf(' Obese\n');
  end
end