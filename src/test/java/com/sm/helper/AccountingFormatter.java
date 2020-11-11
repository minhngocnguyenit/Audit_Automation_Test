package com.sm.helper;

import java.util.Locale;

public class AccountingFormatter {

    public static String format(double d, Locale locale) {
        String rs =  String.format(locale, "%,.2f", d);
        if(d < 0) {
            rs = rs.replace("-", "").trim();
            rs = "(" + rs + ")";
        }
        return rs;
    }

    public double parse(String str, Locale locale) {
        return 0;
    }
}
