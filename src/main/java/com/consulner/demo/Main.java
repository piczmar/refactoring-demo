package com.consulner.demo;

import java.util.Arrays;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        SimpleUsersManager manager = new SimpleUsersManager();
        manager.processUsers(Arrays.asList("John Daw", "Cristian Walker", "Elisabeth Smith", "Pedro Pereira"), new Date());
    }
}
