debug:
	javac src/*.java -d bin/
	cd bin && java NewTris

build:
	javac src/*.java -d bin/

pack:
	jar cvfm jar/NewTris.jar jar/manifest.txt -C bin/ .

clean:
	rm bin/*
