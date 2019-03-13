JAVASRC = $(wildcard src/*.java)
JAVAOBJ = $(JAVASRC:.java=.class)

all:
	javac -d bin/ src/*.java

run:
	$(MAKE) -C bin $^

clean:
	rm src/*.class