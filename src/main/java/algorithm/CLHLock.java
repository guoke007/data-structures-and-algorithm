package algorithm;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Administrator
 * Date: 2019-05-30
 * Time: 14:22
 */
public class CLHLock implements Lock {

    private volatile AtomicReference<CLHNode> tail;

    private volatile ThreadLocal<CLHNode> threadLocal;

    public CLHLock() {
        this.tail = new AtomicReference<>();
        this.threadLocal = new ThreadLocal<>();
    }

    /**
     * 加锁
     */
    public void lock() {
        //获取当前线程的锁节点，如果为空，进行初始化
        CLHNode curNode = threadLocal.get();
        if (curNode == null) {
            System.out.println("==================");
            curNode = new CLHNode();
            threadLocal.set(curNode);
        }
        //通过同步方法获取尾节点，并将当前节点设置为尾节点
        //此时获取到的尾节点为当前节点的前驱节点
        CLHNode predNode = tail.getAndSet(curNode);
        //如果当前节点的前驱节点为空，说明当前节点为头节点，加锁成功
        //如果当前节点的前驱节点不为空，基于前驱节点的锁值（locked == true）进行自旋
        //直到前驱节点释放锁，将locked修改为false
        if (predNode != null) {
            while (predNode.isLocked()) {

            }
        }
    }

    /**
     * 解锁
     */
    public void unlock() {
        //获取当前线程的锁节点
        CLHNode curNode = threadLocal.get();
        threadLocal.remove();
        //如果节点为空或者锁值（locked = false）则无需解锁
        if (curNode == null || curNode.isLocked() == false)
            return;
        //使用同步方法为尾节点赋空值，
        // 赋值不成功，表示当前节点不是尾节点，需要将locked == false 保证解锁该节点
        // 如果当前节点为尾节点，则无需设置该节点的锁值，因为无后续节点
        if (!tail.compareAndSet(curNode, null))
            curNode.setLocked(false);
    }

    public void lockInterruptibly() throws InterruptedException {

    }

    public boolean tryLock() {
        return false;
    }

    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }
    public Condition newCondition() {
        return null;
    }

    class CLHNode {
        private volatile boolean locked = true;

         boolean isLocked() {
            return locked;
        }

         void setLocked(boolean locked) {
            this.locked = locked;
        }
    }
}
