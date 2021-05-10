package com.jiancong;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 有效的括号
 * https://leetcode-cn.com/problems/valid-parentheses/
 * @author jiancongchen on 2021/5/10
 */
public class ValidParentheses {

    private static final Map<Character, Character> parenthesesMap = new HashMap<>();

    static {
        parenthesesMap.put('(', ')');
        parenthesesMap.put('{', '}');
        parenthesesMap.put('[', ']');
    }

    public static boolean isValid(String s) {
        if (s.length() % 2 == 1){
            return false;
        }
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char aChar : chars) {
            if (parenthesesMap.keySet().contains(aChar)){
                stack.add(aChar);
            } else {
                if (stack.isEmpty()){
                    return false;
                }
                Character pop = stack.pop();
                if(pop == null){
                    return false;
                }
                if (!parenthesesMap.get(pop).equals(aChar)){
                    return false;
                }
            }
        }
        if (stack.isEmpty()){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isValid("){"));
    }
}
