echo '============= DOWNLOAD SOURCE APPLICATION =============='
docker build -t todo-application https://github.com/ollyeys/todo-application.git
echo '============= SUCCESSFULLY DOWNLOAD =============='


echo '============= START TESTS =============='
docker compose up --build --abort-on-container-exit || true
docker compose down
echo '============= END =============='
