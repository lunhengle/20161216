----用户表
CREATE TABLE USER (
  ID       INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  USERNAME VARCHAR(50),
  PASSWORD VARCHAR(200),
  ENABLED  INT
);
----角色表
CREATE TABLE ROLE (
  ID   INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  ROLE VARCHAR(50)
);
---用户角色中间表
CREATE TABLE USER_ROLE (
  USER_ID INT,
  ROLE_ID INT
);
----资源表
CREATE TABLE RESOURCE (
  ID  INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  URL VARCHAR(200)
);
----资源角色中间表
CREATE TABLE ROLE_RESOURCE (
  ROLE_ID     INT,
  RESOURCE_ID INT
);