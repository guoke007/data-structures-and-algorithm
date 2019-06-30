package structures.map;

/**
 * 定义一个接口，对外提供存取的方法
 *
 * @param <K>
 * @param <V>
 */
public interface MyMap<K, V> {
    public V put(K k, V v);

    public V get(K k);

    public int size();

    interface Entry<K, V> {
        public V getValue();

        public K getKey();
    }
}
