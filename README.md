# Omegapoint code test
Set up to solve a code test involving Swedish personal numbers.


## Prerequisites

To build this project, it is necessary to use [Java 11](https://docs.oracle.com/en/java/javase/11/), [JUnit4](https://junit.org/junit4/) version 4.13.2, and recommended to use [Apache ANT](https://ant.apache.org/), version 1.10.7 or higher.

The following steps require a terminal.

If using a Linux-based system or similar, such as [WSL], update and upgrade your available packages.

```bash
sudo apt update
sudo apt upgrade
```

ANT can be installed with
```bash
sudo apt-get install ant
```

Verify the installation with
```bash
ant -version
```

Do the same for JUnit 4 and Java:

```bash
sudo apt-get install junit
sudo apt-get install openjdk-11-jre
sudo apt-get install openjdk-11-jdk
```

Set an environment variable pointing to the JUnit jar file:
```bash
export JUNIT=/your/junit/path/
```

For other operative systems, please refer to the linked program documentations.


## Run tests

Position yourself at the root of the project, and call

```bash
ant
```
to compile the project.

To run all tests:
```bash
ant TestAll
```


## Run program

Place yourself at the root directory. Place any test files within the data/inputFiles directory. You can then run the program by specifying the relative file location as an argument.

With Windows or Linux:

```bash
java -cp ./build main.Main ./data/inputFiles/my-data.txt
```


If using STDIN, pipe your data into the program according to OS-specific commands.

With Windows or Linux:

```bash
echo "199990130" | java -cp ./build main.Main
```

A log containing the resulting invalid personal, organisation, or coordination numbers will then be created inside a new data/outputLogs directory.
