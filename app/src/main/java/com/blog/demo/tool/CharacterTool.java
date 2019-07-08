package com.blog.demo.tool;

import java.util.Collection;

public class CharacterTool {

    public static String join(Collection<String> list, String deli) {
        String result = "";

        for (String str : list) {
            if (result.length() == 0) {
                result = str;
            } else {
                result += deli + str;
            }
        }
        return result;
    }

}
