package linklist;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/07/12 11:18
 */
public class 删除排序链表中的重复元素II {


    /**
     * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现的数字。
     * Tips：删除所有重复的节点，也即只保留出现过一次的节点
     *
     * @param head
     * @return
     */
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        head = dummy;
        int rmVal;  //记录待删除的值
        while (head.next != null && head.next.next != null) {
            if (head.next.val == head.next.next.val) {
                rmVal = head.next.val;
                while (head.next != null && head.next.val == rmVal) {
                    head.next = head.next.next;
                }
            } else {
                head = head.next;
            }
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.newLinkedList(new int[]{1,1,1,3,4,5,7,7,8,9});
        System.out.println("原始链表："+head.toString());
        head=deleteDuplicates(head);
        System.out.println("去重之后链表："+head.toString());
    }
}
