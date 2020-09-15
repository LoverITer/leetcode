package cache;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/09/11 21:28
 */

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;


public class Solution {


    /**
     * lru design
     * @param operators int整型二维数组 the ops
     * @param k int整型 the k
     * @return int整型一维数组
     */
    public int[] LRU (int[][] operators, int k) {
        //LRU缓存
        LRUCache<String,Integer> lru=new LRUCache<>(k);
        //结果数组
        int[] ans=new int[2];
        //遍历操作集
        for(int i=0;i<operators.length;i++){
            int opt=operators[i][0];
            String key=String.valueOf(operators[i][1]);
            int value=operators[i].length==3?operators[i][2]:-1;
            //根据opt来执行不同的操作
            if(opt==1){
                lru.put(key,value);
            }else if(opt==2){
                ans[0]=Integer.parseInt(key);
                ans[1]=lru.get(key);
            }
        }
        return ans;
    }
}

class LRUCache<K,V>{

    private DequeLinkedList<K,V> cache;
    private Map<K, DequeLinkedList.Node> map;
    private int capacity;

    public LRUCache(int capacity){
        cache=new DequeLinkedList<>();
        map=new HashMap<>(capacity);
        this.capacity=capacity;
    }

    public void put(K key,V value){
        if(map.containsKey(key)){
            deleteKey(key);
            addRecently(key,value);
            return;
        }
        if(cache.size()>=capacity){
            removeEldestNode();
        }
        addRecently(key,value);
    }

    private void deleteKey(K key){
        DequeLinkedList.Node<K,V> node=map.get(key);
        map.remove(key);
        cache.remove(node);
    }

    private void addRecently(K key,V value){
        DequeLinkedList.Node<K,V> node= new DequeLinkedList.Node<>(key, value);
        cache.add(node);
        map.put(key,node);
    }

    private void removeEldestNode(){
        DequeLinkedList.Node<K,V> node=cache.removeLast();
        if(node!=null){
            map.remove(node.key);
        }
    }

    public Integer get(K key){
        if(key!=null&&map.containsKey(key)){
            makeRecently(key);  //将访问的结点设置为最常使用的的
            return (Integer)map.get(key).value;
        }
        return -1;
    }

    private void makeRecently(K key){
        DequeLinkedList.Node node=map.get(key);
        cache.remove(node);
        cache.add(node);
    }
}


class DequeLinkedList<K,V>{

    //头结点
    private Node<K,V> head;
    //尾结点
    private Node<K,V> tail;
    //双链表的长度
    private int size;

    //结点类型
    static class Node<K,V>{
         K key;
         V value;
        private Node<K,V> next;
        private Node<K,V> prev;

        public Node(K key,V value){
            this.key=key;
            this.value=value;
            this.next=null;
            this.prev=null;
        }

    }

    public DequeLinkedList(){
        this.head=new Node(0,0);
        this.tail=new Node(0,0);
        this.head.next=this.tail;
        this.tail.prev=this.head;
        this.size=0;
    }

    //头插法添加元素
    public void add(Node<K,V> node){
        node.prev=head;
        node.next=head.next;
        head.next.prev=node;
        head.next=node;
        size++;
    }

    //移除一个节点
    public void remove(Node<K,V> node){
        node.prev.next=node.next;
        node.next.prev=node.prev;
        size--;
    }

    //移除尾部结点
    public Node removeLast(){
        if(head.next==tail){
            return null;
        }
        Node lastNode=tail.prev;
        remove(lastNode);
        return lastNode;
    }

    //返回双链表当前的长度
    public int size(){
        return this.size;
    }

    public static void main(String[] args) throws Exception {
        /*Cache<Integer, String> lru = new LRUCache<>(5);
        lru.put(1, "a");
        lru.put(2, "b");
        lru.put(3, "c");
        lru.put(4, "d");
        lru.put(5, "e");
        System.out.println("原始链表为：" + lru.toString());

        lru.get(4);
        System.out.println("获取key=4的元素之后的链表：" + lru.toString());

        lru.put(6, "f");
        System.out.println("新添加一个key=6之后的链表：" + lru.toString());

        lru.put(7,"454");
        System.out.println("新添加key=7之后的链表" + lru.toString());*/
        Callable call= () -> null;
        FutureTask<Integer> task=new FutureTask<>(call);
        Thread thread = new Thread(task,"test");
        thread.start();
        task.get();
        LRUCache<Integer,Integer> cache=new LRUCache<>(2);
        cache.put(1,1);
        cache.put(2,2);
        System.out.println(cache.get(1)); //1
        cache.put(3,3);
        System.out.println(cache.get(2));   //-1
        cache.put(4,4);
        System.out.println(cache.get(1));    //-1
        System.out.println(cache.get(3));    //3
        System.out.println(cache.get(4));    //4
    }

}
