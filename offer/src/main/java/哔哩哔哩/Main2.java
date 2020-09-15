package 哔哩哔哩;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/09/04 18:59
 */
public class Main2 {
    public static void main(String[] args) {

        ArrayList<Integer> list = new ArrayList<>();
         list.stream().toArray();
        IntStream stream= IntStream.builder().build();
        int sum = stream.sum();
        int[] ints = stream.toArray();


        System.out.println(Arrays.toString(new Main2().SpiralMatrix(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}})));
    }

    static int[] ans;
    static int index=0;

    public int[] SpiralMatrix (int[][] matrix) {
        int tr=0;
        int tc=0;
        int dr=matrix.length-1;
        int dc=matrix[0].length-1;
        ans=new int[matrix.length*matrix[0].length];
        while(tr<=dr&&tc<=dc){
           printEdge(matrix,tr++,tc++,dr--,dc--);
        }
        return ans;
    }


    private  void printEdge(int[][] matrix,int tr,int tc,int dr,int dc){
        if(tc==dr){
            for(int i=tc;i<=dc;i++){
                ans[index++]=matrix[tr][i];
            }
        }else if(tc==dc){
            for(int i=tr;i<=dr;i++){
                ans[index++]=matrix[i][tc];
            }
        }else {
            int curC=tc;
            int curR=tr;
            while(curC!=dc){
                ans[index++]=matrix[tr][curC];
                curC++;
            }
            while (curR!=dr){
                ans[index++]=matrix[curR][dc];
                curR++;
            }
            while (curC!=tc){
                ans[index++]=matrix[dr][curC];
                curC--;
            }
            while (curR!=tr){
                ans[index++]=matrix[curR][tc];
                curR--;
            }
        }
    }

}
