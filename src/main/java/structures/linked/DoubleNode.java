package structures.linked;

/**
 * 双向链表
 */
public class DoubleNode<E> {
    //上一个节点
    private DoubleNode pre;
    //下一个节点
    private DoubleNode next;
    //数据元素
    private E data;

    public DoubleNode(E data) {
        this.data = data;
    }

    //获取节点中的数据
    public E getData() {
        return this.data;
    }

    //获取下一个节点
    public DoubleNode next() {
        return this.next;
    }

    //获取上一个节点
    public DoubleNode pre() {
        return this.pre;
    }

    //插入一个节点做为当前节点的下一个节点
    public void after(DoubleNode node) {
        //获取当前节点的下一个节点
        DoubleNode next = this.next;
        //将新增节点的上一个节点指向当前节点
        node.pre = this;
        //将当前节点的下一个节点指向新增节点
        this.next = node;
        //将新增节点的下一个节点指向当前节点的下一个节点
        node.next = next;
        //将当前节点的下一个节点的上一个节点指向当前节点
        next.pre = node;
    }
}
