package com.insper.iotserver.service.security.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Scanner;

public class PasswordGenerator {

    public static void main (String ... args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("Type de pass to encode:");
        Scanner scanner = new Scanner(System.in);
        System.out.println(encoder.encode(scanner.next()));
    }

}
