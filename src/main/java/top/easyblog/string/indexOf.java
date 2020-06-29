package top.easyblog.string;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/06/25 22:51
 */
public class indexOf {

    public static int strStr(String haystack, String needle) {
        int max=0;
        if(needle==null||(max=needle.length())==0){
            return 0;
        }
        if(haystack!=null&&haystack.length()>=needle.length()){
            int j;
            for(int i=0;i<haystack.length()-needle.length()+1;i++){
                for(j=0;j<needle.length();j++){
                    if(haystack.charAt(i+j)!=needle.charAt(j)){
                        break;
                    }
                }
                if(needle.length()==j){
                    return i;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(strStr("a","a"));
    }

}
