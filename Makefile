JAVASRC = $(wildcard src/*.java)
JAVAOBJ = $(JAVASRC:.java=.class)

all:
	javac src/*.java

clean:
	rm src/*.class