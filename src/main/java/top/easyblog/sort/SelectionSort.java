package top.easyblog.sort;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/05/01 23:01
 */
public class SelectionSort implements Sort {


    /**
     * 选择排序JAVA实现
     * 遍历数据，每次从无序序列中找一个比最后一个有序序列大的元素然后放到有序序列末尾即可
     * @param array
     */
    @Override
    public void sort(int[] array) {
        if(array==null||array.length==0){
            return;
        }
        for(int i=0;i<array.length-1;i++){
            int min=i;
            for(int j=i+1;j<array.length;j++){
                //寻找最小值
                if(array[j]<array[min]){
                    min=j;
                }

            }
            //在这一趟找到了一个比array[min]还小的数
            if(min!=i){
                int tmp=array[min];
                array[min]=array[i];
                array[i]=tmp;
            }
        }
    }
}
