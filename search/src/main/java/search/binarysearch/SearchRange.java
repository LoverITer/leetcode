package search.binarysearch;

import java.util.Arrays;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/08/08 09:56
 */
public class SearchRange {


    /**
     * 使用二差查找先找到关键字的一个位置flag=mid，然后再以flag为中心向前、向后搜索
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int[] ans=new int[]{-1,-1};
        if(nums==null||nums.length==0){
            return ans;
        }
        int low=0,flag=-1,high=nums.length-1;
        while(low<=high){
            int mid=low+((high-low)>>1);
            if(nums[mid]==target){
                flag=mid;
                break;
            }else if(nums[mid]<target){
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
        if(flag==-1){
            return ans;
        }
        ans[0]=flag;ans[1]=flag;
        int i=flag;
        while(--i>=0&&nums[i]==target){
            ans[0]=i;
        }
        i=flag;
        while(++i<nums.length&&nums[++i]==target){
            ans[1]=i;
        }
        return ans;
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(new SearchRange().searchRange(new int[]{5,7,7,8,8,10}, 8)));
    }

}
