package com.hzu.competition_master.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hzu.competition_master.bean.Clazz;
import com.hzu.competition_master.bean.Competition;
import com.hzu.competition_master.bean.Student;
import com.hzu.competition_master.service.ClazzService;
import com.hzu.competition_master.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/clazz")
@Transactional
public class ClazzController {

    @Autowired
    private ClazzService clazzService;
    @Autowired
    private StudentService studentService;

    /**
     * 加载班级列表页面
     */
    @GetMapping("/list")
    public String clazzList() {
        return "/clazz/clazz";
    }

    /**
     * 获取班级
     */
    @GetMapping
    @ResponseBody
    public Object getClazz(@RequestParam(value = "page", defaultValue = "1") Integer page,
                           @RequestParam(value = "rows", defaultValue = "100") Integer rows,
                           String clazzName,
                           String from,
                           String q,
                           boolean isLike) {
        Clazz clazz = new Clazz();
        if (q != null && !q.isEmpty()) clazzName = q;
        if (clazzName != null && !clazzName.isEmpty()) clazz.setClazzName(clazzName);
        List<Clazz> clazzList = isLike ? clazzService.findByClazzLike(clazz) : clazzService.findByClazz(clazz);
        if ("combox".equals(from)) {
            return clazzList;
        } else {
            Map<String, Object> resultJson = new HashMap();
            resultJson.put("total", clazzList.size());
            // 分页查询
            if ((page - 1) * rows >= clazzList.size()) resultJson.put("rows", new ArrayList<>());
            else if (page * rows - 1 >= clazzList.size()) resultJson.put("rows", clazzList.subList((page - 1) * rows, clazzList.size()));
            else resultJson.put("rows", clazzList.subList((page - 1) * rows, page * rows));
            return resultJson;
        }
    }

    /**
     * 添加班级
     */
    @PutMapping
    @ResponseBody
    public void addClazz(@RequestBody Clazz clazz) {
        //保存学生信息到数据库
        int count = clazzService.addByClazz(clazz);
        if(count > 0){
        }
    }

    /**
     * 删除学生
     */
    @DeleteMapping
    @ResponseBody
    public void deleteStudent(@RequestBody List<Clazz> clazzList){
        clazzList.forEach(clazz -> {
            clazzService.deleteByClazz(clazz);
        });
    }

    /**
     * 修改班级
     */
    @PostMapping
    @ResponseBody
    public void editClazz(@RequestBody Clazz clazz){
        clazzService.updateById(clazz);
    }

}
