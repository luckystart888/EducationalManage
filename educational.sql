-- （1）信息管理：管理员对教师、学生信息的管理。
-- （2）成绩管理：教师对学生的成绩录入、修改。
-- （3）课表管理：教师对学生课表安排，学生查询课表信息。
-- （4）毕业管理：根据学生学分和毕业所需学分进行比较，判断是否能毕业。
-- （5）就业信息管理：搜集学生就业信息，对薪资信息，就业职位信息，工作单位，工作省份进行信息采集。


-- 创建数据库
CREATE DATABASE educational

-- 院系
CREATE TABLE department(
id INT(5) PRIMARY KEY,
departmentname VARCHAR(20)
);

INSERT INTO department VALUES(1,"信息工程学院");
INSERT INTO department VALUES(2,"管理学院");
INSERT INTO department VALUES(3,"外国语学院");
INSERT INTO department VALUES(4,"城市建设学院");

-- 专业
CREATE TABLE major(
id INT(5) PRIMARY KEY,
departmentid INT,
name VARCHAR(30),
CONSTRAINT fk_major_department FOREIGN KEY(departmentID) REFERENCES department(id)
);

INSERT INTO major VALUES (101,1,"计算机科学与技术");
INSERT INTO major VALUES (102,1,"软件工程");
INSERT INTO major VALUES (201,2,"酒店管理");
INSERT INTO major VALUES (301,3,"英语");
INSERT INTO major VALUES (302,3,"商务英语");
INSERT INTO major VALUES (401,4,"土木工程");
INSERT INTO major VALUES (402,4,"城市规划");

-- 班级
CREATE TABLE class(
id INT(5) PRIMARY KEY,
majorid INT,
departmentid INT,
classname VARCHAR(30),
CONSTRAINT fk_class_major FOREIGN KEY(majorid) REFERENCES major(id),
CONSTRAINT fk_class_department FOREIGN KEY(departmentid) REFERENCES department(id)
);

INSERT INTO class VALUES (10101,101,1,"计算机科学与技术1班");
INSERT INTO class VALUES (10102,101,1,"计算机科学与技术2班");
INSERT INTO class VALUES (10201,102,1,"软件工程1班");
INSERT INTO class VALUES (10202,102,1,"软件工程2班");
INSERT INTO class VALUES (20101,201,2,"酒店管理1班");
INSERT INTO class VALUES (20102,201,2,"酒店管理2班");
INSERT INTO class VALUES (30101,301,3,"英语1班");
INSERT INTO class VALUES (30102,301,3,"英语2班");
INSERT INTO class VALUES (30201,302,3,"商务英语1班");
INSERT INTO class VALUES (30202,302,3,"商务英语2班");
INSERT INTO class VALUES (40101,401,4,"土木工程1班");
INSERT INTO class VALUES (40102,401,4,"土木工程2班");
INSERT INTO class VALUES (40201,402,4,"城市规划1班");
INSERT INTO class VALUES (40202,402,4,"城市规划2班");

-- 权限
CREATE TABLE role(
id INT PRIMARY KEY,
type VARCHAR(10)
);

INSERT INTO role VALUES (1,"管理员");
INSERT INTO role VALUES (2,"教师");
INSERT INTO role VALUES (3,"学生");

-- 用户
CREATE TABLE login(
id INT PRIMARY KEY AUTO_INCREMENT,
userid INT(10) UNIQUE NOT NULL,
password VARCHAR(30) NOT NULL DEFAULT 123456,
role INT(2),
CONSTRAINT fk_login_role FOREIGN KEY(role) REFERENCES role(id)
);

INSERT INTO login VALUES (1,10001,"123456",1);
INSERT INTO login VALUES (2,11001,"123456",2);
INSERT INTO login VALUES (3,12001,"123456",3);

-- 教师
CREATE TABLE teacher(
userid INT(10) PRIMARY KEY,
name VARCHAR(50) NOT NULL,
gender CHAR(5) NOT NULL,
tel VARCHAR(20),
departmentid INT(5),
title VARCHAR(20),
CONSTRAINT fk_teacher_department FOREIGN KEY(departmentid) REFERENCES department(id)
);

INSERT INTO teacher VALUES (11001,"李白","男","18888888888",1,"教师")

-- 学生
CREATE TABLE student(
userid INT(10) PRIMARY KEY,
name VARCHAR(50) NOT NULL,
gender CHAR(5),
birthday DATE,
indate DATE,
tel VARCHAR(20),
classid INT(5),
CONSTRAINT fk_student_class FOREIGN KEY(classid) REFERENCES class(id)
);

INSERT INTO student VALUES (12001,"喜羊羊","男","2000-01-01","2018-09-01","18888888888",10101);

-- 课程
CREATE TABLE course(
courseid INT PRIMARY KEY AUTO_INCREMENT,
coursename varchar(50),
teacherid INT,
classid INT,
coursetype varchar(20),
courseweek varchar(50),
credit DOUBLE(5,2),
CONSTRAINT fk_course_teacher FOREIGN KEY(teacherid) REFERENCES teacher(userid),
CONSTRAINT fk_course_class FOREIGN KEY(classid) REFERENCES class(id)
);

INSERT INTO course VALUES(1,"Java程序设计",11001,10101,"必修","1-8周",4.5);
INSERT INTO course VALUES(2,"C语言程序设计",11001,10101,"必修","1-8周",4.5);


-- 课表
CREATE TABLE coursetable(
id INT PRIMARY KEY AUTO_INCREMENT,
courseid INT,
coursetime varchar(200),
classroom varchar(200),
CONSTRAINT fk_coursetable_course FOREIGN KEY(courseid) REFERENCES course(courseid)
);

INSERT INTO coursetable VALUES (1,1,"星期一第1节","1号教学楼102");
INSERT INTO coursetable VALUES (2,2,"星期一第4节","1号教学楼202");


-- 成绩
CREATE TABLE score(
id INT PRIMARY KEY AUTO_INCREMENT,
courseid INT(10),
studentid INT,
score DOUBLE(5,2) DEFAULT NULL,
credit DOUBLE(5,2) DEFAULT NULL,
CONSTRAINT fk_score_course FOREIGN KEY(courseid) REFERENCES course(courseid),
CONSTRAINT fk_score_student FOREIGN KEY(studentid) REFERENCES student(userid)
);

INSERT INTO score (id,courseid,studentid) VALUES (1,1,12001);
INSERT INTO score (id,courseid,studentid) VALUES (2,2,12001);

-- 毕业管理
CREATE TABLE graduate(
studentid INT(10),
credit1 DOUBLE(5,2) DEFAULT 100.0,
credit2 DOUBLE(5,2) DEFAULT 0.0,
status VARCHAR(10),
CONSTRAINT fk_graduate_student FOREIGN KEY(studentid) REFERENCES student(userid)
);

INSERT INTO graduate (studentid) VALUES (12001);


-- 就业信息
CREATE TABLE employment(
studentid INT(10),
workprovince VARCHAR(50),
workunit VARCHAR(100),
wages VARCHAR(50),
status INT(5),
CONSTRAINT fk_employment_student FOREIGN KEY(studentid) REFERENCES student(userid)
);

INSERT INTO employment (studentid) VALUES (12001);