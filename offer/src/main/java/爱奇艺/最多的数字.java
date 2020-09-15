package 爱奇艺;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/09/13 16:09
 */
public class 最多的数字 {

    public static void main(String[] agrs){
        Scanner sc=new Scanner(System.in);
        String line=sc.nextLine();
        String[] strs=line.split(" ");
        int[] nums=new int[strs.length];
        for(int i=0;i<strs.length;i++){
            nums[i]=Integer.parseInt(strs[i]);
        }
        System.out.println(moreThanNum(nums));
    }


    private static int moreThanNum(int[] nums){
        if(nums==null){
            return 0;
        }
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if(map.containsKey(nums[i])){
                map.put(nums[i],map.get(nums[i])+1);
            }else{
                map.put(nums[i],1);
            }
        }
        int threshold=nums.length/2;
        int[] max={Integer.MIN_VALUE};
        map.forEach((k,v)->{
            int feq=v;
            if(feq>threshold&&max[0]<feq){
                max[0]=k;
            }
        });
        return max[0];
    }

}
