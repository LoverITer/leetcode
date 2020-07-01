package top.easyblog.string;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/06/30 22:40
 */
public class 压缩字符串 {

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

}
