package com.hzu.competition_master.controller;

import com.hzu.competition_master.bean.Admin;
import com.hzu.competition_master.bean.Student;
import com.hzu.competition_master.bean.Teacher;
import com.hzu.competition_master.service.AdminService;
import com.hzu.competition_master.service.StudentService;
import com.hzu.competition_master.service.TeacherService;
import com.hzu.competition_master.service.SystemService;
import com.hzu.competition_master.util.Const;
import com.hzu.competition_master.util.CpachaUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private SystemService systemService;

    /**
     * 跳转登录界面
     */
    @GetMapping("/login")
    public String login() {
        return "system/login";
    }

    /**
     * 跳转登录界面
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.setAttribute(Const.USERTYPE, "");
        session.setAttribute(Const.USERINFO, "");
        return "redirect:/login";
    }

    /**
     * 登录校验
     */
    @PostMapping("/login/verify")
    @ResponseBody
    public Map<String, Object> loginVerify(@RequestBody Map<String, String> requestJson, HttpSession session) {
//        Admin admin = new Admin();
//        admin.setAccount("1");
//        admin.setPassword("1");
//        admin.setId(3);
//        admin.setName("学生");
//        String type = requestJson.get("type");
//        session.setAttribute(Const.USERTYPE, type);
//        session.setAttribute(Const.USERINFO, admin);
//        Map<String, Object> resultJson = new HashMap<>();
//        resultJson.put("success", true);
//        return resultJson;

        String code = requestJson.get("code");
        String type = requestJson.get("type");
        String account = requestJson.get("account");
        String password = requestJson.get("password");
        Map<String, Object> resultJson = new HashMap<>();

        boolean isCodeRight = !code.equalsIgnoreCase((String) session.getAttribute(Const.CODE));
        switch (type) {
            case Const.ADMIN -> {
                Admin admin = new Admin();
                admin.setAccount(account);
                admin.setPassword(password);
                if (systemService.isSuccess(admin)) {
                    // 账号密码验证成功返回ajax状态, 设置session状态
                    admin = adminService.findByAdmin(admin).getFirst();
                    resultJson.put("success", true);
                    session.setAttribute(Const.USERTYPE, Const.ADMIN);
                    session.setAttribute(Const.USERINFO, admin);
                }
            }
            case Const.TEACHER -> {
                Teacher teacher = new Teacher();
                teacher.setAccount(account);
                teacher.setPassword(password);
                if (systemService.isSuccess(teacher)) {
                    // 账号密码验证成功设置ajax状态, 设置session状态
                    teacher = teacherService.findByTeacher(teacher).getFirst();
                    resultJson.put("success", true);
                    session.setAttribute(Const.USERTYPE, Const.TEACHER);
                    session.setAttribute(Const.USERINFO, teacher);
                }
            }
            case Const.STUDENT -> {
                Student student = new Student();
                student.setAccount(account);
                student.setPassword(password);
                if (systemService.isSuccess(student)) {
                    // 账号密码验证成功设置ajax状态, 设置session状态
                    student = studentService.findByStudent(student).getFirst();
                    resultJson.put("success", true);
                    session.setAttribute(Const.USERTYPE, Const.STUDENT);
                    session.setAttribute(Const.USERINFO, student);
                }
            }
        }
        if (!resultJson.containsKey("success")) {
            // 账号密码验证失败设置ajax状态
            resultJson.put("success", false);
            resultJson.put("message", "用户名或密码错误");
        } else {
            // 账号密码验证成功, 验证码验证
            if (!code.equalsIgnoreCase((String) session.getAttribute(Const.CODE))) {
                // 验证码验证失败设置ajax状态
                resultJson.put("success", false);
                resultJson.put("message", "验证码错误");
            }
        }
        return resultJson;
    }

    /**
     * 验证码生成
     */
    @GetMapping("/checkCode")
    public void generateCheckCode(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam(value = "vl", defaultValue = "4", required = false) Integer vl,
                               @RequestParam(value = "w", defaultValue = "110", required = false) Integer w,
                               @RequestParam(value = "h", defaultValue = "39", required = false) Integer h) {
        CpachaUtil cpachaUtil = new CpachaUtil(vl, w, h);
        String generatorVCode = cpachaUtil.generatorVCode();
        // 将验证码存入session
        request.getSession().setAttribute(Const.CODE, generatorVCode);
        BufferedImage generatorRotateVCodeImage = cpachaUtil.generatorRotateVCodeImage(generatorVCode, true);
        try {
            ImageIO.write(generatorRotateVCodeImage, "gif", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
