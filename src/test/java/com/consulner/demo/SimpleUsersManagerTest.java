package com.consulner.demo;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SimpleUsersManagerTest {

    private final SimpleUsersManager manager = new SimpleUsersManager();
    private final SimpleUsersManager managerSpy = spy(manager);

    @Before
    public void setup() {

    }

    @Test
    public void shouldSendGreetingsToWoman() throws ParseException {
        String user = "Marry Poppins";
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2018-03-08");
        doReturn(false).when(managerSpy).isMale(user); // this will save us from calling real REST service

        managerSpy.processUsers(Arrays.asList(user), date);

        verify(managerSpy).sendGreeting(user, "Happy Women's Day");

    }
}
