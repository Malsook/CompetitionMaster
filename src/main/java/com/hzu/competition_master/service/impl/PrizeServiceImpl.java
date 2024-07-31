package com.hzu.competition_master.service.impl;

import com.hzu.competition_master.bean.Competition;
import com.hzu.competition_master.bean.Prize;
import com.hzu.competition_master.bean.Student;
import com.hzu.competition_master.bean.Teacher;
import com.hzu.competition_master.mapper.PrizeMapper;
import com.hzu.competition_master.mapper.StudentMapper;
import com.hzu.competition_master.service.CompetitionService;
import com.hzu.competition_master.service.PrizeService;
import com.hzu.competition_master.service.StudentService;
import com.hzu.competition_master.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PrizeServiceImpl implements PrizeService {
    @Autowired
    PrizeMapper prizeMapper;
    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    CompetitionService competitionService;

    @Override
    public int addByPrize(Prize prize, String competitionName, String studentName, String teacherName) {
        try {
            Competition competition = new Competition();
            competition.setCompetitionName(competitionName);
            prize.setCompetitionId(competitionService.findByCmp(competition).getFirst().getId());
        } catch (NoSuchElementException noSuchElementException) {
            throw new NoSuchElementException("不存在该竞赛");
        }
        try {
            Student student = new Student();
            student.setName(studentName);
            prize.setStudentId(studentService.findByStudent(student).getFirst().getId());
        } catch (NoSuchElementException noSuchElementException) {
            throw new NoSuchElementException("不存在该学生");
        }
        try {
            Teacher teacher = new Teacher();
            teacher.setName(teacherName);
            prize.setTeacherId(teacherService.findByTeacher(teacher).getFirst().getId());
        } catch (NoSuchElementException noSuchElementException) {
            throw new NoSuchElementException("不存在该教师");
        }
        try {
            return prizeMapper.addByPrize(prize);
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            // 字段重复
            throw dataIntegrityViolationException;
        }
    }

    @Override
    public int deleteByPrize(Prize prize) {
        return prizeMapper.deleteByPrize(prize);
    }

    @Override
    public int updateById(Prize prize, String competitionName, String studentName, String teacherName) {
        Competition competition = new Competition();
        competition.setCompetitionName(competitionName);
        prize.setCompetitionId(competitionService.findByCmp(competition).getFirst().getId());
        Student student = new Student();
        student.setName(studentName);
        prize.setStudentId(studentService.findByStudent(student).getFirst().getId());
        Teacher teacher = new Teacher();
        teacher.setName(teacherName);
        prize.setTeacherId(teacherService.findByTeacher(teacher).getFirst().getId());
        try {
            return prizeMapper.updateById(prize);
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            // 字段重复
            throw dataIntegrityViolationException;
        }
    }

    @Override
    public int updateById(Prize prize) {
        try {
            return prizeMapper.updateById(prize);
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            // 字段重复
            throw dataIntegrityViolationException;
        }
    }

    @Override
    public List<HashMap<String, Object>> findByPrize(Prize prize, String competitionName, String studentName, String teacherName) {
        List<HashMap<String, Object>> result = new ArrayList<>();
        Competition competition = new Competition();
        if (competitionName != null && !competitionName.isEmpty()) {
            competition.setCompetitionName(competitionName);
            List<Competition> competitionList = competitionService.findByCmp(competition);
            if (!competitionList.isEmpty())
                prize.setCompetitionId(competitionList.getFirst().getId());
            else return result;
        }
        Student student = new Student();
        if (studentName != null && !studentName.isEmpty()) {
            student.setName(studentName);
            List<Student> studentList = studentService.findByStudent(student);
            if (!studentList.isEmpty())
                prize.setStudentId(studentList.getFirst().getId());
            else return result;
        }
        Teacher teacher = new Teacher();
        if (teacherName != null && !teacherName.isEmpty()) {
            teacher.setName(teacherName);
            List<Teacher> teacherList = teacherService.findByTeacher(teacher);
            if (!teacherList.isEmpty())
                prize.setTeacherId(teacherList.getFirst().getId());
            else return result;
        }

        prizeMapper.findByPrize(prize).forEach(prize1 -> {
            HashMap<String, Object> obj = new HashMap<>();
            obj.put("id", prize1.getId());
            competition.setId(prize1.getCompetitionId());
            obj.put("competitionName", competitionService.findByCmp(competition).getFirst().getCompetitionName());
            obj.put("description", competitionService.findByCmp(competition).getFirst().getDescription());
            student.setId(prize1.getStudentId());
            obj.put("studentName", studentService.findByStudent(student).getFirst().getName());
            teacher.setId(prize1.getTeacherId());
            obj.put("teacherName", teacherService.findByTeacher(teacher).getFirst().getName());
            obj.put("status", switch (prize1.getStatus()) {
                case 1 -> "待审核";
                case 2 -> "已通过";
                case 3 -> "已驳回";
                default -> {
                    yield "异常, 请联系管理员";
                }
            });
            obj.put("certificatePath", prize1.getCertificatePath());
            obj.put("result", prize1.getResult());
            obj.put("participationDate", prize1.getParticipationDate());
            result.add(obj);
        });
        return result;
    }

    @Override
    public List<HashMap<String, Object>> findByPrizeLike(Prize prize, String competitionName, String studentName, String teacherName) {
        List<HashMap<String, Object>> result = new ArrayList<>();
        List<Competition> competitionList = new ArrayList<>();
        List<Student> studentList = new ArrayList<>();
        List<Teacher> teacherList = new ArrayList<>();
        if (competitionName != null) {
            Competition competition = new Competition();
            competition.setCompetitionName(competitionName);
            competitionList = competitionService.findByCmpLike(competition);
        }
        if (studentName != null) {
            Student student = new Student();
            student.setName(studentName);
            studentList = studentService.findByStudentLike(student);
        }
        if (teacherName != null) {
            Teacher teacher = new Teacher();
            teacher.setName(teacherName);
            teacherList = teacherService.findByTeacherLike(teacher);
        }
        List<Student> finalStudentList = studentList;
        List<Teacher> finalTeacherList = teacherList;

        if (!competitionList.isEmpty()) {
            competitionList.forEach(competition -> {
                prize.setCompetitionId(competition.getId());
                if (!finalStudentList.isEmpty()) {
                    finalStudentList.forEach(student -> {
                        prize.setStudentId(student.getId());
                        if (!finalTeacherList.isEmpty()) {
                            finalTeacherList.forEach(teacher -> {
                                prize.setTeacherId(teacher.getId());
                                prizeMapper.findByPrizeLike(prize).forEach(prize1 -> {
                                    HashMap<String, Object> obj = new HashMap<>();
                                    Competition competition2 = new Competition();
                                    competition2.setId(prize1.getCompetitionId());
                                    obj.put("competitionName", competitionService.findByCmp(competition2).getFirst().getCompetitionName());
                                    Student student2 = new Student();
                                    student2.setId(prize1.getStudentId());
                                    obj.put("studentName", studentService.findByStudent(student2).getFirst().getName());
                                    Teacher teacher2 = new Teacher();
                                    teacher2.setId(prize1.getTeacherId());
                                    obj.put("teacherName", teacherService.findByTeacher(teacher2).getFirst().getName());
                                    obj.put("status", prize1.getStatus());
                                    obj.put("certificatePath", prize1.getCertificatePath());
                                    obj.put("result", prize1.getResult());
                                    obj.put("participationDate", prize1.getParticipationDate());
                                    result.add(obj);
                                });
                            });
                        }
                    });
                } else {
                    if (!finalTeacherList.isEmpty()) {
                        finalTeacherList.forEach(teacher -> {
                            prize.setTeacherId(teacher.getId());
                            prizeMapper.findByPrizeLike(prize).forEach(prize1 -> {
                                HashMap<String, Object> obj = new HashMap<>();
                                Competition competition2 = new Competition();
                                competition2.setId(prize1.getCompetitionId());
                                obj.put("competitionName", competitionService.findByCmp(competition2).getFirst().getCompetitionName());
                                Student student2 = new Student();
                                student2.setId(prize1.getStudentId());
                                obj.put("studentName", studentService.findByStudent(student2).getFirst().getName());
                                Teacher teacher2 = new Teacher();
                                teacher2.setId(prize1.getTeacherId());
                                obj.put("teacherName", teacherService.findByTeacher(teacher2).getFirst().getName());
                                obj.put("status", prize1.getStatus());
                                obj.put("certificatePath", prize1.getCertificatePath());
                                obj.put("result", prize1.getResult());
                                obj.put("participationDate", prize1.getParticipationDate());
                                result.add(obj);
                            });
                        });
                    } else {
                        prizeMapper.findByPrizeLike(prize).forEach(prize1 -> {
                            HashMap<String, Object> obj = new HashMap<>();
                            Competition competition2 = new Competition();
                            competition2.setId(prize1.getCompetitionId());
                            obj.put("competitionName", competitionService.findByCmp(competition2).getFirst().getCompetitionName());
                            Student student2 = new Student();
                            student2.setId(prize1.getStudentId());
                            obj.put("studentName", studentService.findByStudent(student2).getFirst().getName());
                            Teacher teacher2 = new Teacher();
                            teacher2.setId(prize1.getTeacherId());
                            obj.put("teacherName", teacherService.findByTeacher(teacher2).getFirst().getName());
                            obj.put("status", prize1.getStatus());
                            obj.put("certificatePath", prize1.getCertificatePath());
                            obj.put("result", prize1.getResult());
                            obj.put("participationDate", prize1.getParticipationDate());
                            result.add(obj);
                        });
                    }
                }
            });
        } else {
            prizeMapper.findByPrizeLike(prize).forEach(prize1 -> {
                HashMap<String, Object> obj = new HashMap<>();
                Competition competition2 = new Competition();
                competition2.setId(prize1.getCompetitionId());
                obj.put("competitionName", competitionService.findByCmp(competition2).getFirst().getCompetitionName());
                Student student2 = new Student();
                student2.setId(prize1.getStudentId());
                obj.put("studentName", studentService.findByStudent(student2).getFirst().getName());
                Teacher teacher2 = new Teacher();
                teacher2.setId(prize1.getTeacherId());
                obj.put("teacherName", teacherService.findByTeacher(teacher2).getFirst().getName());
                obj.put("status", prize1.getStatus());
                obj.put("certificatePath", prize1.getCertificatePath());
                obj.put("result", prize1.getResult());
                obj.put("participationDate", prize1.getParticipationDate());
                result.add(obj);
            });
        }

        return result;


//        List<HashMap<String, Object>> result = new ArrayList<>();
//        Competition competition = new Competition();
//        competition.setCompetitionName(competitionName);
//        Student student = new Student();
//        student.setName(studentName);
//        Teacher teacher = new Teacher();
//        teacher.setName(teacherName);
//        competitionService.findByCmpLike(competition).forEach(competition1 -> {
//            prize.setCompetitionId(competition1.getId());
//            studentService.findByStudentLike(student).forEach(student1 -> {
//                prize.setStudentId(student1.getId());
//                teacherService.findByTeacherLike(teacher).forEach(teacher1 -> {
//                    prize.setTeacherId(teacher1.getId());
//                    prizeMapper.findByPrizeLike(prize).forEach(prize1 -> {
//                        HashMap<String, Object> obj = new HashMap<>();
//                        Competition competition2 = new Competition();
//                        competition2.setId(prize1.getCompetitionId());
//                        obj.put("competitionName", competitionService.findByCmp(competition2).getFirst().getCompetitionName());
//                        Student student2 = new Student();
//                        student2.setId(prize1.getStudentId());
//                        obj.put("studentName", studentService.findByStudent(student2).getFirst().getName());
//                        Teacher teacher2 = new Teacher();
//                        teacher2.setId(prize1.getTeacherId());
//                        obj.put("teacherName", teacherService.findByTeacher(teacher2).getFirst().getName());
//                        obj.put("status", prize1.getStatus());
//                        obj.put("certificatePath", prize1.getCertificatePath());
//                        obj.put("result", prize1.getResult());
//                        obj.put("participationDate", prize1.getParticipationDate());
//                        result.add(obj);
//                    });
//
//                });
//            });
//
//        });
//        return result;
    }
}
