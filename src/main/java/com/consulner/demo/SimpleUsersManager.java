package com.consulner.demo;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class SimpleUsersManager {

    protected static final String GREETING_MAN = "Happy Man's Day";
    protected static final String GREETING_WOMAN = "Happy Women's Day";

    public void processUsers(List<String> users, LocalDate date) {

        for (String user : users) {
            boolean isMale = isMale(user);

            if (isMale && isMenDay(date)) {
                sendGreeting(user, GREETING_MAN);
            } else if (!isMale && isWomenDay(date)) {
                sendGreeting(user, GREETING_WOMAN);
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

    // see International Women's Day: https://en.wikipedia.org/wiki/International_Women%27s_Day
    private boolean isWomenDay(LocalDate date) {
        return date.getDayOfMonth() == 8 && date.getMonth() == Month.MARCH;
    }

    // see International Man's Day: https://en.wikipedia.org/wiki/International_Men%27s_Day
    private boolean isMenDay(LocalDate date) {
        return date.getDayOfMonth() == 19 && date.getMonth() == Month.NOVEMBER;
    }
}
