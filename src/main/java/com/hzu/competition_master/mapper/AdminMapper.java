package com.hzu.competition_master.mapper;

import com.hzu.competition_master.bean.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdminMapper {
    // 根据Id多条件更新
    int updateById(Admin admin);
    // 多条件查询
    List<Admin> findByAdmin(Admin admin);
}
