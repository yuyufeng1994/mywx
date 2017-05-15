package httpclient;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import vo.Button;
import vo.Menu;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yuyufeng on 2017/5/15.
 */
public class MenuHttp {
    private static String appid = "wx8bdab45fad48c7a2";
    private static String secret = "a7200677b6783fb567c8de9dee2e4197";

    @Test
    public void testGetMenu() throws Exception {
        String accessToken = getAccess();
        String uri = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=" + accessToken;
        String res = doGet(uri);
        System.out.println(res);
    }
    @Test
    public void testDelMenu() throws Exception {
        String accessToken = getAccess();
        String uri = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=" + accessToken;
        String res = doGet(uri);
        System.out.println(res);
    }


    @Test
    public void testCreate() throws Exception {
        String accessToken = getAccess();
        //创建自定义菜单
        createMenu("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessToken,Menu.getMenuSting());

    }

    private static String getAccess() throws IOException {
        String uri = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + secret;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(uri);
        System.out.println("executing request " + httpget.getURI());
        // 执行get请求.
        CloseableHttpResponse response = httpclient.execute(httpget);
        try {
            // 获取响应实体
            HttpEntity entity = response.getEntity();
            System.out.println("--------------------------------------");
            // 打印响应状态
            System.out.println(response.getStatusLine());
            if (entity != null) {
                // 打印响应内容长度
//                System.out.println("Response content length: " + entity.getContentLength());
                // 打印响应内容
                String result = EntityUtils.toString(entity);
                System.out.println("Response content: " + result);
                JSONObject json = JSONObject.parseObject(result);
                return json.get("access_token").toString();
            }
            System.out.println("------------------------------------");
        } finally {
            response.close();
        }
        return null;
    }

    public static void createMenu(String uri, String params) throws Exception {
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost
        HttpPost httppost = new HttpPost(uri);
        try {
            httppost.setEntity((new StringEntity(params, "UTF-8")));
            System.out.println("executing request " + httppost.getURI());
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    System.out.println("--------------------------------------");
                    System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));
                    System.out.println("--------------------------------------");
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String doGet(String uri) throws Exception {
//        String uri = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + secret;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(uri);
        System.out.println("executing request " + httpget.getURI());
        // 执行get请求.
        CloseableHttpResponse response = httpclient.execute(httpget);
        try {
            // 获取响应实体
            HttpEntity entity = response.getEntity();
            System.out.println("--------------------------------------");
            // 打印响应状态
            System.out.println(response.getStatusLine());
            if (entity != null) {
                // 打印响应内容长度
//                System.out.println("Response content length: " + entity.getContentLength());
                // 打印响应内容
                String result = EntityUtils.toString(entity);
//                System.out.println("Response content: " + result);
//                JSONObject json = JSONObject.parseObject(result);
                return result;
            }
            System.out.println("------------------------------------");
        } finally {
            response.close();
        }
        return null;
    }

    /**
     * 发送 post请求访问本地应用并根据传递参数不同返回不同结果
     */
    public static void post(String uri, Map<String, String> map) {
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost
        HttpPost httppost = new HttpPost(uri);
        // 创建参数队列
        List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
        for (String s : map.keySet()) {
            formparams.add(new BasicNameValuePair(s, map.get(s)));
        }

        UrlEncodedFormEntity uefEntity;
        try {
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
            httppost.setEntity(uefEntity);
            System.out.println("executing request " + httppost.getURI());
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    System.out.println("--------------------------------------");
                    System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));
                    System.out.println("--------------------------------------");
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
