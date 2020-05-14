package top.easyblog.linklist;



/**
 * @author HuangXin
 * @since 2020/2/11 21:08
 */
public class LinkedListUtils {

    static class ListNode {
        private int val;
        private ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        /**
         * 打印链表结点
         */
        @Override
        public String toString() {
            ListNode p = this;
            StringBuilder sb = new StringBuilder();
            while (p != null) {
                sb.append(p.val).append(" ");
                p = p.next;
            }
            return sb.toString();
        }


        public ListNode addListNode(int val) {
            ListNode head = this;
            if (head != null) {
                ListNode p = head;
                //尾插法
                while (p.next != null) {
                    p = p.next;
                }
                ListNode node = new ListNode(val);
                p.next = node;
                node.next = null;
            }
            return head;
        }
    }

    /**
     * 对链表进行O（nlogn）排序
     *
     * @param head 链表头结点
     * @return 排序后的链表
     */
    public  ListNode sortLinkList(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode left = getMid(head);
        ListNode right = left.next;
        left.next = null;
        return mergeTwoLinkList(sortLinkList(head), sortLinkList(right));
    }

    /**
     * 将链表一分为2
     */
    private  ListNode getMid(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        ListNode sign = null;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            sign = slow;
            slow = sign.next;
        }
        return sign;
    }



    /**
     * 合并两个升序有序的链表
     * 将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     * <br/>
     * 示例：
     * <pre>
     *输入：1->2->4, 1->3->4
     *输出：1->1->2->3->4->4
     * <pre/>
     *
     * @param l1  链表1
     * @param l2  链表2
     * @return    新链表
     */
    public ListNode mergeTwoLinkList(ListNode l1, ListNode l2) {
        //判空，如果有一个链表为空直接返回
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode head = null, p = null;
        //使用归并排序的思想，比较两个链表，比较节点值的大小，然后再做操作
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                if (head == null) {
                    head = l1;
                    p = head;
                } else {
                    p.next = l1;
                    p = p.next;
                }
                l1 = l1.next;
            } else {
                if (head == null) {
                    head = l2;
                    p = head;
                } else {
                    p.next = l2;
                    p=p.next;
                }
                l2 = l2.next;
            }
        }
        //至少其中一个链表遍历完了，接下来直接在新链表尾部接上余下的链表就好了
        if (l1 == null) {
            p.next = l2;
        }
        if (l2 == null) {
            p.next = l1;
        }
        return head;
    }





}
