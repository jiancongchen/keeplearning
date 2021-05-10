package com.jiancong;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author jiancongchen on 2021/5/6
 */
public class LetterCombinationsOfaPhoneNumber {

    public static final Map<Character, String> phoneMap = new HashMap<Character, String>() {{
        put('2', "abc");
        put('3', "def");
        put('4', "ghi");
        put('5', "jkl");
        put('6', "mno");
        put('7', "pqrs");
        put('8', "tuv");
        put('9', "wxyz");
    }};

    public static List<String> letterCombinations(String digits) {
        char[] chars = digits.toCharArray();
        List<String> resultList = new LinkedList<>();
        for (char aChar : chars) {
            resultList = build(resultList, phoneMap.get(aChar).toCharArray());
        }
        return resultList;
    }

    public static List<String> build(List<String> lastResultList, char[] current){
        List<String> resultList = new LinkedList<>();
        if (lastResultList == null || lastResultList.size() == 0 ){
            for (Character character : current) {
                resultList.add(String.valueOf(character));
            }
            return resultList;
        }
        for (String temp : lastResultList) {
            for (Character character : current) {
                resultList.add(temp + character);
            }
        }
        return resultList;
    }

    public static void main(String[] args) {
        String test = "2345";
        System.out.println(letterCombinations(test));
    }

}
