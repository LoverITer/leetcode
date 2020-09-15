package 携程;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/09/08 13:56
 */
public class Test {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] strings = line.split(" ");
            double[] arr = new double[Integer.parseInt(strings[1])];
            arr[0] = Integer.parseInt(strings[0]);
            list.clear();
            double sum = arr[0];
            for (int i = 1; i < arr.length; i++) {
                arr[i] = Math.sqrt(arr[i - 1]);
                sum += arr[i];
            }
            System.out.println(String.format("%.2f",sum));
        }
    }
}


