package linklist;

/**
 * 将两个升序链表合并为一个新的 升序 链表并返回。
 * 新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/07/13 17:55
 */
public class 合并有序链表 {

    /**
     * 模拟归并排序即可
     *
     * @param l1 有序链表1
     * @param l2 有序链表2
     * @return
     */
    public ListNode mergeLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode head = null, tail = null;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                if (head == null) {
                    head = l1;
                    tail = head;
                } else {
                    tail.next = l1;
                    tail = tail.next;
                }
                l1 = l1.next;
            } else {
                if (head == null) {
                    head = l2;
                    tail = head;
                } else {
                    tail.next = l2;
                    tail = tail.next;
                }
                l2 = l2.next;
            }
        }

        if (l1 == null) {
            tail.next = l2;
        }
        if (l2 == null) {
            tail.next = l1;
        }
        return head;
    }

}
