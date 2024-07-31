 # 创建数据库

DROP DATABASE IF EXISTS CompetitionManagementSystem;
CREATE DATABASE CompetitionManagementSystem;
USE CompetitionManagementSystem;


# 管理员表

CREATE TABLE Admins
(
    ID       INT AUTO_INCREMENT PRIMARY KEY,
    Account  VARCHAR(50)  NOT NULL UNIQUE,
    Password VARCHAR(255) NOT NULL,
    Name     VARCHAR(100) NOT NULL
);

# 教师表

CREATE TABLE Teachers
(
    ID       INT AUTO_INCREMENT PRIMARY KEY,
    Account  VARCHAR(50)  NOT NULL UNIQUE,
    Password VARCHAR(255) NOT NULL,
    Name     VARCHAR(100) NOT NULL,
    Email    VARCHAR(100) UNIQUE,
    Number   VARCHAR(100) UNIQUE
);

# 班级表

CREATE TABLE Classes
(
    ID        INT AUTO_INCREMENT PRIMARY KEY,
    ClazzName VARCHAR(100) NOT NULL UNIQUE
);


# 学生表

CREATE TABLE Students
(
    ID        INT AUTO_INCREMENT PRIMARY KEY,
    Account   VARCHAR(50)  NOT NULL UNIQUE,
    Password  VARCHAR(255) NOT NULL,
    Name      VARCHAR(100) NOT NULL,
    ClazzName VARCHAR(100) NOT NULL,
    Email     VARCHAR(100) UNIQUE,
    Number    VARCHAR(100) UNIQUE,
    FOREIGN KEY (ClazzName) REFERENCES Classes (ClazzName) ON DELETE restrict ON UPDATE CASCADE
);


# 比赛表

CREATE TABLE Competitions
(
    ID              INT AUTO_INCREMENT PRIMARY KEY,
    CompetitionName VARCHAR(100) NOT NULL UNIQUE,
    Description     TEXT,
    StartDate       DATE,
    EndDate         DATE
);

# 奖项表

CREATE TABLE Prize
(
    ID                INT AUTO_INCREMENT PRIMARY KEY,
    StudentID         INT NOT NULL,
    CompetitionID     INT NOT NULL,
    TeacherID         INT NOT NULL,
    Status            INT NOT NULL,
    CertificatePath   VARCHAR(255),
    Result            VARCHAR(100),
    ParticipationDate DATE,
    FOREIGN KEY (StudentID) REFERENCES Students (ID) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (CompetitionID) REFERENCES Competitions (ID) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (TeacherID) REFERENCES Teachers (ID) ON DELETE CASCADE ON UPDATE CASCADE
);