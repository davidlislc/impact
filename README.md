#  Project solution

### Description
This project uses [Apache Spark](https://spark.apache.org/) ,a unified analytics engine for large-scale data processing, to report the estimated impact. 

* Project overview: This project is a Spring Boot application. It utilizes Spark and Hadoop libraries to analyze and process big data set at lightening speed.
* The project uses Maven as dependency management tool.
* The project will run on Mac out of box.
* On Windows machine, there is a known issue. The HADOOP_HOME and PATH needs to be set before Sprak runs.A folder called hadoop has been provided at the root.
  Copy the folder of Hadoop to a location, and set system environment variable HADOOP_HOME and 
  system PATH. More on github: [winutils on github](https://github.com/steveloughran/winutils).
  set environment vars:
```
HADOOP_HOME=<your local hadoop-ver folder>
PATH=%PATH%;%HADOOP_HOME%\bin
```
* Please use the inline comments as an explanation of the implementation algorithm.
  
* When the program runs, it will read catalogs and purchase from the data folder, and generates two csv files into impact and impactTotal directories.

* A final csv file impact.csv has been generated for you convenience. It can be found in the data folder.

### Thank you!

