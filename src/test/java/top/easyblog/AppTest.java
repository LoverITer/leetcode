package top.easyblog;

import org.junit.Test;
import top.easyblog.sort.BubbleSort;

import java.util.Arrays;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void shouldAnswerWithTrue() {
        int[] array = {1, 6, 2, 8, 9, 3, 3, 4,7,8,3,78};
        new BubbleSort().sort(array);
        System.out.println(Arrays.toString(array));

    }
}
