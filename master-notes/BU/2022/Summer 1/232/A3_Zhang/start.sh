#!/bin/bash
cd source
javac -d ../class Playground.java
cd class
java Playground
cd ../
javadoc -d docs source/**/*.java