#!/bin/bash

#########################################################################################

function run_app
{
	#git clone 

	printf "\nCompiling project"
	./mvnw clean package
	
	printf "\n\nBuilding a new version"
	docker build -t texo-assessment:latest .

	printf "\n\nRunning the app"
	docker run -d -p 8080:8080 -p 80:8080 -p 443:8443 --name texo-assessment -t texo-assessment
	
	printf "You can reach now http://localhost:8080/winners to retrieve max and min Global Raspberry Awards winners"
}

function run_integration_tests
{
	#git clone 
	
	printf "\nRunning integration tests"
	./mvnw clean test
	printf "\n\nIntegration tests has been completed"
}

#########################################################################################


printf "\n#### Texo Assessment ####"

printf "  \n1) Run App"
printf "  \n2) Run Integration Tests"

echo

read choice

case $choice in
  1) run_app ;;
  2) run_integration_tests ;;
  *) printf "invalid option" ;;
esac

