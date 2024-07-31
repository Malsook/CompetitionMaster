package com.hzu.competition_master.service.impl;

import com.hzu.competition_master.bean.Clazz;
import com.hzu.competition_master.mapper.ClazzMapper;
import com.hzu.competition_master.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    ClazzMapper clazzMapper;
    @Override
    public int addByClazz(Clazz clazz) {
        try {
            return clazzMapper.addByClazz(clazz);
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            // 字段重复
            throw dataIntegrityViolationException;
        }
    }

    @Override
    public int deleteByClazz(Clazz clazz) {
        try {
            return clazzMapper.deleteByClazz(clazz);
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            // 字段冲突
            throw dataIntegrityViolationException;
        }
    }

    @Override
    public int updateById(Clazz clazz) {
        try {
            return clazzMapper.updateById(clazz);
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            // 字段重复
            throw dataIntegrityViolationException;
        }
    }

    @Override
    public List<Clazz> findByClazz(Clazz clazz) {
        return clazzMapper.findByClazz(clazz);
    }

    @Override
    public List<Clazz> findByClazzLike(Clazz clazz) {
        return clazzMapper.findByClazzLike(clazz);
    }
}
