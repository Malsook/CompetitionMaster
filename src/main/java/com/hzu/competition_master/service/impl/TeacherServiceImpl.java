package com.hzu.competition_master.service.impl;

import com.hzu.competition_master.bean.Teacher;
import com.hzu.competition_master.mapper.TeacherMapper;
import com.hzu.competition_master.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;
    @Override
    public int addByTeacher(Teacher teacher) {
        try {
            return teacherMapper.addByTeacher(teacher);
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            // 字段重复
            throw dataIntegrityViolationException;
        }
    }

    @Override
    public int deleteByTeacher(Teacher teacher) {
        return teacherMapper.deleteByTeacher(teacher);
    }

    @Override
    public int updateById(Teacher teacher) {
        try {
            return teacherMapper.updateById(teacher);
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            // 字段重复
            throw dataIntegrityViolationException;
        }
    }

    @Override
    public List<Teacher> findByTeacher(Teacher teacher) {
        return teacherMapper.findByTeacher(teacher);
    }

    @Override
    public List<Teacher> findByTeacherLike(Teacher teacher) {
        return teacherMapper.findByTeacherLike(teacher);
    }
}
