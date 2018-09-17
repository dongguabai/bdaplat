package com.zj.bda.common.datastructure;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Stack;

/**
 * 括号匹配
 *
 * @author Dongguabai
 * @date 2018/9/12 17:26
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ParenthesisMatchingHelper {

    public static boolean mather(String str) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                Character pop = stack.pop();
                if (c == ')' && pop != '(') {
                    return false;
                }
                if (c == ']' && pop != '[') {
                    return false;
                }
                if (c == '}' && pop != '{') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}