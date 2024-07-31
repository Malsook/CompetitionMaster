package com.hzu.competition_master.service.impl;

import com.hzu.competition_master.bean.Admin;
import com.hzu.competition_master.mapper.AdminMapper;
import com.hzu.competition_master.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public int updateById(Admin admin) {
        return adminMapper.updateById(admin);
    }

    @Override
    public List<Admin> findByAdmin(Admin admin) {
        return adminMapper.findByAdmin(admin);
    }
}
