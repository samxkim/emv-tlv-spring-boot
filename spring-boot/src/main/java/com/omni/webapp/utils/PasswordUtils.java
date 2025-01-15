package com.omni.webapp.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordUtils {
    public static boolean passwordValidLength(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
