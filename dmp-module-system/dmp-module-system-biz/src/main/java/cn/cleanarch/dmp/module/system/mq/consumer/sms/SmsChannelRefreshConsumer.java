package cn.cleanarch.dmp.module.system.mq.consumer.sms;

import cn.cleanarch.dmp.framework.mq.core.pubsub.AbstractChannelMessageListener;
import cn.cleanarch.dmp.module.system.mq.message.sms.SmsChannelRefreshMessage;
import cn.cleanarch.dmp.module.system.service.sms.SmsChannelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 针对 {@link SmsChannelRefreshMessage} 的消费者
 *
 * @author 芋道源码
 */
@Component
@Slf4j
public class SmsChannelRefreshConsumer extends AbstractChannelMessageListener<SmsChannelRefreshMessage> {

    @Resource
    private SmsChannelService smsChannelService;

    @Override
    public void onMessage(SmsChannelRefreshMessage message) {
        log.info("[onMessage][收到 SmsChannel 刷新消息]");
        smsChannelService.initSmsClients();
    }

}
