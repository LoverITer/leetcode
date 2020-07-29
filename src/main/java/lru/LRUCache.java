package lru;

import java.util.HashMap;

/**
 * {@code LRU：Least Recently Used}，即最近最久未使用的意思。
 * LRU算法设计思想：是一种常用的页面置换算法，基本思想是选择最近最久未使用的页面予以淘汰。
 *
 * 基本实现思路：维护一个双向链表用于数据存储和结点的快速增删，并且再维护一个Map用于记录缓存
 * 的key和具体在链表中结点的映射关系，这样做是为了提高缓存的查询效率到O(1)。
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/07/07 15:51
 */
public class LRUCache<K, V> {


    /***缓存的容量*/
    private int capacity;
    /***存储K和Node节点的映射 Node中会存放K-V*/
    private HashMap<K, Node> cacheMap;
    /***链表头结点*/
    private Node head;
    /***链表尾结点*/
    private Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cacheMap = new HashMap<>(capacity);
    }

    /**
     * 定义双向链表其中K为Map中的Key 降低查找时间复杂度
     */
    static class Node<K, V> {
        K key;
        V val;
        Node prev;
        Node next;

        Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    /**
     * 访问缓存中的某个值
     * <p>
     *  首先根据给定的key从HashMap中查询是否存在，如果不存在直接返回null；
     *  如果存在那就返回key所映射的Node的值，并且把结点移动到双链表的头部。
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
        return (V) node.val;
    }

    /**
     * 向缓存中存放数据
     *
     * @param key   缓存的key
     * @param value 缓存的value
     */
    public void put(K key, V value) {
        Node node = cacheMap.get(key);
        //如果新元素
        if (node == null) {
            //如果超过元素容纳量
            if (cacheMap.size() >= capacity) {
                //移除最后一个节点
                cacheMap.remove(tail.key);
                removeLast();
            }
            //创建新节点
            node = new Node(key, value);
        }
        //已经存在的元素覆盖旧值
        node.val = value;
        //把元素移动到首部
        moveToHead(node);
        cacheMap.put(key, node);
    }



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

    /**
     * 移除链表的尾结点
     *
     * @return
     */
    private void removeLast() {
        if (tail != null) {
            tail = tail.prev;
            if (tail == null) {
                head = null;
            } else {
                tail.next = null;
            }
        }
    }

    @Override
    public String toString() {
        Node h = head;
        StringBuilder sb = new StringBuilder();
        while (h != null) {
            sb.append(String.format("%s:%s ", h.key, h.val));
            h = h.next;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LRUCache<Integer, String> lru = new LRUCache<>(5);
        lru.put(1, "a");
        lru.put(2, "b");
        lru.put(3, "c");
        lru.put(4, "d");
        lru.put(5, "e");
        System.out.println("原始链表为：" + lru.toString());

        lru.get(4);
        System.out.println("获取key为4的元素之后的链表：" + lru.toString());

        lru.put(6, "f");
        System.out.println("新添加一个key为6之后的链表：" + lru.toString());

        lru.remove(3);
        System.out.println("移除key=3的之后的链表" + lru.toString());
    }

}
