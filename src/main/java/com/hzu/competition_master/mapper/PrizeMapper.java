package com.hzu.competition_master.mapper;

import com.hzu.competition_master.bean.Prize;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PrizeMapper {
    // 多条件增加
    int addByPrize(Prize prize);
    // 多条件删除
    int deleteByPrize(Prize prize);
    // 根据Id多条件更新
    int updateById(Prize prize);
    // 多条件查询
    List<Prize> findByPrize(Prize prize);
    // 多条件模糊查询
    List<Prize> findByPrizeLike(Prize prize);
}
