package top.easyblog.linklist;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/07/19 22:45
 */
public class 链表中间节点 {

    /**
     * 获得一个链表的中间节点
     *
     * @param head 头结点
     * @return 返回一个链表的中间节点
     */
    public static ListNode getMiddleNodeOfList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

}
