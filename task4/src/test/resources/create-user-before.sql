delete from user_role;
delete from users;

insert into users(id, email, password, username) values
(1, 'testuser1@mail.ru', '$2a$10$8QJZc0nt5BjlKXDNPDrKdeJpJ6sIYfLTsobUd24L0ZK0QBvXxPlBu', 'testuser1'),
(2, 'testuser2@mail.ru', '$2a$10$8QJZc0nt5BjlKXDNPDrKdeJpJ6sIYfLTsobUd24L0ZK0QBvXxPlBu', 'testuser2');

insert into user_role(user_id, roles) values
(1, 'ROLE_USER'),
(2, 'ROLE_USER');