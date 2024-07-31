package com.hzu.competition_master.controller;

import com.hzu.competition_master.bean.Student;
import com.hzu.competition_master.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/student")
@Transactional
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     * 加载学生列表页面
     */
    @GetMapping("/list")
    public String studentList() {
        return "/student/student";
    }

    /**
     * 获取学生
     */
    @GetMapping
    @ResponseBody
    public Map<String, Object> getStudent(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                          @RequestParam(value = "rows", defaultValue = "100") Integer rows,
                                          String studentName,
                                          String clazzName,
                                          boolean isLike) {
        Student student = new Student();
        if (studentName != null && !studentName.isEmpty()) student.setName(studentName);
        if (clazzName != null && !clazzName.isEmpty()) student.setClazzName(clazzName);
        List<Student> studentList = isLike ? studentService.findByStudentLike(student) : studentService.findByStudent(student);
        Map<String, Object> resultJson = new HashMap<>();
        resultJson.put("total", studentList.size());
        // 分页查询
        if ((page - 1) * rows >= studentList.size()) resultJson.put("rows", new ArrayList<>());
        else if (page * rows - 1 >= studentList.size()) resultJson.put("rows", studentList.subList((page - 1) * rows, studentList.size()));
        else resultJson.put("rows", studentList.subList((page - 1) * rows, page * rows));
        return resultJson;
    }

    /**
     * 添加学生
     */
    @PutMapping
    @ResponseBody
    public void addStudent(@RequestBody Student student) {
        //保存学生信息到数据库
        int count = studentService.addByStudent(student);
        if(count > 0){
        }
    }

    /**
     * 删除学生
     */
    @DeleteMapping
    @ResponseBody
    public void deleteStudent(@RequestBody List<Student> studentList){
        studentList.forEach(student -> {
            studentService.deleteByStudent(student);
        });
    }

    /**
     * 修改学生
     */
    @PostMapping
    @ResponseBody
    public void editStudent(@RequestBody Student student){
        studentService.updateById(student);
    }

}
