package top.easyblog.search;

import java.util.Objects;

/**
 * 查找算法
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/02/22 01:07
 */
public class SearchAlgorithm {

    /**
     * 二分查找递归版本
     *
     * @param nums   目标数组
     * @param head   查找的起始位置
     * @param tail   查找的末尾
     * @param target 关键字
     * @return 关键字在数组中的索引，如果没找到返回-1
     */
    public int binarySearch(int[] nums, int head, int tail, int target) {
       if(Objects.nonNull(nums)&&nums.length>0){
           int mid=(head+tail)>>>1;
           if(nums[mid]==target){
               return mid;
           }else if(nums[mid]>target){
               return binarySearch(nums,head,mid+1,target);
           }else{
               return binarySearch(nums,mid-1,tail,target);
           }
       }
       return -1;
    }


    public static void main(String[] args) {
        int[] nums=new int[]{1,2,3,4,5,9,67};
        System.out.println(new SearchAlgorithm().binarySearch(nums,0,nums.length-1,5));
    }

}
