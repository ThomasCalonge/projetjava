.PHONY: doc

all:
	@echo "type \'make core\' pour compiler le module principal"
	@echo "type \'make app\'  pour compiler l'application"
	@echo "type \'make gui\'  pour compiler l'interface"
	@echo "type \'make run\'  pour lancer l'application"
	@echo "type \'make doc\'  pour générer la documentation"

core:
	javac -classpath bin -sourcepath src -d bin src/core/*.java

app:
	javac -classpath bin -sourcepath src -d bin src/*.java

gui:
	javac -classpath bin -sourcepath src -d bin src/gui/*.java

doc:
	javadoc -classpath ./bin -sourcepath ./src -public -d ./doc -html5 -javafx -charset utf8 -subpackages core

run:
	@cd bin; java Game

clean:
	rm bin/*.class
	rm bin/core/*.class
