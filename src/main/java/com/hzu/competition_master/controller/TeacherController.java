package com.hzu.competition_master.controller;

import com.hzu.competition_master.bean.Student;
import com.hzu.competition_master.bean.Teacher;
import com.hzu.competition_master.service.StudentService;
import com.hzu.competition_master.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/teacher")
@Transactional
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    /**
     * 加载教师列表页面
     */
    @GetMapping("/list")
    public String teacherList() {
        return "/teacher/teacher";
    }

    /**
     * 获取教师
     */
    @GetMapping
    @ResponseBody
    public Map<String, Object> getTeacher(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                          @RequestParam(value = "rows", defaultValue = "100") Integer rows,
                                          String teacherName,
                                          boolean isLike) {
        Teacher teacher = new Teacher();
        if (!teacherName.isEmpty()) teacher.setName(teacherName);
        List<Teacher> teacherList = isLike ? teacherService.findByTeacherLike(teacher) : teacherService.findByTeacher(teacher);
        Map<String, Object> resultJson = new HashMap<>();
        resultJson.put("total", teacherList.size());
        // 分页查询
        if ((page - 1) * rows >= teacherList.size()) resultJson.put("rows", new ArrayList<>());
        else if (page * rows - 1 >= teacherList.size()) resultJson.put("rows", teacherList.subList((page - 1) * rows, teacherList.size()));
        else resultJson.put("rows", teacherList.subList((page - 1) * rows, page * rows));
        return resultJson;
    }

    /**
     * 添加教师
     */
    @PutMapping
    @ResponseBody
    public void addTeacher(@RequestBody Teacher teacher) {
        //保存学生信息到数据库
        int count = teacherService.addByTeacher(teacher);
        if(count > 0){
        }
    }

    /**
     * 删除教师
     */
    @DeleteMapping
    @ResponseBody
    public void deleteTeacher(@RequestBody List<Teacher> teacherList){
        teacherList.forEach(teacher -> {
            teacherService.deleteByTeacher(teacher);
        });
    }

    /**
     * 修改教师
     */
    @PostMapping
    @ResponseBody
    public void editTeacher(@RequestBody Teacher teacher){
        teacherService.updateById(teacher);
    }

}
