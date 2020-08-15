package _20200731;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 第二题是给一个排序的过程让你实现个排序过程一样的排序算法
 * 排序是快速排序
 * @author ：huangxin
 * @modified ：
 * @since ：2020/07/31 14:52
 */
public class Exam2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        int[] array = new int[len];
        int i = 0;
        while (i < len) {
            array[i++] = sc.nextInt();
        }
        sort(array, 0, array.length - 1);
    }


    /**
     * 第二题考得是快速排序
     *
     * @param array 排序数组
     * @param low   开始排序的位置
     * @param high  结束排序的位置
     */
    public static void sort(int[] array, int low, int high) {
        if (low < high) {
            int mid = partition(array, low, high);
            sort(array, low, mid - 1);
            sort(array, mid + 1, high);
        }
    }

    /**
     * 双边循环法
     *
     * @param array 排序数组
     * @param low   开始排序的位置
     * @param high  结束排序的位置
     * @return
     */
    private static int partition(int[] array, int low, int high) {
        int pivot = array[low];
        while (low < high) {
            while(low<high&&array[high]>pivot){
                high--;
            }
            array[low]=array[high];
            while(low<high&&array[low]<=pivot){
                low++;
            }
            array[high]=array[low];
        }
        //此时 low = high
        array[low]=pivot;
        String output= Arrays.toString(array);
        System.out.println(output.substring(1,output.length()-1).replaceAll(","," "));
        return low;
    }

}
