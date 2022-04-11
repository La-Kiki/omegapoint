# Omegapoint code test
Set up to solve a code test involving Swedish ID numbers.

This program is created to check Swedish personal, coordination, and organisation numbers and whether they comply to correct formats, values, and Luhn's Algorithm.

Accepted personal numbers are of the form (YY)?YYMMDD[-+ε]XXXX, where (YY) must be either 18, 19, or 20, and using + as a delimiter means the birth date occurred over a 100 years ago.

Accepted coordination numbers are of the form (YY)?YYMMDD[-+ε]XXXX, where DD corresponds to an existing calendar day, plus 60. Follows the same rules as personal numbers too.

Accepted organisation numbers are of the form (16)?YYMMDD[-ε]XXXX, where MM must be >= 20.

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

Position yourself at the root of the project, and call either 

```bash
ant
ant build
```
to compile the project.

To run all tests:
```bash
ant TestAll
```

Individual class tests can be run with
```bash
ant Test[className]
```

## Run program

If you want to run the program with your own data, create a text file with line-separated ID numbers that you want to process.

Place yourself at the root directory. Place any test files within the data/inputFiles directory. You can then run the program by specifying the relative file locations of your input files in the Darg.files argument below.


```bash
ant run -Darg.files='./data/inputFiles/exampleFile.txt ./data/inputFiles/exampleFile.txt'
```

The same execution can be achieved with the following Java command:
```bash
java -cp ./build main.Main ./data/inputFiles/[my-data].txt ./data/inputFiles/[my-data].txt
```

If using STDIN, you can also pipe your data into the program.
```bash
echo "199990130" | java -cp ./build main.Main
```

A log containing the resulting invalid personal, organisation, or coordination numbers will then be created inside a new data/outputLogs directory.
