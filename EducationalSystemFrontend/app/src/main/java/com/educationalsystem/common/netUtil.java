package com.educationalsystem.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class netUtil {
    private static final String url="http://169.254.174.138:8080";
    public  static  String doGet(String urlString) throws ExecutionException, InterruptedException {
        String urlGet=url+urlString;
        String result="";
        FutureTask<Object> futureTask = new FutureTask<>(() -> {
            String res = null;
            try {
                BufferedReader reader = null;
                String bookJSONString = null;
                //1.建立连接
                HttpURLConnection httpURLConnection = null;
                URL requestURL = new URL(urlGet);
                httpURLConnection = (HttpURLConnection) requestURL.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.connect();

                //2.获取二进制流
                InputStream inputStream = httpURLConnection.getInputStream();

                //3.将二进制流包装

                reader = new BufferedReader(new InputStreamReader(inputStream));

                //4.从BufferedReader中一行行读取字符串。使用StringBuilder接受。
                String line;
                StringBuilder builder = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
                if (builder.length() == 0) {
                    res = null;
                }

                //5.返回json对应的String数据。
                res = builder.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return res;
        });
        Thread thread = new Thread(futureTask);
        thread.start();
        thread.join();
        result = (String) futureTask.get();

        return  result;

    }
    public  static  String doPost(String urlString,String data) throws InterruptedException, ExecutionException {
        FutureTask<String> futureTask = new FutureTask<>(()->{
            StringBuilder res=new StringBuilder();
            try {
                URL myUrl=new URL(url+urlString);
                HttpURLConnection conn =(HttpURLConnection)myUrl.openConnection();  //获取URLConnection对象
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");  // 设置为POST方式
                conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                OutputStreamWriter outputStreamWriter=new OutputStreamWriter(conn.getOutputStream());
                BufferedWriter bufferedWriter=new BufferedWriter(outputStreamWriter);
                bufferedWriter.write(data);
                bufferedWriter.flush();

                InputStream inputStream = conn.getInputStream();  //读数据,得到的是字节流
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");//包装为字符流
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    res.append(line);
                }
                bufferedReader.close();
                inputStreamReader.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
          return res.toString();
        });
        Thread thread = new Thread(futureTask);
        thread.start();
        thread.join();
        return futureTask.get();

    }

}

