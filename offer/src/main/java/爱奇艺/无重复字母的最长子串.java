package 爱奇艺;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * @modified ：
 * @since ：2020/09/13 15:34
 */
public class 无重复字母的最长子串 {

    public static void main(String[] agrs){
        Scanner sc=new Scanner(System.in);
        String line=sc.nextLine();
        System.out.println(lengthOfLongestSubString(line));
    }


    private static int lengthOfLongestSubString(String s){
        int ans=0;
        //存储字符和其下标的映射关系，当一个字符已经出现过了那就及时切换start指针的位置
        Map<Character,Integer> map=new HashMap<>();
        for(int end=0,start=0,len=s.length();end<len;end++){
            char ch=s.charAt(end);
            if(map.containsKey(ch)){
                start=Math.max(map.get(ch),start);
            }
            ans=Math.max(ans,end-start+1);
            map.put(ch,end+1);
        }
        return ans;
    }

}
