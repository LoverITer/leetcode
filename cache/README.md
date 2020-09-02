### LRU算法到底是怎么一回事？

#### 1、LRU算法是什么？

​        LRU：Least Recently Used，即最近最久未使用的意思。

​        LRU算法是一种常用的页面置换算法，选择最近最久未使用的页面予以淘汰。当限定的空间已存满数据时，应当把最久没有被访问到的数据淘汰。

​         该算法是计算机操作系统中置换页的一种算法，同时在其他领域也有广泛应用，比如Redis的内存淘汰策略，该算法也是面试中面试官常常用来考验面试者代码能力和对LRU算法的正确理解。



#### 2、实现LRU算法

​       理论上，LRU有以下三种实现方式：

​     （1）用一个数组来保存数据，并给每一个数据标记一个访问时间戳，每次插入新数据项的时候，先把数组中存在的数据项的时间戳自增，并将新数据项的时间戳置为0并插入到数组中。每次访问数组中的数据项的时候，将被访问的数据项的时间戳置为0。当数组空间已满时，将时间戳最大的数据项淘汰。

​     （2）利用一个链表来实现，每次新插入数据的时候将新数据插到链表的头部；每次缓存命中（即数据被访问），则将数据移到链表头部；那么当链表满的时候，就将链表尾部的数据丢弃。

​      （3）利用链表和hashmap。当需要插入新的数据项的时候，如果新数据项在链表中存在（一般称为命中），则把该节点移到链表头部，如果不存在，则新建一个节点，放到链表头部，若缓存满了，则把链表最后一个节点删除即可。在访问数据的时候，如果数据项在链表中存在，则把该节点移到链表头部，否则返回-1。这样一来在链表尾部的节点就是最近最久未访问的数据项。

​       对于第一种方法，需要不停地维护数据项的访问时间戳，另外，在插入数据、删除数据以及访问数据时，时间复杂度都是O(n)。对于第二种方法，链表在定位数据的时候时间复杂度为O(n)。所以在一般使用第三种方式来是实现LRU算法。接下来我就使用**HashMap+双链表**的方式手撕一个时间复杂度为O(1)的LRU算法。

> Tips：在jdk中，LinkedHashMap其实已经实现了LRU缓存淘汰算法需要在构造方法第三个参数传入true( accessOrder = true;)，表示按照时间顺序访问。可以直接继承LinkedHashMap来实现。

![](http://image.easyblog.top/15941106427531d2d2669-e453-4a11-8855-0552dffed316.png)

LinkedHashMap中本身就实现了一个方法**removeEldestEntry**用于判断是否需要移除最不常读取的数，方法默认是直接返回false，不会移除元素，所以需要重写该方法。即当缓存满后就移除最不常用的数。下图所示是一个基于LinkedHashMap实现LRU的实例：

![](http://image.easyblog.top/15941115286515deef452-7370-4756-80c2-25c06a94d0c1.png)



##### 手撕LRU算法思路：

1. 使用HashMap保存每个数据项的Key ，通过 Key 就能以 O(1) 的时间得到节点；
2. 访问某个结点的时候就将其从原来位置删除，并重新插入到链表头部。这样就能保证链表尾部存储的就是最近最久未使用的节点，当节点数量大于缓存最大空间时就淘汰链表尾部的节点。
3. 由于使用HashMap保存了 Key 到节点的映射，因此通过 Key 就能以 O(1) 的时间得到节点，然后再以 O(1) 的时间将其从双向队列中删除。

![](http://image.easyblog.top/1594112109605abf0c82a-499c-4746-a40b-7b0ac4c671ea.png)

Java实现如下：

（1）定义基本的链表操作节点

```java
 /**                                     
  * 定义双向链表其中K为Map中的Key 降低查找时间复杂度         
  */                                     
 static class Node<K,V> {                            
     K key;                              
     V val;                              
     Node prev;                          
     Node next;                          
                                         
     public Node(K key, V val) {         
         this.key = key;                 
         this.val = val;                 
     }                                   
 }
```

（2）双向链表定义

我们定义一个LRUCache类，然后定义它的容量、头节点、尾节点以及用一个HashMap来映射key和各个结点，然后一个基本的构造方法初始化。

```java
public class LRUCache<K, V> {

    /***存储K和Node节点的映射 Node中会存放KV*/
    private HashMap<K, Node> cacheMap;
    /***链表头结点*/
    private Node head;
    /***链表尾结点*/
    private Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cacheMap = new HashMap<>(capacity);
    }
}    
```

接下来就是几个操作链表结点的方法：删除：remove()、添加：put()、获取：get()、移动到头部：moveToHead()。

（3）添加元素

添加元素的时候首先判断是不是新的元素，如果是新元素，判断当前的大小是不是大于总容量了，防止超过总链表大小，如果大于的话直接抛弃最后一个节点，然后再以传入的key\value值创建新的节点。对于已经存在的元素，直接覆盖旧值，再将该元素移动到头部，然后保存在map中

```java
/**                                      
 * 向缓存中存放数据                              
 *                                       
 * @param key   缓存的key                   
 * @param value 缓存的value                 
 */                                      
public void put(K key, V value) {        
    Node node = cacheMap.get(key);       
    if (node == null) {                  
        //缓存如果满了就将尾部使用频率不高的数据结点移除        
        if (capacity >= cacheMap.size()) 
            cacheMap.remove(tail.key);   
            removeLast();                
        }                                
        node = new Node(key, value);     
        cacheMap.put(node.key, node);    
    } else {                             
        //在缓存中已经存在这个数据,那就更新值             
        node.val = value;                
    }                                    
    moveToHead(node);                    
}                                        
```

（4）访问元素

通过key值来访问元素，主要的做法就是先从Map中根据给定的key判断如果是不存在的，直接返回null。如果存在，把数据移动到首部头节点，然后再返回旧值。

```java
/**                               
 * 访问缓存中的某个值                      
 *                                
 * @param key                     
 * @return                        
 */                               
public V get(K key) {             
    //直接从Map中获取                   
    Node node = cacheMap.get(key);
    if (node == null) {           
        return null;              
    }                             
    //访问某个结点的时候，将其移动到链表的头部        
    this.moveToHead(node);        
    return node.val;              
}                                 
```

（5）节点删除操作

在根据key删除节点的操作中，我们需要做的是把节点的前一个节点的指针指向当前节点下一个位置，再把当前节点的下一个的节点的上一个指向当前节点的前一个，这么说有点绕，我们来画图来看：

<center><img src="http://image.easyblog.top/15941282747964f235eed-555c-4c14-a703-11844656b955.png" style="width:40%"></center>

```java
 /**                                    
  * 移除链表的一个结点                           
  *                                     
  * @param key                          
  * @return                             
  */                                    
 private Node remove(K key) {           
     Node node = cacheMap.get(key);     
     if (node != null) {                
         if (node.prev != null) {       
             node.prev.next = node.next;
         }                              
         if (node.next != null) {       
             node.next.prev = node.prev;
         }                              
         if (node == head) {            
             head = node.next;          
         }                              
         if (node == tail) {            
             tail = node.prev;          
         }                              
     }                                  
     return cacheMap.remove(key);       
 } 
```

（6）移动元素到头节点

首先把当前节点移除,类似于删除的效果(但是没有移除该元素)，然后再将首节点设为当前节点的下一个,再把当前节点设为头节点的前一个节点。当前几点设为首节点。再把首节点的前一个节点设为null，这样就是间接替换了头节点为当前节点。

```java
/**                                   
 * 将结点移动到头结点                          
 *                                    
 * @param node                        
 */                                   
private void moveToHead(Node node) {  
    if (head == node) {               
        return;                       
    }                                 
    if (node.next != null) {          
        node.next.prev = node.prev;   
    }                                 
    if (node.prev != null) {          
        node.prev.next = node.next;   
    }                                 
    if (node == tail) {               
        tail = tail.prev;             
    }                                 
    if (head == null || tail == null) {
        head = tail = node;           
        return;                       
    }                                 
    node.next = head;                 
    head.prev = node;                 
    head = node;                      
    head.prev = null;                 
}                                     
```

#### 3、测试

代码写完了，我们来测试一下结果：

```java
public static void main(String[] args) {                             
    LRUCache<Integer, String> cache.lru = new LRUCache<Integer, String>(5);
    cache.lru.put(1, "a");                                                 
    cache.lru.put(2, "b");                                                 
    cache.lru.put(3, "c");                                                 
    cache.lru.put(4, "d");                                                 
    cache.lru.put(5, "e");                                                 
    System.out.println("原始链表为：" + cache.lru.toString());                   
                                                                     
    cache.lru.get(4);                                                      
    System.out.println("获取key为4的元素之后的链表：" + cache.lru.toString());         
                                                                     
    cache.lru.put(6, "f");                                                 
    System.out.println("新添加一个key为6之后的链表：" + cache.lru.toString());         
                                                                     
    cache.lru.remove(3);                                                   
    System.out.println("移除key=3的之后的链表" + cache.lru.toString());            
}                                                                  
```

执行结果：

可以看到，也是后添加的结点也是靠近链表头部，也是靠近尾部的元素越是优先被淘汰，这符合LRU算法的思想。

![](http://image.easyblog.top/1594131888381430bdd4c-e1eb-4702-b890-ea81056f9bd0.png)

