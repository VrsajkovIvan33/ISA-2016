INSERT INTO user(name, surname, password, email, type, version) VALUES ("Marko", "Vjestica", "marek", "m.vjestica94@gmail.com", "WAITER", 0)
INSERT INTO waiter(date_of_birth, dress_size, shoe_size, review, id) VALUES ("1960-10-16 00:00:00", 30, 40, 1, (SELECT id FROM user WHERE email="m.vjestica94@gmail.com" AND password="marek"))
INSERT INTO user(name, surname, password, email, type, version) VALUES ("admin", "admin", "admin", "admin@gmail.com", "SYSTEMMANAGER", 1)
INSERT INTO systemmanager(id) VALUES ((SELECT id FROM user WHERE email="admin@gmail.com"))
INSERT INTO user(name, surname, password, email, type, version) VALUES ("proName", "proSurname", "pro", "pro@gmail.com", "PROVIDER", 0)
INSERT INTO provider(id) VALUES ((SELECT id FROM user WHERE email="pro@gmail.com"))

