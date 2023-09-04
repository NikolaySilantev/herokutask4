delete from messages;

insert into messages (id, full_text, receiver, sender, subject, time) values
    (1, 'Hello, testuser1!', 'testuser1', 'testuser2', 'Greetings', NOW()),
    (2, 'Hello, testuser2!', 'testuser2', 'testuser1', 'Greetings', NOW()),
    (3, 'Goodbye, testuser1!', 'testuser1', 'testuser2', 'Farewell', NOW()),
    (4, 'Goodbye, testuser2!', 'testuser2', 'testuser1', 'Farewell', NOW());

alter table messages AUTO_INCREMENT 10;