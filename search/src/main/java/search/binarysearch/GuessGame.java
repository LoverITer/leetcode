package search.binarysearch;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/08/07 16:56
 */
public class GuessGame {


    public static int guessNumber(int n, int pick) {
        int low = 1, high = n;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (mid == pick) {
                return mid;
            } else if (mid < pick) {
                low = mid;
            } else {
                high = mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(guessNumber(10, 7));
    }


}
