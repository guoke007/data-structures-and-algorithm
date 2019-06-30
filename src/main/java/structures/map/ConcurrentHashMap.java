package structures.map;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.Objects.hash;

public class ConcurrentHashMap<K, V> extends AbstractMap<K, V> implements ConcurrentMap<K, V>, Serializable {
    //segments的掩码值，用来确定选择具体的segment
    final int segmentMask;
    //偏移量,用来确定选择具体的segment
    final int segmentShift;
    //segment的散列表
    final Segment[] segments;
    //最大容量
    private static final int MAXIMUM_CAPACITY = 1 << 30;
    //默认容量
    private static final int DEFAULT_CAPACITY = 16;
    //并发级别
    private static final int DEFAULT_CONCURRENCY_LEVEL = 16;

    public ConcurrentHashMap(int segmentMask, int segmentShift, Segment[] segments) {
        this.segmentMask = segmentMask;
        this.segmentShift = segmentShift;
        this.segments = segments;
    }

    static final class Segment<K, V> extends ReentrantLock implements Serializable {
        //在一个segment范围内包含的HashEntry的个数
        //变量声明为volatile保证每次读取的是最新值
        transient volatile int count;
        //table被更新的次数
        transient int modCount;
        //当table中包含的HashEntry个数大于这个数时需要扩容
        transient int threshold;
        //table是有HashEntry对象组成的数组
        //如果散列时发生碰撞，碰撞的 HashEntry 对象就以链表的形式链接成一个链表
        //table数组的数组成员代表散列映射表的一个桶
        //每个table守护整个ConcurrentHashMap包含桶总数的一部分
        //如果并发级别为 16，table则守护ConcurrentHashMap包含的桶总数的 1/16
        transient HashEntry<K, V>[] table;
        //装载因子
        transient float loadFactor;

    }

    static final class HashEntry<K, V> {
        final K key;
        final int hash;
        volatile V value;
        volatile HashEntry<K, V> next;

        public HashEntry(K key, int hash, V value, HashEntry<K, V> next) {
            this.key = key;
            this.hash = hash;
            this.value = value;
            this.next = next;
        }
    }

    public V put(K key, V value) {
        if (value == null || value == null) {
            throw new NullPointerException();
        }
        int hash = hash(key.hashCode());//计算键值对应的散列码
        Segment<K,V> segment = segmentFor(hash);
        return value;
    }
     //通过key值找到对应的segment
    private Segment<K, V> segmentFor(int hash) {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    @Override
    public Object putIfAbsent(Object key, Object value) {
        return null;
    }

    @Override
    public boolean remove(Object key, Object value) {
        return false;
    }

    @Override
    public boolean replace(Object key, Object oldValue, Object newValue) {
        return false;
    }

    @Override
    public Object replace(Object key, Object value) {
        return null;
    }
}
