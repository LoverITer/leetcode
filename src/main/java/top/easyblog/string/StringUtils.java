package top.easyblog.string;

import java.util.Stack;

/**
 * @author ：huangxin
 * @since ：2020/02/15 00:30
 */
public class StringUtils {


    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }


    /**
     * 快手面试题：对字符串进行RLE压缩，将相邻的相同字符，用计数值和字符值来代替。
     * <pre>
     * 例如：aaabccccccddeee，则可用3ab6c2d3e来代替。
     * <pre/>
     *
     *
     * @return 压缩后的字符串
     */
    public String compressString(String str) {
        if (str != null) {
            char[] chars = str.toCharArray();
            int cnt = 0;
            StringBuilder sb = new StringBuilder(chars.length);
            for (int i = 0; i < chars.length; i++) {
                cnt++;
                if (i == chars.length - 1 || chars[i] != chars[i + 1]) {
                    if (cnt > 1) {
                        sb.append(cnt).append(chars[i]);
                    } else {
                        sb.append(chars[i]);
                    }
                    cnt = 0;
                }
            }
            return sb.toString();
        }
        return null;
    }

    /**
     * 反转字符串Ⅰ
     * 不要给另外的数组分配额外的空间，必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题
     *
     * @param str
     * @return
     */
    public String reverseString(String str) {
        if (str.isEmpty()) {
            return "";
        }
        char[] chars = str.toCharArray();
        int left = 0, right = chars.length-1;
        char temp;
        while (left < right) {
            temp = chars[left];
            chars[left++] = chars[right];
            chars[right--] = temp;
        }
        return new String(chars);
    }


    /**
     * 反转字符串 Ⅱ
     * 不要给另外的数组分配额外的空间，必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        String[] ss = s.trim().replaceAll("\\s+", " ").split(" ");
        Stack<String> stack = new Stack<>();
        for (String str : ss) {
            stack.push(str);
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }
        return sb.toString();
    }

    /**
     * 最长无重复字母的子串的长度
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int length;
        if (s == null || (length = s.length()) == 0) {
            return 0;
        }
        if (length == 1) {
            return 1;
        }
        //maxl 记录当前出现过的最长的子串长度
        //start 记录滑动窗口的起始下标
        int maxl = 0, start = 0;
        char[] ch = s.toCharArray();
        //end 起始就是滑动窗口的结束位置
        for (int end = 1; end < length; end++) {
            //内层循环扫描start~end,看看有没有当前子串末尾的字母在子串中已经出现过了
            for (int index = start; index < end; index++) {
                //如果子串末尾的字母在子串中已经出现过了，那就需要把窗口收缩了
                //如果没有出现过，那这次窗口扩大就是安全的
                if (ch[end] == ch[index]) {
                    maxl = Math.max(maxl, end - start);
                    start = index + 1;
                    break;
                }
            }
        }
        return Math.max(maxl, length - start);
    }



}
