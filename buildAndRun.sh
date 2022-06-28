#!/bin/sh
mvn clean package && docker build -t ay/api_yassine .
docker rm -f api_yassine || true && docker run -d -p 9080:9080 -p 9443:9443 --name api_yassine ay/api_yassine