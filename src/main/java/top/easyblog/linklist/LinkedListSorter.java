package top.easyblog.linklist;

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
     * 实现对链表的O（nlogn）排序算法——归并排序
     *
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
        ListNode slow = head,fast=head,sign=slow;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            sign = slow;
        }
        return sign;
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


    public static void main(String[] args) {
        /*ListNode l1 = ListNode.addAll(new int[]{5, 3, 6, 7, 8, 9});
        l1 = insertionSortList(l1);

        ListNode l2 = ListNode.addAll(new int[]{1, -6, 12, 4, 11, 9});
        l2 = insertionSortList(l2);

        ListNode listNode = mergeTwoListASC(l1, l2);
        System.out.println(listNode.toString());*/
        ListNode listNode = ListNode.addAll(new int[]{2, 56, 13, -6, 8, -1, 9, 45});
        System.out.println(mergerSortList(listNode));
    }

}
