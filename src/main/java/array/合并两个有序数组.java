package array;

/**
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 *
 *  
 *
 * 说明:
 * <pre>
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 * </pre>

 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/06/14 23:47
 */
public class 合并两个有序数组 {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] nums3=new int[m];
        System.arraycopy(nums1,0,nums3,0,m);

        int p1=0;
        int p2=0;
        int p=0;

        //归并排序：归并
        while((p1<m)&&(p2<n)){
            nums1[p++]=(nums3[p1]<nums2[p2])?nums3[p1++]:nums2[p2++];
        }

        //还有数组没遍历完
        if(p1<m){
            System.arraycopy(nums3,p1,nums1,p1+p2,m + n - p1 - p2);
        }
        if(p2<n){
            System.arraycopy(nums2,p2,nums1,p1+p2,m + n - p1 - p2);
        }
    }


}
