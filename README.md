# Omegapoint code test
Set up to solve a code test involving Swedish personal numbers.


## Prerequisites

To build this project, it is necessary to use [Java](), [JUnit4](https://junit.org/junit4/) version 4.13.2, and recommended to use [Apache ANT](https://ant.apache.org/), version 1.10.7 or higher.

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
sudo apt-get install openjdk-11-jdk
sudo apt-get install openjdk-11-jdk
sudo apt-get install junit
```

Set an environment variable pointing to the JUnit jar file:
```bash
export JUNIT=/your/junit/path/
```



## Run tests

Position yourself at the root of the project, and call
```bash
ant
```
to compile the project

To run all tests:
```bash
ant TestAll
```


## Run program

Place yourself at the root directory. Place any test files within the data/inputFiles directory. You can then run the program by specifying the file name as an argument.

With Windows or Linux:

```bash
java -cp ./build main.Main my-data.txt
```


If using STDIN, pipe your data into the program according to OS-specific commands.

With Windows Powershell:

```bash
echo "199990130" | java -cp ./build main.Main
```
