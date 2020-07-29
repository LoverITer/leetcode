package string;

import java.util.*;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/06/17 22:33
 */
public class 字母异位词分组 {

    public static List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String,List<String>> map=new HashMap<>();
        if(strs==null||strs.length==0){
            return new ArrayList(map.values());
        }
        for(String str:strs){
            char[] ch=str.toCharArray();
            Arrays.sort(ch);
            //拆分，排序之后化不统一为统一，妙~~！
            String key=String.valueOf(ch);
            if(!map.containsKey(key)){
                List<String> list=new ArrayList<>();
                map.put(key,list);
            }
            map.get(key).add(str);
        }
        return new ArrayList(map.values());
    }


    public static void main(String[] args) {
        /*groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}).forEach(obj->{
            System.out.println(obj);
        });*/

    }

}
