package com.modulopgave1.util;

public class Integer {
    public static boolean isInteger(String s) {
        return isInteger(s,0, 10);
    }

    public static boolean isInteger(String s, int offset, int radix) {
        if(s.isEmpty()) return false;
        for(int i = offset; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(Character.digit(s.charAt(i),radix) < 0) return false;
        }
        return true;
    }
}
