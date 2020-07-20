package top.easyblog.linklist;

import java.util.HashMap;
import java.util.Random;

/**
 * 克隆带有随机指针的单链表
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/07/20 11:33
 */
public class 复制带随机指针的链表 {

    /**
     * 复制节点并连接到原来的链表中，之后再次遍历原链表处理处理random指针的指向，完事后在采用
     * “有丝分裂法”分离分离两个链表即可
     *
     * @param head
     * @return
     */
    public static Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node curr = head;
        //复制节点，将新节点紧跟在旧节点后:
        //1->2->3->4   COPY    1->1`->2->2`->3->3`->4->4`
        while (curr != null) {
            Node node = new Node(curr.val);
            node.next = curr.next;
            Node t = curr.next;
            curr.next = node;
            curr = t;
        }
        //处理新节点的random指针
        curr = head;
        while (curr != null) {
            if (curr.random != null) {
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next;
        }
        //分离链表
        curr = head;
        Node newhead = curr.next;
        while (curr.next != null) {
            //有丝分裂法分离两个链表
            Node t = curr.next;
            curr.next = curr.next.next;
            curr = t;
        }
        return newhead;
    }


    static class Node {
        int val;
        Node next;
        Node random;

        Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }

        /**
         * 创建一个链表
         *
         * @param arr
         * @return
         */
        public static Node newLinkedList(int[] arr) {
            if (arr == null) {
                return null;
            }
            Node head = null, tail = null;
            HashMap<Integer, Node> locationMap = new HashMap<>();
            int counter = 0;
            //创建单链表
            for (int num : arr) {
                Node node = new Node(num);
                locationMap.put(counter, node);
                counter++;
                if (head == null) {
                    head = node;
                    tail = head;
                } else {
                    tail.next = node;
                    tail = tail.next;
                }
            }
            //设置随机指针
            Random random = new Random(10);
            Node curr = head;
            while (curr != null) {
                int ranNum = random.nextInt(counter);
                curr.random = locationMap.get(ranNum);
                curr = curr.next;
            }
            return head;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            Node curr = this;
            while (curr != null) {
                sb.append("[").append(curr.val).append(",").append(curr.random.val);
                if (curr.next != null) {
                    sb.append("],");
                } else {
                    sb.append("]");
                }
                curr = curr.next;
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        Node list = Node.newLinkedList(new int[]{1, 4, 5, 2, 6});
        System.out.println(list.toString());
        System.out.println(copyRandomList(list));
        System.out.println(list.toString());
    }
}
