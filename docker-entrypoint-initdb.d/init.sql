CREATE TABLE IF NOT EXISTS users(
        id SERIAL PRIMARY KEY,
        name VARCHAR(255),
        surname VARCHAR(500),
        username VARCHAR(500),
        password VARCHAR(500)
);

CREATE TABLE IF NOT EXISTS todos(
        id SERIAL PRIMARY KEY,
        title VARCHAR(255),
        description VARCHAR(1000),
        user_id INT,
        FOREIGN KEY(user_id) REFERENCES users(id)
);





insert into users  (name, surname, username, password) values ('test_name','test_surname', 'test_username','test_password');
