package cache;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/09/01 08:14
 */
public interface Cache<K,V> {

    /**
     * 获得元素
     * @param key
     * @return
     */
    V get(K key);

    /**
     * 添加新元素
     * @param key
     * @param val
     */
    void put(K key,V val);

}
