package com.hzu.competition_master.service;

import com.hzu.competition_master.bean.Teacher;

import java.util.List;

public interface TeacherService {
    // 多条件增加
    int addByTeacher(Teacher teacher);
    // 多条件删除
    int deleteByTeacher(Teacher teacher);
    // 根据Id多条件更新
    int updateById(Teacher teacher);
    // 多条件查询
    List<Teacher> findByTeacher(Teacher teacher);
    // 多条件模糊查询
    List<Teacher> findByTeacherLike(Teacher teacher);
}
