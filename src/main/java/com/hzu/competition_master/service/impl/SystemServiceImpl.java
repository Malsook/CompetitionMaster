package com.hzu.competition_master.service.impl;

import com.hzu.competition_master.bean.Admin;
import com.hzu.competition_master.bean.Student;
import com.hzu.competition_master.bean.Teacher;
import com.hzu.competition_master.service.AdminService;
import com.hzu.competition_master.service.StudentService;
import com.hzu.competition_master.service.SystemService;
import com.hzu.competition_master.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemServiceImpl implements SystemService {
    @Autowired
    private AdminService adminService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @Override
    public boolean isSuccess(Object user) {
        if (user instanceof Admin) {
            return !adminService.findByAdmin((Admin) user).isEmpty();
        } else if (user instanceof Teacher) {
            return !teacherService.findByTeacher((Teacher) user).isEmpty();
        } else {
            return !studentService.findByStudent((Student) user).isEmpty();
        }
    }
}
