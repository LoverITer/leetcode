package string;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/06/16 14:59
 */
public class 外观数列 {

    public static String countAndSay(int n) {
        String str="1";
        for(int i=2;i<=n;i++){
            StringBuilder sb=new StringBuilder();
            int start=0,end=0;
            while(end<str.length()-1){
                end++;
                if(str.charAt(end)!=str.charAt(end-1)){
                    sb.append(end-start).append(str.charAt(start));
                    start=end;
                }
            }
            sb.append(end-start+1).append(str.charAt(start));
            str=sb.toString();
        }
        return str;
    }

    public static void main(String[] args) {
        System.out.println(countAndSay(5));
    }

}
