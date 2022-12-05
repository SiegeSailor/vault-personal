#!/bin/bash

clear

ALGORITHM=$1

echo "$(date)"
echo "Execute $ALGORITHM:\n"
javac -d class $ALGORITHM.java
cd class
java $ALGORITHM
cd ..