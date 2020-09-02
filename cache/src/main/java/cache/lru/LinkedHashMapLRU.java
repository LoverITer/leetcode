package cache.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 基于jdk LinkedHashMap的LRU缓存实现
 * @author ：huangxin
 * @modified ：
 * @since ：2020/07/07 16:40
 */
public class LinkedHashMapLRU<K, V> extends LinkedHashMap<K, V> {

    private int capacity;

    public LinkedHashMapLRU(int initialCapacity) {
        //true表示按照访问次数排序
        super(initialCapacity, 0.75f, true);
        //设定最大容量
        capacity = initialCapacity;
    }

    /***
     * 实现LRU的关键方法，如果map里面的元素个数大于了缓存最大容量，则删除链表的顶端元素
     * @param eldest
     * @return
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return this.size() > capacity;
    }
}

class Test{
    public static void main(String[] args) {
        Map<String, String> lru = new LinkedHashMapLRU<>(4);
        lru.put("1","a");
        lru.put("2","b");
        lru.put("3","c");
        lru.put("4","d");
        lru.get("4");
        System.out.println(lru.toString());
        lru.put("5","e");
        lru.get("5");
        System.out.println(lru.toString());
        lru.get("3");
        System.out.println(lru.toString());
        lru.put("6","d");
        System.out.println(lru.toString());
    }
}
