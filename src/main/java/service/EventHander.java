package service;

import po.TextMeaasge;
import utils.MessageUtil;

import java.util.Date;

/**
 * Created by yuyufeng on 2017/5/15.
 */
public class EventHander {
    public static String eventHander(String toUserName, String fromUserName, String message, String event,String eventKey) {
        if("subscribe".equals(event)){
            message = subscribeEvent(toUserName, fromUserName);
        }else if("CLICK".equals(event)){
            System.out.println(eventKey);
            if("FN1".equals(eventKey)){
                TextMeaasge text = new TextMeaasge();
                text.setFromUserName(toUserName);
                text.setToUserName(fromUserName);
                text.setMsgType("text");
                text.setCreateTime(new Date().getTime());
                text.setContent("给我发一张照片就可以识别照片中有多少张脸啦~");
                message = MessageUtil.textMessageToXML(text);
            }else if("FN2".equals(eventKey)){
                TextMeaasge text = new TextMeaasge();
                text.setFromUserName(toUserName);
                text.setToUserName(fromUserName);
                text.setMsgType("text");
                text.setCreateTime(new Date().getTime());
                text.setContent("第二个功能还没有~");
                message = MessageUtil.textMessageToXML(text);
            }else if("CLICK_ME".equals(eventKey)){
                TextMeaasge text = new TextMeaasge();
                text.setFromUserName(toUserName);
                text.setToUserName(fromUserName);
                text.setMsgType("text");
                text.setCreateTime(new Date().getTime());
                text.setContent("你点了我一下~");
                message = MessageUtil.textMessageToXML(text);
            }
        }
        return message;
    }

    private static String subscribeEvent(String toUserName, String fromUserName) {
        String message;
        TextMeaasge text = new TextMeaasge();
        text.setFromUserName(toUserName);
        text.setToUserName(fromUserName);
        text.setMsgType("text");
        text.setCreateTime(new Date().getTime());
        text.setContent("欢迎关注yyf的公众号。试试功能把：（1）发一张照片就可以识别照片中有多少张脸。（2）其它好玩的功能在开发中哦~");
        message = MessageUtil.textMessageToXML(text);
        return message;
    }
}
