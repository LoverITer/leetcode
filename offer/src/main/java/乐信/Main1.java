package 乐信;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/09/09 20:38
 */
public class Main1 {


    public static ArrayList<ArrayList<String>>  productList(ArrayList<String> products,String searchWorlds){
        ArrayList<ArrayList<String>> ans=new ArrayList<>(searchWorlds.length());
        if(products==null){
            for(int i=0;i<searchWorlds.length();i++){
                ans.add(new ArrayList<>());
            }
            return ans;
        }
        products.sort(Comparator.naturalOrder());
        char[] chars = searchWorlds.toCharArray();
        for(int i=0;i<chars.length;i++){
            String str= String.valueOf(Arrays.copyOfRange(chars,0,i+1));
            ArrayList<String> list = new ArrayList<>();
            for(String product:products){
                if(product.startsWith(str)){
                   if(list.size()<3){
                       //
                       list.add(product);
                   }else{
                       HashMap<Integer, String> map = new HashMap<>();
                       int min=Integer.MAX_VALUE;
                       for(String pro:list){
                           for(int j=0;j<str.length();j++){
                               if(pro.charAt(j)!=str.charAt(j)){
                                   min=Math.min(min,j);
                                   map.putIfAbsent(j,pro);
                               }
                           }
                       }
                       //和目标匹配最少的一个串
                       String s = map.get(min);
                       for(int j=0;j<list.size();j++){
                           if(s.equals(list.get(j))){
                               list.set(j,product);
                           }
                       }
                   }
                }
            }
            list.sort(Comparator.naturalOrder());
            ans.add(list);
        }
        return ans;
    }

    public static void main(String[] args) {

    }

}
