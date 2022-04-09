# Omegapoint code test
Set up to solve a code test involving Swedish ID numbers.

This program is created to check Swedish personal, coordination, and organisation numbers and whether they comply to correct formats, values, and Luhn's Algorithm.

Accepted personal numbers are of the form (YY)YYMMDD(-|+|)XXXX, where (YY) must be either 18, 19, or 20.

Accepted coordination numbers are of the form (YY)YYMMDD(-|+|)XXXX, where DD corresponds to an existing calendar day, plus 60. (YY) must be either 18, 19, or 20.

Accepted organisation numbers are of the form (16)YYMMDD(-|)XXXX, where MM must be >= 20.

All three types of ID numbers must be divisible by 10 after applying Luhn's algorithm to them.

## Prerequisites

To build this project, it is necessary to use [Java 11](https://docs.oracle.com/en/java/javase/11/), [JUnit4](https://junit.org/junit4/) version 4.13.2, and recommended to use [Apache ANT](https://ant.apache.org/), version 1.10.7 or higher.

JUnit is included as part of the project library. Installing Java and ANT in the steps below will require a terminal.

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

Do the same for Java:

```bash
sudo apt-get install openjdk-11-jre
sudo apt-get install openjdk-11-jdk
```

For other operative systems, please refer to the linked program documentations.


## Run tests

Position yourself at the root of the project, and call

```bash
ant
```
to compile the project, or
```bash
ant build
```

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
