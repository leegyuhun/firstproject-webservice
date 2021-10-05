package com.example.firstproject.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@RestController
@RequestMapping("/api")
public class ApiController {
    @GetMapping("/APT/GetRawData")
    public String GetRawData(){
        StringBuffer result = new StringBuffer();
        try{
            StringBuilder urlBuilder = new StringBuilder("");
            urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=");
            urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=");
            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=");

            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader rd;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300){
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            }else{
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            String line;
            while((line=rd.readLine()) != null){
                result.append(line+"\n");
            }
            rd.close();
            conn.disconnect();

        } catch (Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }
}
