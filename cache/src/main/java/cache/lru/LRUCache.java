package cache.lru;

import cache.Cache;

import java.util.HashMap;

/**
 * {@code LRU：Least Recently Used}，在操作系统的内存管理中，有一类很重要的算法就是内存页面置换算法（包括FIFO，LRU,LFU等几种常见页面置换算法）
 * LRU算法的设计原则是：如果一个数据在最近一段时间没有被访问到，那么在将来它被访问的可能性也很小。
 * 也就是说，当限定的空间已存满数据时，应当把最久没有被访问到的数据淘汰。
 *
 * 基本实现思路：维护一个双向链表用于数据存储和结点的快速增删，并且再维护一个Map用于记录缓存
 * 的key和具体在链表中结点的映射关系，这样做是为了提高缓存的查询效率到O(1)。
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/07/07 15:51
 */
public class LRUCache<K, V> implements Cache<K,V> {


    /***缓存的容量*/
    private int capacity;
    /***存储K和Node节点的映射 Node中会存放K-V*/
    private HashMap<K, DequeList.Node> map;
    /***双向链表*/
    private DequeList cache;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity);
        cache = new DequeList();
    }


    /**
     * 获取算法
     *
     * @param key
     * @return
     */
    @Override
    public V get(K key) {
        if (key != null && map.containsKey(key)) {
            // 将该数据提升为最近使用的
            makeRecent(key);
            return (V) map.get(key).val;
        }
        return null;
    }

    /**
     * 添加数据
     * @param key
     * @param val
     */
    @Override
    public void put(K key, V val) {
        if(map.containsKey(key)){
            // 删除旧的数据
            deleteKey(key);
            // 新插入的数据为最近使用的数据
            addRecently(key,val);
            return;
        }
        if(cache.size()>=capacity){
            //删除最近未使用的数据
            removeEldestNode();
        }
        addRecently(key,val);
    }

    /**
     * 将某个 key 提升为最近使用的
     */
    private void makeRecent(K key) {
        DequeList.Node<K,V> node = map.get(key);
        // 先从链表中删除这个节点
        cache.remove(node);
        // 重新插到队头
        cache.addHead(node);
    }

    /*** 添加最近使用的元素 */
    private void addRecently(K key, V val) {
        DequeList.Node node = new DequeList.Node(key, val);
        // 链表头部就是最近使用的元素
        cache.addHead(node);
        //在 map 中添加 key 的映射
        map.put(key, node);
    }

    /*** 删除某一个 key */
    private void deleteKey(K key) {
        DequeList.Node node = map.get(key);
        // 从链表中删除
        cache.remove(node);
        // 从 map 中删除
        map.remove(key);
    }

    /*** 删除最久未使用的元素 */
    private void removeEldestNode() {
        // 链表头部的第一个元素就是最久未使用的
        DequeList.Node deletedNode = cache.removeLast();
        if(deletedNode!=null) {
            map.remove(deletedNode.key);
        }
    }

    @Override
    public String toString() {
        return cache.toString();
    }

    public static void main(String[] args) {
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

        Cache<Integer, Integer> cache = new LRUCache<Integer, Integer>(2);
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