package linklist;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 假设链表中每一个节点的值都在 0 - 9 之间，那么链表整体就可以代表一个整数。
 * 给定两个这种链表，请生成代表两个整数相加值的结果链表。
 * 例如：链表 1 为 9->3->7，链表 2 为 6->3，最后生成新的结果链表为 1->0->0->0。
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/09/08 08:23
 */
public class 两个链表求和 {

    /**
     * @param head1 ListNode类
     * @param head2 ListNode类
     * @return ListNode类
     */
    public ListNode addInList(ListNode head1, ListNode head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }
        //使用两个桟保存链表结点
        Deque<Integer> s1 = new ArrayDeque<>();
        Deque<Integer> s2 = new ArrayDeque<>();
        while (head1 != null) {
            s1.push(head1.val);
            head1 = head1.next;
        }
        while (head2 != null) {
            s2.push(head2.val);
            head2 = head2.next;
        }

        ListNode head = new ListNode(0);
        int carry = 0, left = 0;

        while (!s1.isEmpty() && !s2.isEmpty()) {
            int value = s1.pop() + s2.pop()+ carry;
            carry = value / 10;
            left = value % 10;  //余数
            createNode(head, left);
        }
        //处理超长的链表
        while(!s1.isEmpty()){
            int value=s1.pop()+carry;
            carry=value/10;
            left=value%10;
            createNode(head,left);
        }
        while(!s2.isEmpty()){
            int value=s2.pop()+carry;
            carry=value/10;
            left=value%10;
            createNode(head,left);
        }
        if(carry!=0){
            createNode(head,carry);
        }
        return head.next;
    }

    private void createNode(ListNode head, int left) {
        //头插法保证运算结果
        ListNode p = new ListNode(0);
        p.val = left;
        p.next = head.next;
        head.next = p;
    }


    public static void main(String[] args) {
        ListNode l1 = ListNode.newLinkedList(new int[]{9, 3, 7,8});
        ListNode l2 = ListNode.newLinkedList(new int[]{6,3});
        System.out.println(new 两个链表求和().addInList(l1,l2));
    }
}
