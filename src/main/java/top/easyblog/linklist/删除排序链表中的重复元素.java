package top.easyblog.linklist;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/06/14 23:22
 */
public class 删除排序链表中的重复元素 {

    /**
     * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
     *
     * @param head
     * @return
     */
    public static ListNode deleteDuplicates(ListNode head) {
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

    public static void main(String[] args) {
        ListNode head = ListNode.newLinkedList(new int[]{1,1,1,3,4,5,7,7,8,9});
        System.out.println("原始链表："+head.toString());
        head=deleteDuplicates(head);
        System.out.println("去重之后链表："+head.toString());
    }
}
