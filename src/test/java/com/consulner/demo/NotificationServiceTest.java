package com.consulner.demo;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class NotificationServiceTest {
    private final NotificationService notificationService = new NotificationService();

    @Test
    public void testNotification() throws IOException {
        String expected = "John Brown, Hey there !!!\n";
        //Redirect System.out to buffer
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));

        notificationService.sendGreeting("John Brown", "Hey there");

        bo.flush();
        Assert.assertEquals(expected, new String(bo.toByteArray()));
    }
}
