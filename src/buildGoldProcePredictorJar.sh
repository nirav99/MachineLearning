#!/bin/bash

echo "Compiling Projct"

javac ./common/*.java ./linearregression/*.java ./problems/*.java

echo "Generating Manifest file"
manifestFile=`pwd`"/GoldPricePredictorManifest.txt"

echo "Main-Class: problems.GoldPricePredictionProblem\n" > $manifestFile

echo "Building jar file"
outJarName=GoldPricePredictor.jar

jar cvfm $outJarName $manifestFile ./common/*.class ./linearregression/*.class ./problems/*.class

mv $outJarName ../
rm $manifestFile
