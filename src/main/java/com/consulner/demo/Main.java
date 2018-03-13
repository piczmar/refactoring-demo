package com.consulner.demo;

import java.time.LocalDate;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        SimpleUsersManager manager = new SimpleUsersManager(new GenderService(), new NotificationService());
        manager.processUsers(Arrays.asList("John Daw", "Cristian Walker", "Elisabeth Smith", "Pedro Pereira"), LocalDate.now());
    }
}
