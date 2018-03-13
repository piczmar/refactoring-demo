package com.consulner.demo;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class SimpleUsersManager {

    public void processUsers(List<String> users, LocalDate date) {

        for (String user : users) {
            boolean isMale = isMale(user);

            if (isMale && date.getDayOfMonth() == 19 && date.getMonth() == Month.NOVEMBER) {
                // see International Man's Day: https://en.wikipedia.org/wiki/International_Men%27s_Day
                sendGreeting(user, "Happy Man's Day");
            } else if (!isMale && date.getDayOfMonth() == 8 && date.getMonth() == Month.MARCH) {
                // see International Women's Day: https://en.wikipedia.org/wiki/International_Women%27s_Day
                sendGreeting(user, "Happy Women's Day");
            }
        }

    }

    protected boolean isMale(String user) {
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

    protected void sendGreeting(String user, String greeting) {
        System.out.println(user + ", " + greeting + " !!!");
    }
}
