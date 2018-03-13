package com.consulner.demo;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;

public class SimpleUsersManagerTest {

    private final SimpleUsersManager manager = new SimpleUsersManager();
    private final SimpleUsersManager managerSpy = Mockito.spy(manager);

    @Before
    public void setup() {

    }

    // Initial try to test it.
    @Test
    public void shouldSendGreetingsToWomen() {
        String user = "Marry Poppins";

        managerSpy.processUsers(Arrays.asList(user));

        // I'm not a fan of spying, so we will extract the greeting method to a separate service later on..
        Mockito.verify(managerSpy).sendGreeting(user, "Happy Women's Day");

    }
}
