package algorithm;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * 输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。
 * User: Administrator
 * Date: 2019-07-01
 * Time: 13:44
 */
public class Question3 {
    public ArrayList<Integer> printListFromTailToHead1(ListNode listNode) {
        //新建一个集合用于存放链表中的数据
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        //建立一个栈
        Stack<Integer> temp = new Stack<Integer>();
        ListNode node = listNode;
        while (node != null) {
            temp.push(node.val);//压栈
            node = node.next;
        }
        while (!temp.empty())
            arrayList.add(temp.pop());//弹栈
        return arrayList;
    }

    public ArrayList<Integer> printListFromTailToHead2(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        ListNode pNode = listNode;
        if (pNode != null) {
            if (pNode.next != null) {
                list = printListFromTailToHead2(pNode.next);
            }
            list.add(pNode.val);
        }
        return list;
    }

    ArrayList<Integer> arrayList = new ArrayList<Integer>();

    public ArrayList<Integer> printListFromTailToHead3(ListNode listNode) {
        if (listNode != null) {
            this.printListFromTailToHead3(listNode.next);
            //到最后一个节点的时候开始存储数据到list中，从尾到头储存
            arrayList.add(listNode.val);
        }
        return arrayList;
    }

    @Test
    public void testDigui() {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        final ArrayList<Integer> integers = printListFromTailToHead1(listNode);
        System.out.println(integers);
    }
}

class ListNode {
    int val;
    ListNode next = null;

    public ListNode(int val) {
        this.val = val;
    }
}
