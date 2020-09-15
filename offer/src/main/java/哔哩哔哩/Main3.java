package 哔哩哔哩;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/09/04 18:59
 */
public class Main3 {
    public static void main(String[] args) {
        System.out.println(GetFragment("aaaaaaaaaaaaaa"));
    }

    public static int GetFragment (String str) {
        //"aaa bb aaa c"  2
        if(str==null||str.length()==0){
            return 0;
        }
        char[] chars = str.toCharArray();
        int cnt=0;  //分段个数
        int[] nums=new int[str.length()];
        nums[cnt]=1;
        for(int i=1;i<chars.length;i++){
            if(chars[i]==chars[i-1]){
                nums[cnt]++;
            }else{
                nums[++cnt]=1;
            }
        }
        int ans=0;
        int i;
        for(i=0;i<cnt+1;i++){
            ans+=nums[i];
        }
        return ans/(cnt+1);
    }
}
