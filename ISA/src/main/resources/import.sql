INSERT INTO user(name, surname, password, email, type, version) VALUES ("Marko", "Vjestica", "marek", "m.vjestica94@gmail.com", "WAITER", 0)
INSERT INTO waiter(date_of_birth, dress_size, shoe_size, review, id) VALUES ("1960-10-16 00:00:00", 30, 40, 1, (SELECT id FROM user WHERE email="m.vjestica94@gmail.com" AND password="marek"))

