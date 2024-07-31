package com.hzu.competition_master;

import com.hzu.competition_master.bean.*;
import com.hzu.competition_master.mapper.AdminMapper;
import com.hzu.competition_master.mapper.*;
import com.hzu.competition_master.service.ClazzService;
import com.hzu.competition_master.service.PrizeService;
import com.hzu.competition_master.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class CompetitionMasterApplicationTests {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private ClazzService clazzService;
    @Autowired
    private CompetitionMapper competitionMapper;
    @Autowired
    private StudentService studentService;
    @Autowired
    private PrizeService prizeService;
    @Autowired
    DataSource dataSource;
    @Test
    void contextLoads() {
        System.out.println(System.getProperty("user.dir")); // 输出: Large number
    }
    @Test
    void clazzMapperTest() {
        Clazz clazz = new Clazz();
        String clazzName = null;
        clazz.setClazzName(clazzName);
        List<Clazz> clazzList = clazzService.findByClazz(clazz);
        System.out.println(clazzList);
    }
    @Test
    void competitionMapperTest() {
        Competition t = new Competition();
        t.setCompetitionName("比赛五");
        //competitionMapper.addByCmp(t);
        //t.setId(6);
        //t.setDescription("比赛四的描述");
        //competitionMapper.updateById(t);
        //competitionMapper.deleteByCmp(t);
        System.out.println(competitionMapper.findByCmp(t));
        t.setCompetitionName("比赛");
        t.setDescription("描述");
        System.out.println(competitionMapper.findByCmpLike(t));
    }
    @Test
    void studentMapperTest() {
        Student student = new Student();
        student.setClazzName("一班");
        List<Student> studentList = studentService.findByStudent(student);
        System.out.println(studentList);
    }
    @Test
    void studentCompetitionMapperTest() {
        //studentCompetitionMapper.addByStuCmp(t);
//        t.setId(4);
//        t.setClassName("比赛四的描述");
//        studentMapper.updateById(t);
//        studentMapper.deleteByStudent(t);
//        System.out.println(studentMapper.findByCmp(t));
//        t.setCompetitionName("比赛");
//        t.setDescription("描述");
//        System.out.println(studentMapper.findByCmpLike(t));
    }
    @Test
    void Test() throws SQLException {
        System.out.println(dataSource.getClass());
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }
}
