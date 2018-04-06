#!/bin/sh
echo "start";
cp serviceclient.json target/;
cd target/;
java -jar deutsch-lernen-2.0.0.RELEASE-spring-boot.jar;
pdflatex -interaction=batchmode testing.tex;
echo "done";

