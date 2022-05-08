package com.vanthuandev.applicationthemeleaf.utils;

public class ApplicationFormatRole {

    public static String hasRole(String role) {
        return String.format("hasRole('%s')", role);
    }
}
