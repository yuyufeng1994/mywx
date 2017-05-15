package utils;

/**
 * Created by yuyufeng on 2017/5/15.
 */
public class StringUtils {
    public static boolean isBlank(String jsonParam) {
        if (jsonParam == null || "".equals(jsonParam)) {
            return true;
        }
        return false;
    }
}
