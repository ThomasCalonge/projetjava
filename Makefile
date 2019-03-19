core:
	javac -classpath bin/core -sourcepath src/core -d bin src/core/*.java

app:
	javac -classpath bin -sourcepath src -d bin src/*.java
run:
	@cd bin; java BatailleNavale

clean:
	rm bin/*.class
	rm bin/core/*.class