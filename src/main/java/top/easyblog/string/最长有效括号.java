package top.easyblog.string;

import java.util.Stack;

/**32. 最长有效括号
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 * @author ：huangxin
 * @modified ：
 * @since ：2020/06/16 00:03
 */
public class 最长有效括号 {

    public int longestValidParentheses(String s) {
        if(s==null||"".equals(s)){
            return 0;
        }
        Stack<Integer> stack=new Stack<>();
        stack.push(-1);
        int ant=0;
        for(int i=0;i<s.length();i++){
            //如果是左括号就入栈
            if(s.charAt(i)=='('){
                stack.add(i);
            }else if(s.charAt(i)==')'){
                stack.pop();
                if(stack.isEmpty()){
                    stack.push(i);
                }else{
                    ant = Math.max(ant, i - stack.peek());
                }
            }
        }
        return ant;
    }



}
