package structures.linked;

/**
 * 循环链表
 */
public class LoopNode {
    private int data;
    //下一个节点
    private LoopNode next = this;

    public LoopNode(int data) {
        this.data = data;
    }

    //获取节点中的数据
    public int getData() {
        return this.data;
    }

    //获取下一个节点
    public LoopNode next() {
        return this.next;
    }

    //插入一个节点做为当前节点的下一个节点
    public void after(LoopNode node) {
        //获取当前节点的下一个节点
        LoopNode next = this.next;
        //将新节点追加到当前节点后面
        this.next = node;
        //新节点的下一个节点指向当前节点的下一个节点
        node.next = next;
    }

    //删除下一个节点
    public void removeNext() {
        //获取当前节点的下下个节点
        LoopNode next = this.next.next;
        //将当前节点的下一个节点指向下下个节点
        this.next = next;
    }
}
