package linklist;

import java.util.List;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/08/15 15:31
 */
public class 删去链表中倒数第k个节点 {


    public static ListNode deleteKthNode(ListNode head,int k){
        if(head==null){
            return null;
        }
        ListNode fast=head,pre=null,p=head;
        while (--k!=0&&fast.next!=null){
            fast=fast.next;
        }
        if(k==0&&fast.next==null){
            //删除头结点
            head=head.next;
        }
        if(k!=0||fast.next==null){
            //没有k个结点
            return head;
        }
        while (fast.next!=null){
            pre=p;
            p=p.next;
            fast=fast.next;
        }
        pre.next=p.next;
        return head;
    }


    public static void main(String[] args) {
        System.out.println(deleteKthNode(ListNode.newLinkedList(new int[]{4, 2, 6, 8, 1, 2, 9}), 7).toString());
    }

}
