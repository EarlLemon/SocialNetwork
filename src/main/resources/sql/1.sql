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

CREATE TABLE Lessons (
  id_lesson INT AUTO_INCREMENT PRIMARY KEY,
  id_user   INT          NOT NULL,
  post      VARCHAR(250) NOT NULL,
  FOREIGN KEY (id_user) REFERENCES User (id)

);
CREATE TABLE Subscription (
  id_sub      INT AUTO_INCREMENT PRIMARY KEY,
  subsription INT NOT NULL

);

INSERT INTO User (firstname, lastname, email, password, username, gropu_n)
VALUES ('Nikita', 'Falko', 'nikifalko11@gmail.com', 'qwerty', 'earllemon', '545')



