package com.gu.antiSpamCall.service.impl;

import com.gu.antiSpamCall.dao.BWListDao;
import com.gu.antiSpamCall.dao.CallDao;
import com.gu.antiSpamCall.dao.ConfigDao;
import com.gu.antiSpamCall.model.CallRecord;
import com.gu.antiSpamCall.model.SpamCallModelConfig;
import com.gu.antiSpamCall.service.SimService;
import com.gu.antiSpamCall.service.UserService;
import com.gu.antiSpamCall.util.evaluator.SpamEvaluatorContext;
import com.gu.antiSpamCall.util.evaluator.strategy.BaseModelEvaluator;
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

    @Resource
    UserService userService;

    @Override
    public String simCall(String from, String to) {
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        CallRecord record = new CallRecord(from, to, format.format(now), 1);
        callDao.save(record);

        //检测是否在白名单中
        Boolean isInWhiteList = userService.verifyInWhiteList(from, to);
        if (isInWhiteList) {
            log.info(String.format("模拟拨打(白名单): [%s] -> [%s].", from, to));
            return "拨打成功。";
        }

        //检查是否在黑名单中
        Boolean isInBlackList = userService.verifyInBlackList(from, to);
        if (isInBlackList) {
            log.info(String.format("模拟拨打(拦截): [%s] -> [%s].", from, to));
            return "号码被黑名单拦截。";
        }

        //检测是否达到黑名单标准
        long callCountToday = callDao.phoneCallCountByTime(record);
        SpamCallModelConfig baseConfig = configDao.findConfigByName("base_config");

        SpamEvaluatorContext evaluatorContext = new SpamEvaluatorContext(new BaseModelEvaluator(baseConfig, callCountToday));
        log.info(String.format("模拟拨打: [%s] -> [%s], 已拨打 [%d] 次, 模型限制 [%d] 次.", from, to, callCountToday, baseConfig.getCallFromSameNumToday()));
        if (!evaluatorContext.getResult()) {
            //超标
            userService.blackListAdd(from, to);
            log.info(String.format("模拟拨打: [%s] -> [%s], 拨打次数超标，已被加入黑名单.", from, to));
        }
        return "拨打成功。";
    }
}
