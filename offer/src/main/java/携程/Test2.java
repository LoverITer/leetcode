package 携程;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/09/08 14:25
 */
public class Test2 {

    public static void main(String[] agrs){
        /*Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            String  line=sc.nextLine();
            String[] splits=line.split(" ");
            int start=Integer.parseInt(splits[0]);
            int end=Integer.parseInt(splits[1]);
            List<Integer> list = new ArrayList<>();
            for(int i=start;i<=end;i++){
                if(jduge(i)){
                    list.add(i);
                }
            }
            if(list.size()==0){
                System.out.println("no");
            }else{
                list.forEach(num->{
                    System.out.print(num+" ");
                });
                System.out.println();
            }
            list.clear();
        }*/
        System.out.println(jduge(370));
    }

    //判断是否是水仙花数
    private static boolean jduge(int num){
        int sum=0;
        int tmp=num;
        while(tmp>0){
            int bit=tmp%10;
            sum+=Math.pow(bit,3);
            tmp/=10;
        }
        return sum==num;
    }

}
