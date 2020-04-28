package top.easyblog.string;

import java.util.Arrays;

/**
 * @author ：huangxin
 * @since ：2020/02/15 00:30
 */
public class StringUtils {


    public boolean isEmpty(String str) {
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
     * 其作用是将输入的字符串反转过来。
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




}
