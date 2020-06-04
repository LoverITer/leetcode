package top.easyblog;

import org.junit.Test;
import top.easyblog.array.ArrayUtils;
import top.easyblog.math.MathUtils;
import top.easyblog.search.SearchAlgorithm;
import top.easyblog.sort.InsertionSort;
import top.easyblog.sort.QuickSort;
import top.easyblog.string.StringUtils;

import java.io.File;
import java.util.Arrays;
import java.util.Random;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void testForArrayUtils(){
        ArrayUtils arrayUtils = new ArrayUtils();
        int[] nums = {0,0,1,0};
        //System.out.println(Arrays.toString(arrayUtils.nextBiggerPermutationNum(nums)));
        //System.out.println(arrayUtils.dominantIndex(nums));
        //int[][] a = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 }};
        //arrayUtils.spiralOrder(a).forEach(obj->System.out.print(obj+" "));
        //System.out.println(Arrays.toString(arrayUtils.twoSumEqualsTarget(nums, 8)));
        arrayUtils.moveZeroes(nums);
    }

    @Test
    public void shouldAnswerWithTrue() {
        int[] array = {45,3,1,7,23,44};
        /*new BubbleSort().sort(array);
        System.out.println(Arrays.toString(array));*/
        InsertionSort insertSort = new InsertionSort();
        insertSort.binarySort(array);
        System.out.println(Arrays.toString(array));

    }

    @Test
    public void testForStringUtils() {
        StringUtils stringUtils = new StringUtils();
        String str = " apple is                   nice ";
        //System.out.println(stringUtils.compressString(str));
        //System.out.println(stringUtils.isEmpty(str));
        System.out.println(stringUtils.reverseWords(str));
    }


    @Test
    public void testForSearchUtils() {
        SearchAlgorithm searchAlgorithm = new SearchAlgorithm();
        int[] nums = new int[]{3,5,1};
        System.out.println(searchAlgorithm.search(nums, 3));
    }


    @Test
    public void testMathUtils(){
        MathUtils mathUtils = new MathUtils();
        System.out.println(mathUtils.isHappyNumber(19));
    }


    @Test
    public void testForSort(){

        int[] array=new int[500_000];
        Random random = new Random();
        for(int i=0;i<500_000;i++){
            array[i]=random.nextInt(50000000);
        }
        System.out.println(Arrays.toString(array));
        long start=System.nanoTime();
        ///SelectionSort sort = new SelectionSort();
        System.out.println("starting sort....");
        QuickSort sort = new QuickSort();
        sort.sort(array);
        long end=System.nanoTime();
        System.out.println("Completed! Cost "+(end-start)+"ns");
        System.out.println(Arrays.toString(array));
    }

    int indentLevel = -1;
    String htmlPath = "";

    @Test
    public void listPath() {
        File path = new File("C:\\Tomcat_Home\\apache-tomcat-7.0.96");
        listPath(path);
    }

    public void listPath(File path) {
        File files[];
        indentLevel++;
        files = path.listFiles();
        Arrays.sort(files);
        for (int i = 0, n = files.length; i < n; i++) {
            for (int indent = 0; indent < indentLevel; indent++) {
               if(indent==indentLevel-1) {
                   System.out.print("├──");
               }else{
                   System.out.print("│");
               }

            }
            File file = files[i];
            if (file.isDirectory()) {
                System.out.println(" " + file.getAbsolutePath());
            } else {
                System.out.println(" " + file.getName());
            }
            if (files[i].isDirectory()) {
                listPath(files[i]);
            }
        }
        indentLevel--;
    }
}
