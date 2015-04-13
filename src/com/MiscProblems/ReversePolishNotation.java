package com.MiscProblems;

import java.util.Stack;

/**
 * Created by VIRAL on 4/13/2015.
 */
public class ReversePolishNotation {

    public static int evalRPN(String[] tokens){
        int answer = 0;
        String operators = "+-*/";
        Stack<String> stack = new Stack<>();
        for(String t : tokens){
            if(!operators.contains(t)){
                stack.push(t);
            }else{
                int a = Integer.valueOf(stack.pop());
                int b = Integer.valueOf(stack.pop());
                int index = operators.indexOf(t);
                switch(index){
                    case 0:
                        stack.push(String.valueOf(a+b));
                        break;
                    case 1:
                        stack.push(String.valueOf(b-a));
                        break;
                    case 2:
                        stack.push(String.valueOf(a*b));
                        break;
                    case 3:
                        stack.push(String.valueOf(b/a));
                        break;
                }
            }
        }
        answer = Integer.valueOf(stack.pop());
        return answer;
    }

    public static void main(String[] args) {
        String[] tokens = new String[] { "2", "1", "+", "3", "*" };
        System.out.println(evalRPN(tokens));
    }
}
