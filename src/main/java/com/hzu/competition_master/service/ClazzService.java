package com.hzu.competition_master.service;

import com.hzu.competition_master.bean.Clazz;

import java.util.List;

public interface ClazzService {
    // 多条件增加
    int addByClazz(Clazz clazz);
    // 多条件删除
    int deleteByClazz(Clazz clazz);
    // 根据Id多条件更新
    int updateById(Clazz clazz);
    // 多条件查询
    List<Clazz> findByClazz(Clazz clazz);
    // 多条件模糊查询
    List<Clazz> findByClazzLike(Clazz clazz);
}
