package com.example.day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created 朱治文lenovo on 2018/5/10
 * .
 */

public class NetWordUtils {
    private static String tag = "getNetJson";

    public static String getNetJson(String urlString) {
        try {
            //把接口地址封装到URL对象中；
            URL url = new URL(urlString);
            //使用HttpURLConnection， HttpURLConnection是URLConnection的子类；
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(8000);
            //200代表请求数据成功
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == 200) {
                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp="";
                StringBuilder stringBuilder = new StringBuilder();

                while((temp = bufferedReader.readLine()) != null) {
                    stringBuilder.append(temp);
                }
                String result = stringBuilder.toString();
                return result;

            }else {
                //do nothing
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
