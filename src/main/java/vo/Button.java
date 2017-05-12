package vo;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by yuyufeng on 2017/5/9.
 */
public class Button {
    private String type;
    private String name;
    private String key;
    private Button sub_button;
    private String url;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Button getSub_button() {
        return sub_button;
    }

    public void setSub_button(Button sub_button) {
        this.sub_button = sub_button;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
