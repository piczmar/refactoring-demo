package com.consulner.demo;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static com.consulner.demo.SimpleUsersManager.GREETING_MAN;
import static com.consulner.demo.SimpleUsersManager.GREETING_WOMAN;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class SimpleUsersManagerTest {

    private final SimpleUsersManager manager = new SimpleUsersManager();
    private final SimpleUsersManager managerSpy = spy(manager);
    private static final String ANY_NAME = "anyString";

    @Before
    public void setup() {

    }

    @Test
    public void shouldSendGreetingsToWoman() {
        LocalDate date = LocalDate.of(2018, 3, 8);
        doReturn(false).when(managerSpy).isMale(ANY_NAME);

        managerSpy.processUsers(Arrays.asList(ANY_NAME), date);

        verify(managerSpy, times(1)).sendGreeting(ANY_NAME, GREETING_WOMAN);
        verify(managerSpy, times(1)).isMale(ANY_NAME);

    }

    @Test
    public void shouldNotSendGreetingsToWoman() {
        LocalDate date = LocalDate.of(2018, 3, 9);
        doReturn(false).when(managerSpy).isMale(ANY_NAME);

        managerSpy.processUsers(Arrays.asList(ANY_NAME), date);

        verify(managerSpy, never()).sendGreeting(ANY_NAME, GREETING_WOMAN);
        verify(managerSpy, times(1)).isMale(ANY_NAME);
    }

    @Test
    public void shouldSendGreetingsToMan() {
        LocalDate date = LocalDate.of(2018, 11, 19);
        doReturn(true).when(managerSpy).isMale(ANY_NAME);

        managerSpy.processUsers(Arrays.asList(ANY_NAME), date);

        verify(managerSpy, times(1)).sendGreeting(ANY_NAME, GREETING_MAN);
        verify(managerSpy, times(1)).isMale(ANY_NAME);
    }

    @Test
    public void shouldNotSendGreetingsToMan() {
        LocalDate date = LocalDate.of(2018, 3, 8);
        doReturn(true).when(managerSpy).isMale(ANY_NAME);

        managerSpy.processUsers(Arrays.asList(ANY_NAME), date);

        verify(managerSpy, never()).sendGreeting(ANY_NAME, GREETING_MAN);
        verify(managerSpy, times(1)).isMale(ANY_NAME);
    }
}
