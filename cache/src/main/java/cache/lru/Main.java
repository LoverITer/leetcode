package cache.lru;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/09/02 23:28
 */
public class Main {


    private int capacity; //容量
    private int size;     //链表size
    private Map<Integer,Node> map;   //存放key到结点的映射
    private Node head;    //双链表头结点
    private Node tail;    //双链表尾结点

    static class Node{
        int key;
        int value;
        Node next;
        Node prev;

        Node(int key,int value){
            this.key=key;
            this.value=value;
        }
    }

    public Main(int capacity) {
        this.capacity=capacity;
        map=new HashMap<>(capacity);
        head=new Node(0,0);
        tail=new Node(0,0);
        head.next=tail;
        tail.prev=head;
    }

    //头部添加操作
    private void addHead(Node node){
        if(node==null){
            return;
        }
        node.prev=head;
        node.next=head.next;
        head.next=node;
        head.next.prev=node;
        this.size++;
    }

    //移除操作
    private void remove(Node node){
        if(node==null){
            return;
        }
        node.prev.next=node.next;
        node.next.prev=node.prev;
        size--;
    }

    //移除最后一个结点
    private Node removeLast(){
        if(tail.prev==head){
            return null;
        }
        Node lastNode=tail.prev;
        remove(lastNode);
        return lastNode;
    }

    private int size(){
        return this.size;
    }

    //头插并且同步维护HashMap
    private void addRecently(int key,int value){
        Node node=new Node(key,value);
        addHead(node);
        map.put(key,node);
    }

    //将节点设置为最近使用的（移动到链表头部）
    private void makeRecently(int key){
        Node node=map.get(key);
        remove(node);
        addHead(node);
    }

    /*** 删除最久未使用的元素 */
    private void removeEldestNode(){
        Node node=removeLast();
        map.remove(node.key);
    }

    private void deleteKey(int key){
        Node node=map.get(key);
        remove(node);
        map.remove(key);
    }

    public int get(int key) {
        if(map.containsKey(key)){
            makeRecently(key);
            return map.get(key).value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            //存在这个数据的话就更新数据
            deleteKey(key);
            addRecently(key,value);
            return;
        }
        //添加新数据
        //首先，判断添加后容量是否满了
        if(map.size()>=capacity){
            //缓存满了，就移除最近未使用的数据
            removeEldestNode();
        }
        addRecently(key,value);
    }

    public static void main(String[] args) {
        Main cache = new Main(2);
        cache.put(1,1);
        cache.put(2,2);
        System.out.println(cache.get(1)); //1
        cache.put(3,3);
        System.out.println(cache.get(2));   //2
        cache.put(4,4);
        System.out.println(cache.get(1));    //-1
        System.out.println(cache.get(3));    //3
        System.out.println(cache.get(4));    //4
    }

}
