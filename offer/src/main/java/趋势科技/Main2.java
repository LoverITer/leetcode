package 趋势科技;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/09/12 18:56
 */
public class Main2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String n = sc.nextLine();
        int x=sc.nextInt();
        if(n.length()==x||Integer.parseInt(n)==0){
            System.out.println(0);
            return;
        }
        System.out.println(removeKNums(n, x));
    }

    private static String removeKNums(String nums,int x){
        Deque<Character> stack=new ArrayDeque<>();
        for(char num:nums.toCharArray()){
            while(stack.size()>0&&x>0&&stack.peekLast()>num){
                stack.removeLast();
                x--;
            }
            stack.addLast(num);
        }

        for (int i=0;i<x;i++){
            stack.removeLast();
        }
        StringBuilder sb = new StringBuilder();
        boolean zero=true;
        for(char num:stack){
            if(zero&&num=='0'){
                continue;
            }
            zero=false;
            sb.append(num);
        }

        if(sb.length()==0){
            return "0";
        }
        return sb.toString();
    }

}
