package linklist;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * 实现一个单链表
 * @author ：huangxin
 * @modified ：
 * @since ：2020/05/03 22:22
 */
public class MyLinkedList {

    private int val;
    private MyLinkedList next;
    private int size=0;

    /**
     * Initialize your data structure here.
     */
    public MyLinkedList() {
        this.val = -1;
        this.next = null;
    }

    public MyLinkedList(int val) {
        this.val = val;
        this.next = null;
    }

    /**
     * Get the value of the index-th node in the linked list.
     * If the index is invalid, return -1.
     */
    public int get(int index) {
        if (index < 0) {
            return -1;
        }
        //头结点
        MyLinkedList p = this;
        //头结点是哑结点
        int cnt = -1;
        while (p != null) {
            if (cnt == index) {
                return p.val;
            }
            cnt++;
            p = p.next;
        }
        return -1;
    }

    public MyLinkedList getKthNode(int k) {
        if (k < 0) {
            return null;
        }
        MyLinkedList p = this;
        int cnt = -1;
        while (p != null) {
            if (cnt == k) {
                return p;
            }
            cnt++;
            p = p.next;
        }
        return null;
    }

    /**
     * Add a node of value val before the first element of the linked list.
     * After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        MyLinkedList head = this, p;
        MyLinkedList node = new MyLinkedList(val);
        if (head != null) {
            size++;
            p = head.next;
            node.next = p;
            head.next = node;
        }
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        MyLinkedList head = this, p = head;
        MyLinkedList node = new MyLinkedList(val);
        while (p.next != null) {
            p = p.next;
        }
        size++;
        p.next = node;
    }

    /**
     * Add a node of value val before the index-th node in the linked list.
     * If index equals to the length of linked list, the node will be appended to
     * the end of linked list. If index is greater than the length,
     * the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        MyLinkedList p, node;
        if (index == 0) {
            p = this;
        } else {
            p = getKthNode(index);
        }
        if (p == null) {
            return;
        }
        size++;
        node = new MyLinkedList(val);
        node.next = p.next;
        p.next = node;
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        MyLinkedList p, pre;
        if (index == 0) {
            p = this;
        } else {
            p = getKthNode(index);
        }
        if (p == null) {
            return;
        }
        //待删除结点
        pre = p.next;
        p.next = pre.next;
        pre = null;
        size--;
    }

    /**
     * 打印链表结点
     */
    public String toString() {
        MyLinkedList p = this.next;
        StringBuilder sb = new StringBuilder();
        while (p != null) {
            sb.append(p.val).append("->");
            p = p.next;
        }
        String list = sb.toString();
        return list.substring(0, list.length() - 2);
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

        MyLinkedList linkedList = new MyLinkedList();
        Class<MyLinkedList> clazz = MyLinkedList.class;
        Method[] m = clazz.getMethods();
        HashMap<String, Method> methods = new HashMap<>();
        for (Method method : m) {
            methods.put(method.getName(), method);
        }
        String[] methodInvoke = new String[]{"addAtHead", "deleteAtIndex", "addAtHead", "addAtHead", "addAtHead", "addAtHead", "addAtHead", "addAtTail", "get", "deleteAtIndex", "deleteAtIndex"};
        int[] param = new int[]{ 2, 1, 2, 7, 3, 2, 5, 5, 5, 6, 4};
        for (int i = 0; i < methodInvoke.length; i++) {
            methods.get(methodInvoke[i]).invoke(linkedList, param[i]);
            System.out.println(linkedList.toString());
        }
    }
}
