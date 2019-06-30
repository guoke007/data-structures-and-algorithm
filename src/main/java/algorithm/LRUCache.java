package algorithm;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * Description: 最少使用淘汰算法
 * User: Administrator
 * Date: 2019-05-30
 * Time: 9:25
 */
public class LRUCache {
    //默认缓存上线数
    private static final int DEFAUTL_LIMIT = 10;
    private Node head;
    private Node end;
    private int limit = DEFAUTL_LIMIT;
    private HashMap<String, Node> hashMap;

    public LRUCache() {
        this(DEFAUTL_LIMIT);
    }

    public LRUCache(int capacity) {
        this.limit = capacity;
        hashMap = new HashMap<String, Node>();
    }

    public String get(String key) {
        Node node = hashMap.get(key);
        if (node == null)
            return null;
        refreshNode(node);
        return node.value;
    }

    public void put(String key, String value) {
        Node node = hashMap.get(key);
        if (node == null) {//如果没有节点
            if (hashMap.size() >= limit) {//如果超过上线
                String oldKey = removeNode(head);//移除头结点
                hashMap.remove(oldKey);
            }
            Node newNode = new Node(key, value);//构建新节点
            addNode(newNode);//追加节点到末尾
            hashMap.put(key, newNode);//将新增节点放入map中
        } else {//如果节点已经存在
            node.value = value;//更新节点value值
            refreshNode(node);
        }
    }

    public void remove(String key) {
        Node node = hashMap.get(key);
        removeNode(node);
        hashMap.remove(key);
    }

    /**
     * 移除节点
     *
     * @param node
     * @return
     */
    private String removeNode(Node node) {
        if (node == end)//如果当前节点是尾节点
            end = end.prev;
        else if (node == head)
            head = head.next;
        else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        return node.key;
    }

    /**
     * 尾部追加节点
     *
     * @param node
     */
    private void addNode(Node node) {
        if (end != null) {
            end.next = node;
            node.prev = end;
            node.next = null;
        }
        end = node;
        if (head == null) {
            head = node;
        }
    }

    private void refreshNode(Node node) {
        if (node == end) {//如果访问的是尾节点
            return;
        }
        removeNode(node);//移除节点
        addNode(node);//加入到尾节点
    }

    class Node {
        private Node prev;
        private Node next;
        private String key;
        private String value;

        public Node(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}
