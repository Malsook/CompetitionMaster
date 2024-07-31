package com.hzu.competition_master.mapper;

import com.hzu.competition_master.bean.Clazz;
import com.hzu.competition_master.bean.Competition;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CompetitionMapper {
    // 多条件增加
    int addByCmp(Competition Cmp);
    // 多条件删除
    int deleteByCmp(Competition Cmp);
    // 根据Id多条件更新
    int updateById(Competition Cmp);
    // 多条件查询
    List<Competition> findByCmp(Competition Cmp);
    // 多条件模糊查询
    List<Competition> findByCmpLike(Competition Cmp);
}
