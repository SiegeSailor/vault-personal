#!/bin/bash

clear

echo "$(date)"
echo "[Generate Java Documents]:"
javadoc -d docs *.java
javac -d class Entry.java
cd class

echo "$(date)"
echo "[Execute File]:"
java Entry
cd ..