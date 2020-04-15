package top.easyblog.linklist;



/**
 * @author HuangXin
 * @since 2020/2/11 21:08
 */
public class LinkListAlgorithm {

    /**
     * 对链表进行O（nlogn）排序
     *
     * @param head 链表头结点
     * @return 排序后的链表
     */
    public static ListNode sortLinkList(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode left = getMid(head);
        ListNode right = left.next;
        left.next = null;
        return merge(sortLinkList(head), sortLinkList(right));
    }

    /**
     * 将链表一分为2
     */
    private static ListNode getMid(ListNode head) {
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
     * 合并链表
     *
     * @param left
     * @param right
     * @return
     */
    private static ListNode merge(ListNode left, ListNode right) {
        //哑结点
        ListNode dummyNode = new ListNode(0);
        ListNode curr = dummyNode;
        while (left != null && right != null) {
            if (left.val <= right.val) {
                curr.next = left;
                curr = curr.next;
                left = left.next;
            } else {
                curr.next = right;
                curr = curr.next;
                right = right.next;
            }
        }

        //拼接剩余的结点
        if (left != null) {
            curr.next = left;
        }
        if (right != null) {
            curr.next = right;
        }
        return dummyNode.next;
    }


    static class ListNode {
        private int val;
        private ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        /**
         * 打印链表结点
         */
        public void printListNode() {
            ListNode p = this;
            while (p!= null) {
                System.out.print(p.val + " ");
                p = p.next;
            }
        }


        public ListNode addListNode(int val){
            ListNode head=this;
            if(head!=null){
                ListNode p=head;
                //尾插法
                while (p.next!=null){
                    p=p.next;
                }
                ListNode node = new ListNode(val);
                p.next=node;
                node.next=null;
            }
            return head;
        }
    }




}
