package com.consulner.demo;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class SimpleUsersManager {

    protected static final String GREETING_MAN = "Happy Man's Day";
    protected static final String GREETING_WOMAN = "Happy Women's Day";
    private final GenderService genderService;
    private final NotificationService notificationService;

    public SimpleUsersManager(GenderService genderService, NotificationService notificationService) {
        this.genderService = genderService;
        this.notificationService = notificationService;
    }
    public void processUsers(List<String> users, LocalDate date) {

        for (String user : users) {
            boolean isMale = genderService.isMale(user);

            if (isMale && isMenDay(date)) {
                notificationService.sendGreeting(user, GREETING_MAN);
            } else if (!isMale && isWomenDay(date)) {
                notificationService.sendGreeting(user, GREETING_WOMAN);
            }
        }

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
