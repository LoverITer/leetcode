package top.easyblog.string;

/**
 * @author ：huangxin
 * @modified By：
 * @Description：TODO
 * @since ：2020/02/15 00:30
 */
public class StringUtils {


    /**
     * 对字符串进行RLE压缩，将相邻的相同字符，用计数值和字符值来代替。
     * 例如：aaabccccccddeee，则可用3a1b6c2d3e来代替。
     *
     * @return 压缩后的字符串
     */
    public static String zipStrByREL(String str) {
        if (str != null) {
            char[] chars = str.toCharArray();
            int cnt = 0;
            StringBuffer sb = new StringBuffer(chars.length);
            for (int i = 0; i < chars.length; i++) {
                cnt++;
                if (i == chars.length - 1 || chars[i] != chars[i + 1]) {
                    sb.append(cnt + "" + chars[i]);
                    cnt = 0;
                }
            }
            return sb.toString();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(zipStrByREL("aaabccccccddeee"));
    }

}
