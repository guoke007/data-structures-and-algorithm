package com.datastructure.array;

import java.util.Arrays;

/**
 * 创建一个可变长度的数组
 */
public class MyArray {
    private int[] elements;

    public MyArray() {
        this.elements = new int[0];
    }

    //获取数组长度的方法
    public int size() {
        return elements.length;
    }

    //往数组的末尾添加一个元素
    public void add(int element) {
        //创建一个新数组，在原有数组的基础上加一个长度
        int[] newArr = new int[elements.length + 1];
        //将旧数组中元素放入到新数组中
        for (int i = 0; i < elements.length; i++) {
            newArr[i] = elements[i];
        }
        //将元素放入到数组中最后一个位置
        newArr[elements.length] = element;
        this.elements = newArr;
    }

    //打印所有元素到控制台
    public void show() {
        System.out.println(Arrays.toString(elements));
    }

    //删除数组中的元素
    public void delete(int index) {
        //首先判断下标是否越界
        if (index < 0 || index > elements.length - 1) {
            throw new RuntimeException("指针越界异常");
        }
        //创建新数组，长度为原有数组长度-1
        int[] newArr = new int[elements.length - 1];
        //将数组元素复制到新数组
        for (int i = 0; i < newArr.length; i++) {
            //如果数组元素在待删除元素后面4  56789
            if (i >= index) {
                newArr[i] = elements[i + 1];
            } else {//如果数组元素在待删除元素前面
                newArr[i] = elements[i];
            }
        }
        //新数组替换旧数组
        elements = newArr;
    }

    // 插入一个元素到指定位置
    public void insert(int index, int element) {
        //创建新数组，长度为原有数组长度+1
        int[] newArr = new int[elements.length + 1];
        for (int i = 0; i < newArr.length; i++) {
            if (i < index) {//原数组插入点之前的位置
                newArr[i] = elements[i];
            } else if (i > index) {//插入点之后的位置
                newArr[i] = elements[i - 1];
            } else {//插入点
                newArr[i] = element;
            }
        }
        elements = newArr;
    }
    // 取出指定位置的元素
    public int get(int index) {
        //首先判断下标是否越界
        if (index < 0 || index > elements.length - 1) {
            throw new RuntimeException("指针越界异常");
        }
        return elements[index];
    }
    // 替换指定位置的元素
    public void set(int index, int element) {
        //首先判断下标是否越界
        if (index < 0 || index > elements.length - 1) {
            throw new RuntimeException("指针越界异常");
        }
        elements[index] = element;
    }

    //线性查找
    public int search(int target) {
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] == target) {
                return i;
            }
        }
        //没有找到对应元素
        return -1;
    }

    //二分法查找（数组元素有序）
    public int binarySearch(int target) {
        //定义开始位置
        int begin = 0;
        //定义结束位置
        int end = elements.length - 1;
        //确定中间位置
        int mid = (begin + end) / 2;
        while (true) {
            if (begin >= end) {//如果开始位置大于等于结束位置，表示没有这个元素
                return -1;
            }
            if (elements[mid] == target) {
                return mid;
            } else {
                if (target > elements[mid]) {//如果目标元素在中间位置之前
                    begin = begin + 1;//开始位置向前移动一位
                } else {
                    end = mid - 1;//中间位置向后移动一位
                }
                mid = (begin + end) / 2;//取出最新的中间位置
            }
        }
    }
}
