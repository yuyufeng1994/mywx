package vo;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuyufeng on 2017/5/9.
 */
public class Menu {
    private List<Button> button = new ArrayList<Button>();

    public List<Button> getButton() {
        return button;
    }
    public void addButton(Button but) {
        button.add(but);
    }

    public void setButton(List<Button> button) {
        this.button = button;
    }


    public static String getMenuSting(){
        Button button = new Button();
        button.setType("click");
        button.setName("点击");
        button.setKey("CLICK_ME");

        Button fb = new Button();
        fb.setType("click");
        fb.setName("功能");
        fb.setKey("BUTTON_FUNCTION");

        Button fb1 = new Button();
        fb1.setName("1.人脸识别");
        fb1.setType("click");
        fb1.setKey("FN1");
        fb.add(fb1);

        Button fb2 = new Button();
        fb2.setName("2.功能二");
        fb2.setType("click");
        fb2.setKey("FN2");
        fb.add(fb2);

        Button fb3 = new Button();
        fb3.setName("3.VIEW_TEST");
        fb3.setType("view");
        fb3.setUrl("http://120.24.45.38/test");
        fb.add(fb3);

        Button fb4 = new Button();
        fb4.setName("4.图文消息");
        fb4.setType("click");
        fb4.setKey("FN4");
        fb.add(fb4);


        Menu menu = new Menu();
        menu.addButton(button);
        menu.addButton(fb);

        return JSONObject.toJSON(menu).toString();
    }


    public static void main(String[] args) {
        System.out.println(getMenuSting());
    }
}
