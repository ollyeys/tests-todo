#!/bin/bash

docker compose up down
echo '============= DOWNLOAD SOURCE APPLICATION =============='
docker build -t todo-application https://github.com/ollyeys/todo-application.git
echo '============= SUCCESSFULLY =============='

docker compose up --build