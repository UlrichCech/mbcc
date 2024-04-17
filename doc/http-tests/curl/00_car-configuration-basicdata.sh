#!/usr/bin/env bash

curl -X GET -H "Content-Type: application/json" -H "Accept: application/json" http://localhost:8080/api/v1/cars/configurations/basic/cartypes
curl -X GET -H "Content-Type: application/json" -H "Accept: application/json" http://localhost:8080/api/v1/cars/configurations/basic/carclasses
curl -X GET -H "Content-Type: application/json" -H "Accept: application/json" http://localhost:8080/api/v1/cars/configurations/basic/engines
curl -X GET -H "Content-Type: application/json" -H "Accept: application/json" http://localhost:8080/api/v1/cars/configurations/basic/colors
curl -X GET -H "Content-Type: application/json" -H "Accept: application/json" http://localhost:8080/api/v1/cars/configurations/basic/specialfeatures
