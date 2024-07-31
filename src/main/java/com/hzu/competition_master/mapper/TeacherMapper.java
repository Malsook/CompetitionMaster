package com.hzu.competition_master.mapper;

import com.hzu.competition_master.bean.Competition;
import com.hzu.competition_master.bean.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TeacherMapper {
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
