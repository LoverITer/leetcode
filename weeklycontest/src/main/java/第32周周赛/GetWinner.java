package 第32周周赛;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 给你一个由 不同 整数组成的整数数组 arr 和一个整数 k 。
 *
 * 每回合游戏都在数组的前两个元素（即 arr[0] 和 arr[1] ）之间进行。比较 arr[0] 与 arr[1] 的大小，较大的整数将会取得这一回合的胜利并保留在位置 0 ，较小的整数移至数组的末尾。当一个整数赢得 k 个连续回合时，游戏结束，该整数就是比赛的 赢家 。
 *
 * 返回赢得比赛的整数。
 *
 * 题目数据 保证 游戏存在赢家。
 *
 * <pre>
 * 输入：arr = [1,9,8,2,3,7,6,4,5], k = 7
 * 输出：9
 *
 * 输入：arr = [1,11,22,33,44,55,66,77,88,99], k = 1000000000
 * 输出：99
 * </pre>
 * @author ：huangxin
 * @modified ：
 * @since ：2020/08/02 22:32
 */
public class GetWinner {

    public int getWinner(int[] arr, int k) {
        int len;
        if(arr==null||(len=arr.length)==0){
            return 0;
        }
        //k大于数组，那么返回的数一定就是数组中最大的数
        if(k>len){
            int max=arr[0];
            for(int num:arr){
                max=Math.max(max,num);
            }
            return max;
        }
        //k<len，使用队列来模拟这个过程
        Deque<Integer> queue=new ArrayDeque<>();
        int winner=Math.max(arr[0],arr[1]);
        for(int num:arr){
            if(num!=winner){
                queue.offer(num);
            }
        }
        int count=0;
        while(count<k){
            int first=queue.poll();
            if(winner>first){
                count++;
                queue.offer(first);
            }else{
                count=1;
                queue.offer(winner);
                winner=first;
            }
        }
        return winner;
    }


    public static void main(String[] args) {

    }

}
