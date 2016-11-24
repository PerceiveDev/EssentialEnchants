package com.perceivedev.hydrusenchants.util;

import org.bukkit.ChatColor;

/**
 * @author PhantomUnicorns
 */
public class TextUtils {

    public static String hideText(String input) {
        if (input == null || input.length() < 1) {
            return "";
        }
        StringBuilder output = new StringBuilder();
        for (char c : input.toCharArray()) {
            output.append(ChatColor.COLOR_CHAR).append(c);
        }
        return output.toString();
    }

    public static String unhideText(String input) {
        return input.replace("" + ChatColor.COLOR_CHAR, "");
    }

    // @formatter:off
    public static int unnumeral(String number) {
        if (number.startsWith("M")) return 1000 + unnumeral(number.replaceFirst("M", ""));
        if (number.startsWith("CM")) return 900 + unnumeral(number.replaceFirst("CM", ""));
        if (number.startsWith("D")) return 500 + unnumeral(number.replaceFirst("D", ""));
        if (number.startsWith("CD")) return 400 + unnumeral(number.replaceFirst("CD", ""));
        if (number.startsWith("C")) return 100 + unnumeral(number.replaceFirst("C", ""));
        if (number.startsWith("XC")) return 90 + unnumeral(number.replaceFirst("XC", ""));
        if (number.startsWith("L")) return 50 + unnumeral(number.replaceFirst("L", ""));
        if (number.startsWith("XL")) return 40 + unnumeral(number.replaceFirst("XL", ""));
        if (number.startsWith("X")) return 10 + unnumeral(number.replaceFirst("X", ""));
        if (number.startsWith("IX")) return 9 + unnumeral(number.replaceFirst("IX", ""));
        if (number.startsWith("V")) return 5 + unnumeral(number.replaceFirst("V", ""));
        if (number.startsWith("IV")) return 4 + unnumeral(number.replaceFirst("IV", ""));
        if (number.startsWith("I")) return 1 + unnumeral(number.replaceFirst("I", ""));
        return 0;
    }

    public static String numeral(int number) {
        if (number<=0) return "";
        if (number-1000>=0) return "M" + numeral(number-1000);
        if (number-900>=0) return "CM" + numeral(number-900);
        if (number-500>=0) return "D" + numeral(number-500);
        if (number-400>=0) return "CD" + numeral(number-400);
        if (number-100>=0) return "C" + numeral(number-100);
        if (number-90>=0) return "XC" + numeral(number-90);
        if (number-50>=0) return "L" + numeral(number-50);
        if (number-40>=0) return "XL" + numeral(number-40);
        if (number-10>=0) return "X" + numeral(number-10);
        if (number-9>=0) return "IX" + numeral(number-9);
        if (number-5>=0) return "V" + numeral(number-5);
        if (number-4>=0) return "IV" + numeral(number-4);
        if (number-1>=0) return "I" + numeral(number-1);
        return null;
    }

}
