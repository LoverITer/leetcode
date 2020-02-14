package top.easyblog;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import top.easyblog.array.ArrayUtils;

import java.util.Arrays;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void shouldAnswerWithTrue() {
        System.out.println(Arrays.toString(ArrayUtils.getInstance().removeDuplicates(new int[]{0,0,1,1,1,1,2,3,4,5,5})));
        System.out.println(Arrays.toString(ArrayUtils.getInstance().removeElement(new int[]{0,1,2,2,3,0,4,2},2)));



    }
}
