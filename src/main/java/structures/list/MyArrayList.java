package structures.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Administrator on 2019/4/19.
 */
public class MyArrayList<E> implements Iterable<E> {
    //定义默认数组长度
    private static final int DEFAULT_CAPACITY = 10;
    //定义数组
    private E[] elementData;
    //定义结合长度
    private int useSize;

    //定义一个空参构造,使用集合默认长度
    public MyArrayList() {
        doClear();
    }

    //清空数组的方法
    public void clear() {
        doClear();
    }

    //获取集合使用长度的方法
    public int size() {
        return useSize;
    }

    //判断集合是否为空的方法
    public boolean isEmpty() {
        return useSize == 0;
    }

    //去除集合中多余的空间
    public void trimToSize() {
        ensureCapacity(size());
    }

    //通过索引获取元素的方法
    public E get(int index) {
        //对索引值进行校验
        rangeCheck(index);
        return elementData[index];
    }

    //替换集合中指定索引位置的元素，并返回老元素
    public E set(int index, E e) {
        //索引值校验
        rangeCheck(index);
        //取出老元素
        E old = elementData[index];
        //用新元素替换老元素
        elementData[index] = e;
        return old;
    }

    //向集合中追加元素
    public boolean add(E e) {
        add(size(), e);
        return true;
    }

    //重载一个方法，在集合中任意位置添加元素
    public void add(int index, E e) {
        //判断增加元素后集合的使用长度是否超出数组长度
        if (useSize + 1 > elementData.length)
            //按照一定规则进行扩容（比如扩容两倍）,加1是为了防止useSize=0的情况
            ensureCapacity((useSize * 2) + 1);
        //将添加位置之前的元素向前移动一个位置
        for (int i = useSize; i > index; i--) {
            //将数组元素向前移动一位
            elementData[i] = elementData[i - 1];
        }
        //将新元素插入到指定位置
        elementData[index] = e;
        //集合的长度加1
        useSize++;
    }

    //删除指定位置的元素
    public E remove(int index) {
        //将指定位置之后的元素前移一个位置
        for (int i = index; i < size() - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        //集合的长度减少1
        useSize--;
        return elementData[index];
    }

    //对索引值进行校验
    private void rangeCheck(int index) {
        if (index >= useSize || index < 0)
            throw new IndexOutOfBoundsException();
    }

    private void doClear() {
        this.useSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    //如果使用长度超过了数组长度，进行扩容
    private void ensureCapacity(int newCapacity) {
        if (useSize > newCapacity)
            return;
        //创建新的数组
        E[] newArr = (E[]) new Object[newCapacity];
        //将原来集合的元素放入到新数组中
        for (int i = 0; i < useSize; i++) {
            newArr[i] = elementData[i];
        }
        //用新数组替换老数组
        this.elementData = newArr;
    }

    private class MyArrayListIterator implements Iterator<E> {
        //定义一个当前索引位置
        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < size();
        }

        @Override
        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return elementData[current++];
        }

        public void remove() {
            MyArrayList.this.remove(--current);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new MyArrayListIterator();
    }
}
