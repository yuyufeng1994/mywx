package service;

import com.thoughtworks.xstream.XStream;
import po.*;
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
            }else if("FN4".equals(eventKey)){
                PicAndTextMessage ptm = new PicAndTextMessage();
                ptm.setMsgType("news");

                Item item = new Item();
                item.setTitle("文章1");
                item.setDescription("这是第一篇文章");
                item.setPicUrl("http://mmbiz.qpic.cn/mmbiz_jpg/nicmajLyG6OfdFdeVhtDY42OOS9y3r1JETYgNUvpNFVcsc2icdDlky7m5SjdovyzZu9oqDNwxFKnibdPZ0HGic5MtQ/0");
                item.setUrl("http://120.24.45.38/test");
                ptm.addItem(item);

                Item item2 = new Item();
                item2.setTitle("文章2");
                item2.setDescription("这是第二篇文章");
                item2.setPicUrl("http://mmbiz.qpic.cn/mmbiz_jpg/nicmajLyG6Odrn2yEoAWZY1PJSoLptLX5fpZDHGxJlYG1ZeI7SicRUXW5zx2iaucC1jXOVmhr6jj97mSS31AS9DQA/0");
                item2.setUrl("http://120.24.45.38/test");
                ptm.addItem(item2);

                ptm.setToUserName(fromUserName);
                ptm.setFromUserName(toUserName);
                ptm.setCreateTime(new Date().getTime());
                ptm.setArticleCount(ptm.getArticles().size()+"");
                XStream xstream = new XStream();              // 使用XStream将实体类的实例转换成xml格式
                xstream.alias("xml", ptm.getClass()); // 将xml的默认根节点替换成“xml”
                xstream.alias("item", Item.class); //别名替换类
                message = xstream.toXML(ptm);
            }else if("TEST".equals(eventKey)){
                PicMessage pm = new PicMessage();
                pm.setFromUserName(toUserName);
                pm.setToUserName(fromUserName);
                pm.setMsgType("image");
                pm.setCreateTime(new Date().getTime());
                PicMessageImage pmi = new PicMessageImage();
                pmi.setMediaId("9krGQleOLNXdXXJDl1eAsDs3Ff4sei1eWujS5gpp5Q8fSaED19L2xBiKoPka2N1z");
                pm.setImage(pmi);
                XStream xstream = new XStream();
                xstream.alias("xml", pm.getClass()); // 将xml的默认根节点替换成“xml”
                xstream.alias("image", pmi.getClass()); // 将xml的默认根节点替换成“xml”
                message = xstream.toXML(pm);

               /* TextMeaasge text = new TextMeaasge();
                text.setFromUserName(toUserName);
                text.setToUserName(fromUserName);
                text.setMsgType("text");
                text.setCreateTime(new Date().getTime());
                text.setContent("你点了我一下test~");
                XStream xstream = new XStream();
                xstream.alias("xml", text.getClass()); // 将xml的默认根节点替换成“xml”
                message = xstream.toXML(text);*/
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
