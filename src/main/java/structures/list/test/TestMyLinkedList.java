package structures.list.test;

import structures.list.MyLinkedList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2019/4/20.
 */
public class TestMyLinkedList {
    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(2, 6);
        Iterator<Integer> iterator = list.iterator();
        ArrayList list1 = new ArrayList();
        while (iterator.hasNext()) {
            Integer data = iterator.next();
            if(data == 4){
                list.remove(3);
            }
        }
    }
}
