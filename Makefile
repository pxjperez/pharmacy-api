.PHONY: run test package validate
run:
	mvn spring-boot:run
test:
	mvn test
package:
	mvn clean package
validate:
	openspec validate --all
