package stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * <p>
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/07/24 23:48
 */
public class 最小桟 {

    public static void main(String[] args) {
        MinStackI minStackI = new MinStackI();
        MinStackII minStackII = new MinStackII();
    }

}

/**
 * 最小桟第一种实现：使用两个桟，一个桟正常保存数据，另一个桟只保存当前主桟中的最小数，出栈的时候判断
 * 如果主桟的值和辅助桟的值相等时就将辅助桟的值也主桟了，否者只将主桟栈顶元素出栈
 */
class MinStackI {

    /***主桟：正常保存数据*/
    private Deque<Integer> stack;
    /***辅助桟：只保存当前主桟中的最小数*/
    private Deque<Integer> minStack;

    public MinStackI() {
        stack = new ArrayDeque<>();
        minStack = new ArrayDeque<>();
    }

    public void push(int x) {
        stack.push(x);
        if (!minStack.isEmpty()) {
            int top = minStack.peek();
            //新元素小于等于栈顶元素时，则将新元素压入辅助桟
            if (x <= top) {
                minStack.push(x);
            }
        } else {
            minStack.push(x);
        }
    }

    public void pop() {
        int pop = stack.pop();

        int top = minStack.peek();
        //等于的时候再出栈
        if (pop == top) {
            minStack.pop();
        }

    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

}

/**
 * 最小桟的第二种实现方式：直接改写底层，实现一个链表（操作的时候按照桟的规则FILO），
 * 并且在每个链表结点保存一个当前最小值即可保证getMin操作也是0(1)的，实测效率高于MinStackI
 */
class MinStackII {
    class Node {
        int value;
        int min;
        Node next;

        Node(int x, int min) {
            this.value = x;
            this.min = min;
            next = null;
        }
    }

    //相当于栈顶指针，操作他就是在操作栈顶元素
    private Node head;

    //每次加入的节点放到头部
    public void push(int x) {
        if (null == head) {
            head = new Node(x, x);
        } else {
            //当前值和之前头结点的最小值较小的做为当前的 min
            Node n = new Node(x, Math.min(x, head.min));
            n.next = head;
            head = n;
        }
    }

    public void pop() {
        if (head != null)
            head = head.next;
    }

    public int top() {
        if (head != null)
            return head.value;
        return -1;
    }

    public int getMin() {
        if (null != head)
            return head.min;
        return -1;
    }
}



