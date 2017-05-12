package action;

import com.alibaba.fastjson.JSONObject;
import org.dom4j.DocumentException;
import po.TextMeaasge;
import utils.CheckUtil;
import utils.FaceUtil;
import utils.FileDownloadUtil;
import utils.MessageUtil;
import vo.Button;
import vo.Menu;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

/**
 * Created by yuyufeng on 2017/5/9.
 */
@WebServlet(name = "WeixinServlet", urlPatterns = "/weixinServlet")
public class WeixinServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("WeixinServlet.doGet");
        // 接收微信服务器以Get请求发送的4个参数
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        PrintWriter out = response.getWriter();
        if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
            out.print(echostr);        // 校验通过，原样返回echostr参数内容
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("WeixinServlet.doPost");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/xml;charset=utf-8");
        PrintWriter out = response.getWriter();
        try {
            Map<String, String> map = MessageUtil.xmlToMap(request);
            String toUserName = map.get("ToUserName");
            String fromUserName = map.get("FromUserName");
            String msgType = map.get("MsgType");
            String content = map.get("Content");
            String PicUrl = map.get("PicUrl");
            System.out.println("接收消息类型：" + msgType);
            String message = null;
            if ("text".equals(msgType)) {                // 对文本消息进行处理
                TextMeaasge text = new TextMeaasge();
                text.setFromUserName(toUserName);         // 发送和回复是反向的
                text.setToUserName(fromUserName);
                text.setMsgType("text");
                text.setCreateTime(new Date().getTime());
                text.setContent("你发送的消息是：" + content);
                message = MessageUtil.textMessageToXML(text);
                System.out.println(message);
            } else if ("image".equals(msgType)) {
                TextMeaasge text = new TextMeaasge();
                text.setFromUserName(toUserName);         //
                text.setToUserName(fromUserName);
                text.setMsgType("text");
                text.setCreateTime(new Date().getTime());

//                System.out.println(message);
                String upPath = "c://weixin_face/" + fromUserName + text.getCreateTime()+".png";
                FileDownloadUtil.downloadFile(PicUrl, upPath);
                System.out.println(upPath);
                int res = FaceUtil.countPerson(upPath);
                text.setContent("系统在你发送的图片中识别到了：" + res+" 个人");
                message = MessageUtil.textMessageToXML(text);

            }

            out.print(message);                            // 将回应发送给微信服务器
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }
}
