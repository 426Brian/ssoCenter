package com.sso.util;

import org.springframework.util.StreamUtils;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Brian in 0:38 2018/12/12
 */
public class HttpUtil {

    public static String sendRequest(String serverUrl, HashMap<String, String> paramMap) throws Exception {
        String resp = null;

        // 1. 建立 url
        URL url = new URL(serverUrl);

        // 2. 打开连接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        // 3. 设置请求方式
        conn.setRequestMethod("POST");

        // 4. 参数
        StringBuffer sb = new StringBuffer();
        if (paramMap != null && paramMap.size() > 0) {
            Iterator<Map.Entry<String, String>> iterator = paramMap.entrySet().iterator();
            int i = 1;
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                if(i == 1){
                    sb.append(entry.getKey()+"="+entry.getValue());
                }else{
                    sb.append("&"+entry.getKey()+"="+entry.getValue());
                }
                i++;
            }

        }

        // 5. 发送请求
        conn.setDoOutput(true);
        OutputStream outputStream = conn.getOutputStream();
        outputStream.write(new String(sb).getBytes(Charset.forName("utf-8")));
        outputStream.flush();
        outputStream.close();

        // 6. 接收响应
        InputStream inputStream = conn.getInputStream();
        resp = StreamUtils.copyToString(inputStream, Charset.forName("utf-8"));


        // 7. 关闭流, 断开连接
        inputStream.close();
        conn.disconnect();
        return resp;
    }
}
