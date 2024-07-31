package com.hzu.competition_master.controller;

import com.hzu.competition_master.service.AdminService;
import com.hzu.competition_master.service.StudentService;
import com.hzu.competition_master.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/person")
@Transactional
public class PersonController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;

    /**
     * 加载教师个人信息页面
     */
    @GetMapping("/teacher")
    public String teacher() {
        return "/person/teacher";
    }

    /**
     * 加载学生个人信息页面
     */
    @GetMapping("/student")
    public String student() {
        return "/person/student";
    }

}
