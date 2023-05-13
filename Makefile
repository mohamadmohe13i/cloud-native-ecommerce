help:
	@echo "Please use \`make <COMMAND>' where <COMMAND> is one of"
	@echo "  test                 to run tests"
	@echo "  build                to build docker image"

test:
	./gradlew test

build:
	./gradlew jibDockerBuild
