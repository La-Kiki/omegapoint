# Omegapoint code test
Set up to solve a code test involving Swedish personal numbers.

## Prerequisites

To build this project, it is recommended to use the [Apache ANT](https://ant.apache.org/) utility, version 1.10.7 or higher.

If using a Linux-based system or similar, such as [WSL], it can be installed with

```bash
sudo apt update
sudo apt upgrade
sudo apt install ant
```

Verify the installation with
```bash
ant -version
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

Place any test files within the data/inputFiles directory. You can then run the program by specifying the file name as an argument.

If using STDIN, pipe your data into the program according to OS-specific commands.

```bash

```
