package top.easyblog.array;

import java.sql.Connection;
import java.util.*;

/**1481. 不同整数的最少数目
 * 给你一个整数数组 arr 和一个整数 k 。现需要从数组中恰好移除 k 个元素，请找出移除后数组中不同整数的最少数目。
 *
 *  
 *
 * 示例 1：
 *<pre>
 * 输入：arr = [5,5,4], k = 1
 * 输出：1
 * 解释：移除 1 个 4 ，数组中只剩下 5 一种整数。
 * </pre>
 * @author ：huangxin
 * @modified ：
 * @since ：2020/06/16 11:34
 */
public class 不同整数的最少数目 {


    public static int findLeastNumOfUniqueInts(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        HashMap<String, Integer> map = new HashMap<>(arr.length);
        List<Integer> list=new ArrayList<>();
        for (int value : arr) {
            String key = String.valueOf(value);
            if (map.containsKey(key)) {
                map.put(key, map.get(key) + 1);
            } else {
                map.put(key, 1);
            }
        }

        map.forEach((key,val)->{
            list.add(val);
        });

        Collections.sort(list);

        Iterator<Integer> it = list.iterator();
        while(it.hasNext()&&k!=0){
            int val=it.next();
            if(val==k){
                it.remove();
                break;
            }else if(val>k){
                break;
            }else {
                k-=val;
                it.remove();
            }
        }

        return list.size();
    }

    public static void main(String[] args) {
        System.out.println(findLeastNumOfUniqueInts(new int[]{5, 5, 4,6,6,2,1,1,1,1}, 3));
    }

}
