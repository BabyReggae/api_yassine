@echo off
call mvn clean package
call docker build -t ay/api_yassine .
call docker rm -f api_yassine
call docker run -d -p 9080:9080 -p 9443:9443 --name api_yassine ay/api_yassine