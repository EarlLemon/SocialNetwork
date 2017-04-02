INSERT INTO User (firstname, lastname, email, password, username) VALUES ('Nikita', 'Falko', 'nikifalko11@bd.com', 'qwerty', 'earllemon');
INSERT INTO User (firstname, lastname, email, password, username) VALUES ('Some', 'Man', 'sumo@db.com', 'qwerty', 'yerd');
INSERT INTO User (firstname, lastname, email, password, username) VALUES ('Till', 'Lindemann', 'ramm@bd.com', 'admin', 'mutter');
INSERT INTO User (firstname, lastname, email, password, username) VALUES ('Michail', 'Gorshenev', 'misha@db.com', 'kish', 'gorsh');

INSERT INTO Subscription (user_id, subscription) VALUES (1,1);
INSERT INTO Subscription (user_id, subscription) VALUES (1,2);
INSERT INTO Subscription (user_id, subscription) VALUES (1,3);
INSERT INTO Subscription (user_id, subscription) VALUES (2,1);
INSERT INTO Subscription (user_id, subscription) VALUES (2,3);
INSERT INTO Subscription (user_id, subscription) VALUES (3,1);
INSERT INTO Subscription (user_id, subscription) VALUES (3,2);

INSERT INTO Lesson (user_id, lesson_text) VALUES (1,'Im alive!');
INSERT INTO Lesson (user_id, lesson_text) VALUES (1,'Hello there, this is for lessons of life');
INSERT INTO Lesson (user_id, lesson_text) VALUES (2,'Nice to see you))');
INSERT INTO Lesson (user_id, lesson_text) VALUES (2,'Im so strong');
INSERT INTO Lesson (user_id, lesson_text) VALUES (3,'When u drunk, remember dont forget about... Hm... JUST DONT FORGET');
INSERT INTO Lesson (user_id, lesson_text) VALUES (3,'The most tasty sausages was in Hamburg or Bavaria, i dont remember.... ');
INSERT INTO Lesson (user_id, lesson_text) VALUES (4,'What can i say? Im dead...');
INSERT INTO Lesson (user_id, lesson_text) VALUES (2,'Ssssssssstraaaaangeeeer.');