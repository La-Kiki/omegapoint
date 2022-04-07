JFLAGS= -Xlint -g -d
JUNIT = .jar

.SUFFIXES: .java .class


CLASSES = \
    Main.java \
    ValidityCheck.java \
    PersonalNumberValidator.java \
    CoordinationNumberValidator.java \
    OrganisationNumberValidator.java \
    
TESTS =
    ValidityCheckTests.java \
    PersonalNumberValidatorTests.java \
    CoordinationNumberValidatorTests.java \
    OrganisationNumberValidatorTests.java \
    
TESTRUNNER = 
	tests.AllTestsRunner.java
	
MAIN = main.Main

SOURCE = ./src/

vpath %.java ./src/main
vpath %.java ./src/validators
vpath %.java ./src/tests

vpath %.txt ./bin/inputFiles



default:
	make compile
	
	
## DEPENDENCY INSTALLATIONS

install:
	#IF WINDOWS
	ifeq($(OS), Windows_NT)
		installWinDep
		#Run Windows installations
	#ELIF LINUX MAC
	else
		installUnixDep
		#Run Unix installations
	endif

#installWinDep:


installUnixDep:
	sudo apt update
	sudo apt-get install junit



## COMPILING PROGRAM, RUNNING TESTS OR PROGRAM

classes: $(CLASSES:.java=.class)

all: $(SOURCE)/* #$(CLASSES:.java=.class) $(TESTS:.java=.class) install
	#IF JAVA JUNIT NOT DOWNLOADED -> Download
	javac -cp $(JUNIT)/   .jar:JUNIT $(JFLAGS) ./build/ ./src/main/*.java ./src/validators/*.java ./src/tests/*.java  

	
tests: $(TESTS:.java=.class) compile install
	#IF JAVA JUNIT NOT DOWNLOADED -> Download
	java -cp $(JUNIT)/   .jar:./build/tests  org.junit.runner.JUnitCore $(TESTRUNNER)
	
	
run %: % compile install
	java -cp ./build/ $(MAIN) $<
	

.PHONY : clean
clean:
	rm -f ./build/*.class
