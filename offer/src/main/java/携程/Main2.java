package 携程;

import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/09/08 20:04
 */
public class Main2 {

    /**
     * 输入,
     * <p>
     * a bc df gh
     * <p>
     * 输出如下， 结果输出根据单个字符的下标index排序:  Comparator.naturalOrder().
     * <p>
     * abdg
     * <p>
     * abdh
     * <p>
     * abfg
     * <p>
     * abfh
     * <p>
     * acdg
     * <p>
     * acdh
     * <p>
     * acfg
     * <p>
     * acfh
     *
     * @param args
     */
    public static void main(String[] args) {
       /* Scanner sc = new Scanner(System.in);
        String line=sc.nextLine();
        String[] strings = line.split(" ");
        permutation(strings);*/
        ArrayList<Integer> list = IntStream.of(23, 45, 15).boxed().
                collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        list.forEach(e->{
            System.out.print(e+" ");
        });
    }

    private static void permutation(String[] strings) {
        int len=strings.length;
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<len;i++){
            String string = strings[i];
            for(int j=0;j<string.length();j++){

            }
        }
    }


}
