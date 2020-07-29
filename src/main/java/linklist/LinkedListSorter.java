package linklist;

/**
 * 常用排序算法的链表实现
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/06/04 09:10
 */
public class LinkedListSorter {


    /**
     * 插入排序的链表实现
     *
     * @param head 待排序链表的头结点
     * @return top.easyblog.ListNode
     */
    public static ListNode insertionSortList(ListNode head) {
        //哑结点，作为链表的临时头
        ListNode dummy = new ListNode(0), pre;
        dummy.next = head;
        while (head != null && head.next != null) {
            if (head.val <= head.next.val) {
                //相邻两个结点有序，往后移动head，扩大有序序列范围
                head = head.next;
                continue;
            }
            pre = dummy;
            while (pre.next.val < head.next.val) {
                pre = pre.next;
            }
            //要交换的结点,先保存下来,防止断链
            ListNode temp = head.next;
            head.next = temp.next;
            temp.next = pre.next;
            pre.next = temp;
        }
        return dummy.next;
    }


    /**
     * 对链表的O(nlogn)时间复杂度排序——归并排序实现
     * 归并排序是对链表排序最快的一种方式，原理示意如下：
     * <pre>
     * 举个简单的例子,有一个链表：[4,3,1,7,8,9,2,11,5,6]，使用归并排序：
     * step=1: (3->4)->(1->7)->(8->9)->(2->11)->(5->6)
     * step=2: (1->3->4->7)->(2->8->9->11)->(5->6)
     * step=4: (1->2->3->4->7->8->9->11)->(5->6)
     * step=8: (1->2->3->4->5->6->7->8->9->11)
     * </pre>
     * @param head
     * @return
     */
    public static ListNode mergerSortList(ListNode head) {
        if(head==null||head.next==null){
            return head;
        }
        //左链表（尾）
        ListNode left=getMiddleNodeOfList(head);
        //右链表（头）
        ListNode right=left.next;
        //断开链表
        left.next=null;
        return mergeTwoListASC(mergerSortList(head),mergerSortList(right));
    }

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
        /***
         * 初始化slow fast都为head的时，中间节点是slow
         * 初始化slow=head,fast=head.next时，中间节点是slow.next
         */
        ListNode slow = head,fast=head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 合并两个升序有序的链表
     * 将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     * <br/>
     * 示例：
     * <pre>
     * 输入：1->2->4, 1->3->4
     * 输出：1->1->2->3->4->4
     * <pre/>
     *
     * @param l1  链表1
     * @param l2  链表2
     * @return 新链表
     */
    public static ListNode mergeTwoListASC(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode head = null, p = null;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                if (head == null) {
                    head = l1;
                    p = head;
                } else {
                    p.next = l1;
                    p = l1;
                }
                l1 = l1.next;
            } else {
                if (head == null) {
                    head = l2;
                    p = head;
                } else {
                    p.next = l2;
                    p = l2;
                }
                l2 = l2.next;
            }
        }

        //可能出现其中较短的链表遍历完了，当较长的链表还没有遍历完，
        // 此时直接把较长的链表拼接到尾部即可
        if (l1 == null) {
            p.next = l2;
        }
        if (l2 == null) {
            p.next = l1;
        }
        return head;
    }


    /**
     * 对链表的O(nlogn)时间复杂度排序——快排实现
     *
     * @param head
     * @return
     */
    public static ListNode quickSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        quickSortList(head, null);
        return head;
    }

    private static void quickSortList(ListNode head, ListNode tail) {
        if (head == tail) {
            return;
        }
        ListNode mid = partition(head, tail);
        quickSortList(head, mid);
        quickSortList(mid.next, tail);
    }

    private static ListNode partition(ListNode head, ListNode tail) {
        ListNode curr = head, next = head.next;
        while (next != tail) {
            if (next.val < curr.val) {
                int tmp = curr.val;
                curr.val = next.val;
                next.val = tmp;
            }
            next = next.next;
        }
        return curr;
    }

    public static void main(String[] args) {
        ListNode listNode = ListNode.newLinkedList(new int[]{2, 56, 13, -6, 8, -1, 9, 45});
        System.out.println(quickSortList(listNode));
    }

}
