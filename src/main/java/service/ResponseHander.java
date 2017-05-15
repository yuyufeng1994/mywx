package service;

import po.TextMeaasge;
import utils.FaceUtil;
import utils.FileDownloadUtil;
import utils.MessageUtil;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * Created by yuyufeng on 2017/5/15.
 */
public class ResponseHander {
    public static String doType(Map<String, String> map) throws IOException {
        String toUserName = map.get("ToUserName");
        String fromUserName = map.get("FromUserName");
        String msgType = map.get("MsgType");
        String content = map.get("Content");
        String PicUrl = map.get("PicUrl");
        String message = null;
        System.out.println("接收消息类型：" + msgType);
        if ("text".equals(msgType)) {                // 对文本消息进行处理
            message = textHander(toUserName, fromUserName, content);
        } else if ("image".equals(msgType)) {
            message = messageHander(toUserName, fromUserName, PicUrl);
        }else if("event".equals(msgType)){
            String event = map.get("Event");
            String eventKey = map.get("EventKey");
            message = EventHander.eventHander(toUserName, fromUserName, message, event,eventKey);
        }
        return message;
    }




    private static String textHander(String toUserName, String fromUserName, String content) {
        String message;
        TextMeaasge text = new TextMeaasge();
        text.setFromUserName(toUserName);
        text.setToUserName(fromUserName);
        text.setMsgType("text");
        text.setCreateTime(new Date().getTime());
        text.setContent("你发送的消息是：" + content);
        message = MessageUtil.textMessageToXML(text);
        System.out.println(message);
        return message;
    }

    private static String messageHander(String toUserName, String fromUserName, String picUrl) throws IOException {
        String message;
        TextMeaasge text = new TextMeaasge();
        text.setFromUserName(toUserName);         //
        text.setToUserName(fromUserName);
        text.setMsgType("text");
        text.setCreateTime(new Date().getTime());
        String upPath = "c://weixin_face/" + fromUserName + text.getCreateTime() + ".png";
        FileDownloadUtil.downloadFile(picUrl, upPath);
        System.out.println(upPath);
        int res = FaceUtil.countPerson(upPath);
        text.setContent("系统在你发送的图片中识别到了：" + res + " 个人");
        message = MessageUtil.textMessageToXML(text);
        return message;
    }
}
