package com.hzu.competition_master.controller;

import com.hzu.competition_master.bean.Competition;
import com.hzu.competition_master.bean.Student;
import com.hzu.competition_master.service.CompetitionService;
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
@RequestMapping("/competition")
@Transactional
public class CompetitionController {
    @Autowired
    private CompetitionService competitionService;

    /**
     * 加载竞赛列表页面
     */
    @GetMapping("/list")
    public String competitionList() {
        return "/competition/competition";
    }

    /**
     * 获取竞赛
     */
    @GetMapping
    @ResponseBody
    public Map<String, Object> getCompetition(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                          @RequestParam(value = "rows", defaultValue = "100") Integer rows,
                                          String competitionName,
                                          boolean isLike) {
        Competition competition = new Competition();
        competition.setCompetitionName(competitionName);
        List<Competition> competitionList = isLike ? competitionService.findByCmpLike(competition) : competitionService.findByCmp(competition);
        Map<String, Object> resultJson = new HashMap<>();
        resultJson.put("total", competitionList.size());
        // 分页查询
        if ((page - 1) * rows >= competitionList.size()) resultJson.put("rows", new ArrayList<>());
        else if (page * rows - 1 >= competitionList.size()) resultJson.put("rows", competitionList.subList((page - 1) * rows, competitionList.size()));
        else resultJson.put("rows", competitionList.subList((page - 1) * rows, page * rows));
        return resultJson;
    }

    /**
     * 添加学生
     */
    @PutMapping
    @ResponseBody
    public void addCompetition(@RequestBody Competition competition) {
        //保存学生信息到数据库
        int count = competitionService.addByCmp(competition);
        if(count > 0){
        }
    }

    /**
     * 删除学生
     */
    @DeleteMapping
    @ResponseBody
    public void deleteCompetition(@RequestBody List<Competition> competitionList){
        competitionList.forEach(competition -> {
            competitionService.deleteByCmp(competition);
        });
    }

    /**
     * 修改学生
     */
    @PostMapping
    @ResponseBody
    public void editCompetition(@RequestBody Competition competition){
        competitionService.updateById(competition);
    }

}
