package structures.map;

import structures.map.MyMap;

import java.util.ArrayList;
import java.util.List;

public class MyHashMap<K, V> implements MyMap<K, V> {
    //数组初始化大小
    private static int DEFAULT_INITIAL_CAPACITY = 10;
    //数组扩容时的默认比例因子
    private static float DEFAULT_LOAD_FACTOR = 0.75F;
    //数组大小
    private int defaultInitialCapacity;
    //扩容比例因子
    private float defaultLoadFactor;
    //map当中entry的数量
    private int userEntrySize;
    //数组
    private Entry<K, V>[] table = null;

    //初始化数组的大小
    public MyHashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int defaultInitialCapacity, float defaultLoadFactor) {
        if (defaultInitialCapacity < 0) {
            throw new IllegalArgumentException("illegal initial capacity:" + defaultInitialCapacity);
        }
        if (defaultLoadFactor <= 0 || Float.isNaN(defaultLoadFactor)) {
            throw new IllegalArgumentException("illegal default factor:" + defaultLoadFactor);
        }
        this.defaultInitialCapacity = defaultInitialCapacity;
        this.defaultLoadFactor = defaultLoadFactor;
        this.table = new Entry[this.defaultInitialCapacity];
    }

    class Entry<K, V> implements MyMap.Entry<K, V> {
        private K key;
        private V value;
        private Entry<K, V> next;

        public Entry() {
        }

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }
    }

    @Override
    public V put(K k, V v) {
        V oldValue = null;
        //首先判断是否需要扩容
        if (userEntrySize >= defaultInitialCapacity * defaultLoadFactor) {
            //对数组进行扩容(这里扩容为原来的两倍)
            reSize(2 * defaultInitialCapacity);
        }
        //得到hash值，计算出存入到数组中的位置
        int index = hash(k) & (defaultInitialCapacity - 1);
        //如果计算出的位置目前没有任何元素，将元素放入即可
        if (table[index] == null) {
            //代表此处的数组元素没有后驱元素
            table[index] = new Entry<K, V>(k, v, null);
            //已用数组长度加1
            userEntrySize++;
        } else {//代表目前此处已被某个元素占据
            Entry next = table[index];
            Entry e = next;
            while (e != null) {//遍历整个单链表
                //如果原来的链表中存在这个Key值，更新里面的数据
                if (e.getKey() == k || e.getKey().equals(k)) {
                    oldValue = (V) e.getValue();
                    e.value = v;
                    return oldValue;
                }
                e = e.next;//获取元素的下一个元素
            }
            //如果没有重复的Key值，将原位置的元素作为新增元素的下一个元素，新增元素占据这个位置
            table[index] = new Entry<>(k, v, next);
            //已用数组长度加1
            userEntrySize++;
        }
        return oldValue;
    }

    //对Key进行hash算法，返回值为int
    private int hash(K k) {
        int hashCode = k.hashCode();
        hashCode = (hashCode >>> 20) ^ (hashCode >>> 12);
        return hashCode ^ (hashCode >>> 7) ^ (hashCode >> 4);
    }

    //对数组进行扩容的方法
    private void reSize(int i) {
        //按照倍数新建新的数组
        Entry[] newTable = new Entry[i];
        //将新数组的长度赋值给数组默认长度
        this.defaultInitialCapacity = i;
        //新数组为空
        this.userEntrySize = 0;
        //将老数组中的元素重重新散列到新数组
        reHash(newTable);

    }

    private void reHash(Entry<K, V>[] newTable) {
        //新建集合，用于装载老数组里面的全部元素
        List<Entry<K, V>> entryList = new ArrayList<>();
        //遍历单链表，将里面的所有元素添加到集合中
        for (Entry entry : this.table) {
            if (entry != null) {
                do {
                    entryList.add(entry);
                    entry = entry.next;
                } while (entry != null);
            }
        }
        //覆盖旧的引用
        if (newTable.length > 0) {
            table = newTable;
        }
        //重新put到新的数组中
        for (Entry entry : entryList) {
            put((K) entry.getKey(), (V) entry.getValue());
        }
    }

    @Override
    public V get(K k) {
        int index = hash(k) & (defaultInitialCapacity - 1);
        Entry<K, V> entry = this.table[index];
        if (entry == null) {
            return null;
        } else {
            while (entry != null) {
                if (entry.getKey().equals(k) || entry.getKey() == k) {
                    return entry.getValue();
                }
                entry = entry.next;
            }
            return null;
        }
    }

    @Override
    public int size() {
        return this.userEntrySize;
    }
}
