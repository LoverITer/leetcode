package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/07/31 20:40
 */
public class PlushOne {

    public static int[] plusOne(int[] digits) {
        if(digits==null||digits.length==0){
            return new int[]{};
        }
        //从数据的最低位开始模拟计算
        for(int i=digits.length-1;i>=0;i--){
            int flag=(digits[i]+1)%10;
            if(flag!=0){
                digits[i]=flag;
                break;
            }
            digits[i]=flag;
            if(i==0){
                digits=new int[digits.length + 1];
                digits[0]=1;
            }
        }
        return digits;
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(plusOne(new int[]{9, 9,9,9,9})));
    }

}
