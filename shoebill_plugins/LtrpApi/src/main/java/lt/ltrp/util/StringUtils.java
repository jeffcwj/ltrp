package lt.ltrp.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Bebras
 *         2016.02.22.
 */
public class StringUtils {

    private static final Map<Character, Character> ltToLatinChar = new HashMap<>(9);
    private static final String regex = "[�����������������]";
    private static final Map<String, String> colorsToRGB = new HashMap<>();

    static {
        ltToLatinChar.put('�', 'a');
        ltToLatinChar.put('�', 'c');
        ltToLatinChar.put('�', 'e');
        ltToLatinChar.put('�', 'e');
        ltToLatinChar.put('�', 'i');
        ltToLatinChar.put('�', 's');
        ltToLatinChar.put('�', 'u');
        ltToLatinChar.put('�', 'z');

        ltToLatinChar.put('�', 'A');
        ltToLatinChar.put('�', 'C');
        ltToLatinChar.put('�', 'E');
        ltToLatinChar.put('�', 'E');
        ltToLatinChar.put('�', 'I');
        ltToLatinChar.put('�', 'S');
        ltToLatinChar.put('�', 'U');
        ltToLatinChar.put('�', 'Z');

        colorsToRGB.put("{RAUDONA", "{FF0000}");
        colorsToRGB.put("{ZALIA", "{00FF00}");
        colorsToRGB.put("[GELTONA", "{FFFF00}");
        colorsToRGB.put("{MELYNA", "{0000FF}");
        colorsToRGB.put("{ZYDRA}", "{00FFFF}");
        colorsToRGB.put("{VIOLETINE}", "{FF00FF}");
    }



    public static boolean equalsIgnoreLtChars(String s1, String s2) {
        String first = replaceLtChars(s1);
        String second = replaceLtChars(s2);
        return first.equals(second);
    }

    public static boolean equalsIgnoreLtCharsAndCase(String s1, String s2) {
        String first = replaceLtChars(s1.toLowerCase());
        String second = replaceLtChars(s2.toLowerCase());
        return first.equals(second);
    }

    public static String replaceLtChars(String s) {
        String retS = "";
        for(Character character : ltToLatinChar.keySet()) {
            retS = s.replace(character, ltToLatinChar.get(character));
        }
        return retS;
    }

    public static String stripLtChars(String s) {
        return s.replaceAll(regex, "");
    }

    public static boolean isNumeric(String s) {
        for(char c : s.toCharArray()) {
            if(!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Parses colour codes like {RAUDONA} or {MELYNA}
     * @param text text to be parsed
     * @return returns the formatted text
     */
    public static String parseTextColors(String text) {
        for(Iterator<String> it = colorsToRGB.keySet().iterator();  it.hasNext();) {
            String key = it.next();
            text = text.replaceAll(key, colorsToRGB.get(key));
        }
        return text;
    }

    /**
     * Escapes text embedding colors in format {RRGGBB}
     * @param text
     * @return
     */
    public static String escapeColors(String text) {
        return text.replaceAll("\\{", "\\\\\\\\{");
    }

}