package po;

import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yuyufeng on 2017/5/15.
 */
public class PicAndTextMessage {
    private String ToUserName;
    private String FromUserName;
    private long CreateTime;
    private String MsgType;
    private String ArticleCount;
    private List<Item> Articles;
    private String Title;
    private String PicUrl;
    private String Url;

    public void addItem(Item item) {
        if (null == Articles) {
            Articles = new ArrayList<Item>();
        }
        Articles.add(item);
    }

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(String articleCount) {
        ArticleCount = articleCount;
    }

    public List<Item> getArticles() {
        return Articles;
    }

    public void setArticles(List<Item> articles) {
        Articles = articles;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public static void main(String[] args) {
        PicAndTextMessage ptm = new PicAndTextMessage();
        ptm.setMsgType("news");

        Item item = new Item();
        item.setTitle("文章1");
        item.setDescription("这是第一篇文章");
        item.setPicUrl("http://mmbiz.qpic.cn/mmbiz_jpg/nicmajLyG6Odrn2yEoAWZY1PJSoLptLX5fpZDHGxJlYG1ZeI7SicRUXW5zx2iaucC1jXOVmhr6jj97mSS31AS9DQA/0");
        item.setUrl("http://120.24.45.38/test");
        ptm.addItem(item);

        Item item2 = new Item();
        item2.setTitle("文章2");
        item2.setDescription("这是第二篇文章");
        item2.setPicUrl("http://mmbiz.qpic.cn/mmbiz_jpg/nicmajLyG6Odrn2yEoAWZY1PJSoLptLX5fpZDHGxJlYG1ZeI7SicRUXW5zx2iaucC1jXOVmhr6jj97mSS31AS9DQA/0");
        item2.setUrl("http://120.24.45.38/test");
        ptm.addItem(item2);


        ptm.setToUserName("");
        ptm.setFromUserName("");
        ptm.setCreateTime(new Date().getTime());
        ptm.setArticleCount(ptm.getArticles().size() + "");
        XStream xstream = new XStream();              // 使用XStream将实体类的实例转换成xml格式
        xstream.alias("xml", ptm.getClass()); // 将xml的默认根节点替换成“xml”
        xstream.alias("item", Item.class); //别名替换类
        String res = xstream.toXML(ptm);
        System.out.println(res);
    }
}
