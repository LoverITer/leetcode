package top.easyblog.linklist;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/06/14 23:22
 */
public class 删除排序链表中的重复元素 {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = head;
        while (p != null && p.next != null) {
            if (p.val == p.next.val) {
                p.next = p.next.next;
            } else {
                p = p.next;
            }
        }
        return head;
    }
}
