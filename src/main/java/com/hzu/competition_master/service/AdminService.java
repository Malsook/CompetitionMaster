package com.hzu.competition_master.service;

import com.hzu.competition_master.bean.Admin;

import java.util.List;
import java.util.ServiceLoader;

public interface AdminService {
    // 根据Id多条件更新
    int updateById(Admin admin);
    // 多条件查询
    List<Admin> findByAdmin(Admin admin);
}
