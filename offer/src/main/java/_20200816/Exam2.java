package _20200816;



import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/08/16 14:53
 */
public class Exam2 {


    private static final String UNDO="undo";
    private static final String REDO="redo";

    private static String helper(String str) {
        if(str==null||str.length()==0){
            return "";
        }
        String[] chars = str.split(" ");
        Deque<String> mainStack=new ArrayDeque<>();
        Deque<String> tStack=new ArrayDeque<>();
        for(int i=0;i<chars.length;i++){
            String s=chars[i];
            if(!UNDO.equals(s)&&!REDO.equals(s)) {
                //非undo,redo字符串直接入栈
                mainStack.push(chars[i]);
            }else{
                if(UNDO.equals(s)&&!mainStack.isEmpty()){
                    //undo，删除栈顶元素，暂时把它放到临时桟tStack中
                    String pop = mainStack.pop();
                    tStack.push(pop);
                }else if(REDO.equals(s)&&!tStack.isEmpty()){
                    String pop = tStack.pop();
                    mainStack.push(pop);
                }
            }
        }

        StringBuilder sb=new StringBuilder();
        while(!mainStack.isEmpty()){
            sb.append(mainStack.pop()).append(" ");
        }
        return revser(sb.toString());
    }

    private static String revser(String str){
        if(str==null||str.length()==0){
            return "";
        }
        String[] chars = str.split(" ");
        if(chars.length==1){
            return chars[0];
        }
        int left=0,right=chars.length-1;
        while(left<right){
            String t=chars[left];
            chars[left]=chars[right];
            chars[right]=t;
            left++;
            right--;
        }
        StringBuilder sb = new StringBuilder();
        for(String s:chars){
            sb.append(s).append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(helper(str));
        //System.out.println(revser("world hello"));
    }

}
