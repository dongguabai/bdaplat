package dgb.test;

/**
 * @author Dongguabai
 * @date 2018/9/10 16:08
 */
public class MyLinkedList<E> {

    //虚拟头部元素
    private Node<E> head;

    //长度
    private int size;

    public MyLinkedList() {
        head = new Node<E>(null, null);
        size = 0;
    }

    //返回链表中的元素个数
    public int getSize() {
        return size;
    }

    //链表是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 在链表头添加新的元素e
    public void addFirst(E e) {
        add(0, e);
    }

    // 在链表的index(从0开始)位置添加新的元素e
    // 在链表中不是一个常用的操作
    public void add(int index, E e) {
        rangeCheckForAdd(index);
        Node<E> prev = head;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        prev.next = new Node<>(e, prev.next);
        size++;
    }

    // 在链表末尾添加新的元素e
    public void addLast(E e) {
        add(size, e);
    }

    public E get(int index) {
        rangeCheckForAdd(index);
        Node<E> cur = head.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.element;
    }

    // 获得链表的第一个元素
    public E getFirst() {
        return get(0);
    }

    // 获得链表的最后一个元素
    public E getLast() {
        return get(size - 1);
    }

    // 修改链表的第index个位置的元素为e
    public void set(int index, E e) {
        rangeCheckForAdd(index);
        Node cur = head.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.element = e;
    }

    // 修改链表的第index个位置的元素为e
    //返回删除的元素
    public E remove(int index) {
        rangeCheckForAdd(index);
        Node<E> prev = head;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node<E> retNode = prev.next;
        prev.next = retNode.next;
        retNode.next = null;
        size--;
        return retNode.element;
    }

    //删掉第一个
    public E removeFirst(){
        return remove(0);
    }

    //删掉最后一个
    public E removeLast(){
        return remove(size-1);
    }

    // 查找链表中是否有元素e
    public boolean contains(E e) {
        Node cur = head.next;
        while (cur != null) {
            if (cur.element.equals(e)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("size：").append(size).append("\n");
        for (Node cur = head.next; cur != null; cur = cur.next) {
            res.append(cur + "；");
        }
        res.append("NULL");

        return res.toString();
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    //私有内部节点
    private class Node<E> {
        public E element;
        public Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }

        public Node(E element) {
            this(element, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            if (this.element==null){
                return null;
            }
            return this.element.toString();
        }
    }

}
