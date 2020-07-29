package linklist;

/**
 * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 * <p>
 * 你应当保留两个分区中每个节点的初始相对位置。
 * <pre>
 *
 * 示例:
 *
 * 输入: head = 1->4->3->2->5->2, x = 3
 * 输出: 1->2->2->4->3->5
 * </pre>
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/07/15 16:54
 */
public class 分隔链表 {

    /**
     * 维护两个链表l1和l2，l1中是小于x的结点，l2中大于等于x的结点，遍历链表之后得到分隔好的
     * l1和l2，然后合并两个链表即可。
     *
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode l1 = null, l2 = null;
        ListNode l1Tail = null, l2Tail = null;
        while (head != null) {
            //小于x的结点连接到l1链表下
            if (head.val < x) {
                if (l1 == null) {
                    l1 = head;
                    l1Tail = head;
                } else {
                    l1Tail.next = head;
                    l1Tail = head;
                }
            } else {
                //大于等于x的结点连接到l2链表下
                if (l2 == null) {
                    l2 = head;
                    l2Tail = head;
                } else {
                    l2Tail.next = head;
                    l2Tail = head;
                }
            }
            head = head.next;
        }

        if (l2Tail != null) {
            l2Tail.next = null;
        }
        if (l1 != null) {
            l1Tail.next = l2;
            return l1;
        }
        return l2;
    }

}
