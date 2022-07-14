#!/bin/bash
javadoc -d docs *.java
javac -d class Entry.java
cd class
java Entry
cd ..