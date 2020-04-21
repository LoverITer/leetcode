package top.easyblog;

import org.junit.Test;
import top.easyblog.array.ArrayUtils;
import top.easyblog.sort.BubbleSort;
import top.easyblog.sort.DirectInsertSort;

import java.util.Arrays;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void testForArrayUtils(){
        ArrayUtils arrayUtils = new ArrayUtils();
        int[] nums={1,5,6,3,7};
        System.out.println(Arrays.toString(arrayUtils.nextBiggerPermutationNum(nums)));
    }

    @Test
    public void shouldAnswerWithTrue() {
        int[] array = {45,3,1,7,23,44};
        /*new BubbleSort().sort(array);
        System.out.println(Arrays.toString(array));*/
        DirectInsertSort insertSort = new DirectInsertSort();
        insertSort.binarySort(array);
        System.out.println(Arrays.toString(array));

    }
}
