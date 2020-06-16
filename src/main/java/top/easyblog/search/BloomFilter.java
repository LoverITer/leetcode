package top.easyblog.search;

import java.util.BitSet;

/**
 * <h4>布隆过滤器-JAVA实现</h4>
 * 布隆过滤器的原理：
 * <ul>
 *     <li>1.需要K个hash函数对插入的关键字进行K次hash计算</li>
 *     <li>2.需要一个足够大的位数组来按照哈希结果把对应下标的值置为1</li>
 * </ul>
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/06/10 09:31
 */
public class BloomFilter {

    /**
     * 为数组大小
     */
    private static final int ARRAY_SIZE = 1 << 24;

    /**
     * 通过这个数组可以创建 6 个不同的哈希函数
     */
    private static final int[] SEEDS = new int[]{3, 13, 46, 71, 91, 134};

    /**
     * 位数组，只能存0或1
     */
    private BitSet bitSet = new BitSet(ARRAY_SIZE);

    /**
     * 存放包含 hash 函数的类的数组
     */
    private SimpleHash[] hashes = new SimpleHash[SEEDS.length];

    public BloomFilter() {
        for (int i = 0; i < SEEDS.length; i++) {
            hashes[i] = new SimpleHash(ARRAY_SIZE, SEEDS[i]);
        }
    }

    /**
     * 添加元素到布隆过滤器中
     */
    public void add(Object value) {
        //把关键字带入每个hash函数计算，并把对应下标位置的位数组值设置为1\true
        for (SimpleHash f : hashes) {
            bitSet.set(f.hash(value), true);
        }
    }

    /**
     * 判断元素在布隆过滤器中是否存在
     *
     * @param value 关键字
     * @return 关键字存在返回true, 否则返回false
     */
    public boolean contains(Object value) {
        boolean exist = true;
        for (SimpleHash f : hashes) {
            exist = exist && bitSet.get(f.hash(value));
        }
        return exist;
    }

    /**
     * 存放Hash函数
     */
    private static class SimpleHash {
        int capacity;
        int seed;

        public SimpleHash(int capacity, int seed) {
            this.capacity = capacity;
            this.seed = seed;
        }


        public int hash(Object val) {
            int h;
            return (val == null) ? 0 : Math.abs(seed * (capacity - 1) & ((h = val.hashCode()) ^ (h >>> 16)));
        }
    }
}
