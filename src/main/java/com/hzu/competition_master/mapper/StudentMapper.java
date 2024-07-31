package com.hzu.competition_master.mapper;

import com.hzu.competition_master.bean.Competition;
import com.hzu.competition_master.bean.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentMapper {
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
