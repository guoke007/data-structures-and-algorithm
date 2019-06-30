package com.datastructure.array;

public class MyStack {
    //栈的底层我们使用数组来存储数据
    private int[] elements;

    public MyStack() {
        this.elements = new int[0];
    }

    //压入元素
    public void push(int element) {
        //创建新数组，长度为原数组的+1
        int[] newArr = new int[elements.length + 1];
        //将原数组的元素放入到新数组中
        for (int i = 0; i < elements.length; i++) {
            newArr[i] = elements[i];
        }
        //将压入的元素放到数组的尾部
        newArr[elements.length] = element;
        //使用新数组替换旧数组
        this.elements = newArr;
    }

    //取出栈顶元素
    public int pop() {
        if (elements.length == 0) {
            throw new RuntimeException("当前栈中没有元素");
        }
        //取出栈顶元素
        int element = elements[elements.length - 1];
        //创建新数组
        int[] newArr = new int[elements.length - 1];
        //将原数组中其他元素复制到新数组中
        for (int i = 0; i < newArr.length; i++) {
            newArr[i] = elements[i];
        }
        //新数组替换老数组
        this.elements = newArr;
        //返回栈顶元素
        return element;
    }

    //查看栈顶元素
    public int peek() {
        if (elements.length == 0) {
            throw new RuntimeException("当前栈中没有元素");
        }
        return elements[elements.length - 1];
    }

    //判断栈是否为空
    public boolean isEmpty() {
        return elements.length == 0;
    }
}
