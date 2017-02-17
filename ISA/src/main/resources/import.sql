INSERT INTO user(name, surname, password, email, type, version) VALUES ("Boris", "Janjic", "boki", "cmd3395@yahoo.com", "GUEST", 0)
INSERT INTO guest(active, id) VALUES (true , (SELECT id FROM user WHERE email="cmd3395@yahoo.com" AND password="boki"))

INSERT INTO user(name, surname, password, email, type, version) VALUES ("admin", "admin", "admin", "admin@gmail.com", "SYSTEMMANAGER", 0)
INSERT INTO systemmanager(id) VALUES ((SELECT id FROM user WHERE email="admin@gmail.com"))

INSERT INTO restaurant(rname, rtype, version) VALUES ("Bob's Country Bunker", "Country", 0)

INSERT INTO user(name, surname, password, email, type, version) VALUES ("Marko", "Vjestica", "marek", "m.vjestica94@gmail.com", "WAITER", 0)
INSERT INTO waiter(date_of_birth, dress_size, shoe_size, review, id, rid) VALUES ("1960-10-16 00:00:00", 30, 40, 1, (SELECT id FROM user WHERE email="m.vjestica94@gmail.com" AND password="marek"), (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"))

INSERT INTO user(name, surname, password, email, type, version) VALUES ("Dick", "Steele", "BiggusDickus", "dickeyMoose@gmail.com", "RESTAURANTMANAGER", 0)
INSERT INTO restaurantmanager(date_of_birth, id, rid) VALUES ("1960-10-16 00:00:00", (SELECT id FROM user WHERE email="dickeyMoose@gmail.com"), (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"))

INSERT INTO user(name, surname, password, email, type, version) VALUES ("proName", "proSurname", "pro", "pro@gmail.com", "PROVIDER", 0)
INSERT INTO provider(id) VALUES ((SELECT id FROM user WHERE email="pro@gmail.com"))
INSERT INTO user(name, surname, password, email, type, version) VALUES ("res", "man", "res", "res@gmail.com", "RESTAURANTMANAGER", 0)
INSERT INTO restaurantmanager(date_of_birth, id, rid) VALUES ("1970-05-16 00:00:00", (SELECT id FROM user WHERE email="res@gmail.com"), 1)

INSERT INTO restaurantsegment (version, rs_Name, rs_Color) VALUES (0, 'Indoors', 'blue')
INSERT INTO restaurantsegment (version, rs_Name, rs_Color) VALUES (0, 'Smoking', 'red')
INSERT INTO restaurantsegment (version, rs_Name, rs_Color) VALUES (0, 'Garden - open', 'green')
INSERT INTO restaurantsegment (version, rs_Name, rs_Color) VALUES (0, 'Garden - covered', 'yellow')

INSERT INTO tableregion (version, tr_Mark, tr_Color) VALUES (0, 1, 'red')
INSERT INTO tableregion (version, tr_Mark, tr_Color) VALUES (0, 2, 'blue')
INSERT INTO tableregion (version, tr_Mark, tr_Color) VALUES (0, 3, 'yellow')
INSERT INTO tableregion (version, tr_Mark, tr_Color) VALUES (0, 4, 'green')
INSERT INTO tableregion (version, tr_Mark, tr_Color) VALUES (0, 5, 'purple')
INSERT INTO tableregion (version, tr_Mark, tr_Color) VALUES (0, 6, 'orange')
INSERT INTO tableregion (version, tr_Mark, tr_Color) VALUES (0, 7, 'brown')
INSERT INTO tableregion (version, tr_Mark, tr_Color) VALUES (0, 8, 'darkblue')
INSERT INTO tableregion (version, tr_Mark, tr_Color) VALUES (0, 9, 'lightgreen')
INSERT INTO tableregion (version, tr_Mark, tr_Color) VALUES (0, 10, 'maroon')

INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (1, 0, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), True, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (2, 1, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), True, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (3, 2, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), True, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (4, 3, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), True, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (5, 4, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), True, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (6, 5, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), True, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (7, 6, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), True, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (8, 7, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), False, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (9, 8, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), True, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (10, 9, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), True, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (11, 10, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), True, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (12, 11, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), True, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (13, 12, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), True, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (14, 13, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), True, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (15, 14, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), True, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (16, 15, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), True, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (17, 16, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), False, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (18, 17, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), True, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (19, 18, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), True, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (20, 19, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), True, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (21, 20, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), True, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (22, 21, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), True, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (23, 22, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), True, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (24, 23, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), True, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (25, 24, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), True, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (26, 25, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), True, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (27, 26, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), True, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (28, 27, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), True, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (29, 28, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), True, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (30, 29, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), True, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (31, 30, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), False, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (32, 31, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), True, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (33, 32, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), True, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (34, 33, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), True, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (35, 34, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), False, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (36, 35, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), True, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (37, 36, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), True, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (38, 37, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), True, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (39, 38, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), True, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (40, 39, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), True, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (41, 40, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), True, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (42, 41, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), True, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (43, 42, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), True, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (44, 43, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), True, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (45, 44, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), True, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (46, 45, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), True, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (47, 46, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), True, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (48, 47, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), True, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (49, 48, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), True, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
INSERT INTO restauranttable (rt_Number, rt_Position, rid, rt_Active, version, rsid, trid) VALUES (50, 49, (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"), True, 0, (SELECT rsid FROM restaurantsegment WHERE rs_Name="Indoors"), (SELECT trid FROM tableregion WHERE tr_Mark=1))
