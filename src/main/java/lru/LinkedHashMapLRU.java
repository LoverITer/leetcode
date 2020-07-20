package lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
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
        Map<String, String> lru = new LinkedHashMapLRU<>(10);

    }
}
