package com.trial.demo.com.trial.demo.utils;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class Server {

    public static Map<String, Object> MyGETRequest(String city) throws IOException {
        try {
            ConnectionRequest r = new ConnectionRequest();
            r.setPost(false);
            r.setUrl("https://community-open-weather-map.p.rapidapi.com/weather");
            r.addRequestHeader("x-rapidapi-host", "community-open-weather-map.p.rapidapi.com");
            r.addRequestHeader("x-rapidapi-key", );// Add key
            r.addArgument("q", city);
            NetworkManager.getInstance().addToQueueAndWait(r);
            Map<String, Object> result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(r.getResponseData()), "UTF-8"));
            System.out.println(result.toString());

            //System.out.println(result.keySet().toString());
            //System.out.println(result.values().toString());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("not connected");
            return null;
        }


    }
}
