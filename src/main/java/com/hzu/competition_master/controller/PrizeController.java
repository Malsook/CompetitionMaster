package com.hzu.competition_master.controller;

import com.hzu.competition_master.bean.Competition;
import com.hzu.competition_master.bean.Prize;
import com.hzu.competition_master.bean.Student;
import com.hzu.competition_master.bean.Teacher;
import com.hzu.competition_master.service.CompetitionService;
import com.hzu.competition_master.service.PrizeService;
import com.hzu.competition_master.service.StudentService;
import com.hzu.competition_master.service.TeacherService;
import com.hzu.competition_master.util.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/prize")
@Transactional
public class PrizeController {
    @Autowired
    private PrizeService prizeService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CompetitionService competitionService;
    @Autowired
    private UploadUtils uploadUtils;

    /**
     * 加载奖项信息页面
     */
    @GetMapping("/list")
    public String prizeList() {
        return "/prize/prizeInfo";
    }

    /**
     * 加载奖项审核页面
     */
    @GetMapping("/process/list")
    public String prizeProcessList() {
        return "/prize/prizeProcess";
    }

    /**
     * 获取奖项
     */
    @GetMapping
    @ResponseBody
    public Map<String, Object> getPrize(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                        @RequestParam(value = "rows", defaultValue = "100") Integer rows,
                                        String competitionName,
                                        String studentName,
                                        String teacherName,
                                        @RequestParam(value = "status", defaultValue = "0") int status,
                                        String result,
                                        boolean isLike) {
        Prize prize = new Prize();
        prize.setStatus(status);
        if (result != null && !result.isEmpty()) prize.setResult(result);
        List<HashMap<String, Object>> objs = isLike ? prizeService.findByPrizeLike(prize, competitionName, studentName, teacherName) : prizeService.findByPrize(prize, competitionName, studentName, teacherName);
        Map<String, Object> resultJson = new HashMap<>();
        resultJson.put("total", objs.size());
        // 分页查询
        if ((page - 1) * rows >= objs.size()) resultJson.put("rows", new ArrayList<>());
        else if (page * rows - 1 >= objs.size()) resultJson.put("rows", objs.subList((page - 1) * rows, objs.size()));
        else resultJson.put("rows", objs.subList((page - 1) * rows, page * rows));
        return resultJson;
    }

    /**
     * 添加奖项
     */
    @PutMapping
    @ResponseBody
    public void addPrize(@RequestBody Map<String, Object> requestJson) throws IOException {
        Prize prize = new Prize();
        prize.setResult(((String) requestJson.get("result")).isEmpty() ? null : (String) requestJson.get("result"));
        prize.setParticipationDate(((String) requestJson.get("participationDate")).isEmpty() ? null : (String) requestJson.get("participationDate"));
        prize.setCertificatePath(((String) requestJson.get("certificatePath")).isEmpty() ? null : (String) requestJson.get("certificatePath"));
        //保存信息到数据库
        prize.setStatus(Prize.STATUS_WAIT);
        int count = prizeService.addByPrize(prize, (String) requestJson.get("competitionName"), (String) requestJson.get("studentName"), (String) requestJson.get("teacherName"));

    }

    @PutMapping(value = "/uploadCertificate")
    @ResponseBody
    public String uploadCertificate(MultipartFile file) throws IOException {
        return uploadUtils.upload(file);
    }

    /**
     * 删除奖项
     */
    @DeleteMapping
    @ResponseBody
    public void deletePrize(@RequestBody List<Prize> prizeList) {
        prizeList.forEach(prize -> {
            prizeService.deleteByPrize(prize);
        });
    }

    /**
     * 修改奖项
     */
    @PostMapping
    @ResponseBody
    public void editPrize(@RequestBody Map<String, Object> requestJson) {
        Prize prize = new Prize();
        prize.setId(Integer.parseInt((String) requestJson.get("id")));
        prize.setResult(((String) requestJson.get("result")).isEmpty() ? null : (String) requestJson.get("result"));
        prize.setParticipationDate(((String) requestJson.get("participationDate")).isEmpty() ? null : (String) requestJson.get("participationDate"));

        prize.setStatus(switch ((String) requestJson.get("status")) {
            case "待审核" -> 1;
            case "已通过" -> 2;
            case "已驳回" -> 3;
            default -> throw new IllegalStateException("Unexpected value: " + (String) requestJson.get("status"));
        });
        prizeService.updateById(prize, (String) requestJson.get("competitionName"), (String) requestJson.get("studentName"), (String) requestJson.get("teacherName"));
    }

    /**
     * 审批奖项
     */
    @PostMapping("/processPrize")
    @ResponseBody
    public void processPrize(@RequestBody Map<String, Object> requestJson) {
        Prize prize = new Prize();
        prizeService.updateById(prize);
    }
}
