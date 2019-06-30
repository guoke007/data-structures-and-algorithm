package structures.linked.test;

import structures.linked.SingleNode;

public class TestLinked {
    public static void main(String[] args) {
        //创建节点
        SingleNode n1 = new SingleNode(1);
        SingleNode n2 = new SingleNode(2);
        SingleNode n3 = new SingleNode(3);
        //追加一个节点（到末尾）
        n1.append(n2).append(n3);
        n1.show();//1 2 3
        //追加到节点作为当前节点的下一个节点
        SingleNode n4 = new SingleNode(4);
        n1.after(n4);
        n1.show();//1 4 2 3
        System.out.println(n1.getNext().getData());//4
        n1.removeNext();
        n1.show();//1 2 3

    }
}
