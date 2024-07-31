package com.hzu.competition_master.service.impl;

import com.hzu.competition_master.bean.Student;
import com.hzu.competition_master.mapper.StudentMapper;
import com.hzu.competition_master.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public int addByStudent(Student student) {
        try {
            return studentMapper.addByStudent(student);
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            // 字段重复
            throw dataIntegrityViolationException;
        }
    }

    @Override
    public int deleteByStudent(Student student) {
        return studentMapper.deleteByStudent(student);
    }

    @Override
    public int updateById(Student student) {
        try {
            return studentMapper.updateById(student);
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            // 字段重复
            throw dataIntegrityViolationException;
        }
    }

    @Override
    public List<Student> findByStudent(Student student) {

        return studentMapper.findByStudent(student);
    }

    @Override
    public List<Student> findByStudentLike(Student student) {
        return studentMapper.findByStudentLike(student);
    }
}
