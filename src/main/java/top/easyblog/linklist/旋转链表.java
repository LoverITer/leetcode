package top.easyblog.linklist;

/**
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/07/13 18:10
 */
public class 旋转链表 {

    /**
     * 1. 遍历链表获得链表的长度len
     * 2. 通过取余计算出实际有效的逆转次数 k，比如链表长度为5，输入的逆转次数为99，
     * 那么实际有效的逆转次数就是99%5=4
     * 3. 从头遍历到len-k的位置就是逆转后链表的尾结点tail，链表从此断开
     * 4. 尾结点的下一个节点就是新的头结点，遍历到转链表的尾部连接原先前半截链表即可
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        //获得链表的长度len
        int len = 0;
        ListNode temp = head;
        while (temp != null) {
            len++;
            temp = temp.next;
        }
        k = k % len;  //得到实际有效的逆转次数
        if (k == 0) {
            //没有意义的逆转
            return head;
        }
        ListNode tail = head;
        //len-k位置是逆转之后的链表的尾部结点
        for (int i = 1; i < len - k; i++) {
            tail = tail.next;
        }
        //下一个结点就是头部节点
        ListNode newhead = tail.next;
        tail.next = null;
        temp = newhead;
        while (temp.next != null) {
            temp = temp.next;
        }
        //和前面的链表重新连上
        temp.next = head;
        return newhead;
    }

}
