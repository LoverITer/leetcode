package top.easyblog.stack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
 * <p>
 * 将输入的额一般表达值首先转化为逆波兰表达式，然后对逆波兰表达式进行求值即可
 * </p>
 *
 * @author ：huangxin
 * @modified ：
 * @see 逆波兰表达式求值
 * @since ：2020/07/20 23:27
 */
public class 一般表达式转逆波兰表达式并求值 {

    /**
     * 计算一个普通表达式的值：
     * 先将普通表达式转化为逆波兰表达式，然后再遍历逆波兰表达式数组进行计算
     *
     * @param s 中缀表达式
     * @return 表达式的最终结果
     */
    public static int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Object[] tokens = convert2RPN(s.replaceAll(" ", "")).toArray();
        return 逆波兰表达式求值.evalRPN(tokens);
    }

    /**
     * 将中缀表达式转化为后缀表达式（逆波兰表达式）
     *
     * 使用一个桟来保存比那里过程中遇到的操作符，然后遍历表达式字符串s,
     * <pre>
     * 1、遇到数字就直接将数字保存到token中；
     * 2、遇到运算符首先执行出栈逻辑，只要当前栈顶不是"("就把操作符出栈到token中，然后将操作符入栈
     * 3、遇到“）”直接执行出栈逻辑，只要当前栈顶不是"("就把操作符出栈到token中，之后将"("也出栈
     * </pre>
     * @param s 计算表达式
     * @return 逆波兰表达式字符数组
     */
    public static List<String> convert2RPN(String s) {
        char[] exp = s.toCharArray();
        List<String> token = new ArrayList<>();
        if (exp.length == 0) {
            return token;
        }
        if (exp.length == 1) {
            token.add(String.valueOf(exp[0]));
            return token;
        }
        //桟：存储符号
        Deque<String> stack = new ArrayDeque<>();
        for (int i = 0; i < exp.length; i++) {
            String ele = String.valueOf(exp[i]);
            int num = 0;
            //解决数字分隔之后重组的问题
            while (i < exp.length && exp[i] >= '0' && exp[i] <= '9') {
                num = num * 10 + exp[i++] - '0';
                ele = String.valueOf(num);
            }
            if (ele.matches("[-]?\\d+")) {
                //数字，直接添加到逆波兰表达式中
                token.add(ele);
                i--;
            } else if (stack.isEmpty()) {
                stack.push(ele);
            } else if ("+".equals(ele) || "-".equals(ele)) {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    token.add(stack.pop());
                }
                stack.push(ele);
            } else if ("*".equals(ele) || "/".equals(ele)) {
                while (!stack.isEmpty() && (!stack.peek().equals("*") ||
                        !stack.peek().equals("/"))) {
                    token.add(stack.pop());
                }
                stack.push(ele);
            } else if ("(".equals(ele)) {
                stack.push(ele);
            } else if (")".equals(ele)) {
                //遇到")"就出栈"("以及其之上的所有符号到逆波兰表达式中
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    token.add(stack.pop());
                }
                stack.pop();
            }
        }
        while (!stack.isEmpty()) {
            token.add(stack.pop());
        }
        return token;
    }

    public static void main(String[] args) {
        System.out.println(calculate("(1+(4+5+2)-3)*(6+8)"));
    }

}
