package top.easyblog.linklist;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/07/15 20:00
 */
public class 环形链表 {

    /**
     * 基础版：给定一个链表，判断链表中是否有环。
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        /*快慢指针:slower每次走一步，faster每次走两步，如果链表有环，
         那么在某次遍历后两个指针会相遇*/
        ListNode slower = head, faster = head;
        while (faster != null && faster.next != null) {
            slower = slower.next;
            faster = faster.next.next;
            if (slower == faster) {
                //快慢指针相遇了就说明有环
                return true;
            }
        }
        //链表遍历结束没有发现环
        return false;
    }

    /**
     * 升级版：给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
     * @param head
     * @return
     */
    public ListNode detectCycleHead(ListNode head) {
        if(head==null){
            return null;
        }
        ListNode slower=head,faster=head;
        ListNode cycleHead=null;
        while(faster!=null&&faster.next!=null){
            slower=slower.next;
            faster=faster.next.next;
            //判断是否有环，如果没有环直接返回
            if(slower==faster){
                //有环，那就判断入环位置:做法是从head位置在开一个指针cycleHead，它和slower指针同步
                //每次向后移动一步，那么在下一次他们就会在环开始的地方相遇
                cycleHead=head;
                while(slower!=cycleHead){
                    slower=slower.next;
                    cycleHead=cycleHead.next;
                }
                break;
            }
        }
        return cycleHead;
    }

}
