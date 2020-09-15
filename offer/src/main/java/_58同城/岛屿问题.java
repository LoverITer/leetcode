package _58同城;

import java.util.Scanner;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/09/14 20:43
 */
public class 岛屿问题 {

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int m=sc.nextInt();
        int n=sc.nextInt();
        int[][] arr=new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                String line=sc.next();
                arr[i][j]=Integer.parseInt(line);
            }
        }
        System.out.println(helper(arr));
    }

    public static int helper(int[][] arr){
        int ans=0;
        if(arr==null||arr[0].length==0){
            return ans;
        }

        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                if(arr[i][j]==1){
                    ans++;
                    search(arr,i,j,arr.length,arr[0].length);
                }
            }
        }
        return ans;
    }

    private static void search(int[][] arr,int i,int j,int cols,int rows){
        if(i<0||i>=cols||j<0||j>=rows||arr[i][j]!=1){
            return;
        }
        arr[i][j]=2;
        search(arr,i+1,j,cols,rows);
        search(arr,i-1,j,cols,rows);
        search(arr,i,j+1,cols,rows);
        search(arr,i,j-1,cols,rows);
    }



}
