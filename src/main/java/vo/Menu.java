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

    public static void main(String[] args) {
        Button button = new Button();
        button.setType("click");
        button.setName("新闻");
        button.setKey("BUTTON_NEWS");

        Button button2 = new Button();
        button2.setType("click");
        button2.setName("绑定");
        button2.setKey("BUTTON_DEPEND");

        Menu menu = new Menu();
        menu.addButton(button);
        menu.addButton(button2);

        System.out.println(JSONObject.toJSON(menu));
    }
}
