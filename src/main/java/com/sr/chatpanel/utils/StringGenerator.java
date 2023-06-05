package com.sr.chatpanel.utils;

public class StringGenerator {
    private static final String AlphaString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "abcdefghijklmnopqrstuvxyz";

    public static String getRandomAlphaString(int size) {
        StringBuilder sb = new StringBuilder(size);

        for (int i = 0; i < size; i++) {
            int index = (int) (AlphaString.length() * Math.random());
            sb.append(AlphaString.charAt(index));
        }

        return sb.toString();
    }
}
