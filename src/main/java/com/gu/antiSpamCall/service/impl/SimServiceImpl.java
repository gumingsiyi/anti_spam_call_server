package com.gu.antiSpamCall.service.impl;

import com.gu.antiSpamCall.dao.CallDao;
import com.gu.antiSpamCall.dao.ConfigDao;
import com.gu.antiSpamCall.model.CallRecord;
import com.gu.antiSpamCall.service.SimService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Service
public class SimServiceImpl implements SimService {
    @Resource
    CallDao callDao;

    @Resource
    ConfigDao configDao;

    @Override
    public void simCall(String from, String to) {
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        CallRecord record = new CallRecord(from, to, format.format(now), 1);
        callDao.save(record);
        log.info(String.format("模拟拨打: %s -> %s", from, to));

        //检测是否达到黑名单标准
        long callTodayCount = callDao.phoneCallCountByTime(record);

    }
}
