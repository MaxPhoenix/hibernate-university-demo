DROP TABLE IF EXISTS Student CASCADE;

CREATE TABLE IF NOT EXISTS Student (
     id        INTEGER  PRIMARY KEY AUTO_INCREMENT,
     first_name VARCHAR(50) NOT NULL,
     last_name VARCHAR(50) NOT NULL,
     email        VARCHAR(50)  NOT NULL
);


INSERT INTO Student(email, first_name, last_name)
VALUES
('mlencina@gmail.com', 'Max', 'Lencina'),
('flencina@gmail.com', 'Fede', 'Lencina'),
('ff@gmail.com', 'Fernando', 'Fernandez');
