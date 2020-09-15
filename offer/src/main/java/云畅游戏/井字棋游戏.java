package 云畅游戏;

import java.util.Scanner;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/09/03 20:27
 */
public class 井字棋游戏 {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] board=new int[3][3];
        String line = sc.next();
        String[] split = line.split(",");
        for(int i=0;i<split.length;i++){
            int j= (int) Math.floor((double) i/3);
            board[i%3][j]= Integer.parseInt(split[i]);
        }
        System.out.println(isWin(board));
    }

    private static boolean isWin(int[][] board) {
        int row=board.length;
        for(int i=0;i<row;i++){
            int rows=0;
            int cols=0;
            for(int j=0;j<row;j++){
                rows+=board[i][j];
                cols+=board[j][i];
            }
            if(rows==row){
                return true;
            }
            if(cols==row){
                return true;
            }
        }

        int first=0;
        int second=0;
        for(int i=0;i<row;i++){
            first+=board[i][i];
            second+=board[i][row-1-i];
        }
        if(first==row){
            return true;
        }
        if(second==row){
            return true;
        }
        return false;
    }

}
