package com.consulner.demo;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class SimpleUsersManagerTest {

    private final SimpleUsersManager manager = new SimpleUsersManager();
    private final SimpleUsersManager managerSpy = spy(manager);

    @Before
    public void setup() {

    }

    @Test
    public void shouldSendGreetingsToWoman() {
        String user = "Marry Poppins";
        LocalDate date = LocalDate.of(2018, 3, 8);
        doReturn(false).when(managerSpy).isMale(user); // this will save us from calling real REST service

        managerSpy.processUsers(Arrays.asList(user), date);

        verify(managerSpy, times(1)).sendGreeting(user, "Happy Women's Day");
        verify(managerSpy, times(1)).isMale(user);
    }
}
