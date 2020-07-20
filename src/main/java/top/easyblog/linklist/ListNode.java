package top.easyblog.linklist;

/**
 * 单链表节点类型——ListNode
 * <p>一个ListNode实例就表示一个单链表的节点，节点的值保存在int类型的{@code val}中，节点的后继引用
 * 保存在ListNode类型的{@code next}中</p>
 * @author ：huangxin
 * @modified ：
 * @since ：2020/06/04 09:03
 */
public class ListNode {

    public int val;
    public ListNode next;

    public ListNode() {
    }

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


    /**
     * 向当前链表添加一个新结点，提供两种插入方式：头部插入和尾部插入
     *
     * @param val          待插入的值
     * @param insertToTail 是否要使用尾插法，true表示是，false表示使用头插法
     * @return 链表头结点
     */
    public ListNode addListNode(int val, boolean insertToTail) {
        ListNode head = this;
        if (head != null) {
            if (insertToTail) {
                //尾插法
                ListNode p = head;
                while (p.next != null) {
                    p = p.next;
                }
                ListNode node = new ListNode(val);
                p.next = node;
                node.next = null;
            } else {
                //头插法
                ListNode node = new ListNode(val);
                node.next = head;
                head = node;
            }
        }
        return head;
    }


    /**
     * 默认使用尾插法
     *
     * @param val
     * @return
     */
    public ListNode addListNode(int val) {
        return addListNode(val, true);
    }


    /**
     * 将一个数组使用尾插的方式转化成链表
     *
     * @param values int类型的数组
     * @return 链表头结点
     */
    public static ListNode newLinkedList(int[] values) {
        ListNode head = null;
        if (values != null && values.length > 0) {
            head = new ListNode(values[0]);
            for (int i = 1; i < values.length; i++) {
                //头插的时候这里需要更新头结点的引用
                head = head.addListNode(values[i]);
            }
        }
        return head;
    }


}
