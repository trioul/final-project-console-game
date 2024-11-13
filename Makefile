CLASSPATH = .
TESTCLASSPATH = $(CLASSPATH):Test/:Test/junit4.jar
JFLAGS = -g -cp $(TESTCLASSPATH)
.SUFFIXES: .java .class
.java.class:
	javac $(JFLAGS) $*.java

TESTSRC = Test/TestRunner.java Test/GameTest.java

SRC = \
	Game.java Item.java Weapon.java

default: classes

classes: $(SRC:.java=.class)

clean:
	rm -f *.class Test/*.class

run: classes
	java -cp $(CLASSPATH) Game

test-build: classes Test/TestRunner.class Test/GameTest.class

test: test-build
	java -cp $(TESTCLASSPATH) TestRunner GameTest
