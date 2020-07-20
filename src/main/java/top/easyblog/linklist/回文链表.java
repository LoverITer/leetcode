package top.easyblog.linklist;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/07/19 22:34
 */
public class 回文链表 {

    /**
     * 判断一个链表是否为回文链表。
     * <p>将链表从中间一份为二，然后逆转后半截链表，之后同时遍历两根链表，如果发现值不相同
     * 直接返回false；当遍历结束后如果没有发现不一致的就返回true</p>
     *
     * @param head 链表头结点
     * @return {@code true | false},是回文链表返回true,不是回文链表返回false
     */
    public static boolean isPalindrome(ListNode head) {
        if (head == null) {
            return false;
        }
        //找到链表的中间节点
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode tail = 反转链表.reverseLinkedList(slow);
        //从中间断开链表
        slow.next = null;
        while (head != null && tail != null) {
            if (head.val != tail.val) {
                return false;
            }
            head = head.next;
            tail = tail.next;
        }
        return true;
    }


    public static void main(String[] args) {
        ListNode list = ListNode.newLinkedList(new int[]{1, 2, 1});
        System.out.println(isPalindrome(list));
    }

}
