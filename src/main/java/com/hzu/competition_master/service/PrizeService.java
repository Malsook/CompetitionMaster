package com.hzu.competition_master.service;

import com.hzu.competition_master.bean.Prize;

import java.util.HashMap;
import java.util.List;

public interface PrizeService {
    // 多条件增加
    int addByPrize(Prize prize, String competitionName, String studentName, String teacherName);
    // 多条件删除
    int deleteByPrize(Prize prize);
    // 根据Id多条件更新
    int updateById(Prize prize, String competitionName, String studentName, String teacherName);
    // 根据Id多条件更新
    int updateById(Prize prize);
    // 多条件查询
    List<HashMap<String, Object>> findByPrize(Prize prize, String competitionName, String studentName, String teacherName);
    // 多条件模糊查询
    List<HashMap<String, Object>> findByPrizeLike(Prize prize, String competitionName, String studentName, String teacherName);
}
