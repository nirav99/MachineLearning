#!/bin/bash

echo "Compiling Projct"

javac ./common/*.java ./linearregression/*.java ./problems/*.java

echo "Generating Manifest file"
manifestFile=`pwd`"/HousePricePredictorManifest.txt"

echo "Main-Class: problems.HousePricePredictionProblem\n" > $manifestFile

echo "Building jar file"
outJarName=HousePricePredictor.jar

jar cvfm $outJarName $manifestFile ./common/*.class ./linearregression/*.class ./problems/*.class

mv $outJarName ../
rm $manifestFile
