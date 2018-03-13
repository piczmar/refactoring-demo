package com.consulner.demo;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class GenderService {

    public boolean isMale(String user) {
        try {
            /**
             * Expected JSON like:
             * {"name": "Sam","gender": "male","probability": 0.76,"count": 3336}
             */
            HttpResponse<JsonNode> json = Unirest.get("https://api.genderize.io/?name=" + user.split("\\s")[0])
                    .asJson();
            System.out.println(json.getBody());
            double probability = json.getBody().getObject().getDouble("probability");
            return probability == 1.0;
        }
        catch (UnirestException e) {
            // if failed do nothing.
            e.printStackTrace();
        }
        return true;
    }

}
