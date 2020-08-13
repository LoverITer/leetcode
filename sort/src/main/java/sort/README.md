### 十大经典排序算法（动图演示）

#### 0、算法概述

##### 0.1 算法分类

十种常见排序算法可以分为两大类：

- **比较类排序**：通过比较来决定元素间的相对次序，由于其时间复杂度不能突破O(nlogn)，因此也称为非线性时间比较类排序。
- **非比较类排序**：不通过比较来决定元素间的相对次序，它可以突破基于比较排序的时间下界，以线性时间运行，因此也称为线性时间非比较类排序。 

<center><img src="https://img2018.cnblogs.com/blog/849589/201903/849589-20190306165258970-1789860540.png" style="width:60%"></center>


##### 0.2 相关概念

- **稳定**：如果a原本在b前面，而a=b，排序之后a仍然在b的前面。
- **不稳定**：如果a原本在b的前面，而a=b，排序之后 a 可能会出现在 b 的后面。
- **时间复杂度**：对排序数据的总的操作次数。反映当n变化时，操作次数呈现什么规律。
- **空间复杂度：**是指算法在计算机

内执行时所需存储空间的度量，它也是数据规模n的函数。 

#### 1、冒泡排序（Bubble Sort）

冒泡排序是一种简单的排序算法。它重复地走访过要排序的数列，一次比较两个元素，如果它们的顺序错误就把它们交换过来。走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。这个算法的名字由来是因为越小的元素会经由交换慢慢“浮”到数列的顶端。 

##### 1.1 算法描述

- 比较相邻的元素。如果第一个比第二个大，就交换它们两个；
- 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；
- 针对所有的元素重复以上的步骤，除了最后一个；
- 重复步骤1~3，直到排序完成。

##### 1.2 动图演示

![img](https://images2017.cnblogs.com/blog/849589/201710/849589-20171015223238449-2146169197.gif)

##### 1.3 代码实现

```java
public int[] bubbleSort(int[] array){
    int len;
    if(array==null||(len=array.length)==0){
        return new int[]{};
    }
    for(int i=0;i<len;i++){
        for(int j=0;j<len-i-1;j++){
            if(array[j]>array[j+1]){
                //使用异或的性质交换两个数
                array[j]=array[j]^array[j+1];
                array[j+1]=array[j]^array[j+1];
                array[j]=array[j]^array[j+1];
            }
        }
    }
    return array;
}
```



##### 优化

当我们的数字序列基本有的时候往往只需要扫描几次就可以排序好了，当数字序列有序了之后在重复扫描那就是在做无用功，因此我们需要优化一下代码的实现。

```java
public int[] bubbleSort(int[] sort){
    int len;
    if(array==null||(len=array.length)==0){
        return new int[]{};
    }
    for(int i=0;i<len;i++){
        boolean moved=false;
        for(int j=0;j<len-i-1;j++){
            if(array[j]>array[j+1]){
                array[j]=array[j]^array[j+1];
                array[j+1]=array[j]^array[j+1];
                array[j]=array[j]^array[j+1];
                /**
                *本次扫描发生过一次交换就记录下来，告诉程序接下来还需要扫描，
                *如果一次扫描框中没有发生或数据交换那就说明数字序列已经有序了，那就结束排序
                */
                moved=true;
            }
        }
        if(!moved){
            break;
        }
    }
    return array;
}
```

#### 2、选择排序（Selection Sort）

选择排序(Selection-sort)是一种简单直观的排序算法。它的工作原理：首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。 

##### 2.1 算法描述

n个记录的直接选择排序可经过n-1趟直接选择排序得到有序结果。具体算法描述如下：

- 初始状态：无序区为R[1..n]，有序区为空；
- 第i趟排序(i=1,2,3…n-1)开始时，当前有序区和无序区分别为R[1..i-1]和R(i..n）。该趟排序从当前无序区中-选出关键字最小的记录 R[k]，将它与无序区的第1个记录R交换，使R[1..i]和R[i+1..n)分别变为记录个数增加1个的新有序区和记录个数减少1个的新无序区；
- n-1趟结束，数组有序化了。

##### **2.2 动图演示**

![img](https://images2017.cnblogs.com/blog/849589/201710/849589-20171015224719590-1433219824.gif)　　

##### 2.3 代码实现

```java
public int[] selectionSort(int[] array){
    int len;
    if(array==null||(len=array.length)==0){
        return new int[]{};
    }
    for(int i=0;i<len-1;i++){
        int min=i;
        for(int j=i+1;j<len;j++){
            if(array[min]>array[j]){
                min=j;
            }
        }
        if(min!=i){
            array[i]=array[i]^array[min];
            array[min]=array[i]^array[min];
            array[i]=array[i]^array[min];
        }
    }
}
```

##### 2.4 算法分析

表现最稳定的排序算法之一，因为无论什么数据进去都是O(n^2)的时间复杂度，所以用到它的时候，数据规模越小越好。唯一的好处可能就是不占用额外的内存空间了吧。理论上讲，选择排序可能也是平时排序一般人想到的最多的排序方法了吧。



#### 3、插入排序（Insertion Sort）

插入排序（Insertion-Sort）的算法描述是一种简单直观的排序算法。它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。

##### 3.1 算法描述

一般来说，插入排序都采用in-place在数组上实现。具体算法描述如下：

- 从第一个元素开始，该元素可以认为已经被排序；
- 取出下一个元素，在已经排序的元素序列中从后向前扫描；
- 如果该元素（已排序）大于新元素，将该元素移到下一位置；
- 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
- 将新元素插入到该位置后；
- 重复步骤2~5。

##### 3.2 动图演示

![img](https://images2017.cnblogs.com/blog/849589/201710/849589-20171015225645277-1151100000.gif)

##### 3.2 代码实现

```java
public int[] insertionSort(int[] array){
    int len;
    if(array==null||(len=array.length)==0){
        return new int[]{};
    }
    for(int i=1;i<len;i++){
        for(int j=i-1;(j>=0)&&(array[j]>array[j+1]);j--){
            array[j]=array[j]^array[j+1];
            array[j+1]=array[j]^array[j+1];
            array[j]=array[j]^array[j+1];
        }
    }
    return array;
}
```



##### 优化

上面的算法在寻找合适的插入位置的时候使用的是顺序查找，非常的耗费时间，既然前面的数字序列是已经排序好的，那么我们可以思考可以用二分查找优化寻找合适插入位置的逻辑。

```java
public int[] insertionSort(int[] array){
    int len;
    if(array==null||(len=array.length)==0){
        return new int[]{};
    }
    for(int i=1;i<len;i++){
        int curr=array[i];
        int index=findInsertIndex(array,0,i-1,curr);
        //腾出插入的位置
        for(int j = i; j > index; j--) {
           array[j] = array[j - 1];
        }
        //插入合适位置
        array[index]=curr;
    }
    return array;
}

private int findInsertIndex(int[] array,int low,int high,int key){
    while(left<=right){
        int mid=low+((high-low)>>>1);
        if(array[mid]>key){
            high=mid-1;
        }else if(array[mid]<key){
            low=mid+1;
        }else{
            return mid;
        }
    }
    return low;
}
```

##### 3.4 算法分析

插入排序在实现上，通常采用in-place排序（即只需用到O(1)的额外空间的排序），因而在从后向前扫描过程中，需要反复把已排序元素逐步向后挪位，为最新元素提供插入空间。插入排序在小数据量或数据基本有序的情况下非常高效，但是数据量一大就基本不那么好使了，基于此Shell排序应运而生。



#### 4、希尔排序（Shell Sort）

1959年Shell发明，第一个突破O(n^2)的排序算法，是简单插入排序的改进版。它与插入排序的不同之处在于，它会优先比较距离较远的元素。希尔排序又叫**缩小增量排序**。

##### 4.1 算法描述

先将整个待排序的记录序列分割成为若干子序列（分割成小数据量）分别进行直接插入排序，具体算法描述：

- 选择一个增量序列t1，t2，…，tk，其中ti>tj，tk=1；
- 按增量序列个数k，对序列进行k 趟排序；
- 每趟排序，根据对应的增量ti，将待排序列分割成若干长度为m 的子序列，分别对各子表进行直接插入排序。仅增量因子为1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。

##### 4.2 动图演示

![img](https://images2018.cnblogs.com/blog/849589/201803/849589-20180331170017421-364506073.gif)

##### 4.3 代码实现

```java
public int[] shellSort(int[] array){
    int len;
    if(array==null||(len=array.length)==0){
        return new int[]{};
    }
    int gpa=1;
    //寻找最大的合适增量
    while(gpa<len){
        gpa=gpa*3+1;
    }
    while(gpa>0){
        //内部其实就是简单插入排序
        for(int i=gpa;i<len;i++){
            for(int j=i-gpa;(j>=0)&&(array[j]>array[j+gpa]);j-=gpa){
                array[j] = array[j] ^ array[j + gpa];
                array[j + gpa] = array[j] ^ array[j + gpa];
                array[j] = array[j] ^ array[j + gpa];
            }
        }
        //计算剩下的增量
        gpa=(int)Math.floor(gpa/3);
    }
    return array;
}
```

##### 4.4 算法分析

希尔排序的核心在于间隔序列的设定。既可以提前设定好间隔序列，也可以动态的定义间隔序列。动态定义间隔序列的算法是《算法（第4版）》的合著者Robert Sedgewick提出的。　

#### 5、归并排序（Merge Sort）

归并排序是建立在归并操作上的一种有效的排序算法。该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序。若将两个有序表合并成一个有序表，称为2-路归并。 

##### 5.1 算法描述

- 把长度为n的输入序列分成两个长度为n/2的子序列；
- 对这两个子序列分别采用归并排序；
- 将两个排序好的子序列合并成一个最终的排序序列。

##### 5.2 动图演示

![img](https://images2017.cnblogs.com/blog/849589/201710/849589-20171015230557043-37375010.gif)

##### 5.3 代码实现

```java
public class MergeSort {

    public void sort(int[] array) {
        int len;
        if (array == null || (len = array.length) == 0) {
            return;
        }
        mergeSort(array, 0, len - 1);
    }

    /**
     * 递归的分隔数组，并调用方法{@code merge}合并两个有序数组
     *
     * @param array 待排序数组
     * @param low   排序起始位置
     * @param high  排序终止位置
     */
    private void mergeSort(int[] array, int low, int high) {
        if (low < high) {
            int mid = low + ((high - low) >>> 1);
            mergeSort(array, low, mid);
            mergeSort(array, mid + 1, high);
            merge(array, low, mid, high);
        }

    }

    /**
     * 合并两个有序数组
     *
     * @param array 数组
     * @param low   数组1起始地址
     * @param mid   数组1和数组2的分界点
     * @param high  数组2结束地址
     */
    private void merge(int[] array, int low, int mid, int high) {
        //开辟额外的空间
        int[] tmp = new int[high - low + 1];
        int i = 0, p1 = low, p2 = mid + 1; //这里p1,p2分别表示两个数组的起始地址
        while (p1 <= mid && p2 <= high) {
            tmp[i++] = array[p1] < array[p2] ? array[p1++] : array[p2++];
        }

        //退出大循环之后还需要分别判断一下哪个数组还没有遍历完
        while (p1 <= mid) {
            tmp[i++] = array[p1++];
        }
        while (p2 <= high) {
            tmp[i++] = array[p2++];
        }
        //把最终的排序结果复制到原数组
        for (i = 0; i < tmp.length; i++) {
            array[low + i] = tmp[i];
        }
    }
}
```

##### 5.4 算法分析

归并排序是一种稳定的排序方法。和选择排序一样，归并排序的性能不受输入数据的影响，但表现比选择排序好的多，因为始终都是O(nlogn）的时间复杂度。代价是需要额外的内存空间。

#### 6、快速排序（Quick Sort）

快速排序的基本思想：通过一趟排序将待排记录分隔成独立的两部分，其中一部分记录的关键字均比另一部分的关键字小，则可分别对这两部分记录继续进行排序，以达到整个序列有序。

##### 6.1 算法描述

快速排序使用分治法来把一个串（list）分为两个子串（sub-lists）。具体算法描述如下：

- 从数列中挑出一个元素，称为 “基准”（pivot）；
- 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
- 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。

##### 6.2 动图演示

![img](https://images2017.cnblogs.com/blog/849589/201710/849589-20171015230936371-1413523412.gif)

##### 6.3 代码实现

```java
public class QuickSort {

    public void sort(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int mid = partition(array, low, high);
            quickSort(array, low, mid - 1);
            quickSort(array, mid + 1, high);
        }
    }

    /**
     * 快排-双边循环的法
     */
    private int partition(int[] array, int low, int high) {
        //将数组的首元素作为基准元素，进行划分数组
        int pivot = array[low];
        while (low < high) {
            while (low < high && array[high] > pivot){
                high--;
            }
            array[low] = array[high];
            while (low < high && array[low] <= pivot){
                low++;
            }
            array[high] = array[low];
        }
        //low = high
        array[low] = pivot;
        //数组中的元素应该是以pivot为中心，在pivot之前的数都比他小，在pivot之后的数都比他大
        return low;
    }

}
```

#### 7、堆排序（Heap Sort）

堆排序（Heapsort）是利用**堆**这种数据结构而设计的一种排序算法，堆排序是一种**选择排序，**它的最坏，最好，平均时间复杂度均为O(nlogn)，它也是不稳定排序。首先简单了解下堆结构。

**堆是具有以下性质的完全二叉树：每个结点的值都大于或等于其左右孩子结点的值，称为大顶堆；或者每个结点的值都小于或等于其左右孩子结点的值，称为小顶堆。如下图：**

<center><img src="http://image.easyblog.top/1596295958742f4de0257-a04f-4107-8209-4083f74983c7.png" style="width:80%;" /></center>

同时，我们对堆中的结点按层进行编号，将这种逻辑结构映射到数组中就是下面这个样子

<center><img src="http://image.easyblog.top/1596296082211f89bfd76-80e5-4f0a-8b00-effe7efed7ee.png" style="zoom:80%;" /></center>

该数组从逻辑上讲就是一个堆结构，我们用简单的公式来描述一下堆的定义就是：

**大顶堆：arr[i] >= arr[2i+1] && arr[i] >= arr[2i+2]**  

**小顶堆：arr[i] <= arr[2i+1] && arr[i] <= arr[2i+2]**  

**一般升序排序使用大顶堆，降序排序使用小顶堆。**

ok，了解了这些定义。接下来，我们来看看堆排序的基本思想及基本步骤：

##### 7.1 算法描述

以升序排序为例，算法思路 如下：

* 将待排序的序列构造成一个大顶堆，根据大顶堆的性质，当前堆的根节点（堆顶）就是序列中最大的元素
* 将堆顶元素和最后一个元素交换，然后将剩下的节点重新构造成一个大顶堆；
* 重复步骤2，如此反复，从第一次构建大顶堆开始，每一次构建，我们都能获得一个序列的最大值，然后把它放到大顶堆的尾部。最后，就得到一个有序的序列了。

##### 7.2 动图演示

![img](https://images2017.cnblogs.com/blog/849589/201710/849589-20171015231308699-356134237.gif)

##### 7.3 代码实现

```java
public class HeapSort{

    public int[] sort(int array){
        int len;
        if(array==null||(len=array.length)==0){
           return new int[]{};
        }
        //1.构建大顶堆
        for(int i=len/2-1;i>=0;i--){
            heapify(array,i,len);
        }
        for(int i=len-1;i>0;i--){
            //2.交换堆顶和末尾结点(请忽略交换的方式，反正就是交换array[0]和array[i])
            array[0] = array[0] ^ array[i];
            array[i] = array[0] ^ array[i];
            array[0] = array[0] ^ array[i];
            len--;
            //3.交换过后重新调整为新的大顶堆
            heapify(array,0,len);
        }
        return array;
    }
    
    /**
     * 将普通的数组堆化，构建成一个大顶堆
     *
     * @param array 待堆化数组
     * @param i     堆化开始位置
     * @param len   堆化长度
     */
    private void heapify(int[] array,int i,int len){
        int root=array[i]; //保存当前根节点值
        for(int k=2*i+1;i<len;k=2*i+1){
            if(k+1<len&&array[k]<array[k+1]){
                //如果左子结点小于右子结点，k指向右子结点
                k++;
            }
            if(array[k]>root){
                array[i]=array[k];
                i=k;
            }else{
                break;
            }
        }
        //将根节点值放置到合适的位置
        array[i]=root;
    }
}
```



##### 7.4 算法分析

堆排序是一种选择排序，整体主要由构建初始堆+交换堆顶元素和末尾元素并重建堆两部分组成。其中构建初始堆经推导复杂度为O(n)，在交换并重建堆的过程中，需交换n-1次，而重建堆的过程中，根据完全二叉树的性质，[log2(n-1),log2(n-2)...1]逐步递减，近似为nlogn。所以堆排序时间复杂度一般认为就是O(nlogn)级。

#### 8、计数排序（Counting Sort）

计数排序不是基于比较的排序算法，其核心在于将输入的数据值转化为键存储在额外开辟的数组空间中。 作为一种线性时间复杂度的排序，计数排序要求输入的数据必须是有确定范围的整数。

##### 8.1 算法描述

- 找出待排序的数组中最大和最小的元素；
- 统计数组中每个值为i的元素出现的次数，存入数组C的第i项；
- 对所有的计数累加（从C中的第一个元素开始，每一项和前一项相加）；
- 反向填充目标数组：将每个元素i放在新数组的第C(i)项，每放一个元素就将C(i)减去1。

##### 8.2 动图演示

![img](https://images2017.cnblogs.com/blog/849589/201710/849589-20171015231740840-6968181.gif)

##### 8.3 代码实现

```java
public void countingSort(int[] array){
    //找最大、最小值
    int max = array[0];
    int min = array[0];
    for(int num:array){
        if(max<num){
            max=num;
        }
        if(min>num){
            min=num;
        }
    }
    int[] countArray=new int[max+1];
    for(int value:array){
        countArray[value]++;
    }
    int index=0;
    for(int i=0;i<countArray.length;i++){
        int j=0;
        while(j<countArray[i]){
            array[index++]=i;
            j++;
        }
    }
}
```

##### 优化

```java
public static int[] countingSort(int[] array) {
   //1.得到数列的最大值和最小值，并算出差值d
   int max = array[0];
   int min = array[0];
   for(int i=1; i<array.length; i++) {
       if(array[i] > max) {
           max = array[i];
       }
       if(array[i] < min) {
           min = array[i];
       }
   }
   //2.创建统计数组并统计对应元素个数
   int[] countArray = new int[ max - min+1];
   for(int i=0; i<array.length; i++) {
       countArray[array[i]-min]++;
   }
   //3.统计数组做变形，后面的元素等于前面的元素之和
   int sum = 0;
   for(int i=0;i<countArray.length;i++) {
       sum += countArray[i];
       countArray[i] = sum;
   }
   //4.倒序遍历原始数列，从统计数组找到正确位置，输出到结果数组
   int[] sortedArray = new int[array.length];
   for(int i=array.length-1;i>=0;i--) {
       sortedArray[countArray[array[i]-min]-1]=array[i];
       countArray[array[i]-min]--;
   }
   return sortedArray;
}
```





##### 8.4 算法分析

计数排序是一个稳定的排序算法。当输入的元素是 n 个 0到 k 之间的整数时，时间复杂度是O(n+k)，空间复杂度也是O(n+k)，其排序速度快于任何比较排序算法。当k不是很大并且序列比较集中时，计数排序是一个很有效的排序算法。

#### 9、桶排序（Bucket Sort）

桶排序是计数排序的升级版。它利用了函数的映射关系，高效与否的关键就在于这个映射函数的确定。桶排序 (Bucket sort)的工作的原理：假设输入数据服从均匀分布，将数据分到有限数量的桶里，每个桶再分别排序（有可能再使用别的排序算法或是以递归方式继续使用桶排序进行排）。

##### 9.1 算法描述

- 设置一个定量的数组当作空桶；
- 遍历输入数据，并且把数据一个一个放到对应的桶里去；
- 对每个不是空的桶进行排序；
- 从不是空的桶里把排好序的数据拼接起来。 

##### 9.2 图片演示

![img](https://images2017.cnblogs.com/blog/849589/201710/849589-20171015232107090-1920702011.png)

##### 9.3 代码实现

```java
public static void sort(int[] arr){                                       
    // 计算最大值与最小值                                                          
    int max = Integer.MIN_VALUE;                                          
    int min = Integer.MAX_VALUE;                                          
    for(int i = 0; i < arr.length; i++){                                  
        max = Math.max(max, arr[i]);                                      
        min = Math.min(min, arr[i]);                                      
    }                                                                     
                                                                          
    // 计算桶的数量                                                             
    int bucketNum = (max - min) / arr.length + 1;                         
    ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<>(bucketNum); 
    for(int i = 0; i < bucketNum; i++){                                   
        bucketArr.add(new ArrayList<>());                                 
    }                                                                     
                                                                          
    // 将每个元素放入桶                                                           
    for(int i = 0; i < arr.length; i++){                                  
        int num = (arr[i] - min) / (arr.length);                          
        bucketArr.get(num).add(arr[i]);                                   
    }                                                                     
                                                                          
    // 对每个桶进行排序                                                           
    for(int i = 0; i < bucketArr.size(); i++){                            
        //桶内使用其他排序算法排序                                                    
        Collections.sort(bucketArr.get(i));                               
    }                                                                     
                                                                          
    // 将桶中的元素赋值到原序列                                                       
    int index = 0;                                                        
    for(int i = 0; i < bucketArr.size(); i++){                            
        for(int j = 0; j < bucketArr.get(i).size(); j++){                 
            arr[index++] = bucketArr.get(i).get(j);                       
        }                                                                 
    }                                                                     
}                                                                         
```

##### 9.4 算法分析

- 平均时间复杂度：O(n + k)
- 最佳时间复杂度：O(n + k)
- 最差时间复杂度：O(n ^ 2)
- 空间复杂度：O(n * k)
- 稳定性：稳定

 

#### 10、基数排序（Radix Sort）

基数排序是按照低位先排序，然后收集；再按照高位排序，然后再收集；依次类推，直到最高位。有时候有些属性是有优先级顺序的，先按低优先级排序，再按高优先级排序。最后的次序就是高优先级高的在前，高优先级相同的低优先级高的在前。

##### 10.1 算法描述

- 取得数组中的最大数，并取得位数；
- arr为原始数组，从最低位开始取每个位组成radix数组；
- 对radix进行计数排序（利用计数排序适用于小范围数的特点）；

##### 10.2 动图演示

![img](https://images2017.cnblogs.com/blog/849589/201710/849589-20171015232453668-1397662527.gif) 

##### 10.3 代码实现

```java
public class RadixSort {

    public int[] sort(int[] sourceArray) throws Exception {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        int maxDigit = getMaxDigit(arr);
        return radixSort(arr, maxDigit);
    }

    /**
     * 获取最高位数
     */
    private int getMaxDigit(int[] arr) {
        int maxValue = getMaxValue(arr);
        return getNumLenght(maxValue);
    }

    private int getMaxValue(int[] arr) {
        int maxValue = arr[0];
        for (int value : arr) {
            if (maxValue < value) {
                maxValue = value;
            }
        }
        return maxValue;
    }

    protected int getNumLenght(long num) {
        if (num == 0) {
            return 1;
        }
        int lenght = 0;
        for (long temp = num; temp != 0; temp /= 10) {
            lenght++;
        }
        return lenght;
    }

    private int[] radixSort(int[] arr, int maxDigit) {
        int mod = 10;
        int dev = 1;

        for (int i = 0; i < maxDigit; i++, dev *= 10, mod *= 10) {
            // 考虑负数的情况，这里扩展一倍队列数，其中 [0-9]对应负数，[10-19]对应正数 (bucket + 10)
            int[][] counter = new int[mod * 2][0];

            for (int j = 0; j < arr.length; j++) {
                int bucket = ((arr[j] % mod) / dev) + mod;
                counter[bucket] = arrayAppend(counter[bucket], arr[j]);
            }

            int pos = 0;
            for (int[] bucket : counter) {
                for (int value : bucket) {
                    arr[pos++] = value;
                }
            }
        }

        return arr;
    }

    /**
     * 自动扩容，并保存数据
     *
     * @param arr
     * @param value
     */
    private int[] arrayAppend(int[] arr, int value) {
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = value;
        return arr;
    }

}
```

##### 10.4 算法分析

基数排序基于分别排序，分别收集，所以是稳定的。但基数排序的性能比桶排序要略差，每一次关键字的桶分配都需要O(n)的时间复杂度，而且分配之后得到新的关键字序列又需要O(n)的时间复杂度。假如待排数据可以分为d个关键字，则基数排序的时间复杂度将是O(d*2n) ，当然d要远远小于n，因此基本上还是线性级别的。

基数排序的空间复杂度为O(n+k)，其中k为桶的数量。一般来说n>>k，因此额外空间需要大概n个左右。





##### 参考资料

* 【1】[十大经典排序算法（动图演示）](https://www.cnblogs.com/onepixel/p/7674659.html)
* 【2】[图解排序算法(三)之堆排序](https://www.cnblogs.com/chengxiao/p/6129630.html)