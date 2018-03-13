package com.consulner.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

import static com.consulner.demo.SimpleUsersManager.GREETING_MAN;
import static com.consulner.demo.SimpleUsersManager.GREETING_WOMAN;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(Parameterized.class)
public class SimpleUsersManagerTest {

    private static final String ANY_NAME = "anyString";

    private final GenderService genderService = mock(GenderService.class);
    private final NotificationService notificationService = mock(NotificationService.class);
    private final SimpleUsersManager manager = new SimpleUsersManager(genderService, notificationService);

    private final int year, month, day;
    private final boolean isMenDay, isWomenDay;
    private final boolean isMale;


    @Parameters(name =
            "{index}: isMale={0}, yyyy.M.d={1}.{2}.{3} => isMenDay={4}, isWomenDay={5}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                // isMale, year, month, day, isMenDay, isWomenDay
                {false, 2018, 3, 8, false, true},
                {true, 2018, 11, 19, true, false},
                {false, 2018, 3, 7, false, false},
                {true, 2018, 10, 8, false, false},
                {true, 2018, 11, 18, false, false}
        });
    }


    public SimpleUsersManagerTest(boolean isMale, int year, int month, int day, boolean isMenDay, boolean isWomenDay) {
        this.isMale = isMale;
        this.year = year;
        this.month = month;
        this.day = day;
        this.isMenDay = isMenDay;
        this.isWomenDay = isWomenDay;
    }

    @Test
    public void test() {
        LocalDate date = LocalDate.of(year, month, day);
        doReturn(isMale).when(genderService).isMale(ANY_NAME);

        manager.processUsers(Arrays.asList(ANY_NAME), date);

        if (isWomenDay) {
            verify(notificationService, times(1)).sendGreeting(ANY_NAME, GREETING_WOMAN);
            verify(genderService, times(1)).isMale(ANY_NAME);
        } else if (isMenDay) {
            verify(notificationService, times(1)).sendGreeting(ANY_NAME, GREETING_MAN);
            verify(genderService, times(1)).isMale(ANY_NAME);
        } else {
            verify(notificationService, never()).sendGreeting(ANY_NAME, GREETING_WOMAN);
            verify(notificationService, never()).sendGreeting(ANY_NAME, GREETING_MAN);
            verify(genderService, times(1)).isMale(ANY_NAME);
        }
    }

}