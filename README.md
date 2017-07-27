# Contributors Test demo

[![Build Status](https://travis-ci.org/jproyo/contributorstest.svg?branch=master)](https://travis-ci.org/jproyo/contributorstest.svg?branch=master)

## Requirements

- Given a city name (e.g. Barcelona) the service returns a list of the top contributors (by
number of repositories) in GitHub.
- The service should give the possibility to choose the Top 50, Top 100 or Top 150.
- The service can be implemented using any set of technologies (language, framework,
libraries).
- Paying attention to concurrency and other scalability issues will be appreciated.
- Provide clear instruction on how to launch the service.

## Prerequisites

- JDK 8
- Maven 3.0.4 or newer

## Run Tests

> mvn clean install

## Run the solution

To run the solution you should execute maven command as follow

> mvn spring-boot:run

### Test local server

> curl -i -H 'Content-Type: application/json' -H 'Authorization: 432bdf23466bsdf211fashhgb' http://localhost:8080/contributors/:city/top/:number

For example if you want **Barcelona** contributors top 50 you should run

> curl -i -H 'Content-Type: application/json' -H 'Authorization: 432bdf23466bsdf211fashhgb' http://localhost:8080/contributors/barcelona/top/50
