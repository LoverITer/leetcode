package cache.lru;

import javax.annotation.Nonnull;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/08/31 11:14
 */
public class DequeList<K, V>  {

    //链表头尾结点
    private Node<K, V>  head;
    private Node<K, V>  tail;
    //链表长度
    private int size;

     static class Node<K, V> {
        K key;
        V val;
        Node<K, V> next;
        Node<K, V> prev;

        Node(K key, V val) {
            this.key = key;
            this.val = val;
        }

    }

    public DequeList() {
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
        this.size = 0;
    }

    //添加到队头
    public void addHead(@Nonnull Node<K,V> node) {
        node.prev=head;
        node.next=head.next;
        head.next.prev=node;
        head.next=node;
        size++;
    }

    // 删除链表中的 node 节点（node 一定存在）
    // 由于是双链表且给的是目标 Node 节点，时间 O(1)
    public void remove(@Nonnull Node<K,V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        size--;
    }

    //移除链表尾部结点
    public Node removeLast() {
        if (head.next == tail) {
            return null;
        }
        Node last = tail.prev;
        remove(last);
        return last;
    }

    //返回链表长度
    public int size() {
        return this.size;
    }

   @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node p=head.next;
        while (p.next != null) {
            sb.append(p.key).append(":").append(p.val).append(" ");
            p=p.next;
        }
        return sb.toString();
    }
}
