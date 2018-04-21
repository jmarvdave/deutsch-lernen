#!/bin/sh
echo "start";
cp serviceclient.json target/;
cd target/;
java -jar deutsch-lernen-2.0.0.RELEASE-spring-boot.jar;
pdflatex -interaction=batchmode testing.tex;
lpr -P davisBrother_DCP-J562DW_d80f9925b8d6 testing.pdf;
echo "done";

