-----添加用户
INSERT INTO USER (ID, USERNAME, PASSWORD, ENABLED) VALUES (1, 'user', '4da49c16db42ca04538d629ef0533fe8', 1);
INSERT INTO USER (ID, USERNAME, PASSWORD, ENABLED) VALUES (2, 'user1', 'e290d6c2246a3a45386e72b3c3a5868c', 0);
INSERT INTO USER (ID, USERNAME, PASSWORD, ENABLED) VALUES (3, 'admin', 'a66abb5684c45962d887564f08346e8d', 1);
INSERT INTO USER (ID, USERNAME, PASSWORD, ENABLED) VALUES (4, 'superadmin', '90bbb23d2b633ac4b95bcee603286e67', 1);
----添加角色
INSERT INTO ROLE (ID, ROLE) VALUES (1, 'ROLE_USER');
INSERT INTO ROLE (ID, ROLE) VALUES (2, 'ROLE_ADMIN');
----用户添加角色
INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES (1, 1);
INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES (2, 1);
INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES (3, 2);
----添加资源
INSERT INTO RESOURCE (ID, URL) VALUES (1, '/homePage.do');
INSERT INTO RESOURCE (ID, URL) VALUES (2, '/user.do');
INSERT INTO RESOURCE (ID, URL) VALUES (3, '/admin.do');
---角色添加资源
INSERT INTO ROLE_RESOURCE (ROLE_ID, RESOURCE_ID) VALUES (1, 1);
INSERT INTO ROLE_RESOURCE (ROLE_ID, RESOURCE_ID) VALUES (1, 2);
INSERT INTO ROLE_RESOURCE (ROLE_ID, RESOURCE_ID) VALUES (2, 1);
INSERT INTO ROLE_RESOURCE (ROLE_ID, RESOURCE_ID) VALUES (2, 3);