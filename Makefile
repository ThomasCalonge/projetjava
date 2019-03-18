core:
	javac -d bin src/core/*.java

run:
	$(MAKE) -C bin $^


clean:
	rm bin/*.class
	rm bin/core/*.class