package com.gu.antiSpamCall.service.impl;

import com.gu.antiSpamCall.dao.CallDao;
import com.gu.antiSpamCall.dao.ConfigDao;
import com.gu.antiSpamCall.dto.response.SpamCallModelResponse;
import com.gu.antiSpamCall.model.CallRecord;
import com.gu.antiSpamCall.model.SpamCallModelConfig;
import com.gu.antiSpamCall.service.ModelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Service
public class ModelServiceImpl implements ModelService {
    @Resource
    ConfigDao configDao;

    @Resource
    CallDao callDao;

    @Override
    public SpamCallModelResponse getConfig(String name) {
        SpamCallModelResponse response = new SpamCallModelResponse();
        SpamCallModelConfig config = configDao.findConfigByName(name);
        response.setConfig(config);
        log.info(String.format("获取防骚扰模型 [%s]", name));
        return response;
    }

    @Override
    public boolean updateBaseConfig(SpamCallModelConfig config) {
        try {
            configDao.updateBaseConfig(config);
            log.info("模型更新成功 [base_config]");
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public long queryCallCountToday(String from, String to) {
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(now);
        CallRecord record = new CallRecord(null, from, to, time, -1);
        long res = callDao.phoneCallCountByTime(record);
        //log.info(String.format("[%s] [%s] -> [%s] 拨打 [%d] 次.", time, from, to, res));
        return  res;
    }
}
