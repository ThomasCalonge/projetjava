all:
	@echo type \'make core\' to build the core module
	@echo type \'make app\' to build the app
	@echo type \'make run\' to run the app

core:
	javac -classpath bin -sourcepath src -d bin src/core/*.java

app:
	javac -classpath bin -sourcepath src -d bin src/*.java

run:
	@cd bin; java BatailleNavale

clean:
	rm bin/*.class
	rm bin/core/*.class