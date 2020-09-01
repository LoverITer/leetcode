package cache.lfu;

import cache.Cache;

import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 * LFU（Least Frequently Used）最近最少使用算法。它是基于“如果一个数据在最近一段时间内使用次数很少，
 * 那么在将来一段时间内被使用的可能性也很小”的思路
 * 注意LFU和LRU算法的不同之处，LRU的淘汰规则是基于访问时间，而LFU是基于访问次数的。
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/09/01 08:19
 */
public class LFUCache<K, V> implements Cache<K, V> {

    /***缓存容量*/
    private int capacity;
    //key-value映射
    private HashMap<K, V> keyToVal;
    //key-freq映射
    private HashMap<K, Integer> keyToFreq;
    //freq-key映射
    private HashMap<Integer, LinkedHashSet<K>> freqToKeys;
    //key的最低频次
    private int minFreq;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        keyToVal = new HashMap<>(capacity);
        keyToFreq = new HashMap<>(capacity);
        freqToKeys = new HashMap<>(capacity);
    }

    @Override
    public V get(K key) {
        if (keyToVal.containsKey(key)) {
            V val = keyToVal.get(key);
            //更新key
            updateFreq(key);
            return val;
        }
        return null;
    }

    private void updateFreq(K key) {
        //更新kF表
        Integer freq = keyToFreq.get(key);
        keyToFreq.put(key, freq + 1);
        //更新Fk表
        freqToKeys.get(freq).remove(key);
        freqToKeys.putIfAbsent(freq + 1, new LinkedHashSet<>());
        freqToKeys.get(freq + 1).add(key);
        if (freqToKeys.get(key).isEmpty()) {
            freqToKeys.remove(key);
            if (freq == minFreq) {
                minFreq++;
            }
        }
    }

    @Override
    public void put(K key, V val) {
        if (keyToVal.containsKey(key)) {
            //更新value
            keyToVal.put(key, val);
            //更新freq
            updateFreq(key);
            return;
        }
        if (keyToVal.size() >= this.capacity) {
            removeMinFreqKey();
        }
        //添加新的元素
        keyToVal.put(key, val);
        keyToFreq.put(key, 1);
        freqToKeys.putIfAbsent(1, new LinkedHashSet<>());
        freqToKeys.get(1).add(key);
        //插入新值后，最小的频次肯定是1
        this.minFreq = 1;
    }

    /**
     * 核心实现：移除频次最低并且时间最老的key
     */
    private void removeMinFreqKey() {
        LinkedHashSet<K> keys = freqToKeys.get(minFreq);
        K expireKey = keys.iterator().next();
        //更新FK
        keys.remove(expireKey);
        //如果keys
        if (keys.isEmpty()) {
            freqToKeys.remove(minFreq);
        }
        //更新KF
        keyToFreq.remove(expireKey);
        //更新KV
        keyToVal.remove(expireKey);
    }


    @Override
    public String toString() {
        return "LFUCache{" +
                "capacity=" + capacity +
                ", keyToVal=" + keyToVal.toString() +
                ", keyToFreq=" + keyToFreq.toString() +
                ", freqToKeys=" + freqToKeys.toString() +
                ", minFreq=" + minFreq +
                '}';
    }

    public static void main(String[] args) {
        // 构造一个容量为 3 的 LFU 缓存
        LFUCache cache = new LFUCache(3);

        // 插入两对 (key, val)，对应的 freq 为 1
        cache.put(1, 10);
        cache.put(2, 20);
        cache.put(5, 50);

        System.out.println(cache);
        // 查询 key 为 1 对应的 val
        // 返回 10，同时键 1 对应的 freq 变为 2
        System.out.println(cache.get(1));
        System.out.println(cache);
        // 容量已满，淘汰 freq 最小的并且最老键 2
        // 插入键值对 (3, 30)，对应的 freq 为 1
        cache.put(3, 30);
        System.out.println(cache);
        // 键 2 已经被淘汰删除，返回 -1
        System.out.println(cache.get(2));
        System.out.println(cache);
    }

}
