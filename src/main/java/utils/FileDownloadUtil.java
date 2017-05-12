package utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by yuyufeng on 2017/5/9.
 */
public class FileDownloadUtil {
    public static void main(String[] args) throws IOException {
        FileDownloadUtil.downloadFile("http://mmbiz.qpic.cn/mmbiz_jpg/nicmajLyG6Off4uDKoWSymYlqopWeUA5VVrdJ5XWJcs3hNZ4VNq4DEjGPY6iaOsV1J0LtRz82yVTqCewyzpfhxCw/0", "H://test/wx/aa.png");

    }
    public static void downloadFile(String fileUrl, String path) throws IOException {
        URL url;
        try {
            url = new URL(fileUrl);
        } catch (MalformedURLException e2) {
            e2.printStackTrace();
            return;
        }


        InputStream is;
        OutputStream os = new FileOutputStream(path);
        try {
            is = url.openStream();
        } catch (IOException e1) {
            e1.printStackTrace();
            return;
        }


        byte[] b = new byte[1024];
        int len;
        while ((len = is.read(b)) != -1) {
            os.write(b, 0, len);
        }
        os.close();
        is.close();

    }
}
