#!/bin/bash
javac -d class Entry.java
cd class
java Entry
cd ..
javadoc -d docs *.java