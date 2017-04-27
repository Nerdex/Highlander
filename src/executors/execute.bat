@echo off
set CLASSPATH=.
set CLASSPATH=%CLASSPATH%;path/to/needed/jars/my.jar
%JAVA_HOME%\bin\jar -Xms128 -Xmx384m -Xnoclassgc //classname

#Will change when the game is complete