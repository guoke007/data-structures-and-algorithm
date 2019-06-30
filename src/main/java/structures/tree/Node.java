package structures.tree;

public class Node {
    //设置树的权
    private int value;
    //左节点
    private Node leftNode;
    //右节点
    private Node rightNode;

    public Node(int value) {
        this.value = value;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

    //前序遍历
    public void frontShow() {
        frontShow(this);
    }

    private void frontShow(Node node) {
        if (node == null) return;
        //找到当前节点
        System.out.println(node.value);
        node.frontShow(node.leftNode);
        node.frontShow(node.rightNode);
    }

    public void middleShow() {
        middleShow(this);
    }

    private void middleShow(Node node) {
        if (node == null) return;
        //找到当前节点的左节点
        node.middleShow(node.leftNode);
        //找到当前节点
        System.out.println(node.value);
        //当前节点的右节点
        node.middleShow(node.rightNode);
    }

    public void afterShow() {
        afterShow(this);
    }

    private void afterShow(Node node) {
        if (node == null) return;
        node.afterShow(node.leftNode);
        node.afterShow(node.rightNode);
        //找到当前节点
        System.out.println(node.value);
    }
}
