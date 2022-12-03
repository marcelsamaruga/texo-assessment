# Getting Started
* Author: Marcel Samaruga da Costa
* Github: http://github.com/marcelsamaruga
* Last Update: Dec 3rd 2022

## TEXO Assessment
The api is an assessment to join Texo.
It should expose an API showing the producers with min/max intervals of the Golden Raspberry Awards.


## Endpoints
### Show the producers with max/min intervals 
* [/winners](http://localhost:8080/winners)
#### Response (example):
````
{
    "min": [
        {
            "producer": "Matthew Vaughn",
            "interval": 13,
            "previousWin": 2002,
            "followingWin": 2015
        }
    ],
    "max": [
        {
            "producer": "Bo Derek",
            "interval": 66,
            "previousWin": 1924,
            "followingWin": 1990
        }
    ]
}
````

## Running Application
Download [init.sh](https://github.com/marcelsamaruga/texo-assessment/blob/main/init.sh)
Run the bash file init.sh.
Remember to give the right permission. [Permission](https://www.andrewcbancroft.com/blog/musings/make-bash-script-executable/)

` chmod u+x init.sh `

To run on Windows, you need to use WSL (Windows Subsystem for Linux).

Choose one of the following options:
### Texo Assessment ####
1) Run App
2) Run Integration Tests

#### Running the app
* The command will clone the project from the github repository (if wasn't before)
* Run maven command to package the application skipping the tests.
* Run the app
* Endpoint [/winners](http://localhost:8080/winners) is available.

Example:

```
➜  ~ curl http://localhost:8080/winners
{"min":[{"producer":"Bo Derek","interval":6,"previousWin":1984,"followingWin":1990}],"max":[{"producer":"Matthew Vaughn","interval":13,"previousWin":2002,"followingWin":2015}]}%
```

#### Running integration tests
* The command will clone the project from the github repository (if wasn't before)
* Run maven command to test the application
* It should display "Integration tests has been completed"

