core:
	javac -d bin src/core/*.java

run:
	@cd bin; java BatailleNavale

clean:
	rm bin/*.class
	rm bin/core/*.class