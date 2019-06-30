package structures.linked;

/**
 * 单链表
 */
public class SingleNode {
    private int data;

    private SingleNode next;

    public SingleNode(int data) {
        this.data = data;
    }

    //获取节点中的数据
    public int getData() {
        return this.data;
    }

    //获取当前节点的下一个节点
    public SingleNode getNext() {
        return this.next;
    }

    //判断当前节点是否为最后一个节点
    public boolean isLast() {
        return this.next == null;
    }

    //显示所有节点信息
    public void show() {
        SingleNode currentNode = this;
        while (true) {
            SingleNode next = currentNode.next;
            System.out.print(currentNode.getData() + " ");
            if (next == null) {
                System.out.println();
                break;
            }
            currentNode = next;
        }
    }

    //为节点追加节点
    public SingleNode append(SingleNode node) {
        //获取当前节点
        SingleNode currentNode = this;
        while (true) {//循环获取最后一个节点
            //获取当前节点的下一个节点
            SingleNode next = currentNode.next;
            //如果当前节点的下一个节点为空
            if (next == null) {
                break;
            }
            currentNode = next;
        }
        //将节点追加到当前节点下面
        currentNode.next = node;
        return this;
    }

    //插入一个节点做为当前节点的下一个节点
    public void after(SingleNode node) {
        //找到当前节点的下一个节点
        SingleNode next = this.next;
        // 新节点追加为当前节点的下一个节点
        this.next = node;
        //将当前节点的原来的下一个节点追加为新节点的下一个节点
        node.next = next;
    }

    //删除下一个节点
    public void removeNext() {
        if (this.next == null) {
            throw new IllegalArgumentException("当前节点已经是最后一个节点");
        }
        //获取当前节点的下下一个节点
        SingleNode nextNext = this.next.next;
        //将当前节点的下下个节点作为当前节点的下一个节点
        this.next = nextNext;
    }
}
