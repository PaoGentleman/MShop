-- 删除数据库
DROP DATABASE IF EXISTS mshop;
-- 创建数据库
CREATE  DATABASE mshop CHARACTER SET UTF8;
-- 使用mshop数据库
USE mshop;
-- 删除数据表
DROP TABLE IF EXISTS shopcar;
DROP TABLE IF EXISTS details;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS member;
DROP TABLE IF EXISTS goods;
DROP TABLE IF EXISTS item;
DROP TABLE IF EXISTS admin;
-- 创建数据表
-- 1.创建商品类型表
CREATE TABLE item(
  iid     INT    AUTO_INCREMENT,
  title   VARCHAR(200),
  CONSTRAINT pk_iid PRIMARY KEY(iid)
) engine=InnoDB;

-- 2.创建管理员信息表
CREATE TABLE admin(
  aid       VARCHAR(50),
  password  VARCHAR(32) NOT NULL,
  lastdate  DATETIME,
  CONSTRAINT pk_aid PRIMARY KEY(aid)
) engine=InnoDB;

-- 3.创建用户信息表
CREATE TABLE member(
  mid       VARCHAR(50),
  password  VARCHAR(32) NOT NULL,
  name      VARCHAR(50),
  phone     VARCHAR(50),
  address   VARCHAR(100),
  code      VARCHAR(100),
  status    INT,
  regdate   DATETIME  NOT NULL,
  photo     VARCHAR(50) DEFAULT 'nophoto.jpg',
  CONSTRAINT pk_mid PRIMARY KEY(mid)
) engine=InnoDB;

-- 4.创建商品信息表
CREATE TABLE goods(
  gid       INT   AUTO_INCREMENT,
  iid       INT,
  aid       VARCHAR(50),
  title     VARCHAR(50),
  pubdate   DATETIME,
  price     FLOAT ,
  amount    INT,
  bow       INT,
  note      TEXT,
  photo     VARCHAR(100),
  status    INT,
  CONSTRAINT pk_gid PRIMARY KEY(gid),
  CONSTRAINT fk_iid FOREIGN KEY(iid) REFERENCES item(iid) ON DELETE SET NULL,
  CONSTRAINT fk_aid FOREIGN KEY(aid) REFERENCES admin(aid) ON DELETE SET NULL
) engine=InnoDB;

-- 5.创建订单信息表
CREATE TABLE orders(
  oid       INT   AUTO_INCREMENT,
  mid       VARCHAR(50),
  name      VARCHAR(50),
  phone     VARCHAR(50),
  address   VARCHAR(100),
  credate   DATETIME,
  pay       FLOAT,
  CONSTRAINT pk_oid PRIMARY KEY(oid),
  CONSTRAINT fk_mid FOREIGN KEY(mid) REFERENCES member(mid) ON DELETE CASCADE
) engine=InnoDB;

-- 6.创建订单详情表
CREATE TABLE details(
  odid      INT   AUTO_INCREMENT,
  oid       INT   NOT NULL,
  gid       INT,
  title     VARCHAR(50),
  price     FLOAT   NOT NULL,
  amount    INT   NOT NULL,
  CONSTRAINT pk_odid PRIMARY KEY(odid),
  CONSTRAINT fk_oid2 FOREIGN KEY(oid) REFERENCES orders(oid) ON DELETE CASCADE,
  CONSTRAINT fk_gid2 FOREIGN KEY(gid) REFERENCES goods(gid) ON DELETE SET NULL
) engine=InnoDB;
-- 7.购物车信息表
CREATE TABLE shopcar(
  gid		INT,
  mid		VARCHAR(50),
  amount	INT,
  CONSTRAINT  fk_gid3 FOREIGN KEY(gid) REFERENCES goods(gid) ON DELETE CASCADE,
  CONSTRAINT  fk_mid3 FOREIGN KEY(mid) REFERENCES member(mid) ON DELETE CASCADE
);


-- 编写测试数据
-- 增加商品分类信息
INSERT INTO item(title) VALUES ('厨房用品');
INSERT INTO item(title) VALUES ('儿童玩具');
INSERT INTO item(title) VALUES ('医疗机械');
INSERT INTO item(title) VALUES ('电子竞技');
INSERT INTO item(title) VALUES ('办公用品');
-- 增加管理员信息
INSERT INTO admin(aid,password) VALUES ('admin','5D41402ABC4B2A76B9719D911017C592');
-- 增加普通用户信息
INSERT INTO member(mid,password,regdate) VALUES ('zhf','5B54C0A045F179BCBBBC9ABCB8B5CD4C','2018-8-23');
-- 提交事物
COMMIT;