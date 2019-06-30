package structures.tree.test;

import structures.tree.BinaryTree;
import structures.tree.Node;

public class TestBinaryTree {
    public static void main(String[] args) {
        //创建一个根节点
        Node root = new Node(1);
        //创建一棵空树
        BinaryTree tree = new BinaryTree();
        //给空树一个根节点
        tree.setRoot(root);
        //创建一个节点作为根节点的左节点
        Node leftNode = new Node(2);
        root.setLeftNode(leftNode);
        //创建一个节点作为根节点的右节点
        Node rightNode = new Node(3);
        root.setRightNode(rightNode);
        //为树的第二层节点设置子节点(左)
        leftNode.setLeftNode(new Node(4));
        leftNode.setRightNode(new Node(5));
        //为树的第二层节点设置子节点(右)
        rightNode.setLeftNode(new Node(6));
        rightNode.setRightNode(new Node(7));
        //前序遍历树
        tree.frontShow();
        System.out.println("====================");
        //中序遍历
        tree.middleShow();
        System.out.println("======================");
        //后序遍历
        tree.afterShow();
    }
}
