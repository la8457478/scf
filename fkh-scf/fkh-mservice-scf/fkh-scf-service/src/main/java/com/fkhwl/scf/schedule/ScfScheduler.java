package com.fkhwl.scf.schedule;


import com.fkhwl.scf.entity.dto.ScfConfigDTO;
import com.fkhwl.scf.entity.po.ScfConfig;
import com.fkhwl.scf.service.ScfConfigService;
import com.fkhwl.scf.service.ScfSchedulerService;
import com.isharing.commons.string.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import lombok.extern.slf4j.Slf4j;

/**
* 定时任务:
* @return:
* @Author: liuan
* @Date: 2020/4/10 19:30
*/
@Configuration
@Transactional
@Slf4j
public class ScfScheduler implements SchedulingConfigurer {

    @Autowired
    private ScfSchedulerService scfSchedulerService;
    @Autowired
    private ScfConfigService scfConfigService;

    private static String cron;
    @Value("${scf.schedule.open}")
    private Boolean scfScheduleOpen=false;
    private Runnable doTask() {
        return new Runnable() {
            @Override
            public void run() {
                log.info("-------------------------------执行"+cron);
                scfSchedulerService.generateSubitemClaimsOrder();//真正需要运行的逻辑代码
            }
        };
    }

    private Trigger getTrigger() {
        return new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                if(scfScheduleOpen){
                    // 触发器
                    CronTrigger trigger = new CronTrigger(getCron());
                    return trigger.nextExecutionTime(triggerContext);
                }
                return null;
            }
        };
    }

    /**
     * 　"0 0 12 * * ?" 每天中午12点触发
     * 　　　　"0 15 10 ? * *" 每天上午10:15触发
     * 　　　　"0 15 10 * * ?" 每天上午10:15触发
     * 　　　　"0 15 10 * * ? *" 每天上午10:15触发
     * 　　　　"0 15 10 * * ? 2005" 2005年的每天上午10:15触发
     * 　　　　"0 * 14 * * ?" 在每天下午2点到下午2:59期间的每1分钟触发
     * 　　　　"0 0/5 14 * * ?" 在每天下午2点到下午2:55期间的每5分钟触发
     * 　　　　"0 0/5 14,18 * * ?" 在每天下午2点到2:55期间和下午6点到6:55期间的每5分钟触发
     * 　　　　"0 0-5 14 * * ?" 在每天下午2点到下午2:05期间的每1分钟触发
     * 　　　　"0 10,44 14 ? 3 WED" 每年三月的星期三的下午2:10和2:44触发
     * 　　　　"0 15 10 ? * MON-FRI" 周一至周五的上午10:15触发
     * 　　　　"0 15 10 15 * ?" 每月15日上午10:15触发
     * 　　　　"0 15 10 L * ?" 每月最后一日的上午10:15触发
     * 　　　　"0 15 10 ? * 6L" 每月的最后一个星期五上午10:15触发
     * 　　　　"0 15 10 ? * 6L 2002-2005" 2002年至2005年的每月的最后一个星期五上午10:15触发
     * 　　　　"0 15 10 ? * 6#3" 每月的第三个星期五上午10:15触发
     * 　　　　每天早上6点     0 6 * * *     每两个小时     0
     *          每天0点 0 0 0 * * ?
     * <p>
     * <p>
     * 此方法每月一号凌晨1点触发
     */
    public String getCron() {
//        Jedis jedis = commonUtil.getJedis();
//        scfConfigService.getConfigByConfigKey()
        ScfConfigDTO scfConfig =   scfConfigService.getConfigByConfigKey(ScfConfig.SCHEDULE_EXECUTE_HOUR_CONFIG_KEY);
        String newCron =   "0 * * * * ?";//可以改成配置到数据库中
        if(scfConfig!=null){
            newCron =   "0 0 "+ scfConfig.getConfigValue()+ " * * ?";//可以改成配置到数据库中
        }

        if (StringUtils.isEmpty(newCron)) {
//            jedis.set("cron","0 30 0 ? * Mon");
            return newCron;
        }
        if (!newCron.equals(cron)) {
            log.info(new StringBuffer("Cron has been changed to:'").append(newCron).append("'. Old cron was:'").append(cron).append("'").toString());
            cron = newCron;
        }
        return cron;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(doTask(), getTrigger());
    }
}
