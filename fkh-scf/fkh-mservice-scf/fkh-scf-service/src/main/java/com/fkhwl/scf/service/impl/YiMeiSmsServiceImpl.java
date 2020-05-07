
package com.fkhwl.scf.service.impl;

import com.fkhwl.scf.service.YiMeiSmsService;
import com.fkhwl.scf.sms.YiMeiSmsClient;
import com.fkhwl.starter.notify.AbstractNotifyContent;
import org.jetbrains.annotations.Contract;
import org.springframework.stereotype.Service;

@Service
public class YiMeiSmsServiceImpl implements YiMeiSmsService {
    private YiMeiSmsClient yiMeiSmsClient;
    @Contract(
        pure = true
    )
    public YiMeiSmsServiceImpl(YiMeiSmsClient yiMeiSmsClient) {
        this.yiMeiSmsClient = yiMeiSmsClient;
    }

    public String sendMsg(AbstractNotifyContent notifyContent) {
        return this.yiMeiSmsClient.sendMsg(notifyContent);
    }

    public String getMo() throws Throwable {
        return this.yiMeiSmsClient.getMos();
    }
}
