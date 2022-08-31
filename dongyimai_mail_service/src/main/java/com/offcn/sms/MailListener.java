package com.offcn.sms;

import com.offcn.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * @author wcz
 * @create 2022-08-23 11:08
 */
@Component("mailListener")
public class MailListener implements MessageListener {
    @Autowired
    private MailUtil mailUtil;
    @Override
    public void onMessage(Message message) {
        if (message instanceof MapMessage) {

            MapMessage map = (MapMessage) message;

            try {
                mailUtil.textSend(map.getString("login"));
            } catch (JMSException e) {
                e.printStackTrace();
            }

        }
    }
}