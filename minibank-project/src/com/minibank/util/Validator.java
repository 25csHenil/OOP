package com.minibank.util;
import java.util.regex.Pattern;
public class Validator {
    private static final Pattern MOBILE_PAT = Pattern.compile("^[6-9]\\d{9}$");
    private static final Pattern EMAIL_PAT = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    private static final Pattern PAN_PAT = Pattern.compile("^[A-Z]{5}[0-9]{4}[A-Z]$");
    private static final Pattern IFSC_PAT = Pattern.compile("^[A-Z]{4}0[A-Z0-9]{6}$");

    public static boolean isValidMobile(String s) { return s != null && MOBILE_PAT.matcher(s).matches(); }
    public static boolean isValidEmail(String s) { return s != null && EMAIL_PAT.matcher(s).matches(); }
    public static boolean isValidPan(String s) { return s != null && PAN_PAT.matcher(s).matches(); }
    public static boolean isValidIfsc(String s) { return s != null && IFSC_PAT.matcher(s).matches(); }
    public static boolean isValidAmount(String s) { return s != null && s.matches("^[1-9]\\d*$"); }
}
