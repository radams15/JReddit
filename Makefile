all: build


build:
	rm -rf target/*.jar
	mvn package

install:
	cp target/JReddit-*-jar-with-dependencies.jar /Applications/JReddit.app/Contents/Resources/bin/JReddit.jar

clean:
	mvn clean
