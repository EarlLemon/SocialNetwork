CREATE TABLE User (
  id        INT AUTO_INCREMENT PRIMARY KEY,
  firstname VARCHAR(100) NOT NULL,
  lastname  VARCHAR(100) NOT NULL,
  email     VARCHAR(128) NOT NULL,
  password  VARCHAR(50)  NOT NULL,
  username  VARCHAR(50)  NOT NULL,
  gropu_n   VARCHAR(3)   NOT NULL,
  UNIQUE (email),
  UNIQUE (username)
);

CREATE TABLE Lesson (
  lesson_id INT AUTO_INCREMENT PRIMARY KEY,
  user_id   INT          NOT NULL,
  lesson_text      VARCHAR(250) NOT NULL,
  FOREIGN KEY (user_id) REFERENCES User (id)

);
CREATE TABLE Subscription (
  sub_id      INT AUTO_INCREMENT PRIMARY KEY,
  subsription INT NOT NULL

);

INSERT INTO User (firstname, lastname, email, password, username, gropu_n)
VALUES ('Nikita', 'Falko', 'nikifalko11@gmail.com', 'qwerty', 'earllemon', '545')



