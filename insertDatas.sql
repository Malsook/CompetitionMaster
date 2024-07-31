
-- 进入目标数据库
USE CompetitionManagementSystem;

INSERT INTO Admins (Account, Password, Name)
VALUES ('admin', 'admin', '管理员');

-- 生成250个教师的数据，假设教师名字为示例中的'教师1'、'教师2'...
DELIMITER $$

CREATE PROCEDURE InsertTeachers()
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE i <= 250 DO
            INSERT INTO Teachers (Account, Password, Name, Email, Number)
            VALUES (CONCAT('teacher', i), 'password', CONCAT('教师', i), CONCAT('teacher', i, '@qq.com'), CONCAT('138', LPAD(i, 8, '0')));
            SET i = i + 1;
        END WHILE;
END$$

DELIMITER ;

CALL InsertTeachers();


-- 生成50个班级的数据，假设班级名字为示例中的'班级1'、'班级2'...
DELIMITER $$

CREATE PROCEDURE InsertClasses()
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE i <= 10 DO
            INSERT INTO Classes (ClazzName)
            VALUES (CONCAT('软件工程', i, '班'));
            SET i = i + 1;
        END WHILE;
    SET i = 1;
    WHILE i <= 10 DO
            INSERT INTO Classes (ClazzName)
            VALUES (CONCAT('计科', i, '班'));
            SET i = i + 1;
        END WHILE;
    SET i = 1;
    WHILE i <= 10 DO
            INSERT INTO Classes (ClazzName)
            VALUES (CONCAT('网络工程', i, '班'));
            SET i = i + 1;
        END WHILE;
    SET i = 1;
    WHILE i <= 10 DO
            INSERT INTO Classes (ClazzName)
            VALUES (CONCAT('网络安全', i, '班'));
            SET i = i + 1;
        END WHILE;
END$$

DELIMITER ;

CALL InsertClasses();

-- 生成每个班级50个学生的数据，假设学生名字为示例中的'学生1'、'学生2'...
DELIMITER $$

CREATE PROCEDURE InsertStudents()
BEGIN
    DECLARE class_id INT DEFAULT 1;
    DECLARE student_id INT DEFAULT 1;
    DECLARE clazz_name VARCHAR(100);

    WHILE class_id <= 10 DO
            -- 获取当前班级的名称
            SELECT ClazzName INTO clazz_name FROM Classes WHERE ID = class_id;

            WHILE student_id <= 50 DO
                    INSERT INTO Students (Account, Password, Name, ClazzName, Email, Number)
                    VALUES (
                               CONCAT('student', (class_id - 1) * 50 + student_id),
                               'password',
                               CONCAT('学生', (class_id - 1) * 50 + student_id),
                               clazz_name,
                               CONCAT('student', (class_id - 1) * 50 + student_id, '@qq.com'),
                               CONCAT('139', LPAD((class_id - 1) * 50 + student_id, 8, '0'))
                           );
                    SET student_id = student_id + 1;
                END WHILE;
            SET student_id = 1;
            SET class_id = class_id + 1;
        END WHILE;
END$$

DELIMITER ;

CALL InsertStudents();

-- 生成50个比赛的数据，假设比赛名字为示例中的'竞赛1'、'竞赛2'...
DELIMITER $$

CREATE PROCEDURE InsertCompetitions()
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE i <= 50 DO
            INSERT INTO Competitions (CompetitionName, Description, StartDate, EndDate)
            VALUES (CONCAT('竞赛', i), CONCAT('竞赛描述竞赛描述竞赛描述竞赛描述竞赛描述竞赛描述竞赛描述竞赛描述竞赛描述竞赛描述竞赛描述竞赛描述竞赛描述竞赛描述竞赛描述', i), DATE_ADD(NOW(), INTERVAL i DAY), DATE_ADD(NOW(), INTERVAL (i + 10) DAY));
            SET i = i + 1;
        END WHILE;
END$$

DELIMITER ;

CALL InsertCompetitions();

-- 为了示例，假设每个学生和每个教师的奖项数据为简化的数据
-- 生成奖项数据
DELIMITER $$

CREATE PROCEDURE InsertPrizes()
BEGIN
    DECLARE student_id INT DEFAULT 1;
    DECLARE teacher_id INT DEFAULT 1;
    DECLARE competition_id INT DEFAULT 1;
    DECLARE status INT DEFAULT 1;
    DECLARE re INT DEFAULT 1;

    WHILE student_id <= 250 DO
            -- 插入奖项数据
            INSERT INTO Prize (StudentID, CompetitionID, TeacherID, Status, CertificatePath, Result, ParticipationDate)
            VALUES (student_id, competition_id, teacher_id, status, CONCAT('/certificatePicture/', student_id, '.png'), CONCAT(re, '等奖'), DATE_ADD(NOW(), INTERVAL student_id DAY));

            -- 更新状态
            SET status = (status % 3) + 1;
            SET re = (re % 3) + 1;

            -- 更新学生ID教师ID，每5个奖项更新一次
            IF status = 1 THEN
                SET teacher_id = teacher_id + 1;
                SET student_id = student_id + 1;
            END IF;

            -- 更新比赛ID
            SET competition_id = (competition_id % 50) + 1;
        END WHILE;
END$$

DELIMITER ;

-- 调用存储过程插入奖项数据
CALL InsertPrizes();
