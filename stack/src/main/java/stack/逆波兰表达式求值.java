package stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/07/20 14:00
 */
public class 逆波兰表达式求值 {

    public static int evalRPN(Object[] tokens) {
        if (tokens == null || tokens.length == 0) {
            return 0;
        }

        Deque<Integer> stack = new ArrayDeque<>();
        for (Object ele : tokens) {
            //遍历tokens数组，如果当前字符是运算符就把栈顶的两个数组弹出栈并计算
            //否则如果是数字那就直接压入桟即可
            if ("+".equals(ele) || "-".equals(ele) || "*".equals(ele) || "/".equals(ele)) {
                if (stack.size() < 2) {
                    return -1;
                }
                //遇到符号就取栈顶的两个数字计算
                int v1=stack.pop();   //减数 or 除数
                int v2=stack.pop();   //被减数 or 被除数
                int result = 0;
                switch (String.valueOf(ele)) {
                    case "+":
                        result = v1 + v2;
                        break;
                    case "-":
                        result = v2 - v1;
                        break;
                    case "*":
                        result = v1 * v2;
                        break;
                    case "/":
                        result = v2 / v1;
                        break;
                }
                //把计算结果压入桟
                stack.push(result);
            } else {
                //数字压入桟中
                stack.push(Integer.parseInt(String.valueOf(ele)));
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        System.out.println(evalRPN(new String[]{"10", "8", "-"}));
        System.out.println(evalRPN(new String[]{"4", "13", "5", "/", "+"}));
    }

}
