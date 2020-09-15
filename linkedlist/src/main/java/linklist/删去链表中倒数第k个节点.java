package linklist;

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
        ListNode faster=head,slow=head,pre=null;
        while(--k>0&&faster.next!=null){
            faster=faster.next;
        }
        //考虑删除头结点的情况
        if(k==0&&faster.next==null){
            //删除头结点
            head=head.next;
        }
        //考虑没有K个结点的情况
        if(k!=0||faster.next==null){
            //没有k个结点
            return head;
        }
        //常规删除操作
        while(faster.next!=null){
            pre=slow;
            slow=slow.next;
            faster=faster.next;
        }
        pre.next=slow.next;
        return head;
    }


    public static void main(String[] args) {
        System.out.println(deleteKthNode(ListNode.newLinkedList(new int[]{2}), 1).toString());
    }

}
