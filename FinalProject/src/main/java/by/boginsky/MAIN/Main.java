package by.boginsky.MAIN;

import by.boginsky.util.PasswordEncryption;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        System.out.println(PasswordEncryption.encryptsPassword("asd"));
        Optional<String> a = Optional.of("2222");
        if(a.isPresent()){
            System.out.println(a.get());
        }
    }
}
