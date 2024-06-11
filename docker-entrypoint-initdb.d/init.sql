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
        status BOOLEAN,
        targetdate DATE,
        FOREIGN KEY(user_id) REFERENCES users(id)
);


truncate table todos restart identity;
truncate table users restart identity cascade;


insert into users  (name, surname, username, password) values ('test','test', 'test','test');


insert into todos  (title, description, user_id, targetdate, status) values ('test', 'test',1,'2024-08-14',true);
insert into todos  (title, description, user_id, targetdate, status) values ('test', 'test',1,'2024-08-14',true);
insert into todos  (title, description, user_id, targetdate, status) values ('test', 'test',1,'2024-08-14',true);
