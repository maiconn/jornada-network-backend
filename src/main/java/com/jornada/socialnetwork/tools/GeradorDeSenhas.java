package com.jornada.socialnetwork.tools;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeradorDeSenhas {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode("GymForge@2024");
        System.out.println(encode);

//        String senha = "$2a$10$tFPtm97bNGCK4MzepKtFB.L3.GPwaC0TNhUhdhu1wGjXfXHhhr8UC";
//        boolean senhaCorreta = encoder.matches("123", senha);
//        System.out.println(senhaCorreta);
    }
}
