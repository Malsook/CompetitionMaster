package com.hzu.competition_master.service.impl;

import com.hzu.competition_master.bean.Competition;
import com.hzu.competition_master.mapper.CompetitionMapper;
import com.hzu.competition_master.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetitionServiceImpl implements CompetitionService {
    @Autowired
    CompetitionMapper competitionMapper;

    @Override
    public int addByCmp(Competition Cmp) {
        try {
            return competitionMapper.addByCmp(Cmp);
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            // 字段重复
            throw dataIntegrityViolationException;
        }
    }

    @Override
    public int deleteByCmp(Competition Cmp) {
        return competitionMapper.deleteByCmp(Cmp);
    }

    @Override
    public int updateById(Competition Cmp) {
        try {
            return competitionMapper.updateById(Cmp);
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            // 字段重复
            throw dataIntegrityViolationException;
        }
    }

    @Override
    public List<Competition> findByCmp(Competition Cmp) {
        try {
            return competitionMapper.findByCmp(Cmp);
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            // 字段重复
            throw dataIntegrityViolationException;
        } catch (Exception dataIntegrityViolationException) {
            // 字段重复
            throw dataIntegrityViolationException;
        }
    }

    @Override
    public List<Competition> findByCmpLike(Competition Cmp) {
        return competitionMapper.findByCmpLike(Cmp);
    }
}
