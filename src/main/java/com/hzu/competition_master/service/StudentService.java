package com.hzu.competition_master.service;

import com.hzu.competition_master.bean.Student;

import java.util.List;

public interface StudentService {
    // 多条件增加
    int addByStudent(Student student);
    // 多条件删除
    int deleteByStudent(Student student);
    // 根据Id多条件更新
    int updateById(Student student);
    // 多条件查询
    List<Student> findByStudent(Student student);
    // 多条件模糊查询
    List<Student> findByStudentLike(Student student);
}
