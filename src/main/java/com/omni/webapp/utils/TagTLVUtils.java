package com.omni.webapp.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TagTLVUtils {
    public static boolean enteredInputValid(String data) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]*$");
        Matcher matcher = pattern.matcher(data);
        return matcher.matches();
    }
}
