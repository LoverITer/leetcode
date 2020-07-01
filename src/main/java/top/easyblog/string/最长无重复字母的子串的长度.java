package top.easyblog.string;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/06/30 22:41
 */
public class 最长无重复字母的子串的长度 {

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
