package org.autozone;

import com.github.javafaker.Faker;

import java.util.Locale;

public class App {

    public static void main(String[] args) {
        Faker faker = new Faker(new Locale("en-US"));
        System.out.println(faker.name().firstName());
        System.out.println(faker.name().lastName());
        System.out.println(faker.number().digits(10));
        System.out.println(faker.name().firstName() + "@mail.com");
        System.out.println(faker.address().streetAddress());
        System.out.println(faker.address().city());
        System.out.println(faker.address().state());
        System.out.println(faker.address().zipCode());
    }
}
