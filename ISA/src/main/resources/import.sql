INSERT INTO user(name, surname, password, email, type, version) VALUES ("Boris", "Janjic", "boki", "cmd3395@yahoo.com", "GUEST", 0)
INSERT INTO user(name, surname, password, email, type, version) VALUES ("Marko", "Markovic", "marko", "marko@yahoo.com", "GUEST", 0)
INSERT INTO user(name, surname, password, email, type, version) VALUES ("Marko", "Jankovic", "janko", "janko@yahoo.com", "GUEST", 0)
INSERT INTO user(name, surname, password, email, type, version) VALUES ("Dejan", "Jankovic", "dejan", "dejan@yahoo.com", "GUEST", 0)
INSERT INTO guest(active, id) VALUES (true , (SELECT id FROM user WHERE email="cmd3395@yahoo.com" AND password="boki"))
INSERT INTO guest(active, id) VALUES (true , (SELECT id FROM user WHERE email="marko@yahoo.com" AND password="marko"))
INSERT INTO guest(active, id) VALUES (true , (SELECT id FROM user WHERE email="janko@yahoo.com" AND password="janko"))
INSERT INTO guest(active, id) VALUES (true , (SELECT id FROM user WHERE email="dejan@yahoo.com" AND password="dejan"))

INSERT INTO user(name, surname, password, email, type, version) VALUES ("admin", "admin", "admin", "admin@gmail.com", "SYSTEMMANAGER", 0)
INSERT INTO systemmanager(id) VALUES ((SELECT id FROM user WHERE email="admin@gmail.com"))

INSERT INTO restaurant(rname, rtype, version) VALUES ("Bob's Country Bunker", "Country", 0)

INSERT INTO user(name, surname, password, email, type, version) VALUES ("Marko", "Vjestica", "marek", "m.vjestica94@gmail.com", "WAITER", 0)
INSERT INTO waiter(date_of_birth, dress_size, shoe_size, review, id, rid) VALUES ("1960-10-16 00:00:00", 30, 40, 1, (SELECT id FROM user WHERE email="m.vjestica94@gmail.com" AND password="marek"), (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"))

INSERT INTO user(name, surname, password, email, type, version) VALUES ("Branko", "Brankovic", "barek", "b.brankovic@gmail.com", "WAITER", 0)
INSERT INTO waiter(date_of_birth, dress_size, shoe_size, review, id, rid) VALUES ("1975-01-05 00:00:00", 30, 40, 1, (SELECT id FROM user WHERE email="b.brankovic@gmail.com" AND password="barek"), (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"))

INSERT INTO user(name, surname, password, email, type, version) VALUES ("Dick", "Steele", "BiggusDickus", "dickeyMoose@gmail.com", "RESTAURANTMANAGER", 0)
INSERT INTO restaurantmanager(date_of_birth, id, rid) VALUES ("1960-10-16 00:00:00", (SELECT id FROM user WHERE email="dickeyMoose@gmail.com"), (SELECT rid FROM restaurant WHERE rname="Bob's Country Bunker"))

INSERT INTO user(name, surname, password, email, type, version) VALUES ("proName", "proSurname", "pro", "pro@gmail.com", "PROVIDER", 0)
INSERT INTO provider(id) VALUES ((SELECT id FROM user WHERE email="pro@gmail.com"))

INSERT INTO user(name, surname, password, email, type, version) VALUES ("res", "man", "res", "res@gmail.com", "RESTAURANTMANAGER", 0)
INSERT INTO restaurantmanager(date_of_birth, id, rid) VALUES ("1970-05-16 00:00:00", (SELECT id FROM user WHERE email="res@gmail.com"), 1)

INSERT INTO user(name, surname, password, email, type, version) VALUES ("bar", "man", "bar", "bar@gmail.com", "BARTENDER", 0)
INSERT INTO bartender(date_of_birth, dress_size, shoe_size, id, rid) VALUES ("1970-05-16 00:00:00", 56, 43, (SELECT id FROM user WHERE email="bar@gmail.com"), 1)

INSERT INTO user(name, surname, password, email, type, version) VALUES ("Novica", "Sarenac", "nov", "ns@gmail.com", "COOK", 0)
INSERT INTO cook(date_of_birth, dress_size, shoe_size, typecook, id, rid) VALUES ("1994-08-12 00:00:00", 56, 41, 'Salad', (SELECT id FROM user WHERE email="ns@gmail.com"), 1)

INSERT INTO user(name, surname, password, email, type, version) VALUES ("Ivan", "Vrsajkov", "iva", "iv@gmail.com", "COOK", 0)
INSERT INTO cook(date_of_birth, dress_size, shoe_size, typecook, id, rid) VALUES ("1994-03-04 00:00:00", 56, 39, 'Grilled Dish', (SELECT id FROM user WHERE email="iv@gmail.com"), 1)

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



INSERT INTO menu (mid, mname, mtype, mdescription, mprice, mreview, rid, version) VALUES (1, 'Whiskey', 'Drink', 'Alcohol', 150, 0, 1, 0);
INSERT INTO menu (mid, mname, mtype, mdescription, mprice, mreview, rid, version) VALUES (2, 'Juice', 'Drink', 'Orange Juice', 100, 0, 1, 0);
INSERT INTO menu (mid, mname, mtype, mdescription, mprice, mreview, rid, version) VALUES (3, 'Cabbage', 'Salad', '', 70, 0, 1, 0);
INSERT INTO menu (mid, mname, mtype, mdescription, mprice, mreview, rid, version) VALUES (4, 'Stew', 'Cooked Meal', 'Stew with chicken meat', 300, 0, 1, 0);
INSERT INTO menu (mid, mname, mtype, mdescription, mprice, mreview, rid, version) VALUES (5, 'Pljeskavica', 'Grilled Dish', 'Pork', 250, 0, 1, 0);
INSERT INTO menu (mid, mname, mtype, mdescription, mprice, mreview, rid, version) VALUES (6, 'Cevapi', 'Grilled Dish', 'Pork', 300, 0, 1, 0);

INSERT INTO restaurantreview (rrid, rrreview, rrdate, uid, rid, version) VALUES (1, 5, "2016-05-16 00:00:00", 1, 1, 0);
INSERT INTO restaurantreview (rrid, rrreview, rrdate, uid, rid, version) VALUES (2, 4, "2016-01-25 00:00:00", 2, 1, 0);
INSERT INTO restaurantreview (rrid, rrreview, rrdate, uid, rid, version) VALUES (3, 3, "2016-10-01 00:00:00", 3, 1, 0);
INSERT INTO restaurantreview (rrid, rrreview, rrdate, uid, rid, version) VALUES (4, 5, "2016-11-08 00:00:00", 4, 1, 0);
INSERT INTO restaurantreview (rrid, rrreview, rrdate, uid, rid, version) VALUES (5, 5, "2015-02-14 00:00:00", 5, 1, 0);

INSERT INTO waiterreview (wrid, wrreview, wrdate, wid, rid, version) VALUES (1, 5, "2016-05-16 00:00:00", (SELECT id FROM user WHERE email="m.vjestica94@gmail.com"), 1, 0);
INSERT INTO waiterreview (wrid, wrreview, wrdate, wid, rid, version) VALUES (2, 4, "2016-01-25 00:00:00", (SELECT id FROM user WHERE email="m.vjestica94@gmail.com"), 1, 0);
INSERT INTO waiterreview (wrid, wrreview, wrdate, wid, rid, version) VALUES (3, 3, "2016-10-01 00:00:00", (SELECT id FROM user WHERE email="b.brankovic@gmail.com"), 1, 0);
INSERT INTO waiterreview (wrid, wrreview, wrdate, wid, rid, version) VALUES (4, 3, "2016-11-08 00:00:00", (SELECT id FROM user WHERE email="b.brankovic@gmail.com"), 1, 0);
INSERT INTO waiterreview (wrid, wrreview, wrdate, wid, rid, version) VALUES (5, 5, "2015-02-14 00:00:00", (SELECT id FROM user WHERE email="b.brankovic@gmail.com"), 1, 0);

INSERT INTO menureview (mrid, mrreview, mrdate, uid, mid, rid, version) VALUES (1, 5, "2016-05-16 00:00:00", (SELECT id FROM user WHERE email="iv@gmail.com"), 5, 1, 0);
INSERT INTO menureview (mrid, mrreview, mrdate, uid, mid, rid, version) VALUES (2, 2, "2016-01-25 00:00:00", (SELECT id FROM user WHERE email="iv@gmail.com"), 6, 1, 0);
INSERT INTO menureview (mrid, mrreview, mrdate, uid, mid, rid, version) VALUES (3, 3, "2016-10-01 00:00:00", (SELECT id FROM user WHERE email="ns@gmail.com"), 3, 1, 0);
INSERT INTO menureview (mrid, mrreview, mrdate, uid, mid, rid, version) VALUES (4, 2, "2016-11-08 00:00:00", (SELECT id FROM user WHERE email="ns@gmail.com"), 3, 1, 0);
INSERT INTO menureview (mrid, mrreview, mrdate, uid, mid, rid, version) VALUES (5, 5, "2015-02-14 00:00:00", (SELECT id FROM user WHERE email="ns@gmail.com"), 3, 1, 0);

INSERT INTO restaurantorder (version, rtid, o_Status, o_Assigned, o_Year, o_Month, o_Day, o_Hour, o_Minute, o_Bill_Created) VALUES (0, 1 , "Waiting for waiter", false, 2017, 1, 24, 16, 0, false)
INSERT INTO orderitem (version, uid, mid, oid, oi_Status, oi_ReadyByArrival, oi_Hour, oi_Minute) VALUES (0, 1, 5, 1, "Waiting for waiter", false, 16, 0)