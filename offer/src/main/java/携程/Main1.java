package 携程;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/09/08 19:32
 */
public class Main1 {


    private static Set<String> badWords=new HashSet<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String badWord=sc.nextLine();
        String content=sc.nextLine();
        String replaceWord=sc.nextLine();
        char[] chars = badWord.toCharArray();

        permutation(chars,0);
        Iterator<String> it = badWords.iterator();
        while(it.hasNext()){
            String str=it.next();
            if(content.contains(str)){
                content = content.replaceAll(str, replaceWord);
            }
        }
        System.out.println(content);
    }


    /*private static void permutation(char[] arr, int start){
        if(start== arr.length-1){
            badWords.add(String.valueOf(arr));
        }else{
            for(int i=start;i<arr.length;i++){
                char t=arr[start];
                arr[start]=arr[i];
                arr[i]=t;
                permutation(arr,start+1);
                t=arr[start];
                arr[start]=arr[i];
                arr[i]=t;
            }
        }

    }*/

    private static void permutation(char[] arr, int start){
        if(start== arr.length-1){
            badWords.add(String.valueOf(arr));
        }else{
            for(int i=start;i<arr.length;i++){
                if(check(arr,start,i)){
                    char t=arr[start];
                    arr[start]=arr[i];
                    arr[i]=t;
                    permutation(arr,start+1);
                    t=arr[start];
                    arr[start]=arr[i];
                    arr[i]=t;
                }
            }
        }

    }

    private static boolean check(char[] arr,int k,int i){
        if(i>k){
            for(int j=k;j<i;j++){
                if(arr[j]==arr[i]){
                    return false;
                }
            }
        }
        return true;
    }




}
