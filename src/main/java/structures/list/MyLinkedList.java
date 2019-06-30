package structures.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Administrator on 2019/4/19.
 */
public class MyLinkedList<E> implements Iterable<E> {
    private int useSize;//链表长度
    private Node<E> beginMarker;//开始节点
    private Node<E> endMarker;//结束节点
    private int modCount;//记录集合长度被修改的次数

    //封装一个链表
    private static class Node<E> {
        public Node(E data, Node<E> prev, Node<E> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        private E data;
        private Node<E> prev;
        private Node<E> next;

    }

    public MyLinkedList() {
        doClear();
    }

    public void clear() {
        doClear();
    }

    private void doClear() {
        beginMarker = new Node<E>(null, null, null);//定义一个开始节点
        endMarker = new Node<E>(null, beginMarker, null);//定义一个结束节点
        beginMarker.next = endMarker;//将结束节点赋给开始节点的下一个节点
        useSize = 0;
        modCount++;
    }

    //定义获取链表长度的方法
    public int size() {
        return useSize;
    }

    //链表长度是否为空的方法
    public boolean isEmpty() {
        return useSize == 0;
    }

    //追加节点到最后位置
    public boolean add(E data) {
        add(size(), data);
        return true;
    }

    //在指定位置添加节点
    public void add(int index, E data) {
        //获取指定位置的节点
        Node<E> node = getNode(index, 0, size());
        //在指定位置节点之前追加节点
        addBefore(node, data);
    }

    //通过索引获取指定位置的节点
    public E get(int index) {
        return getNode(index, 0, size() - 1).data;
    }

    //修改指定位置节点的数据
    public E set(int index, E data) {
        //找到指定位置的节点
        Node<E> oldNode = getNode(index);
        oldNode.data = data;
        //将指定位置的元素
        return oldNode.data;
    }

    //删除指定位置的节点
    public E remove(int index) {
        //找到需要删除的节点
        Node<E> node = getNode(index);
        remove(node);
        return node.data;
    }

    //在指定节点之前添加节点
    private void addBefore(Node<E> node, E data) {
        //获取指定节点的上一个节点
        Node<E> pre = node.prev;
        //创建一个链表新节点
        Node<E> newNode = new Node<>(data, pre, node);
        //指定节点的下一个节点更改为新节点
        pre.next = newNode;
        //将指定节点的上一个节点设置为新节点
        node.prev = newNode;
        useSize++;
        modCount++;

    }

    //删除节点的方法
    private void remove(Node<E> node) {
        //找到删除节点的上一个节点
        node.prev.next = node.next;
        node.next.prev = node.prev;
        //修改计数器增加1
        useSize--;
        modCount++;
    }

    //遍历查询集合中指定位置节点的方法
    private Node<E> getNode(int index, int begin, int end) {
        Node<E> node;
        if (index < begin || index > end) {
            throw new IndexOutOfBoundsException();
        }
        if (index < size() / 2) {
            node = beginMarker.next;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = endMarker;
            for (int i = size(); i > index; i--) {
                node = node.prev;
            }
        }
        return node;
    }

    //查询集合中指定位置的节点的方法
    private Node<E> getNode(int index) {
        return getNode(index, 0, size() - 1);
    }

    //构造一个迭代器
    private class MyLinkedListIterator implements Iterator {
        //初始化为第一个节点
        private Node<E> current = beginMarker.next;
        //当前修改的此数
        private int expectedCount = modCount;
        //删除是否成功
        private boolean okToRemove = false;

        @Override
        public boolean hasNext() {

            return current != endMarker;
        }

        @Override
        public E next() {
            if (modCount != expectedCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E data = current.data;
            current = current.next;
            okToRemove = true;
            return data;
        }

        @Override
        public void remove() {
            if (modCount != expectedCount) {
                throw new ConcurrentModificationException();
            }
            if (!okToRemove) {
                throw new IllegalStateException();
            }
            MyLinkedList.this.remove(current.prev);
            expectedCount++;
            okToRemove = false;
        }
    }

    //发布私有类
    @Override
    public Iterator<E> iterator() {
        return new MyLinkedListIterator();
    }
}
