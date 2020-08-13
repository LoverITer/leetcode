package linklist;

/**
 * 反转一个单链表。
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/07/13 10:41
 */
public class 反转链表 {


    /**
     * 迭代法逆转整个链表
     *
     * @param head 链表头结点
     * @return   ListNode
     */
    public static ListNode reverseLinkedList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = null;
        while (head != null) {
            //下一个节点
            ListNode nextNode = head.next;
            //当前结点指向上一个节点
            head.next = prev;
            //移动指针，准备下一次迭代
            prev = head;
            head = nextNode;
        }
        return prev;
    }


    /**
     * 递归法逆转整个链表
     * 递归到链表尾部，然后从尾部开始翻转
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        //从链表尾部开始翻转
        ListNode node = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }


    /**
     * 迭代法逆转区间位置 m 到 n 的链表
     * <p>
     * Tips：1 ≤ m ≤ n ≤ 链表长度。
     */
    public static ListNode reverseListBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;
        for (int i = 1; i < m; i++) {
            prev = prev.next;
        }
        head = prev.next;
        for (int i = m; i < n; i++) {
            ListNode nextNode = head.next;
            head.next = nextNode.next;
            nextNode.next = prev.next;
            prev.next = nextNode;
        }
        return head;
    }

}
