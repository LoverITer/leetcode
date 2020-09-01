package math;

/**
 * <h4>LeetCode 67. 二进制求和</h4>
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 *
 * 输入为 非空 字符串且只包含数字 1 和 0。
 * 示例 :
 * <pre>
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 *
 * </pre>
 * @author ：huangxin
 * @modified ：
 * @since ：2020/06/08 11:49
 */
public class 二进制求和 {

    public String addBinary(String a, String b) {
        if (isEmpty(a)) {
            return b;
        }
        if (isEmpty(b)) {
            return a;
        }
        StringBuilder answer = new StringBuilder();
        int carry = 0;
        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {
            int sum = carry;
            sum += i >= 0 ? a.charAt(i) - '0' : 0;
            sum += j >= 0 ? b.charAt(j) - '0' : 0;
            answer.append(sum % 2);
            //进位位
            carry = sum / 2;
        }
        //最高位溢出检测
        answer.append(carry == 1 ? carry : "");
        //反转输出结果
        return answer.reverse().toString();
    }


    private boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }


}
