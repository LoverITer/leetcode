package lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/07/11 14:16
 */
//1.必须为女生；
//2. 姓名为4个字。
public class PredicateTest {
    public static void main(String[] args) {
        String[] array = {"迪丽热巴,女", "古力娜扎,女", "马尔扎哈,男", "赵丽颖,女"};
        List<String> list = filter(array,
                (str) -> {
                    return "女".equals(str.split(",")[1]);
                },
                (str) -> {
                    return str.split(",")[0].length() == 3;
                });
        System.out.println(list);
    }

    private static List<String> filter(String[] array, Predicate<String> one, Predicate<String> two) {
        List<String> list = new ArrayList<>();
        for (String info : array) {
            //and相当于与操作
            if (one.and(two).test(info)) {
                list.add(info);
            }
        }
        return list;
    }
}
