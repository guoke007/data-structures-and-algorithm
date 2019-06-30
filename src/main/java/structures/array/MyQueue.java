package com.datastructure.array;


public class MyQueue {
    //队列的底层我们使用数组来存储数据
    private int[] elements;

    public MyQueue() {
        this.elements = new int[0];
    }

    //入队
    public void add(int element) {
        //创建新数组，长度为原数组的+1
        int[] newArr = new int[elements.length + 1];
        //将原数组的元素放入到新数组中
        for (int i = 0; i < elements.length; i++) {
            newArr[i] = elements[i];
        }
        //将加入的元素放到数组的尾部
        newArr[elements.length] = element;
        //使用新数组替换旧数组
        this.elements = newArr;
    }

    //出队
    public int poll() {
        if (elements.length == 0) {
            throw new RuntimeException("当前队列中没有元素");
        }
        //创建新数组
        int[] newArr = new int[elements.length - 1];
        //将原数组中其他元素复制到新数组中
        for (int i = 1; i < elements.length; i++) {
            newArr[i - 1] = elements[i];
        }
        //取出原数组中第一个元素
        int element = elements[0];
        //用新数组替换老数组
        elements = newArr;
        //返回队列中的第一个元素
        return element;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return elements.length == 0;
    }
}
